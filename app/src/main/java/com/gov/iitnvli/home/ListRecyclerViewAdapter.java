package com.gov.iitnvli.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gov.iitnvli.R;
import com.gov.iitnvli.datamodel.DetailsDataModel;
import com.gov.iitnvli.global.ActivityConstant;
import com.gov.iitnvli.datamodel.ListItemModel;
import com.gov.iitnvli.httpcommunication.httpmanager.HttpRequestManager;
import com.gov.iitnvli.httpcommunication.httpmanager.RequestType;
import com.gov.iitnvli.httpcommunication.httpmanager.ResponseHandler;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Murtuza on 7/15/16.
 */
public class ListRecyclerViewAdapter extends RecyclerView.Adapter implements ResponseHandler{

    private List<ListItemModel> itemList;
    private LandingActivity activity;
    private int fragType;
    private int TYPE_HEADER = 0;
    private int TYPE_CELL = 1;
    private HttpRequestManager httpRequestManager;

    public ListRecyclerViewAdapter(List<ListItemModel> itemList, LandingActivity activity, int fragType) {
        this.itemList = itemList;
        this.activity = activity;
        this.fragType = fragType;
        httpRequestManager = new HttpRequestManager(activity, this);
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
            View smallListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card_small, parent, false);
            viewHolder = new SmallListItemHolder(smallListView);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BigListItemHolder) {
            ListItemModel listItemModel = itemList.get(position);
            ((BigListItemHolder) holder).title.setText(listItemModel.getTitle());
            Picasso.with(activity).load(listItemModel.getHeaderImage()).into(((BigListItemHolder) holder).image);
            ((BigListItemHolder) holder).particularItem = listItemModel;
        } else if (holder instanceof SmallListItemHolder) {
            ListItemModel listItemModel = itemList.get(position);
            ((SmallListItemHolder) holder).title.setText(listItemModel.getTitle());
            ((SmallListItemHolder) holder).description.setText(listItemModel.getDescription());
            Picasso.with(activity).load(listItemModel.getImage()).into(((SmallListItemHolder) holder).image);
            ((SmallListItemHolder) holder).particularItem = listItemModel;
        }
    }

    public class BigListItemHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageView image;
        public ListItemModel particularItem;

        public BigListItemHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            image = (ImageView) itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callDetailWS(particularItem.getEntityId());
                }
            });
        }
    }

    public class SmallListItemHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView description;
        public ImageView image;
        public ListItemModel particularItem;

        public SmallListItemHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            image = (ImageView) itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callDetailWS(particularItem.getEntityId());
                }
            });
        }
    }

    private void openScr(DetailsDataModel detailsDataModel) {
        if (fragType == ActivityConstant.BOOK_DETAIL_FRAGMENT){
            openBookDetail(detailsDataModel);
        }
        else  if (fragType == ActivityConstant.THESISK_DETAIL_FRAGMENT){
            openThesisDetail(detailsDataModel);
        }
        else  if (fragType == ActivityConstant.GENERAL_DETAIL_FRAGMENT){
            openGeneralDetail(detailsDataModel);
        }
    }

    private void openGeneralDetail(DetailsDataModel detailsDataModel) {
        activity.navigateTo(ActivityConstant.GENERAL_DETAIL_FRAGMENT,detailsDataModel, true, null);
    }

    private void callDetailWS(String entityID) {
        httpRequestManager.getDetails(entityID);
    }

    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {
        if (responseObject == null){
            return;
        }

        if (responseType.equals(RequestType.GET_DETAILS)){
            openScr((DetailsDataModel) responseObject);
        }

    }

    private void openBookDetail(DetailsDataModel detailsDataModel) {
        activity.navigateTo(ActivityConstant.BOOK_DETAIL_FRAGMENT,detailsDataModel, true, null);
    }

    private void openThesisDetail(DetailsDataModel detailsDataModel) {
        activity.navigateTo(ActivityConstant.THESISK_DETAIL_FRAGMENT,detailsDataModel, true, null);
    }
}
