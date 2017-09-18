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


public class NewsGroupAdapter extends ArrayAdapter<NewsSource.NewsItem> {
    private final Context context;
    private final ArrayList<NewsSource.NewsItem> news_items;
    private SimpleDateFormat date_format;

    public NewsGroupAdapter(Context context, ArrayList<NewsSource.NewsItem> news_items) {
        super(context, -1, news_items);
        this.context = context;
        this.news_items = news_items;
        date_format = new SimpleDateFormat("yyyy.MM.dd");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.news_item, null);
        }
        ImageView image = (ImageView) convertView.findViewById(R.id.news_image);
        Picasso.with(context).load(news_items.get(position).image).into(image);
        TextView title_text_view = (TextView) convertView.findViewById(R.id.news_item_title);
        title_text_view.setText(news_items.get(position).title);
        TextView date_text_view = (TextView) convertView.findViewById(R.id.news_date);
        date_text_view.setText(date_format.format(news_items.get(position).datetime));
        return convertView;
    }
}