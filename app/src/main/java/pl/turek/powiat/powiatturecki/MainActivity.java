package pl.turek.powiat.powiatturecki;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    NavigationView navigation;

    class News extends NewsSource {

        private Activity activity;

        public News(Activity activity_) {
            super();
            activity = activity_;
        }

        @Override
        public void processResults(final ArrayList<NewsGroup> news_groups) {
            ExpandableListView news_group_expandalne_list_view = (ExpandableListView) findViewById(R.id.NewsGroupExpandableListView);
            NewsAdapter news_adapter = new NewsAdapter(activity, news_groups);
            news_group_expandalne_list_view.setAdapter(news_adapter);
            news_group_expandalne_list_view.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    Intent page_intent = new Intent(MainActivity.this, PageActivity.class);
                    page_intent.putExtra("id", news_groups.get(groupPosition).news_items.get(childPosition).page_id);
                    MainActivity.this.startActivity(page_intent);
                    return true;
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setHomeButtonEnabled(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        News news = new News(this);
        news.execute();
        Intent intent = new Intent(this, StarterService.class);
        startService(intent);

        navigation = (NavigationView) findViewById(R.id.nav_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.action_1:
                        Intent i = new Intent(MainActivity.this, MainActivity2.class);
                        //Intent i = new Intent(MainActivity.this, PageActivity.class);
                        //i.putExtra("id", 372);
                        startActivity(i);
                        break;
                    case R.id.action_2:
                        break;
                }
                return false;
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}
