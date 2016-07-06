package edu.galileo.android.facebookrecipes.recipelist;

import com.raizlabs.android.dbflow.sql.language.Select;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import edu.galileo.android.facebookrecipes.BaseTest;
import edu.galileo.android.facebookrecipes.BuildConfig;
import edu.galileo.android.facebookrecipes.FacebookRecipesApp;
import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.lib.base.EventBus;
import edu.galileo.android.facebookrecipes.recipelist.event.RecipeListEvent;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Created by Roberto Hdez. on 05/07/16.
 * <roberto.htamayo@gmail.com>
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RecipeListRepositoryImplTest extends BaseTest {
    @Mock
    private EventBus eventBus;

    private FacebookRecipesApp app;
    private RecipeListRepositoryImpl repository;
    private ArgumentCaptor<RecipeListEvent> recipeListEventArgumentCaptor;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        repository = new RecipeListRepositoryImpl(eventBus);
        app = (FacebookRecipesApp) RuntimeEnvironment.application;
        recipeListEventArgumentCaptor = ArgumentCaptor.forClass(RecipeListEvent.class);

        app.onCreate();
    }

    @After
    public void tearDown() throws Exception{
        app.onTerminate();
    }

    @Test
    public void testGetSavedRecipes_eventPosted() throws Exception {
        int recipeToStore = 5;
        Recipe currentRecipe;
        List<Recipe> testRecipeList = new ArrayList<>();
        for(int i=0; i<recipeToStore; i++){
            currentRecipe = new Recipe();
            currentRecipe.setRecipeId("id"+i);
            currentRecipe.save();
            testRecipeList.add(currentRecipe);
        }

        List<Recipe> recipesFromDB = new Select().from(Recipe.class).queryList();
        repository.getSavedRecipes();

        verify(eventBus).post(recipeListEventArgumentCaptor.capture());
        RecipeListEvent event = recipeListEventArgumentCaptor.getValue();

        assertEquals(RecipeListEvent.READ_EVENT, event.getType());
        assertEquals(recipesFromDB, event.getRecipeList());

        for(Recipe recipe:testRecipeList){
            recipe.delete();
        }
    }
}
