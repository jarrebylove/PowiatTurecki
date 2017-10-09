package pl.turek.powiat.powiatturecki;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NewsGroupRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem;
    private int totalItemCount;

    private final Activity activity;
    private final Context context;
    //private final ArrayList<NewsSource.NewsItem> news_items;
    private final NewsSource news;
    private final int group_id;
    private SimpleDateFormat date_format;

    //public NewsGroupRecyclerAdapter(Activity activity, Context context, RecyclerView recyclerView, ArrayList<NewsSource.NewsItem> news_items) {
    public NewsGroupRecyclerAdapter(Activity activity, Context context, RecyclerView recyclerView, NewsSource news, int group_id) {
        super();
        this.activity = activity;
        this.context = context;
        //this.news_items = news_items;
        this.news = news;
        this.group_id = group_id;
        date_format = new SimpleDateFormat("yyyy.MM.dd");
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }
    private OnLoadMoreListener onLoadMoreListener;

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity).inflate(R.layout.news_item_loading, parent, false);
            return new LoadingViewHolder(view);
        } else if (viewType == VIEW_TYPE_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            return new NewsItemViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        } else if (holder instanceof NewsItemViewHolder) {
            NewsSource.NewsItem newsItem = news.news_groups.get(group_id).news_items.get(position);
            Picasso.with(context).load(newsItem.image).into(((NewsItemViewHolder)holder).image);
            ((NewsItemViewHolder)holder).title_text_view.setText(newsItem.title);
            ((NewsItemViewHolder)holder).date_text_view.setText(date_format.format(newsItem.datetime));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent page_intent = new Intent(activity, PageActivity.class);
                    page_intent.putExtra("id", news.news_groups.get(group_id).news_items.get(position).page_id);
                    activity.startActivity(page_intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return news.news_groups.get(group_id).news_items == null ? 0 : news.news_groups.get(group_id).news_items.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.news_item_progress_bar);
        }
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
