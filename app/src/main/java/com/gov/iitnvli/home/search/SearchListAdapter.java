package com.gov.iitnvli.home.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gov.iitnvli.R;
import com.gov.iitnvli.datamodel.SearchDataModel;
import com.gov.iitnvli.home.LandingActivity;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by Murtuza on 7/19/16.
 */
public class SearchListAdapter extends BaseAdapter {

    private static final int TYPE_ROW = 0;
    private static final int TYPE_HEADER = 1;

    private ArrayList<SearchDataModel> itemList = new ArrayList<>();
    private TreeSet<Integer> sectionHeader = new TreeSet<Integer>();

    private LayoutInflater inflater;

    public SearchListAdapter(LandingActivity activity) {
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(SearchDataModel item) {
        itemList.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(SearchDataModel item) {
        itemList.add(item);
        sectionHeader.add(itemList.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_HEADER : TYPE_ROW;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public String getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView sectionHeader;
        public TextView more;
        public ImageView listIcon;
        public TextView title;
        public TextView description;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int rowType = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();
            switch (rowType) {
                case TYPE_ROW:
                    convertView = inflater.inflate(R.layout.search_row, null);
                    holder.sectionHeader = (TextView) convertView.findViewById(R.id.sectionHeader);
                    holder.more = (TextView) convertView.findViewById(R.id.more);
                    break;
                case TYPE_HEADER:
                    convertView = inflater.inflate(R.layout.search_header, null);
                    holder.listIcon = (ImageView) convertView.findViewById(R.id.listIcon);
                    holder.title = (TextView) convertView.findViewById(R.id.title);
                    holder.description = (TextView) convertView.findViewById(R.id.description);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        switch (rowType) {
//            case TYPE_ROW:
//                holder.sectionHeader.setText(itemList.get(position).getHeader());
//                break;
//            case TYPE_HEADER:
//                holder.title.setText(itemList.get(position).getTitle());
//                holder.description.setText(itemList.get(position).getDescription());
//                break;
//        }


        return convertView;
    }
}
