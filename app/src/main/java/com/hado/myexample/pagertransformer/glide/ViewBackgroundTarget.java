package com.hado.myexample.pagertransformer.glide;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;


public abstract class ViewBackgroundTarget<Z> extends ViewTarget<View, Z> implements GlideAnimation.ViewAdapter {
    public ViewBackgroundTarget(View view) { super(view); }
    @Override public void onLoadCleared(Drawable placeholder) { setBackground(placeholder); }
    @Override public void onLoadStarted(Drawable placeholder) { setBackground(placeholder); }
    @Override public void onLoadFailed(Exception e, Drawable errorDrawable) { setBackground(errorDrawable); }
    @Override public void onResourceReady(Z resource, GlideAnimation<? super Z> glideAnimation) {
        if (glideAnimation == null || !glideAnimation.animate(resource, this)) {
            setResource(resource);
        }
    }
    @Override public void setDrawable(Drawable drawable) { setBackground(drawable); }
    @Override public Drawable getCurrentDrawable() { return view.getBackground(); }

    @SuppressWarnings("deprecation")
    protected void setBackground(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    protected abstract void setResource(Z resource);
}
