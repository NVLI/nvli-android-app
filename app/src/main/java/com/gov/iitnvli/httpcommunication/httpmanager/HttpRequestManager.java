package com.gov.iitnvli.httpcommunication.httpmanager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gov.iitnvli.R;
import com.gov.iitnvli.global.AppConstants;
import com.gov.iitnvli.utils.DialogUtils;

import org.json.JSONObject;

import java.util.HashMap;


/**
 * Created by Murtuza
 */
public class HttpRequestManager implements Response.ErrorListener {

    private String requestType = "";
    private Activity activity;
    private RequestQueue requestQueue;
    private ResponseHandler responseHandler;
    private ProgressDialog progressdialog;

    private JListener jListener;
    private ParseJson parseJson;

    private ConnectionDetector cDetector;

    private static final int SOCKET_TIMEOUT = 60000;

    public HttpRequestManager(Activity activity, ResponseHandler responseHandler) {
        this.activity = activity;
        this.responseHandler = responseHandler;

        parseJson = new ParseJson(activity);


        requestQueue = Volley.newRequestQueue(activity);

        cDetector = new ConnectionDetector(activity);

        progressdialog = new ProgressDialog(activity);
        progressdialog.setMessage("Loading");
        progressdialog.setCancelable(false);
        progressdialog.setIndeterminate(true);
    }

   public void getDashboardList(String offset, String limit) {
       requestType = RequestType.GET_DASHBOARD_LIST;
       String urlRequest = HttpConstants.getDashBoardList;
       urlRequest += "_format=" + AppConstants.FORMAT;
       urlRequest += "&offset=" + offset;
       urlRequest += "&limit=" + limit;
       makeJsonRequest(urlRequest, null, Request.Method.GET);
   }

    public void getDetails(String entityId) {
        requestType = RequestType.GET_DETAILS;
        String urlRequest = HttpConstants.getDetails;
        urlRequest += entityId + "?";
        urlRequest += "_format=" + AppConstants.FORMAT;
        makeJsonRequest(urlRequest, null, Request.Method.GET);
    }

    private void makeJsonRequest(String url, HashMap<String, String> params, int    methodType) {
        Log.e("Request: ", url);

        if (!isInternetAvailable()) {
            Toast.makeText(activity, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            return;
        }
        jListener = (jListener != null) ? jListener : new JListener();
        progressdialog.show();

        //GET request//
        JSONObject postData = null;
        if (params != null) {
            Log.e("Post request: ", "");
            //POST request//
            postData = new JSONObject(params);
        }

        JsonObjectRequest jReq = new JsonObjectRequest(methodType, url, postData, jListener, this);

        RetryPolicy policy = new DefaultRetryPolicy(SOCKET_TIMEOUT, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jReq.setRetryPolicy(policy);
        requestQueue.add(jReq);
    }


    public void onErrorResponse(VolleyError vError) {
        Log.e("Error Response", "............");
        progressdialog.dismiss();
        String msg = (cDetector.isConnectingToInternet()) ? "Service currently unavailable. Please try again later" : "Please check your internet connection";
        DialogUtils.showAlert(activity, msg);
        cleanRequest();
    }

    private void cleanRequest() {
        progressdialog.dismiss();
        requestType = "";
    }

    private boolean isInternetAvailable() {
        ConnectionDetector cd = new ConnectionDetector(activity.getApplicationContext());
        return cd.isConnectingToInternet();
    }

    private class JListener implements Response.Listener<JSONObject> {
        public void onResponse(JSONObject response) {
            try {
                progressdialog.dismiss();
                Object obj = parseJson.parseJsonData(requestType, response.toString());
                responseHandler.onSuccessResponse(obj, requestType);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
