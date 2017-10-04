package pl.turek.powiat.powiatturecki;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


interface JSONSource1Listener {
    void response(String response);
}

public class JSONSource1 extends AsyncTask<Void, Void, Void> {
    protected String MASTER_URL = "https://www.powiat.turek.pl";
    private String url;
    private HTTP2String http2string;
    private String response;

    private List<JSONSource1Listener> listeners = new ArrayList<JSONSource1Listener>();

    public void addListener(JSONSource1Listener toAdd) {
        listeners.add(toAdd);
    }
    private class HTTP2String {
        private URL url;

        private String stream2string(InputStream is) {
            Log.e("stream2string", "start...");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            } catch (Exception e) {
                Log.e("stream2string", e.getMessage());
            }
            finally {
                try {
                    is.close();
                } catch (Exception e) {
                    Log.e("stream2string_2", e.getMessage());
                }
            }
            Log.e("stream2string", "end...");
            return sb.toString();
        }

        public HTTP2String(String url) {
            Log.e("http2string", "start...");
            try {
                this.url = new URL(url);
            } catch (Exception e) {
                Log.e("http2string", e.getMessage());

            }
            Log.e("http2string", "end...");
        }

        public String get() {
            Log.e("get", "start...");
            String response = null;
            int statusCode = 0;
            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                statusCode = connection.getResponseCode();
                InputStream in = new BufferedInputStream(connection.getInputStream());
                response = stream2string(in);
            } catch (Exception e) {
                Log.e("get-status", ""+statusCode);
                Log.e("get-exc", e.getMessage());
                Log.e("get-exc", e.toString());
            }
            Log.e("get", "end...");
            return response;
        }
    }

    public JSONSource1(String url) {

        this.url = MASTER_URL + url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        http2string = new HTTP2String(url);
        response = http2string.get();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        //processJSON(response);
        for (JSONSource1Listener hl : listeners)
            hl.response(response);
    }

    //public abstract void processJSON(String response);
}