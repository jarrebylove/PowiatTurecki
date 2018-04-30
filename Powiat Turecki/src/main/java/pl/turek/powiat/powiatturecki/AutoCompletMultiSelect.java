package pl.turek.powiat.powiatturecki;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jarre on 28.04.18.
 */

public class AutoCompletMultiSelect extends LinearLayout {
    class SelectedItemsAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public SelectedItemsAdapter(Context context, String[] values) {
            super(context, -1, values);
            this.context = context;
            this.values = values;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View item_view = inflater.inflate(R.layout.auto_complet_multi_select_item, parent, false);
            TextView value_txt = (TextView) item_view.findViewById(R.id.item_value);
            value_txt.setText(values[position]);
            return item_view;
        }
    }

    private int item_add_button_add_icon = android.R.drawable.ic_input_add;
    private int item_add_button_close_icon = android.R.drawable.ic_menu_close_clear_cancel;
    private String title;
    private String[] items;
    ArrayAdapter<String> items_adapter;
    private TextView title_txt;
    private TextView count_txt;
    private FloatingActionButton item_add_btn;
    private AutoCompleteTextView item_add_txt;
    private String[] selected_items;
    private SelectedItemsAdapter selected_items_adapter;
    private ListView items_lst;

    public AutoCompletMultiSelect(Context context) {
        super(context);
        init();
    }
    public AutoCompletMultiSelect(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoCompletMultiSelect(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setTitle(String title) {
        this.title = title;
        title_txt.setText(title);
    }
    public void setItems(String[] items) {
        this.items = items;
        items_adapter = new ArrayAdapter<String> (this.getContext(), android.R.layout.select_dialog_item, items);
        item_add_txt.setAdapter(items_adapter);
    }
    private void item_add_txt_show() {
        item_add_txt.setVisibility(VISIBLE);
        item_add_txt.requestFocus();
        item_add_btn.setImageResource(item_add_button_close_icon);
    }
    private void  item_add_txt_hide() {
        item_add_txt.setVisibility(GONE);
        item_add_txt.setText("");
        item_add_btn.setImageResource(item_add_button_add_icon);
    }
    private void init() {
        inflate(getContext(), R.layout.auto_complet_multi_select, this);
        this.title_txt = (TextView)findViewById(R.id.title_txt);
        this.count_txt = (TextView)findViewById(R.id.count_txt);
        this.item_add_btn = (FloatingActionButton) findViewById(R.id.item_add_bnt);
        this.item_add_txt = (AutoCompleteTextView) findViewById(R.id.item_add_txt);
        this.items_lst = (ListView) findViewById(R.id.items_lst);
        item_add_txt.setVisibility(GONE);
        item_add_txt.setThreshold(1);
        item_add_txt.setAdapter(items_adapter);
        item_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item_add_txt.getVisibility() == VISIBLE) {
                    item_add_txt_hide();
                } else {
                    item_add_txt_show();
                }
            }
        });
        item_add_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item_add_txt_hide();
                String selected = (String) parent.getItemAtPosition(position);
                //ArrayUtils.add(selected_items, selected);
                selected_items_adapter.notifyDataSetChanged();
            }
        });
        //selected_items = new ArrayList<String>();
        selected_items_adapter=new SelectedItemsAdapter(getContext(), selected_items);
        items_lst.setAdapter(selected_items_adapter);
    }
}
