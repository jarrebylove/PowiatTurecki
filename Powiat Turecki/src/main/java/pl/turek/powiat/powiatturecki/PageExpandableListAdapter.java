package pl.turek.powiat.powiatturecki;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

/**
 * Created by jarre on 01.05.17.
 */

public class PageExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity activity;
    private final Context context;
    private PageSource.Page page;

    public PageExpandableListAdapter(Activity activity, Context context, PageSource.Page page){
        super();
        this.activity = activity;
        this.context = context;
        this.page = page;
    }

    @Override
    public int getGroupCount() {
        return 3;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition == 0) {
            return 0;
        } else if (groupPosition == 1) {
            return page.pictures.size();
        } else if (groupPosition == 2) {
            return page.movies.size();
        } else if (groupPosition == 3) {
            return page.files.size();
        } else if (groupPosition == 4) {
            return page.links.size();
        }
        return 0;
    }

    @Override
    public String getGroup(int groupPosition) {
        if (groupPosition == 0) {
            return null;
        } else if (groupPosition == 1) {
            return String.format("Zdjęcia (%d)", getChildrenCount(groupPosition));
        } else if (groupPosition == 2) {
            return String.format("Filmy (%d)", getChildrenCount(groupPosition));
        } else if (groupPosition == 3) {
            return String.format("Pliki (%d)", getChildrenCount(groupPosition));
        } else if (groupPosition == 4) {
            return String.format("Linki (%d)", getChildrenCount(groupPosition));
        }
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Log.i("getGroupView", "" + groupPosition);
        if (groupPosition == 0) {
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.page_body, null);
            }
            TextView title = (TextView) convertView.findViewById(R.id.page_title);
            title.setText(page.title);
            ImageView banner = (ImageView) convertView.findViewById(R.id.page_banner);
            Picasso.with(context).load(page.banner).into(banner);
            TextView body = (TextView) convertView.findViewById(R.id.page_body);
            body.setText(Html.fromHtml(page.body));
            return convertView;
        } else {
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.page_group, null);
            }
            TextView title = (TextView) convertView.findViewById(R.id.title);
            title.setText(getGroup(groupPosition));
            if (getChildrenCount(groupPosition) == 0)
                convertView.setVisibility(View.GONE);
            else
                convertView.setVisibility(View.VISIBLE);
            return convertView;
        }
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (groupPosition == 0) {
            return null;
        } else if (groupPosition == 1) {
            ImageView picture;
            //if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.page_picture, null);
            //}
            picture = (ImageView) convertView.findViewById(R.id.page_picture);
            Picasso.with(context).load(page.pictures.get(childPosition).thumb_url).placeholder(R.drawable.ic_turek_county_arms).into(picture);
            picture.setOnClickListener(new ImageView.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent picture_intent = new Intent(activity, PictureActivity.class);
                    //picture_intent.putExtra("url", page.pictures.get(childPosition).url);
                    //activity.startActivity(picture_intent);
                    Bundle extra = new Bundle();
                    extra.putSerializable("pictures", page.pictures);
                    Intent gallery_intent = new Intent(activity, GalleryActivity.class);
                    gallery_intent.putExtra("pictures", extra);
                    gallery_intent.putExtra("position", childPosition);
                    activity.startActivity(gallery_intent);
                }
            });
            return convertView;
        } else if (groupPosition == 2) {
            VideoView video;
            //if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.page_movie, null);
            //}
            video = (VideoView) convertView.findViewById(R.id.page_video);
            MediaController mc = new MediaController(context);
            mc.setAnchorView(video);
            mc.setMediaPlayer(video);
            video.setMediaController(mc);
            video.setVideoURI(Uri.parse(page.movies.get(childPosition).url));
            video.start();
            return convertView;
        }
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
