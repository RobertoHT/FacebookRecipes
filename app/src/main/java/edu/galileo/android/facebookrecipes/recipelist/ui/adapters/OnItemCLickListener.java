package edu.galileo.android.facebookrecipes.recipelist.ui.adapters;

import edu.galileo.android.facebookrecipes.entities.Recipe;

/**
 * Created by praxis on 24/06/16.
 */
public interface OnItemCLickListener {
    void onFavClick(Recipe recipe);
    void onItemCLick(Recipe recipe);
    void onDeleteClick(Recipe recipe);
}
