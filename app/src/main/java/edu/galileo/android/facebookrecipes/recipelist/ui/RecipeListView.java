package edu.galileo.android.facebookrecipes.recipelist.ui;

import java.util.List;

import edu.galileo.android.facebookrecipes.entities.Recipe;

/**
 * Created by praxis on 24/06/16.
 */
public interface RecipeListView {
    void setRecipes(List<Recipe> data);
    void recipeUpdate();
    void recipeDeleted(Recipe recipe);
}
