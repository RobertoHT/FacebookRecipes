package edu.galileo.android.facebookrecipes.recipemain;

import org.greenrobot.eventbus.Subscribe;

import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.lib.base.EventBus;
import edu.galileo.android.facebookrecipes.recipemain.events.RecipeMainEvent;
import edu.galileo.android.facebookrecipes.recipemain.ui.RecipeMainView;

/**
 * Created by praxis on 22/06/16.
 */
public class RecipeMainPresenterImpl implements RecipeMainPresenter {
    private EventBus eventBus;
    private RecipeMainView view;
    private SaveRecipeInteractor saveInteractor;
    private GetNextRecipeInteractor nextInteractor;

    public RecipeMainPresenterImpl(EventBus eventBus, RecipeMainView view, SaveRecipeInteractor saveInteractor, GetNextRecipeInteractor nextInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.saveInteractor = saveInteractor;
        this.nextInteractor = nextInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public void dismissRecipe() {
        if(this.view != null){
            view.dismissAnimation();
        }
        getNextRecipe();
    }

    @Override
    public void getNextRecipe() {
        if(this.view != null){
            view.hideUIElements();
            view.showProgress();
        }
        nextInteractor.execute();
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        if(this.view != null){
            view.saveAnimation();
            view.hideUIElements();
            view.showProgress();
        }
        saveInteractor.execute(recipe);
    }

    @Override
    @Subscribe
    public void enEventMainThread(RecipeMainEvent event) {
        if(this.view != null){
            String error = event.getError();
            if(error != null){
                view.hideProgress();
                view.onGetRecipeError(error);
            }else{
                if(event.getType() == RecipeMainEvent.NEXT_EVENT){
                    view.setRecipe(event.getRecipe());
                }
                else if(event.getType() == RecipeMainEvent.SAVE_EVENT){
                    view.onRecipeSaved();
                    nextInteractor.execute();
                }
            }
        }
    }

    @Override
    public void imageReady() {
        if(this.view != null){
            view.hideProgress();
            view.showUIElements();
        }
    }

    @Override
    public void imageError(String error) {
        if(this.view != null){
            view.onGetRecipeError(error);
        }
    }

    @Override
    public RecipeMainView getView() {
        return this.view;
    }
}
