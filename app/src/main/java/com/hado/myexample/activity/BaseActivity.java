package com.hado.myexample.activity;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * Created by Ngo Hado on 17-Jun-16.
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        onPreSetContentView(savedInstanceState);
        super.onCreate(savedInstanceState);
        onPostSetContentView(savedInstanceState);
        setContentView(setContentViewID());
        ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int setContentViewID();

    protected void onPreSetContentView(Bundle savedInstanceState) {

    }

    protected void onPostSetContentView(Bundle savedInstanceState) {

    }

}
