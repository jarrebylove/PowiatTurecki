package pl.turek.powiat.powiatturecki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PageActivity2 extends MainActivity {

    class Page extends PageSource {

        private Activity activity;

        public Page(Activity activity, int id) {
            super(id);
            this.activity = activity;
        }

        @Override
        public void processResults(Page page) {
            PageExpandableListAdapter page_expandable_list_adapter = new PageExpandableListAdapter(activity, PageActivity2.this, page);
            ExpandableListView page_expandable_list_view = (ExpandableListView) findViewById(R.id.page_expandable_list_view);
            page_expandable_list_view.setAdapter(page_expandable_list_adapter);
            loading_done();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_content);
        stub.setLayoutResource(R.layout.page2);
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
