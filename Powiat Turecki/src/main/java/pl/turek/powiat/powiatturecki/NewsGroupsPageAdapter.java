package pl.turek.powiat.powiatturecki;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewsGroupsPageAdapter extends PagerAdapter {
    private NewsSource news;
    private Activity activity;

    public NewsGroupsPageAdapter(NewsSource news, Activity activity) {
        super();
        this.news = news;
        this.activity = activity;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int group_position) {
        RecyclerView recycler_view = new RecyclerView(container.getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        recycler_view.setLayoutParams(params);
        recycler_view.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(activity);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(llm);
        final NewsGroupRecyclerAdapter newsGroupRecyclerAdapter = new NewsGroupRecyclerAdapter(activity, container.getContext(), recycler_view, news, group_position);
        recycler_view.setAdapter(newsGroupRecyclerAdapter);
        news.addListener(newsGroupRecyclerAdapter);
        newsGroupRecyclerAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //Toast.makeText(activity, "Load more (onLoadMore)...", Toast.LENGTH_SHORT).show();
                //news.news_groups.get(group_position).news_items.add(null);
                //newsGroupRecyclerAdapter.notifyItemInserted(news.news_groups.get(group_position).news_items.size() - 1);
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        //news.news_groups.get(group_position).news_items.remove(news.news_groups.get(group_position).news_items.size() - 1);
                        //newsGroupRecyclerAdapter.notifyItemRemoved(news.news_groups.get(group_position).news_items.size() - 1);
                        news.loadMore(news.news_groups.get(group_position).id, 10);
                    }
                });
            }
        });
        container.addView(recycler_view);
        return recycler_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RecyclerView)object);
    }
    @Override
    public boolean isViewFromObject(View view, Object o) {
        return o==view;
    }

    @Override
    public int getCount() {
        return news.news_groups.size();
    }

    @Override
    public String getPageTitle(int position) {
       return news.news_groups.get(position).name;
    }

    //public void update() {
        //Toast.makeText(activity, "Load more (update)...", Toast.LENGTH_SHORT).show();
        //notifyDataSetChanged();
        //setLoaded();
        //this.
    //}
}