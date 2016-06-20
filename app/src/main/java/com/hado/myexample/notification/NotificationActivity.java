package com.hado.myexample.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.hado.myexample.R;
import com.hado.myexample.activity.BaseActivity;
import com.hado.myexample.activity.MainActivity;
import com.hado.myexample.util.DebugLog;

import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;

public class NotificationActivity extends BaseActivity implements MyNotificationBuilder.OnClickComponentNotification {

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
        actions.put(R.id.img_number, "description");

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        MyNotificationBuilder.getInstance(getApplicationContext(), R.layout.item_exercise).setSmallIcon(R.mipmap.ic_launcher)
                .setOnClick(actions, this)
                .setCancelable(false)
                .setContentIntent(intent, PendingIntent.FLAG_UPDATE_CURRENT).show();
    }

    @Override
    public void onClicked(Context context, Intent intent, String action) {
        DebugLog.i(action);
    }
}
