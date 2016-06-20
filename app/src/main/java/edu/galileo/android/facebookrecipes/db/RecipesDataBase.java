package edu.galileo.android.facebookrecipes.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by praxis on 20/06/16.
 */
@Database(name = RecipesDataBase.NAME, version = RecipesDataBase.VERSION)
public class RecipesDataBase {
    public static final int VERSION = 1;
    public static final String NAME = "Recipes";
}
