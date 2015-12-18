package fr.cmm.service;

import fr.cmm.domain.Recipe;
import fr.cmm.helper.PageQuery;
import org.jongo.MongoCollection;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.stream.StreamSupport;

import static fr.cmm.SpringProfiles.INTEG;
import static java.util.Arrays.asList;
import static java.util.stream.StreamSupport.stream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ImageServiceTestConfig.class)
@ActiveProfiles(INTEG)
public class RecipeServiceTest {
    @Inject
    private RecipeService recipeService;

    @Inject
    private MongoCollection recipeCollection;

    @Before
    @After
    public void clean() {
        recipeCollection.remove();
    }

    /*@Test
    public void save() {
        Recipe recipe = new Recipe();
        recipe.setTitle("test recipe");

        recipeService.save(recipe);

        assertEquals("test recipe", recipeCollection.findOne().as(Recipe.class).getTitle());
    }*/

    @Test
    void save() {
        recipeService.save(new Recipe(title: 'test recipe'))
        assert recipeCollection.findOne().as(Recipe).title == 'test recipe'
    }

    /*@Test
    public void findById() {
        Recipe recipe = new Recipe();
        recipe.setTitle("test recipe");

        recipeService.save(recipe);

        assertEquals("test recipe", recipeService.findById(recipe.getId()).getTitle());
    }*/

    @Test
    void findById(){
        recipeService.save(new Recipe (title : 'test recipe', id : '56728b44300416fb9c3fa05d'))
        assert recipeService.findById('56728b44300416fb9c3fa05d').title == 'test recipe'
    }

    /*@Test
    public void findByQuery() {
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());

        assertEquals(5, stream(recipeService.findByQuery(new PageQuery()).spliterator(), false).count());
    }*/

    @Test
    void findByQuery(){
        5.times{recipeService.save(new Recipe())}
        assert stream(recipeService.findByQuery(new PageQuery()).spliterator(), false).count() == 5
    }


    /*@Test
    public void findByQueryWithCustomPageSize() {
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());

        PageQuery pageQuery = new PageQuery();
        pageQuery.setSize(2);

        assertEquals(2, stream(recipeService.findByQuery(pageQuery).spliterator(), false).count());
    }*/

    @Test
    void findByQueryWithCustomPageSize(){
        5.times{recipeService.save(new Recipe())}
        stream(recipeService.findByQuery(new PageQuery('size' : 2)).spliterator(), false).count() == 2
    }

    @Test
    public void findByQueryWithTag() {
        recipeService.save(new Recipe().withTags("tag1"));
        recipeService.save(new Recipe().withTags("tag1"));
        recipeService.save(new Recipe().withTags("tag2"));
        recipeService.save(new Recipe().withTags("tag2"));
        recipeService.save(new Recipe().withTags("tag3"));

        PageQuery pageQuery = new PageQuery();
        pageQuery.setTag("tag1");

        assertEquals(2, stream(recipeService.findByQuery(pageQuery).spliterator(), false).count());
    }

    @Test
    public void findAllTags() {
        recipeService.save(new Recipe().withTags("tag1", "tag2"));
        recipeService.save(new Recipe().withTags("tag2", "tag3"));

        assertEquals(asList("tag1", "tag2", "tag3"), recipeService.findAllTags());
    }

    @Test
    public void findByIdWithInvalidId(){
        assertNull(recipeService.findById("123pasvalide456"));
    }

    @Test
    public void countByQuerryTag24(){
        recipeService.save(new Recipe().withTags("tag1", "tag2"));
        recipeService.save(new Recipe().withTags("tag2", "tag3"));
        recipeService.save(new Recipe().withTags("tag1", "tag2"));
        recipeService.save(new Recipe().withTags("tag2", "tag3"));

        PageQuery pageQuery = new PageQuery();
        pageQuery.setTag("tag1");

        assertEquals(2, recipeService.countByQuery(pageQuery));
    }

    @Test
    public void countByQuerryTag44(){
        PageQuery pageQuery = new PageQuery();
        pageQuery.setTag("tag2");

        recipeService.save(new Recipe().withTags("tag1", "tag2"));
        recipeService.save(new Recipe().withTags("tag2", "tag3"));
        recipeService.save(new Recipe().withTags("tag1", "tag2"));
        recipeService.save(new Recipe().withTags("tag2", "tag3"));

        assertEquals(4, recipeService.countByQuery(pageQuery));
    }
}