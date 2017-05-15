package pl.turek.powiat.powiatturecki;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        ViewPager gallery = (ViewPager) findViewById(R.id.gallery);
        Bundle extra = getIntent().getBundleExtra("pictures");
        ArrayList<PageSource.Picture> pictures = (ArrayList<PageSource.Picture>) extra.getSerializable("pictures");
        GalleryAdapter adapter = new GalleryAdapter(this.getApplicationContext(), pictures);
        gallery.setAdapter(adapter);
    }
}
