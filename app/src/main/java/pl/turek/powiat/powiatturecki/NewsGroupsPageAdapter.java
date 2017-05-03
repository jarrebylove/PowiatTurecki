package pl.turek.powiat.powiatturecki;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NewsGroupsPageAdapter extends PagerAdapter {
    private ArrayList<NewsSource.NewsGroup> news_groups;
    private Activity activity;

    public NewsGroupsPageAdapter(ArrayList<NewsSource.NewsGroup> news_groups, Activity activity) {
        super();
        this.news_groups = news_groups;
        this.activity = activity;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int group_position) {
        ListView list_view = new ListView(container.getContext());
        //RecyclerView list_view = new RecyclerView(container.getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        list_view.setLayoutParams(params);
        list_view.setAdapter(new NewsGroupAdapter(container.getContext(), news_groups.get(group_position).news_items));
        list_view.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int child_position, long id) {
                Intent page_intent = new Intent(activity, PageActivity2.class);
                page_intent.putExtra("id", news_groups.get(group_position).news_items.get(child_position).page_id);
                activity.startActivity(page_intent);
            }
        });container.addView(list_view);
        return list_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ListView)object);
    }
    @Override
    public boolean isViewFromObject(View view, Object o) {
        return o==view;
    }

    @Override
    public int getCount() {
        return news_groups.size();
    }

    @Override
    public String getPageTitle(int position) {
       return news_groups.get(position).name;
    }
}