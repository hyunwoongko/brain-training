package com.hyunwoong.braintraining.receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.hyunwoong.braintraining.R;
import com.hyunwoong.braintraining.view.LoginView;

import static android.graphics.Color.MAGENTA;

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    @SuppressWarnings("ResourceType")
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.
                getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeating_intent = new Intent(context, LoginView.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.
                getActivity(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, ReceiverModel.getInstance().getCHANNEL_ID())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("매일 매일 트레이닝!")
                .setContentText("매일 아침 당신의 두뇌를 단련하세요.")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setPriority(Notification.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // 오레오 이상에서는 반드시 채널이 필요함
            NotificationChannel channel = new NotificationChannel(ReceiverModel.getInstance().getCHANNEL_ID(),
                    ReceiverModel.getInstance().getCHANNEL_NAME(), NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLightColor(MAGENTA);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(channel);
            notificationManager.notify(100 /* ID of notification */, builder.build());

        }
    }
}