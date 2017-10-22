package pl.turek.powiat.powiatturecki;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by jarre on 22.10.17.
 */

public class PagePicturesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private final Activity activity;
    private final Context context;
    private final PageSource.Page page;

    public PagePicturesAdapter(Activity activity, Context context, PageSource.Page page) {
        super();
        this.activity = activity;
        this.context = context;
        this.page = page;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity).inflate(R.layout.news_item_loading, parent, false);
            return new LoadingViewHolder(view);
        } else if (viewType == VIEW_TYPE_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.page_picture, parent, false);
            return new PagePictureViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        } else if (holder instanceof PagePictureViewHolder) {
            PictureCnt picture_cnt = page.pictures.get(position);
            Picasso.with(context).load(picture_cnt.thumb_url).into(((PagePictureViewHolder)holder).image);
            //holder.itemView.setOnClickListener(new View.OnClickListener() {
                //@Override
                //public void onClick(View view) {
                    //Intent page_intent = new Intent(activity, PageActivity.class);
                    //page_intent.putExtra("id", news.news_groups.get(group_id).news_items.get(position).page_id);
                    //activity.startActivity(page_intent);
            //    }
            //});
        }
    }

    @Override
    public int getItemCount() {
        return page.pictures == null ? 0 : page.pictures.size();
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.news_item_progress_bar);
        }
    }

    public class PagePictureViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image;
        //TextView title_text_view;

        public PagePictureViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.page_picture);
            //title_text_view = (TextView) itemView.findViewById(R.id.news_item_title);
        }
    }
}
