package pl.turek.powiat.powiatturecki;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewStub;
import android.widget.ListView;

import java.util.ArrayList;

public class MessagesActivity extends MainActivity {

    class Messages extends MessagesSource {

        private Activity activity;

        public Messages(Activity activity) {
            super();
            this.activity = activity;
        }

        @Override
        public void processResults(final ArrayList<Message> messages) {
            if(messages != null) {
                ListView messages_list_view = (ListView) findViewById(R.id.messages_listview);
                MessagesListViewAdapter adapter = new MessagesListViewAdapter(MessagesActivity.this, messages);
                messages_list_view.setAdapter(adapter);
                loading_done();
            } else {
                loading_done();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_content);
        stub.setLayoutResource(R.layout.messages);
        View inflated = stub.inflate();
        Messages messages = new Messages(this);
        messages.execute();
    }
}
