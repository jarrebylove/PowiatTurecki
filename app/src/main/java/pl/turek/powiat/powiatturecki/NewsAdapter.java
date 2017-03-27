package pl.turek.powiat.powiatturecki;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<NewsSource.NewsGroup> news_groups;
    private SimpleDateFormat date_format;

    public NewsAdapter(Context context_, ArrayList<NewsSource.NewsGroup> news_groups_) {
        context = context_;
        news_groups = news_groups_;
        date_format = new SimpleDateFormat("yyyy.MM,dd");
    }

    @Override
    public NewsSource.NewsItem getChild(int groupPosition, int childPosititon) {
        return news_groups.get(groupPosition).news_items.get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
      if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.news_item, null);
        }
        Picasso.with(context).load(getChild(groupPosition, childPosition).image).into((ImageView) convertView.findViewById(R.id.news_image));
        //ImageFromURL image_from_url = new ImageFromURL((ImageView) convertView.findViewById(R.id.news_image));
        //image_from_url.execute(getChild(groupPosition, childPosition).image);
        TextView title_text_view = (TextView) convertView.findViewById(R.id.news_item_title);
        title_text_view.setText(getChild(groupPosition, childPosition).title);
        TextView date_text_view = (TextView) convertView.findViewById(R.id.news_date);
        date_text_view.setText(date_format.format(getChild(groupPosition, childPosition).datetime));
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return news_groups.get(groupPosition).news_items.size();
    }

    @Override
    public NewsSource.NewsGroup getGroup(int groupPosition) {
        return news_groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
            return news_groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
         if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.news_group, null);
        }
        TextView name_text_view = (TextView) convertView.findViewById(R.id.news_group_name);
        name_text_view.setText(getGroup(groupPosition).name);
        ExpandableListView expandable_list_view = (ExpandableListView) parent;
        expandable_list_view.expandGroup(groupPosition);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}