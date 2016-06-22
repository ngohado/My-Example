package com.hado.myexample.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import com.hado.myexample.R;
import com.hado.myexample.activity.BaseActivity;
import com.hado.myexample.activity.MainActivity;

import java.util.HashMap;
import java.util.Map;

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
        Map<Integer, String> actions = new HashMap<>();
        actions.put(R.id.img_number, "image");
        actions.put(R.id.tv_exercise_name, "name");
        actions.put(R.id.tv_exercise_description, "description");

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        NotificationHelper.getInstance(getApplicationContext()).setSmallIcon(R.mipmap.ic_launcher)
                .setTitle("Hello there")
                .setContent("My name is Hado")
                .setVibrate(false)
                .setSound(false, R.raw.notification_sound)
                .setLedLight(0xff00ff00)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.notification))
                .setCancelable(true)
                .setContentIntent(intent, PendingIntent.FLAG_UPDATE_CURRENT).show(1234);
    }

    @Override
    public void onClicked(Context context, Intent intent, Notification notification, String action) {
        notification.contentView.setImageViewResource(R.id.img_number, R.mipmap.bluetooth);
        NotificationHelper.getInstance(getApplicationContext()).updateNotification(notification, 1234);
    }
}
