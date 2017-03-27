package pl.turek.powiat.powiatturecki;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class ImageFromURL extends AsyncTask<String, Void, Bitmap> {
    ImageView image_view;

    public ImageFromURL(ImageView image_view) {
        this.image_view = image_view;
    }

    protected Bitmap doInBackground(String... urls) {
        Bitmap bitmap = null;
        try {
            InputStream in = new java.net.URL(urls[0]).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("E", e.getMessage());
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap result) {
        image_view.setImageBitmap(result);
    }
}