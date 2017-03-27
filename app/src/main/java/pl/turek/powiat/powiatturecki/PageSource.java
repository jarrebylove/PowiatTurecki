package pl.turek.powiat.powiatturecki;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class PageSource extends JSONSource {

    private Page page;

    public class Page {
        public int id;
        public String title;
        public String body;
    }

    public PageSource(int page_id) {
        super(String.format("https://www.powiat.turek.pl/ajax/page/%d/", page_id));
        page = new Page();
    }
    @Override
    public void processJSON(String response) {
        if (response != null) {
            //SimpleDateFormat json_date_format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            try {
                JSONObject json_page = new JSONObject(response);
                page.id = json_page.getInt("id");
                page.title = json_page.getString("title");
                page.body = json_page.getString("body");
            } catch (Exception e) {
            }
            processResults(page);
        }
    }
    public abstract void processResults(Page page);
}
