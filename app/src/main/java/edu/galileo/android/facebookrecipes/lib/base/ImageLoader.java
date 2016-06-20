package edu.galileo.android.facebookrecipes.lib.base;

import android.widget.ImageView;

/**
 * Created by praxis on 14/06/16.
 */
public interface ImageLoader {
    void load(ImageView imageView, String URL);
    void setOnFinishedImageLoadingListener(Object listener);
}
