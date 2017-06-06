package com.example.orangemodule.broad;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

import com.example.orangemodule.MainActivity;
import com.example.orangemodule.R;

public class ModuleReceiver extends BroadcastReceiver {
    public ModuleReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (intent.getAction() == "android.hardware.usb.action.USB_DEVICE_ATTACHED") {
            Intent broadcastIntent = new Intent(context, OpenReceiver.class);
            PendingIntent pi = PendingIntent.getBroadcast(context, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            Notification notification = new NotificationCompat.Builder(context)
                    .setContentTitle("框架已经连接")
                    .setContentText("点击进入app")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    //当框架连接时，手机震动一下
                    .setVibrate(new long[]{0, 1000})
                    .build();
            manager.notify(1, notification);
        } else if (intent.getAction() == "android.hardware.usb.action.USB_DEVICE_DETACHED") {
            Notification notification = new NotificationCompat.Builder(context)
                    .setContentTitle("框架已经断开")
                    .setContentText("请检查连接")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true)
                    .build();
            manager.notify(2, notification);
        }


    }
}
