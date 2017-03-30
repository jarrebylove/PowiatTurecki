package pl.turek.powiat.powiatturecki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PagePicturesAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<PageSource.Picture> pictures;

    public PagePicturesAdapter(Context context, ArrayList<PageSource.Picture> pictures) {
        this.context = context;
        this.pictures = pictures;
    }

    @Override
    public PageSource.Picture getItem(int position) {
        return pictures.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    /*public View getView(int position, View convertView, ViewGroup parent) {
        ImageView picture;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.page_picture, null);
            picture  = (ImageView) convertView.findViewById(R.id.page_picture);
        } else {
            picture = (ImageView) convertView;
        }

        Picasso.with(context).load(getItem(position).thumb).into(picture);
        return picture;
    }*/
    public View getView(int position, View convertView, ViewGroup parent) {
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View gridView;
        ImageView picture;
        if (convertView == null) {
            //gridView = new View(context);
            //gridView = inflater.inflate(R.layout.page_picture, null);
            //ImageView picture = (ImageView) gridView.findViewById(R.id.page_picture);
            //Picasso.with(context).load(getItem(position).thumb).into(picture);
            picture = new ImageView(context);
        } else {
            //gridView = (View) convertView;
            picture = (ImageView) convertView;
        }
        Picasso.with(context).load(getItem(position).thumb).into(picture);
        return picture;
        //return gridView;
    }

    @Override
    public int getCount() {
        return pictures.size();
    }

    //@Override
    //public boolean hasStableIds() {
    //    return false;
    //}

}