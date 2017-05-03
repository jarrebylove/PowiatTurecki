package pl.turek.powiat.powiatturecki;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        return 2;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition == 0) {
            return 0;
        } else if (groupPosition == 1) {
            return page.pictures.size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        if (groupPosition == 0) {
            return null;
        } else if (groupPosition == 1) {
            return "Zdjęcia";
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
        if (groupPosition == 0) {
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.page_body, null);
            }
            TextView title = (TextView) convertView.findViewById(R.id.page_title);
            title.setText(page.title);
            ImageView banner = (ImageView) convertView.findViewById(R.id.page_banner);
            Picasso.with(activity).load(page.banner).into(banner);
            TextView body = (TextView) convertView.findViewById(R.id.page_body);
            body.setText(Html.fromHtml(page.body));
            return convertView;
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (groupPosition == 0) {
            return null;
        } else if (groupPosition == 1) {
            ImageView picture;
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.page_picture, null);
            }
            picture = (ImageView) convertView.findViewById(R.id.page_picture);
            Picasso.with(context).load(page.pictures.get(childPosition).thumb).placeholder(R.drawable.ic_turek_county_arms).into(picture);
            return convertView;
        }
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
