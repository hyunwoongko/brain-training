package com.hyunwoong.braintraining.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.view.MainView;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.graphics.Color.MAGENTA;

public class MessagingService extends FirebaseMessagingService {
// FCM 포그라운드 메시징 전용.
// 백그라운드에선 필요없었지만 포그라운드에선 필요함.

    private static final String CHANNEL_ID = "com.example.user";
    private static final String CHANNEL_NAME = "BrainTraining Channel";

    private NotificationManager notificationManager;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {

            String title = remoteMessage.getData().get("title");
            String text = remoteMessage.getData().get("text");
            sendNotification(title, text);
        }
    }
    @SuppressWarnings("ResourceType") // 이거 해줘야 컴파일 에러 안남.
    private void sendNotification(String title, String text) {
        Intent intent = new Intent(this, MainView.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setContentIntent(pendingIntent);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // 오레오 이상에서는 반드시 채널이 필요함
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLightColor(MAGENTA);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(channel);
            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
        }
    }
}
