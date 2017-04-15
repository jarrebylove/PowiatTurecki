package pl.turek.powiat.powiatturecki;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewsGroupsPageAdapter extends PagerAdapter {
    private ArrayList<NewsSource.NewsGroup> news_groups;

    public NewsGroupsPageAdapter(ArrayList<NewsSource.NewsGroup> news_groups) {
        super();
        this.news_groups = news_groups;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        ListView list_view = new ListView(container.getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        list_view.setLayoutParams(params);
        list_view.setAdapter(new NewsGroupAdapter(container.getContext(), news_groups.get(position).news_items));
        container.addView(list_view);
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