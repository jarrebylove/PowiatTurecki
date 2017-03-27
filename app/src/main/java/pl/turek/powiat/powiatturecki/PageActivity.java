package pl.turek.powiat.powiatturecki;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class PageActivity extends AppCompatActivity {

    class Page extends PageSource {

        private Activity activity;

        public Page(Activity activity_, int id) {
            super(id);
            activity = activity_;
        }

        @Override
        public void processResults(Page page) {
            TextView title = (TextView) findViewById(R.id.page_title);
            title.setText(page.title);
            TextView body = (TextView) findViewById(R.id.page_body);
            body.setText(Html.fromHtml(page.body));

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page);

        Page page = new Page(this, 1);
    }
}
