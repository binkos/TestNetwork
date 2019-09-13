package com.example.testnetwork.Fragments.Alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.testnetwork.Fragments.FragmentMail;
import com.example.testnetwork.R;

public class AlarmReceiver extends BroadcastReceiver {
    NotificationManager manager;

    public AlarmReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"HELLO",Toast.LENGTH_SHORT).show();

        Notification notification =
                new NotificationCompat.Builder(context,"YOUR_CHANNEL_ID")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Title")
                        .setContentText("You choose time: "+ intent.getIntExtra("hours",0)+": "+intent.getIntExtra("minutes",0))
                        .setTicker("You have notification...")
                        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                        .setVibrate(new long[]{400,800,1000})
                        .build();

        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,notification);

    }
}
