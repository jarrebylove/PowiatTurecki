package pl.turek.powiat.powiatturecki;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    class News extends NewsSource {

        private Activity activity;

        public News(Activity activity_) {
            super();
            activity = activity_;
        }

        @Override
        public void processResults(ArrayList<NewsGroup> news_groups) {
            ExpandableListView news_group_expandalne_list_view = (ExpandableListView) findViewById(R.id.NewsGroupExpandableListView);
            NewsAdapter news_adapter = new NewsAdapter(activity, news_groups);
            news_group_expandalne_list_view.setAdapter(news_adapter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        News news = new News(this);
        news.execute();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}
