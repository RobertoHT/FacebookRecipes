package edu.galileo.android.facebookrecipes.recipelist;

/**
 * Created by praxis on 24/06/16.
 */
public class RecipeListInteractorImpl implements RecipeListInteractor{
    private RecipeListRepository repository;

    public RecipeListInteractorImpl(RecipeListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getSavedRecipes();
    }
}
