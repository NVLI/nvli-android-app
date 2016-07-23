package com.gov.iitnvli.httpcommunication.httpmanager;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gov.iitnvli.R;
import com.gov.iitnvli.datamodel.DashboardDataModel;
import com.gov.iitnvli.datamodel.DetailsDataModel;
import com.gov.iitnvli.datamodel.ResponseState;
import com.gov.iitnvli.global.AppConstants;
import com.gov.iitnvli.utils.DialogUtils;


public class ParseJson {
    private Activity activity;

    public ParseJson(Activity activity) {
        this.activity = activity;
    }

    public Object parseJsonData(String requestType, String response) {
        Log.e("Request Type: ", requestType);
        Log.e("Parsing Json: ", response);

        Gson gson = new GsonBuilder().create();

        if (requestType.equals(RequestType.GET_DASHBOARD_LIST) ) {
            DashboardDataModel dashboardDataModel = gson.fromJson(response, DashboardDataModel.class);
            boolean isValid = isValidResponse(dashboardDataModel);
            return (isValid) ? dashboardDataModel : null;
        }

        if (requestType.equals(RequestType.GET_DETAILS) ) {
            DetailsDataModel detailsDataModel = gson.fromJson(response, DetailsDataModel.class);
            boolean isValid = isValidResponse(detailsDataModel);
            return (isValid) ? detailsDataModel : null;
        }

        return null;
    }

    private boolean isValidResponse(ResponseState responseState) {
        if (responseState == null) {
            DialogUtils.showAlert(activity, activity.getResources().getString(R.string.trylater));
            return false;
        }

        if (!responseState.isSuccess()) {
            String message = responseState.getMessage();
            DialogUtils.showAlert(activity, message);
            return false;
        }
        return true;
    }

    private boolean isValidResponseGeneral(Object responseObject) {
        if (responseObject == null) {
            DialogUtils.showAlert(activity, activity.getResources().getString(R.string.trylater));
            return false;
        }
        return true;
    }
}
