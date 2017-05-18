package pl.turek.powiat.powiatturecki;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class PageSource extends JSONSource implements Serializable {

    private Page page;

    public class Page implements Serializable {
        public int id;
        public String title;
        public String banner;
        public String body;
        public ArrayList<PictureCnt> pictures;
        public ArrayList<Movie> movies;
        public ArrayList<File> files;
        public ArrayList<Link> links;
    }

    public class Picture implements Serializable {
        public int id;
        public String url;
        public String thumb_url;
        public String name;
    }

    public class Movie implements Serializable {
        public int id;
        public String url;
        public String name;
    }

    public class File implements Serializable {
        public int id;
        public String url;
        public String name;
    }

    public class Link implements Serializable {
        public int id;
        public String url;
        public String name;
    }

    public PageSource(int page_id) {
        super(String.format("/ajax/page/%d/", page_id));
        page = new Page();
    }
    @Override
    public void processJSON(String response) {
        if (response != null) {
            try {
                JSONObject json_page = new JSONObject(response);
                page.id = json_page.getInt("id");
                page.title = json_page.getString("title");
                page.banner = MASTER_URL + json_page.getString("banner");
                page.body = json_page.getString("body");
                page.pictures = new ArrayList<>();
                JSONArray json_pictures = new JSONArray(json_page.getString("pictures"));
                for (int i = 0; i < json_pictures.length(); i++) {
                    JSONObject json_picture = json_pictures.getJSONObject(i);
                    PictureCnt picture = new PictureCnt();
                    picture.id = json_picture.getInt("id");
                    picture.url = MASTER_URL + json_picture.getString("url");
                    picture.thumb_url = MASTER_URL + json_picture.getString("thumb_url");
                    picture.name = json_picture.getString("name");
                    page.pictures.add(picture);
                }
                page.movies = new ArrayList<>();
                JSONArray json_movies = new JSONArray(json_page.getString("movies"));
                for (int i = 0; i < json_movies.length(); i++) {
                    JSONObject json_movie = json_movies.getJSONObject(i);
                    Movie movie = new Movie();
                    movie.id = json_movie.getInt("id");
                    movie.url = MASTER_URL + json_movie.getString("url");
                    movie.name = json_movie.getString("name");
                    page.movies.add(movie);
                }
                page.files = new ArrayList<>();
                JSONArray json_files = new JSONArray(json_page.getString("files"));
                for (int i = 0; i < json_files.length(); i++) {
                    JSONObject json_file = json_files.getJSONObject(i);
                    File file = new File();
                    file.id = json_file.getInt("id");
                    file.url = MASTER_URL + json_file.getString("url");
                    file.name = json_file.getString("name");
                    page.files.add(file);
                }
                page.links = new ArrayList<>();
                JSONArray json_links = new JSONArray(json_page.getString("links"));
                for (int i = 0; i < json_links.length(); i++) {
                    JSONObject json_link = json_links.getJSONObject(i);
                    Link link = new Link();
                    link.id = json_link.getInt("id");
                    link.url = MASTER_URL + json_link.getString("url");
                    link.name = json_link.getString("name");
                    page.links.add(link);
                }
            } catch (Exception e) {
            Log.e("E", e.getMessage());
            }
            processResults(page);
        }
    }
    public abstract void processResults(Page page);
}
