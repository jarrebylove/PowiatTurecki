package pl.turek.powiat.powiatturecki;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jarre on 04.10.17.
 */
interface JSONSourceListener {
    void response(String response);
}
public class JSONSource {
    protected String MASTER_URL = "https://www.powiat.turek.pl";
    private String url;
    private List<JSONSourceListener> listeners = new ArrayList<JSONSourceListener>();

    public void addListener(JSONSourceListener toAdd) {
        listeners.add(toAdd);
    }

    public JSONSource(String url) {
        this.url = MASTER_URL + url;

    }

    public void execute() {
        AndroidNetworking.get(this.url)
                //.addPathParameter("pageNumber", "0")
                //.addQueryParameter("limit", "3")
                .setPriority(Priority.LOW)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        for (JSONSourceListener hl : listeners)
                            hl.response(response);
                    }
                    @Override
                    public void onError(ANError error) {
                        for (JSONSourceListener hl : listeners)
                            hl.response(null);
                    }
                });
    }
}
