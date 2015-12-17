<%@ tag body-content="empty" pageEncoding="UTF-8" %>
<%@ attribute name="name" required="true" %>
<div class="container">
    <div class="header">
        <ul>
            <nav class="navbar navbar-default navbar-fixed-top">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>

                    </div>

                    <div class="collapse navbar-collapse" id="navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li class=${name=="index"? "active":""}><a class="navbar-brand" href="/">Cooking Miam Miam</a></li>
                            <li class=${name=="recettes"? "active":""}><a href="/recettes">Toutes les recettes</a></li>
                            <li class=${name=="recette du moment"? "active":""}><a href="/recette-du-moment">Recette du moment</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </ul>
    </div>
</div>