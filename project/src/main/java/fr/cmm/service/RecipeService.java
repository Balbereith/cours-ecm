package fr.cmm.service;

import fr.cmm.domain.Recipe;
import fr.cmm.helper.PageQuery;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.springframework.stereotype.Service;
import sun.jvm.hotspot.debugger.Page;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

@Service
public class RecipeService {
    @Inject
    private MongoCollection recipeCollection;

    private class ReturningValues {

        public final String value;
        public final Object[] index;

        public ReturningValues(String value, Object[] index) {
            this.value = value;
            this.index = index;
        }
        private ReturningValues(PageQuery query){
            String mongoQuery = "{}";
            String[] params = {};

            if (query.getTag() != null && !"".equals(query.getTag())) {
                mongoQuery = "{tags: #}";
                params = new String[]{query.getTag()};
            }
            this.value = mongoQuery;
            this.index = params;
        }

        public String getValue() {
            return value;
        }
        public Object[] getIndex() {
            return index;
        }

    }

    public Iterable<Recipe> findByQuery(PageQuery query) {
        ReturningValues varMongoQuery = new ReturningValues(query);

        return recipeCollection
                .find(varMongoQuery.getValue(), (Object[]) varMongoQuery.getIndex())
                .skip(query.skip())
                .limit(query.getSize())
                .as(Recipe.class);
    }

    public long countByQuery(PageQuery query) {
        ReturningValues varMongoQuery = new ReturningValues(query);

        return recipeCollection.count(varMongoQuery.getValue(), (Object[]) varMongoQuery.getIndex());
    }

    public Iterator<Recipe> findRandom(int count) {
        return recipeCollection.find("{randomLocation: {$near: [#, 0]}}", Math.random()).limit(count).as(Recipe.class);
    }

    public Recipe findById(String id) {
        try {
            new ObjectId(id);
        } catch (IllegalArgumentException e) {
            return null;
        }

        return recipeCollection.findOne(new ObjectId(id)).as(Recipe.class);
    }

    public void save(Recipe recipe) {
        recipeCollection.save(recipe);
    }

    public List<String> findAllTags() {
        return recipeCollection.distinct("tags").as(String.class);
    }
}
