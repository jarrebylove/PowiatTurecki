package pl.turek.powiat.powiatturecki;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.ViewStub;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PageActivity2 extends MainActivity implements PageSourceListener {

    @Override
    public void onLoad(PageSource.Page page) {
        if(page != null) {
            TextView title = (TextView) findViewById(R.id.page_title);
            title.setText(page.title);
            ImageView banner = (ImageView) findViewById(R.id.page_banner);
            Picasso.with(this).load(page.banner).into(banner);
            TextView body = (TextView) findViewById(R.id.page_body);
            body.setText(Html.fromHtml(page.body));
            RecyclerView pictures = (RecyclerView) findViewById(R.id.pictures);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.HORIZONTAL);
            pictures.setLayoutManager(llm);
            PagePicturesAdapter pagePicturesAdapter = new PagePicturesAdapter(this, this, page);
            pictures.setAdapter(pagePicturesAdapter);

            loading_done();
        } else {
            loading_done();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_content);
        stub.setLayoutResource(R.layout.page3);
        stub.inflate();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            int id =(int) bundle.get("id");
            PageSource page = new PageSource(id);
            page.addListener(this);
        }
    }
 }
