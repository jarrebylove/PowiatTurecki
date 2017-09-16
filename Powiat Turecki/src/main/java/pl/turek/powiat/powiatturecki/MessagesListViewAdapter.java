package pl.turek.powiat.powiatturecki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by jarre on 16.09.17.
 */

class MessagesListViewAdapter extends ArrayAdapter<MessagesSource.Message> {
    private final Context context;
    private final ArrayList<MessagesSource.Message> messages;
    private SimpleDateFormat date_format;

    public MessagesListViewAdapter(Context context, ArrayList<MessagesSource.Message> messages) {
        super(context, -1, messages);
        this.context = context;
        this.messages = messages;
        date_format = new SimpleDateFormat("yyyy.MM.dd");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.messages_item, null);
        }
        TextView title_text_view = (TextView) convertView.findViewById(R.id.messages_item_title);
        title_text_view.setText(messages.get(position).title);
        return convertView;
    }
}
