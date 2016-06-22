package com.hado.myexample.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.hado.myexample.util.DebugLog;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by Ngo Hado on 19-Jun-16.
 */
public class NotificationHelper {
    private static NotificationHelper notificationHelper;

    private Context context;

    private NotificationCompat.Builder builder;

    private NotificationManager notificationManager;

    private RemoteViews remoteViews;

    private OnClickComponentNotification listener;

    public NotificationHelper(Context context) {
        this.context = context;
        builder = new NotificationCompat.Builder(context);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        remoteViews = null;
    }

    public NotificationHelper(Context context, int idLayout) {
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
    public static NotificationHelper getInstance(Context context) {
        notificationHelper = new NotificationHelper(context);
        return notificationHelper;
    }


    /**
     * @param context  require application context
     * @param idLayout is identifier of notification layout
     * @return
     */
    public static NotificationHelper getInstance(Context context, int idLayout) {
        notificationHelper = new NotificationHelper(context, idLayout);
        return notificationHelper;
    }

    public NotificationHelper setVibrate(boolean isDefault) {
        if (isDefault) {
            builder.setDefaults(Notification.DEFAULT_VIBRATE);
        } else {
            builder.setVibrate(new long[]{200, 1000});
        }
        return notificationHelper;
    }

    public NotificationHelper setLedLight(int color) {
        builder.setLights(color, 1000, 3000);
        return notificationHelper;
    }

    public NotificationHelper setSound(boolean isDefault, int rawSound) {
        if (isDefault) {
            //3 methods perform the same action
            builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
//            builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
//            builder.setDefaults(Notification.DEFAULT_SOUND);
        } else {
            DebugLog.i(rawSound + "");
            Uri uriSound = Uri.parse("android.resource://" + context.getPackageName() + "/" + rawSound);
            builder.setSound(uriSound);
        }
        return notificationHelper;
    }

    public NotificationHelper setSmallIcon(int icon) {
        builder.setSmallIcon(icon);
        return notificationHelper;
    }

    public NotificationHelper setSmallIcon(int icon, int level) {
        builder.setSmallIcon(icon, level);
        return notificationHelper;
    }

    public NotificationHelper setTitle(String title) {
        if (remoteViews == null) {
            builder.setContentTitle(title);
        }
        return notificationHelper;
    }

    public NotificationHelper setContent(String content) {
        if (remoteViews == null) {
            builder.setContentText(content);
        }
        return notificationHelper;
    }

    public NotificationHelper setCancelable(boolean cancelable) {
        builder.setOngoing(!cancelable);
        return notificationHelper;
    }

    public NotificationHelper setLargeIcon(Bitmap largeIcon) {
        if (remoteViews == null) {
            builder.setLargeIcon(largeIcon);
        }
        return notificationHelper;
    }

    public NotificationHelper setContentIntent(Intent intent, int requestCode, int flag) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, flag);
        builder.setContentIntent(pendingIntent);
        return notificationHelper;
    }

    public NotificationHelper setContentIntent(Intent intent, int flag) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, generateUniqueId(), intent, flag);
        builder.setContentIntent(pendingIntent);
        return notificationHelper;
    }

    public NotificationHelper setContentIntent(Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, generateUniqueId(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        return notificationHelper;
    }

    public NotificationHelper setImageView(int id, int resource) {
        if (remoteViews != null) {
            remoteViews.setImageViewResource(id, resource);
        }
        return notificationHelper;
    }

    public NotificationHelper setImageView(int id, Bitmap bitmap) {
        if (remoteViews != null) {
            remoteViews.setImageViewBitmap(id, bitmap);
        }
        return notificationHelper;
    }


    public NotificationHelper setTextView(int id, String text) {
        if (remoteViews != null) {
            remoteViews.setTextViewText(id, text);
        }
        return notificationHelper;
    }

    public RemoteViews getRemoteViews() {
        return remoteViews;
    }

    public NotificationHelper setImageView(int id, Uri uri) {
        if (remoteViews != null) {
            remoteViews.setImageViewUri(id, uri);
        }
        return notificationHelper;
    }

    public NotificationHelper setOnClick(Map<Integer, String> events, OnClickComponentNotification listener) {
        if (remoteViews == null) {
            return notificationHelper;
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

        return notificationHelper;
    }

    protected static int generateUniqueId() {
        String uniqueNumber = String.format("%d%d%d%d", Calendar.getInstance().get(Calendar.DAY_OF_WEEK), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.MILLISECOND));
        return Integer.parseInt(uniqueNumber);
    }

    public void show(int notificationId) {
        notificationManager.notify(notificationId, builder.build());
        recycleBitmap(builder.mLargeIcon);
    }

    public void show() {
        notificationManager.notify(generateUniqueId(), builder.build());
        recycleBitmap(builder.mLargeIcon);
    }

    public void showForeground(Service service, int notificationId) {
        service.startForeground(notificationId, builder.build());
        recycleBitmap(builder.mLargeIcon);
    }

    public void showForeground(Service service) {
        service.startForeground(generateUniqueId(), builder.build());
        recycleBitmap(builder.mLargeIcon);
    }

    public void updateNotification(Notification notification, int notificationId) {
        notificationManager.notify(notificationId, notification);
    }

    public void updateNotification(Service service, Notification notification, int notificationId) {
        service.startForeground(notificationId, notification);
    }

    private void recycleBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    class NotificationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (listener != null) {
                listener.onClicked(context, intent, builder.build(), intent.getAction().replace(context.getPackageName() + ".", ""));

            }
        }
    }

    public interface OnClickComponentNotification {
        void onClicked(Context context, Intent intent, Notification notification, String action);
    }

}
