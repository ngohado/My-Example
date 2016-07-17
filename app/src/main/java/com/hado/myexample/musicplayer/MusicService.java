package com.hado.myexample.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.hado.myexample.R;
import com.hado.myexample.notification.NotificationHelper;
import com.hado.myexample.util.DebugLog;

/**
 * Created by Ngo Hado on 16-Jul-16.
 */
public class MusicService extends Service {

    int position = 0;
    private MusicServiceBinder binder = new MusicServiceBinder(this);

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Bundle bundle = msg.getData();
                Messenger sMessenger = new Messenger(((Messenger) bundle.getParcelable("OK")).getBinder());
                try {
                    sMessenger.send(new Message());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private Messenger messenger = new Messenger(handler);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    @Override
    public void onCreate() {
        NotificationHelper.getInstance(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTitle("hello")
                .setContent("hello")
                .showForeground(this);
        new MyThread().start();
    }

    public class MyThread extends Thread {

        @Override
        public void run() {
            while (position < 50) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                position++;
                DebugLog.i(String.valueOf(position));
                stopSelf();
            }
        }
    }

    public int getPosition() {
        return position;
    }

}
