package pl.turek.powiat.powiatturecki;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewStub;

import java.util.ArrayList;

public class NewsActivity extends MainActivity {

    class News extends NewsSource {

        private Activity activity;

        public News(Activity activity) {
            super();
            this.activity = activity;
        }

        @Override
        public void processResults(final ArrayList<NewsGroup> news_groups) {
            ViewPager news_group_pager = (ViewPager) findViewById(R.id.news_group_pager);
            NewsGroupsPageAdapter adapter = new NewsGroupsPageAdapter(news_groups, activity);
            news_group_pager.setAdapter(adapter);
            loading_done();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_content);
        stub.setLayoutResource(R.layout.news);
        View inflated = stub.inflate();
        News news = new News(this);
        news.execute();
    }
}
