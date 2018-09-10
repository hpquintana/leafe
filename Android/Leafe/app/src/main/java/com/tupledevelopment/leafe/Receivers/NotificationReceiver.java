package com.tupledevelopment.leafe.Receivers;

        import android.app.Notification;
        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.support.v4.app.NotificationCompat;
        import android.widget.Toast;

        import com.tupledevelopment.leafe.Activities.ViewJobs;
        import com.tupledevelopment.leafe.R;

public class NotificationReceiver extends BroadcastReceiver {

    private static final int MY_NOTIFICATION_ID=1;
    NotificationManager notificationManager;
    Notification myNotification;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Notification received!", Toast.LENGTH_LONG).show();

        Intent myIntent = new Intent(context, ViewJobs.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                myIntent,
                Intent.FLAG_ACTIVITY_NEW_TASK); //TODO look into this

        myNotification = new NotificationCompat.Builder(context)
                .setContentTitle("Exercise of Notification!")
                .setContentText("Do Something...")
                .setTicker("Notification!")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .build();

        notificationManager =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
    }

}