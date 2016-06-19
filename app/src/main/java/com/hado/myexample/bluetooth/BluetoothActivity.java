package com.hado.myexample.bluetooth;

import android.support.v7.widget.Toolbar;

import com.hado.myexample.R;
import com.hado.myexample.activity.BaseActivity;

import butterknife.Bind;

public class BluetoothActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    public Toolbar toolbar;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bluetooth Example");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_bluetooth;
    }
}
