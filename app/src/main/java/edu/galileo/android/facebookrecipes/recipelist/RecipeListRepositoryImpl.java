package edu.galileo.android.facebookrecipes.recipelist;

import com.raizlabs.android.dbflow.list.FlowCursorList;

import java.util.Arrays;
import java.util.List;

import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.lib.base.EventBus;
import edu.galileo.android.facebookrecipes.recipelist.event.RecipeListEvent;

/**
 * Created by praxis on 24/06/16.
 */
public class RecipeListRepositoryImpl implements RecipeListRepository {
    private EventBus eventBus;

    public RecipeListRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getSavedRecipes() {
        FlowCursorList storedRecipes = new FlowCursorList<Recipe>(false, Recipe.class);
        post(RecipeListEvent.READ_EVENT, storedRecipes.getAll());
        storedRecipes.close();
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        recipe.update();
        post();
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        recipe.delete();
        post(RecipeListEvent.DELETE_EVENT, Arrays.asList(recipe));
    }

    private void post(int type, List<Recipe> recipeList){
        RecipeListEvent event = new RecipeListEvent();
        event.setType(type);
        event.setRecipeList(recipeList);
        eventBus.post(event);
    }

    private void post(){
        post(RecipeListEvent.UPDATE_EVENT, null);
    }
}