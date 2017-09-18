package pl.turek.powiat.powiatturecki;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewStub;

import java.util.ArrayList;

public class NewsActivity extends MainActivity implements NewsSourceListener {

    @Override
    public void onLoad(ArrayList<NewsSource.NewsGroup> news_groups) {
        if(news_groups != null) {
            ViewPager news_group_pager = (ViewPager) findViewById(R.id.news_group_pager);
            NewsGroupsPageAdapter adapter = new NewsGroupsPageAdapter(news_groups, this);
            news_group_pager.setAdapter(adapter);
            loading_done();
        } else {
            loading_done();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_content);
        stub.setLayoutResource(R.layout.news);
        View inflated = stub.inflate();
        NewsSource news = new NewsSource();
        news.addListener(this);
    }
}
