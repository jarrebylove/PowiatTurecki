package pl.turek.powiat.powiatturecki;

import android.util.Log;

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
        public String banner;
        public String body;
        public ArrayList<Picture> pictures;
    }

    public class Picture {
        public int id;
        public String image;
        public String thumb;
        public String name;
    }

    public PageSource(int page_id) {
        super(String.format("https://www.powiat.turek.pl/ajax/page/%d/", page_id));
        page = new Page();
    }
    @Override
    public void processJSON(String response) {
        if (response != null) {
            try {
                JSONObject json_page = new JSONObject(response);
                page.id = json_page.getInt("id");
                page.title = json_page.getString("title");
                page.banner = "https://www.powiat.turek.pl" + json_page.getString("banner");
                page.body = json_page.getString("body");
                page.pictures = new ArrayList<>();
                JSONArray json_pictures = new JSONArray(json_page.getString("pictures"));
                for (int i = 0; i < json_pictures.length(); i++) {
                    JSONObject json_picture = json_pictures.getJSONObject(i);
                    Picture picture = new Picture();
                    picture.id = json_picture.getInt("id");
                    picture.image = "https://www.powiat.turek.pl" + json_picture.getString("image");
                    picture.thumb = "https://www.powiat.turek.pl" + json_picture.getString("thumb");
                    picture.name = json_picture.getString("name");
                    page.pictures.add(picture);
                }
            } catch (Exception e) {
            Log.e("E", e.getMessage());
            }
            processResults(page);
        }
    }
    public abstract void processResults(Page page);
}
