package pl.turek.powiat.powiatturecki;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public abstract class JSONSource extends AsyncTask<Void, Void, Void> {
    private String url;
    private HTTP2String http2string;
    private String response;

    private class HTTP2String {
        private URL url;

        private String stream2string(InputStream is) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            } catch (Exception e) {}
            finally {
                try {
                    is.close();
                } catch (Exception e) {}
            }
            return sb.toString();
        }

        public HTTP2String(String url_) {
            try {
                url = new URL(url_);
            } catch (Exception e) {}
        }

        public String get() {
            String response = null;
            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream in = new BufferedInputStream(connection.getInputStream());
                response = stream2string(in);
            } catch (Exception e) {}
            return response;
        }
    }

    public JSONSource(String url_) {
        url = url_;
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
        processJSON(response);
    }

    public abstract void processJSON(String response);
}