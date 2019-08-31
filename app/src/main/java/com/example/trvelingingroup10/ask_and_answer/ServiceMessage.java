package com.example.trvelingingroup10.ask_and_answer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.trvelingingroup10.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class ServiceMessage extends FirebaseMessagingService {

    private NotificationManager notificationManager;
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        createNotificationChannel();
        createNotificationWhenAppIsInForground();


    }



    private void createNotificationWhenAppIsInForground() {
        //**add this line**
        int requestID = (int) System.currentTimeMillis();

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notificationManager  = (NotificationManager) getApplication().getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(getApplicationContext(), AskAndAnswerActivity.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent contentIntent = PendingIntent.getActivity(this, requestID,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification=new NotificationCompat.Builder(this,"chanel")
                .setSmallIcon(R.drawable.shepherdlogoorange)
                .setSound(alarmSound)
                .setVibrate(new long[]{500,500,500,500,500,500})
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH).build();
        notificationManager.notify(1,notification);

    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {

            NotificationChannel channel =new NotificationChannel("chanel","chanel",NotificationManager.IMPORTANCE_HIGH);

            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

}