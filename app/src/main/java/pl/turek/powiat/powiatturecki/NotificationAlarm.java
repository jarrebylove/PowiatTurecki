package pl.turek.powiat.powiatturecki;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class NotificationAlarm extends BroadcastReceiver {

    class Messages extends MessagesSource {
        Context context;
        NotificationManager notify_manager;
        NotificationCompat.Builder notify_builder;


        public Messages(Context context) {
            super();
            this.context = context;
        }

        @Override
        public void processResults(ArrayList<Message> messages) {
            notify_manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            MessagesDB messages_db = new MessagesDB(context);
            SQLiteDatabase messages_db_rw = messages_db.getWritableDatabase();
            int n_id = 1;
            for(int i=0; i < messages.size(); i++) {
                Log.i("bg", "Message processed");
                ContentValues values = new ContentValues();
                values.put(MessagesContract.MessageEntry._ID, messages.get(i).id);
                values.put(MessagesContract.MessageEntry.COLUMN_NAME_SOURCE_ID, messages.get(i).source_id);
                values.put(MessagesContract.MessageEntry.COLUMN_NAME_CATEGORY_ID, messages.get(i).category_id);
                values.put(MessagesContract.MessageEntry.COLUMN_NAME_BEGIN, messages.get(i).begin.getTime());
                if (messages.get(i).end != null)
                    values.put(MessagesContract.MessageEntry.COLUMN_NAME_END, messages.get(i).end.getTime());
                values.put(MessagesContract.MessageEntry.COLUMN_NAME_TITLE, messages.get(i).title);
                values.put(MessagesContract.MessageEntry.COLUMN_NAME_PAGE_ID, messages.get(i).page_id);
                try {
                    messages_db_rw.insertOrThrow(MessagesContract.MessageEntry.TABLE_NAME, null, values);
                    Log.i("bg", "Message inserted");
                    Intent page_intent = new Intent(context, PageActivity.class);
                    page_intent.putExtra("id", messages.get(i).page_id);
                    TaskStackBuilder stack_builder = TaskStackBuilder.create(context);
                    stack_builder.addParentStack(PageActivity.class);
                    stack_builder.addNextIntent(page_intent);
                    PendingIntent result_pending_intent = stack_builder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                    Uri notity_def_snd = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    notify_builder = new NotificationCompat.Builder(context);
                    notify_builder.setSmallIcon(R.drawable.ic_turek_county_arms_bw);
                    notify_builder.setContentTitle("Powiat Turecki");
                    notify_builder.setContentText(messages.get(i).title);
                    notify_builder.setSound(notity_def_snd);
                    notify_builder.setVibrate(new long[] { 1000, 1000});
                    notify_builder.setLights(0xffffffff, 3000, 3000);
                    notify_builder.setContentIntent(result_pending_intent);
                    notify_manager.notify(n_id, notify_builder.build());
                } catch (SQLiteConstraintException e){
                    Log.i("bg", "Message not inserted");
                }
            }
            messages_db.close();
        }
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Messages messages = new Messages(context);
        messages.execute();
        Log.i("bg", "Message recived");
    }
}