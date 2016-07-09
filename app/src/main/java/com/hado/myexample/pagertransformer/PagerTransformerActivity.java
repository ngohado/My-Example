package com.hado.myexample.pagertransformer;

import android.support.v4.view.ViewPager;

import com.hado.myexample.R;
import com.hado.myexample.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class PagerTransformerActivity extends BaseActivity {

    @Bind(R.id.viewpager)
    public ViewPager viewPager;
    public ScreenAdapter adapter;

    public List<PagerItem> items = new ArrayList<>();

    @Override
    protected void initData() {
        items.add(new PagerItem("Monday", "36", R.mipmap.screen1));
        items.add(new PagerItem("Tuesday", "30", R.mipmap.screen2));
        items.add(new PagerItem("Wednesday", "27", R.mipmap.screen3));
        adapter = new ScreenAdapter(getSupportFragmentManager(), items);
        viewPager.setPageTransformer(true, new EffectTransformer());
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_pager_transformer;
    }


}
