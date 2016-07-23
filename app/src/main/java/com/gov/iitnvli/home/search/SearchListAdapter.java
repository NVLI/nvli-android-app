package com.gov.iitnvli.home.search;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gov.iitnvli.R;
import com.gov.iitnvli.datamodel.SearchListModel;
import com.gov.iitnvli.home.LandingActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by Murtuza on 7/19/16.
 */
public class SearchListAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ROW = 0;
    private static final int TYPE_HEADER = 1;

    private ArrayList<SearchListModel> itemList = new ArrayList<>();
    private TreeSet<Integer> sectionHeader = new TreeSet<Integer>();

    private LayoutInflater inflater;
    private LandingActivity activity;

    public SearchListAdapter(LandingActivity activity) {
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(SearchListModel item) {
        itemList.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(SearchListModel item) {
        itemList.add(item);
        sectionHeader.add(itemList.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_HEADER : TYPE_ROW;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == TYPE_HEADER) {
            View headerRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_header, parent, false);
            viewHolder = new HeaderListItemHolder(headerRow);
        } else if (viewType == TYPE_ROW) {
            View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_row, parent, false);
            viewHolder = new RowListItemHolder(row);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderListItemHolder) {
            SearchListModel searchListModel = itemList.get(position);
            ((HeaderListItemHolder) holder).sectionHeader.setText(searchListModel.getHeader());
        } else if (holder instanceof RowListItemHolder) {
            SearchListModel searchListModel = itemList.get(position);
            ((RowListItemHolder) holder).title.setText(searchListModel.getTitle());
            ((RowListItemHolder) holder).description.setText(searchListModel.getDescription());
            if (searchListModel.getImageUrl() == null) {
                ((RowListItemHolder) holder).listIcon.setImageResource(searchListModel.getImageRes());
            } else {
                Picasso.with(activity).load(searchListModel.getImageUrl()).into(((RowListItemHolder) holder).listIcon);
            }

        }
    }

    public class HeaderListItemHolder extends RecyclerView.ViewHolder {

        public TextView sectionHeader;
        public TextView more;

        public HeaderListItemHolder(View itemView) {
            super(itemView);
            sectionHeader = (TextView) itemView.findViewById(R.id.sectionHeader);
            more = (TextView) itemView.findViewById(R.id.more);
            sectionHeader.setTypeface(Typeface.DEFAULT_BOLD);
            more.setTypeface(Typeface.DEFAULT_BOLD);
        }
    }

    public class RowListItemHolder extends RecyclerView.ViewHolder {

        public ImageView listIcon;
        public TextView title;
        public TextView description;

        public RowListItemHolder(View itemView) {
            super(itemView);
            listIcon = (ImageView) itemView.findViewById(R.id.listIcon);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
