package com.hado.myexample.musicplayer;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;

import com.hado.myexample.R;
import com.hado.myexample.activity.BaseActivity;
import com.hado.myexample.util.DebugLog;

import butterknife.Bind;

public class MusicActivity extends BaseActivity implements ServiceConnection{

    @Bind(R.id.tabs)
    public TopTabsCustom topTabsCustom;

    private MusicService service;

    private Messenger messenger;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            DebugLog.d("what the fuck? 2");
        }
    };

    private Messenger rcvMessenger = new Messenger(handler);

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        topTabsCustom.setTabChangeListener(new TopTabsCustom.OnTabSelectedChange() {
            @Override
            public void onTabSelected(View viewBeforeSelected, View viewSelected, int positionBeforeSelected, int positionSelected) {
                if (positionSelected == 1) {
                    Intent intent = new Intent(MusicActivity.this, MusicService.class);
                    startService(intent);
                    bindService(intent, MusicActivity.this, BIND_AUTO_CREATE);
                }
            }
        });
    }


    @Override
    protected int setContentViewID() {
        return R.layout.activity_music;
    }

    @Override
    public void onServiceConnected(ComponentName name, final IBinder service) {
//        this.service = ((MusicServiceBinder) service).getService();
//        DebugLog.d(this.service.getPosition());
        messenger = new Messenger(service);
        Message msg = new Message();
        msg.what = 1;
        Bundle bundle = new Bundle();
        bundle.putParcelable("OK", rcvMessenger);
        msg.setData(bundle);
        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        DebugLog.d("onServiceDisconnected");
    }

    @Override
    protected void onDestroy() {
        unbindService(this);
        super.onDestroy();
    }
}
