package edu.galileo.android.facebookrecipes.lib.di;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.facebookrecipes.lib.GlideImageLoader;
import edu.galileo.android.facebookrecipes.lib.GreenRobotEventBus;
import edu.galileo.android.facebookrecipes.lib.base.EventBus;
import edu.galileo.android.facebookrecipes.lib.base.ImageLoader;

/**
 * Created by praxis on 14/06/16.
 */
@Module
public class LibsModule {
    private Activity activity;

    public LibsModule() {
    }
    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return new GreenRobotEventBus();
    }

    @Provides
    @Singleton
    ImageLoader provideImageLoader(Activity activity) {
        GlideImageLoader imageLoader = new GlideImageLoader();
        if (activity != null) {
            imageLoader.setLoaderContext(activity);
        }
        return imageLoader;
    }

    @Provides
    @Singleton
    Activity provideActivity(){
        return this.activity;
    }
}
