package pl.turek.powiat.powiatturecki;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PageActivity extends MainActivity {

    class Page extends PageSource {

        private Activity activity;

        public Page(Activity activity, int id) {
            super(id);
            this.activity = activity;
        }

        @Override
        public void processResults(Page page) {
            TextView title = (TextView) findViewById(R.id.page_title);
            title.setText(page.title);
            ImageView banner = (ImageView) findViewById(R.id.page_banner);
            Picasso.with(activity).load(page.banner).into(banner);
            TextView body = (TextView) findViewById(R.id.page_body);
            body.setText(Html.fromHtml(page.body));
            if (page.pictures.size() > 0) {
                ExpandableHeightGridView page_pictures = (ExpandableHeightGridView) findViewById(R.id.page_pictures);
                PagePicturesAdapter page_pictures_adapter = new PagePicturesAdapter(activity, page.pictures);
                page_pictures.setAdapter(page_pictures_adapter);
                page_pictures.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return event.getAction() == MotionEvent.ACTION_MOVE;
                    }
                });
                page_pictures.setExpanded(true);
            }
            loading_done();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_content);
        stub.setLayoutResource(R.layout.page);
        stub.inflate();
        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            int id =(int) bundle.get("id");
            Page page = new Page(this, id);
            page.execute();
        }
    }
}
