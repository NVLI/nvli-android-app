package com.gov.iitnvli.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gov.iitnvli.R;
import com.gov.iitnvli.httpcommunication.datamodel.ListItemModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Murtuza on 7/15/16.
 */
public class ListRecyclerViewAdapter extends RecyclerView.Adapter {

    private List<ListItemModel> itemList;
    private LandingActivity activity;
    private int TYPE_HEADER = 0;
    private int TYPE_CELL = 1;

    public ListRecyclerViewAdapter(List<ListItemModel> itemList, LandingActivity activity) {
        this.itemList = itemList;
        this.activity = activity;

    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == TYPE_HEADER) {
            View bigListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card_big, parent, false);
            viewHolder = new BigListItemHolder(bigListView);
        } else if (viewType == TYPE_CELL) {
            View samllListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card_small, parent, false);
            viewHolder = new SmallListItemHolder(samllListView);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BigListItemHolder) {
        } else if (holder instanceof SmallListItemHolder) {
        }
    }

    public class BigListItemHolder extends RecyclerView.ViewHolder {

        public BigListItemHolder(View itemView) {
            super(itemView);

        }
    }

    public class SmallListItemHolder extends RecyclerView.ViewHolder {

        public SmallListItemHolder(View itemView) {
            super(itemView);

        }
    }
}
