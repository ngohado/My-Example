package com.hado.myexample.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by Ngo Hado on 19-Jun-16.
 */
public class MyNotificationBuilder {
    private static MyNotificationBuilder myNotificationBuilder;
    private Context context;

    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private RemoteViews remoteViews;
    private OnClickComponentNotification listener;

    public MyNotificationBuilder(Context context) {
        this.context = context;
        builder = new NotificationCompat.Builder(context);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        remoteViews = null;
    }

    public MyNotificationBuilder(Context context, int idLayout) {
        this.context = context;
        builder = new NotificationCompat.Builder(context);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        remoteViews = new RemoteViews(context.getPackageName(), idLayout);
        builder.setContent(remoteViews);
    }

    /**
     * @param context require application context
     * @return
     */
    public static MyNotificationBuilder getInstance(Context context) {
        myNotificationBuilder = new MyNotificationBuilder(context);
        return myNotificationBuilder;
    }


    /**
     * @param context  require application context
     * @param idLayout is identifier of notification layout
     * @return
     */
    public static MyNotificationBuilder getInstance(Context context, int idLayout) {
        myNotificationBuilder = new MyNotificationBuilder(context, idLayout);
        return myNotificationBuilder;
    }

    public MyNotificationBuilder setSmallIcon(int icon) {
        builder.setSmallIcon(icon);
        return myNotificationBuilder;
    }

    public MyNotificationBuilder setSmallIcon(int icon, int level) {
        builder.setSmallIcon(icon, level);
        return myNotificationBuilder;
    }

    public MyNotificationBuilder setTitle(String title) {
        if (remoteViews == null) {
            builder.setContentTitle(title);
        }
        return myNotificationBuilder;
    }

    public MyNotificationBuilder setContent(String content) {
        if (remoteViews == null) {
            builder.setContentText(content);
        }
        return myNotificationBuilder;
    }

    public MyNotificationBuilder setCancelable(boolean cancelable) {
        builder.setOngoing(!cancelable);
        return myNotificationBuilder;
    }

    public MyNotificationBuilder setLargeIcon(Bitmap largeIcon) {
        if (remoteViews == null) {
            builder.setLargeIcon(largeIcon);
        }
        largeIcon.recycle();
        return myNotificationBuilder;
    }

    public MyNotificationBuilder setContentIntent(Intent intent, int requestCode, int flag) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, flag);
        builder.setContentIntent(pendingIntent);
        return myNotificationBuilder;
    }

    public MyNotificationBuilder setContentIntent(Intent intent, int flag) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, generateUniqueId(), intent, flag);
        builder.setContentIntent(pendingIntent);
        return myNotificationBuilder;
    }

    public MyNotificationBuilder setContentIntent(Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, generateUniqueId(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        return myNotificationBuilder;
    }

    public MyNotificationBuilder setImageView(int id, int resource) {
        if (remoteViews != null) {
            remoteViews.setImageViewResource(id, resource);
        }
        return myNotificationBuilder;
    }

    public MyNotificationBuilder setImageView(int id, Bitmap bitmap) {
        if (remoteViews != null) {
            remoteViews.setImageViewBitmap(id, bitmap);
        }
        return myNotificationBuilder;
    }


    public MyNotificationBuilder setTextView(int id, String text) {
        if (remoteViews != null) {
            remoteViews.setTextViewText(id, text);
        }
        return myNotificationBuilder;
    }

    public RemoteViews getRemoteViews() {
        return remoteViews;
    }

    public MyNotificationBuilder setImageView(int id, Uri uri) {
        if (remoteViews != null) {
            remoteViews.setImageViewUri(id, uri);
        }
        return myNotificationBuilder;
    }

    public MyNotificationBuilder setOnClick(Map<Integer, String> events, OnClickComponentNotification listener) {
        if (remoteViews == null) {
            return myNotificationBuilder;
        }

        this.listener = listener;

        IntentFilter intentFilter = new IntentFilter();

        for (Map.Entry<Integer, String> event : events.entrySet()) {
            StringBuilder packageName = new StringBuilder(context.getPackageName());
            packageName.append('.');
            Intent intentAction = new Intent(packageName.append(event.getValue()).toString());
            PendingIntent pendingIntentAction = PendingIntent.getBroadcast(context, event.getKey(), intentAction, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(event.getKey(), pendingIntentAction);
            intentFilter.addAction(packageName.toString());
        }

        context.registerReceiver(new NotificationReceiver(), intentFilter);

        return myNotificationBuilder;
    }


    protected static int generateUniqueId() {
        String uniqueNumber = String.format("%d%d%d%d", Calendar.getInstance().get(Calendar.DAY_OF_WEEK), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.MILLISECOND));
        return Integer.parseInt(uniqueNumber);
    }

    public void show(int notificationId) {
        notificationManager.notify(notificationId, builder.build());
    }

    public void show() {
        builder.setColor(Color.RED);
        notificationManager.notify(generateUniqueId(), builder.build());
    }


    class NotificationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (listener != null) {
                listener.onClicked(context, intent, intent.getAction());
            }
        }
    }

    public interface OnClickComponentNotification {
        void onClicked(Context context, Intent intent, String action);
    }

}
