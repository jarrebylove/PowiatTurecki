package pl.turek.powiat.powiatturecki;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class MessagesSource extends JSONSource {

    private ArrayList<Message> messages;

    public class Message {
        public int id;
        public int source_id;
        public int category_id;
        public Date begin;
        public Date end;
        public String title;
        public int page_id;
    }

    public MessagesSource() {
        super("/ajax/messages/");
        messages = new ArrayList<>();
    }
    @Override
    public void processJSON(String response) {
        if (response != null) {
            SimpleDateFormat json_date_format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            try {
                JSONArray json_messages = new JSONArray(response);
                for (int i = 0; i < json_messages.length(); i++) {
                    Log.i("bg", "Message precess json");
                    JSONObject json_message = json_messages.getJSONObject(i);
                    Message message = new Message();
                    message.id = json_message.getInt("id");
                    message.source_id = json_message.getInt("source_id");
                    message.category_id = json_message.getInt("category_id");
                    message.begin = json_date_format.parse(json_message.getString("begin"));
                    if (json_message.getString("end") != "null")
                        message.end = json_date_format.parse(json_message.getString("end"));
                    message.title = json_message.getString("title");
                    message.page_id = json_message.getInt("page_id");
                    messages.add(message);
                }
            } catch (Exception e) {
                Log.i("bg", "Message precess json exception: "+e.getMessage());
            }
            Log.i("bg", "Message precess json after");
            processResults(messages);
        }
    }
    public abstract void processResults(ArrayList<Message> messages);
}
