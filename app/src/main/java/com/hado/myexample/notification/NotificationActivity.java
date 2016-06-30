package com.hado.myexample.notification;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;

import com.hado.myexample.R;
import com.hado.myexample.activity.BaseActivity;

import butterknife.OnClick;

public class NotificationActivity extends BaseActivity implements NotificationHelper.OnClickComponentNotification {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_notification;
    }

    @OnClick(R.id.btn_normal)
    public void buttonNormalClicked() {
//        NotificationHelper.getInstance(getApplicationContext()).setTitle("Hello there!")
//                .setContent("My name is Hado")
//                .setSmallIcon(R.draw)
    }

    @OnClick(R.id.btn_effect)
    public void buttonEffectClicked() {

    }

    @OnClick(R.id.btn_custom)
    public void buttonCustomViewClicked() {

    }

    @Override
    public void onClicked(Context context, Intent intent, Notification notification, String action) {
        notification.contentView.setImageViewResource(R.id.img_number, R.mipmap.bluetooth);
        NotificationHelper.getInstance(getApplicationContext()).updateNotification(notification, 1234);
    }
}
