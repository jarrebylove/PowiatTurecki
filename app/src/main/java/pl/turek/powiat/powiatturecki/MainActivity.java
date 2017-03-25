package pl.turek.powiat.powiatturecki;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView news_group_list_view ;
    private ArrayAdapter<String> news_group_adapter ;

    class News extends NewsSource {

        private Activity activity;

        public News(Activity activity_) {
            super();
            activity = activity_;
        }

        @Override
        public void processResults(ArrayList<NewsGroup> news_groups) {
            news_group_list_view = (ListView) findViewById(R.id.NewsGroupListView);
            news_group_adapter = new ArrayAdapter<>(activity, R.layout.news_group, R.id.news_group_name);
            for(NewsGroup news_group : news_groups) {
                //
                ListView news_item_list_view = (ListView) findViewById(R.id.NewsItemListView);
                ArrayAdapter<String> news_item_adapter = new ArrayAdapter<>(activity, R.layout.news_item, R.id.news_item_title);
                for(NewsItem news_item : news_group.news_items){
                    news_item_adapter.add(news_item.title);
                }
                //news_item_list_view.setAdapter(news_item_adapter);
                //
                news_group_adapter.add(news_group.name);
            }
            news_group_list_view.setAdapter(news_group_adapter);
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
