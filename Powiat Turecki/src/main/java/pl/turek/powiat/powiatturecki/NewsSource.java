package pl.turek.powiat.powiatturecki;

import android.util.Log;

import org.json.JSONObject;
import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

interface NewsSourceListener{
    void onLoad();
}

public class NewsSource implements JSONSourceListener {

    public ArrayList<NewsGroup> news_groups;
    private JSONSource source;
    private List<NewsSourceListener> listeners = new ArrayList<>();

    public void addListener(NewsSourceListener toAdd) {
        listeners.add(toAdd);
    }
    public class NewsGroup {
        public int id;
        public String name;
        public String info;
        public int order;
        public ArrayList<NewsItem> news_items;
    }

    public class NewsItem {
        public int id;
        public String title;
        public int page_id;
        public String image;
        public Date datetime;
        public boolean sticky;
    }

    public NewsSource() {
        news_groups = new ArrayList<>();
        source = new JSONSource("/ajax/news/");
        source.addListener(this);
        source.execute();

    }
    public void loadMore(int group_id, int amount) {
        int offset = 0;
        for (NewsGroup ng: news_groups)
            if (ng.id == group_id) {
                offset = ng.news_items.size();
            }
        Log.d("lM", "/ajax/news/?g="+group_id+"&o="+offset+"&a="+amount);
        source = new JSONSource("/ajax/news/?g="+group_id+"&o="+offset+"&a="+amount);
        source.addListener(this);
        source.execute();
    }

    @Override
    public void response(String response) {
        if (response != null) {
            SimpleDateFormat json_date_format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            try {
                JSONArray json_news_groups = new JSONArray(response);
                for (int i = 0; i < json_news_groups.length(); i++) {
                    Boolean new_news_group = true;
                    JSONObject json_news_group = json_news_groups.getJSONObject(i);
                    NewsGroup news_group = new NewsGroup();
                    for (NewsGroup ng: news_groups)
                        if (ng.id == json_news_group.getInt("id")) {
                            new_news_group = false;
                            news_group = ng;
                            break;
                    }
                    if (new_news_group) {
                        news_group.id = json_news_group.getInt("id");
                        news_group.name = json_news_group.getString("name");
                        news_group.info = json_news_group.getString("info");
                        news_group.order = json_news_group.getInt("order");
                        news_group.news_items = new ArrayList<>();
                    }
                    JSONArray json_news_items = new JSONArray(json_news_group.getString("news_items"));
                    for (int j = 0; j < json_news_items.length(); j++) {
                        JSONObject json_news_item = json_news_items.getJSONObject(j);
                        NewsItem news_item = new NewsItem();
                        news_item.id = json_news_item.getInt("id");
                        news_item.title = json_news_item.getString("title");
                        news_item.page_id = json_news_item.getInt("page_id");
                        news_item.image = "https://www.powiat.turek.pl" + json_news_item.getString("image");
                        news_item.datetime = json_date_format.parse(json_news_item.getString("datetime"));
                        news_item.sticky = json_news_item.getBoolean("sticky");
                        news_group.news_items.add(news_item);
                    }
                    if (new_news_group)
                        news_groups.add(news_group);
                }
            } catch (Exception e) {
            }
            processResults(news_groups);
        } else {
            processResults(null);
        }
    }
    public void processResults(ArrayList<NewsGroup> news_groups){
        for (NewsSourceListener hl : listeners)
            hl.onLoad();
    };
}
