package pl.turek.powiat.powiatturecki;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NewsGroupRecyclerAdapter extends RecyclerView.Adapter<NewsGroupRecyclerAdapter.NewsItemViewHolder> {
    private  final Activity activity;
    private final Context context;
    private final ArrayList<NewsSource.NewsItem> news_items;
    private SimpleDateFormat date_format;

    public NewsGroupRecyclerAdapter(Activity activity, Context context, ArrayList<NewsSource.NewsItem> news_items) {
        super();
        this.activity = activity;
        this.context = context;
        this.news_items = news_items;
        date_format = new SimpleDateFormat("yyyy.MM.dd");
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsItemViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, final int position) {
        NewsSource.NewsItem newsItem = news_items.get(position);
        Picasso.with(context).load(newsItem.image).into(holder.image);
        holder.title_text_view.setText(newsItem.title);
        holder.date_text_view.setText(date_format.format(newsItem.datetime));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page_intent = new Intent(activity, PageActivity.class);
                page_intent.putExtra("id", news_items.get(position).page_id);
                activity.startActivity(page_intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return news_items.size();
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image;
        TextView title_text_view;
        TextView date_text_view;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.news_image);
            title_text_view = (TextView) itemView.findViewById(R.id.news_item_title);
            date_text_view = (TextView) itemView.findViewById(R.id.news_date);
        }
    }
}
