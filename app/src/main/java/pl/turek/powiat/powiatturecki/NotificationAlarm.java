package pl.turek.powiat.powiatturecki;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

public class NotificationAlarm extends BroadcastReceiver {

    class Messages extends MessagesSource {


        public Messages() {
            super();
        }

        @Override
        public void processResults(ArrayList<Message> messages) {
        }
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Messages messages = new Messages();
        messages.execute();
    }
}