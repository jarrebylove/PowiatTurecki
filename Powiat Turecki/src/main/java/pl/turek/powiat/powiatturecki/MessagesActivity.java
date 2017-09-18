package pl.turek.powiat.powiatturecki;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ListView;

import java.util.ArrayList;

public class MessagesActivity extends MainActivity implements MessagesSourceListener {

    @Override
    public void onLoad(ArrayList<MessagesSource.Message> messages) {
        if(messages != null) {
            ListView messages_list_view = (ListView) findViewById(R.id.messages_listview);
            MessagesListViewAdapter adapter = new MessagesListViewAdapter(MessagesActivity.this, messages);
            messages_list_view.setAdapter(adapter);
            loading_done();
        } else {
            loading_done();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_content);
        stub.setLayoutResource(R.layout.messages);
        View inflated = stub.inflate();
        MessagesSource messages = new MessagesSource();
        messages.addListener(this);
    }
}
