package pl.turek.powiat.powiatturecki;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.github.chrisbanes.photoview.PhotoView;

import com.squareup.picasso.Picasso;

public class PictureActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture);
        PhotoView image_view = (PhotoView) findViewById(R.id.picture);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String url = (String) bundle.get("url");
            Picasso.with(this).load(url).placeholder(R.drawable.ic_turek_county_arms).into(image_view);
        }
    }

 }