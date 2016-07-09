package com.hado.myexample.pagertransformer;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.hado.myexample.R;

/**
 * Created by Ngo Hado on 08-Jul-16.
 */
public class EffectTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View page, float position) {
        if (position < -1) {
            page.setAlpha(1);
        } else if (position <= 1) {
             page.findViewById(R.id.img_background).setTranslationX(-position * page.getWidth()/2);
             page.findViewById(R.id.tv_days).setAlpha(1 - Math.abs(position));
             page.findViewById(R.id.tv_temperature).setAlpha(1 - Math.abs(position));
        } else {
            page.setAlpha(1);
        }

    }
}
