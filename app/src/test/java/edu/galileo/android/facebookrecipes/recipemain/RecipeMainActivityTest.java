package edu.galileo.android.facebookrecipes.recipemain;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.util.ActivityController;

import edu.galileo.android.facebookrecipes.BaseTest;
import edu.galileo.android.facebookrecipes.BuildConfig;
import edu.galileo.android.facebookrecipes.R;
import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.lib.base.ImageLoader;
import edu.galileo.android.facebookrecipes.login.ui.LoginActivity;
import edu.galileo.android.facebookrecipes.recipelist.ui.RecipeListActivity;
import edu.galileo.android.facebookrecipes.recipemain.ui.RecipeMainActivity;
import edu.galileo.android.facebookrecipes.recipemain.ui.RecipeMainView;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Roberto Hdez. on 04/07/16.
 * <roberto.htamayo@gmail.com>
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RecipeMainActivityTest extends BaseTest {
    @Mock
    private Recipe currentRecipe;
    @Mock
    private ImageLoader imageLoader;
    @Mock
    private RecipeMainPresenter presenter;

    private RecipeMainView view;
    private RecipeMainActivity activity;
    private ActivityController<RecipeMainActivity> controller;
    private ShadowActivity shadowActivity;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RecipeMainActivity recipeMainActivity = new RecipeMainActivity(){
            public ImageLoader getImageLoader(){
                return imageLoader;
            }
            public RecipeMainPresenter getPresenter(){
                return presenter;
            }
        };

        controller = ActivityController.of(Robolectric.getShadowsAdapter(), recipeMainActivity).create().visible();
        activity = controller.get();
        view = (RecipeMainView)activity;
        shadowActivity = shadowOf(activity);
    }

    @Test
    public void testOnActivityCreated_getNextRecipe() throws Exception {
        verify(presenter).onCreate();
        verify(presenter).getNextRecipe();
    }

    @Test
    public void testOnActivityDestroy_destroyPresenter() throws Exception {
        controller.destroy();
        verify(presenter).onDestroy();
    }

    @Test
    public void testLogoutMenuClicked_ShouldLaunchLoginActivity() throws Exception {
        shadowActivity.clickMenuItem(R.id.action_logout);
        Intent intent = shadowActivity.peekNextStartedActivity();
        assertEquals(new ComponentName(activity, LoginActivity.class), intent.getComponent());
    }

    @Test
    public void testListMenuClicked_ShouldLaunchRecipeListActivity() throws Exception {
        shadowActivity.clickMenuItem(R.id.action_list);
        Intent intent = shadowActivity.peekNextStartedActivity();
        assertEquals(new ComponentName(activity, RecipeListActivity.class), intent.getComponent());
    }

    @Test
    public void testShowProgress_progressBarShouldBeVisible() throws Exception {
        view.showProgress();

        ProgressBar progressbar = (ProgressBar) activity.findViewById(R.id.progress_bar);
        assertEquals(View.VISIBLE, progressbar.getVisibility());
    }

    @Test
    public void testHideProgress_progressBarShouldBeGone() throws Exception {
        view.hideProgress();

        ProgressBar progressbar = (ProgressBar) activity.findViewById(R.id.progress_bar);
        assertEquals(View.GONE, progressbar.getVisibility());
    }

    @Test
    public void testShowUIElements_buttonsBarShouldBeVisible() throws Exception {
        view.showUIElements();

        ImageButton imgKeep = (ImageButton) activity.findViewById(R.id.imgKeep);
        ImageButton imgDismiss = (ImageButton) activity.findViewById(R.id.imgDismiss);

        assertEquals(View.VISIBLE, imgKeep.getVisibility());
        assertEquals(View.VISIBLE, imgDismiss.getVisibility());
    }

    @Test
    public void testHideUIElements_buttonsBarShouldBeGone() throws Exception {
        view.hideUIElements();

        ImageButton imgKeep = (ImageButton) activity.findViewById(R.id.imgKeep);
        ImageButton imgDismiss = (ImageButton) activity.findViewById(R.id.imgDismiss);

        assertEquals(View.GONE, imgKeep.getVisibility());
        assertEquals(View.GONE, imgDismiss.getVisibility());
    }

    @Test
    public void testSetRecipe_ImageLoaderShouldBeCalled() throws Exception {
        String url = "http://galileo.edu";
        when(currentRecipe.getImageURL()).thenReturn(url);

        view.setRecipe(currentRecipe);
        ImageView imgRecipe = (ImageView) activity.findViewById(R.id.imgRecipe);
        verify(imageLoader).load(imgRecipe, currentRecipe.getImageURL());
    }

    @Test
    public void testSaveAnimation_AnimationShouldBeStarted() throws Exception {
        view.saveAnimation();

        ImageView imgRecipe = (ImageView) activity.findViewById(R.id.imgRecipe);
        assertNotNull(imgRecipe.getAnimation());
        assertTrue(imgRecipe.getAnimation().hasStarted());
    }

    @Test
    public void testDismissAnimation_AnimationShouldBeStarted() throws Exception {
        view.dismissAnimation();

        ImageView imgRecipe = (ImageView) activity.findViewById(R.id.imgRecipe);
        assertNotNull(imgRecipe.getAnimation());
        assertTrue(imgRecipe.getAnimation().hasStarted());
    }
}
