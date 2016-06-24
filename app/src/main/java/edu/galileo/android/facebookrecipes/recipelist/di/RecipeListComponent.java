package edu.galileo.android.facebookrecipes.recipelist.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.facebookrecipes.lib.base.ImageLoader;
import edu.galileo.android.facebookrecipes.lib.di.LibsModule;
import edu.galileo.android.facebookrecipes.recipelist.RecipeListPresenter;
import edu.galileo.android.facebookrecipes.recipelist.ui.adapters.RecipesAdapter;
import edu.galileo.android.facebookrecipes.recipemain.RecipeMainPresenter;

/**
 * Created by praxis on 22/06/16.
 */
@Singleton
@Component(modules = {RecipeListModule.class, LibsModule.class})
public interface RecipeListComponent {
    RecipesAdapter getAdapter();
    RecipeListPresenter getPresenter();
}
