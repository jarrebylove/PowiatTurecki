package pl.turek.powiat.powiatturecki;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

public abstract class NewsSource extends JSONSource {

    private ArrayList<NewsGroup> news_groups;

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
        public Date datetime;
        public boolean sticky;
    }

    public NewsSource() {
        super("https://www.powiat.turek.pl/ajax/news/");
        news_groups = new ArrayList<>();
    }
    @Override
    public void processJSON(String response) {
        if (response != null) {
            try {
                JSONArray json_news_groups = new JSONArray(response);
                for (int i = 0; i < json_news_groups.length(); i++) {
                    JSONObject json_news_group = json_news_groups.getJSONObject(i);
                    NewsGroup news_group = new NewsGroup();
                    news_group.id = json_news_group.getInt("id");
                    news_group.name = json_news_group.getString("name");
                    news_group.info = json_news_group.getString("info");
                    news_group.order = json_news_group.getInt("order");
                    news_group.news_items = new ArrayList<>();
                    JSONArray json_news_items = new JSONArray(json_news_group.getString("news_items"));
                    for (int j = 0; j < json_news_items.length(); j++) {
                        JSONObject json_news_item = json_news_items.getJSONObject(j);
                        NewsItem news_item = new NewsItem();
                        news_item.id = json_news_item.getInt("id");
                        news_item.title = json_news_item.getString("title");
                        news_item.page_id = json_news_item.getInt("page_id");
                        news_group.news_items.add(news_item);
                    }
                    news_groups.add(news_group);
                }
            } catch (Exception e) {
            }
            processResults(news_groups);
        }
    }
    public abstract void processResults(ArrayList<NewsGroup> news_groups);
}
