package pl.turek.powiat.powiatturecki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PagePicturesAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<PageSource.Picture> pictures;
    private LayoutInflater inflater;

    public PagePicturesAdapter(Context context, ArrayList<PageSource.Picture> pictures) {
        super(context, R.layout.page_picture, pictures);
        this.context = context;
        this.pictures = pictures;
        inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView picture;
        if (convertView == null) {
             convertView = inflater.inflate(R.layout.page_picture, parent, false);

        }
        picture = (ImageView) convertView.findViewById(R.id.page_picture);
        Picasso.with(context).load(pictures.get(position).thumb_url).placeholder(R.drawable.ic_turek_county_arms).into(picture);
        return convertView;
    }

    @Override
    public int getCount() {
        return pictures.size();
    }
}