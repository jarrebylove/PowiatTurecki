package pl.turek.powiat.powiatturecki;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewStub;
import android.widget.ExpandableListView;

public class PageActivity extends MainActivity implements PageSourceListener {

    @Override
    public void onLoad(PageSource.Page page) {
        if(page != null) {
            PageExpandableListAdapter page_expandable_list_adapter = new PageExpandableListAdapter(this, PageActivity.this, page);
            ExpandableListView page_expandable_list_view = (ExpandableListView) findViewById(R.id.page_expandable_list_view);
            page_expandable_list_view.setAdapter(page_expandable_list_adapter);
            loading_done();
        } else {
            loading_done();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_content);
        stub.setLayoutResource(R.layout.page2);
        stub.inflate();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            int id =(int) bundle.get("id");
            PageSource page = new PageSource(id);
            page.addListener(this);
            int a=1;
            Log.e("t",""+a);
        }
    }
 }
