package pl.turek.powiat.powiatturecki;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseMsg";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            //notify_builder = new NotificationCompat.Builder(context);
            //notify_builder.setSmallIcon(R.drawable.ic_turek_county_arms_bw);
            //notify_builder.setContentTitle("Powiat Turecki");
            //notify_builder.setContentText(messages.get(i).title);
            //notify_builder.setSound(notity_def_snd);
            //notify_builder.setVibrate(new long[] { 1000, 1000});
            //notify_builder.setLights(0xffffffff, 3000, 3000);
            //notify_builder.setContentIntent(result_pending_intent);
            //notify_manager.notify(n_id, notify_builder.build());
        }
    }
}
