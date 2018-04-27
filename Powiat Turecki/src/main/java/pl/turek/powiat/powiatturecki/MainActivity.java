package pl.turek.powiat.powiatturecki;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.androidnetworking.AndroidNetworking;

public abstract class MainActivity extends AppCompatActivity {

    NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworking.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        Intent intent = new Intent(this, StarterService.class);
        startService(intent);

        navigation = (NavigationView) findViewById(R.id.nav_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                Intent intent;
                switch (id) {
                    case R.id.action_1:
                        intent = new Intent(MainActivity.this, NewsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_2:
                        break;
                    case R.id.action_3:
                        intent = new Intent(MainActivity.this, PageActivity.class);
                        intent.putExtra("id", 138);
                        startActivity(intent);
                        break;
                    case R.id.action_4:
                        intent = new Intent(MainActivity.this, MapActivity.class);
                        //intent.putExtra("id", 138);
                        startActivity(intent);
                        break;
                    case R.id.action_5:
                        intent = new Intent(MainActivity.this, MessagesActivity.class);
                        //intent.putExtra("id", 138);
                        startActivity(intent);
                        break;
                    case R.id.action_6:
                        intent = new Intent(MainActivity.this, PageActivity2.class);
                        intent.putExtra("id", 138);
                        startActivity(intent);
                        break;
                    case R.id.action_7:
                        intent = new Intent(MainActivity.this, LifeBandActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

    }
    void loading_start() {
        findViewById(R.id.loading_animation).setVisibility(View.GONE);
    }
    void loading_done() {

        findViewById(R.id.loading_animation).setVisibility(View.GONE);
    }
    //public boolean onCreateOptionsMenu(Menu menu) {
    //    MenuInflater inflater = getMenuInflater();
    //    inflater.inflate(R.menu.toolbar_menu, menu);
    //   return true;
    //}
}
