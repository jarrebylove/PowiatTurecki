package pl.turek.powiat.powiatturecki;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class GalleryAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<PictureCnt> pictures;

    public GalleryAdapter(Context context, ArrayList<PictureCnt> pictures) {
        super();
        this.context = context;
        this.pictures = pictures;
    }
    @Override
    public int getCount() {
        return pictures.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.picture, container, false);
        PhotoView photo_view = (PhotoView) layout.findViewById(R.id.picture);
        Picasso.with(context).load(pictures.get(position).url).placeholder(R.drawable.ic_turek_county_arms).into(photo_view);
        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}