package pl.turek.powiat.powiatturecki;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView news_group_list_view ;
    private ArrayAdapter<String> news_group_adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        news_group_list_view = (ListView) findViewById(R.id.NewsGroupListView);
        String cars[] = {"Mercedes", "Fiat", "Ferrari", "Aston Martin", "Lamborghini", "Skoda", "Volkswagen", "Audi", "Citroen"};
        ArrayList<String> carL = new ArrayList<String>();
        carL.addAll( Arrays.asList(cars) );
        news_group_adapter = new ArrayAdapter<String>(this, R.layout.news_group, R.id.textView3, carL);
        news_group_list_view.setAdapter(news_group_adapter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}
