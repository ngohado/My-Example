package com.hado.myexample.pagertransformer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Ngo Hado on 08-Jul-16.
 */
public class ScreenAdapter extends FragmentStatePagerAdapter {

    List<PagerItem> items;

    public ScreenAdapter(FragmentManager fm, List<PagerItem> items) {
        super(fm);
        this.items = items;
    }


    @Override
    public Fragment getItem(int position) {
        return ClockScreenFragment.getInstance(items.get(position));
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
