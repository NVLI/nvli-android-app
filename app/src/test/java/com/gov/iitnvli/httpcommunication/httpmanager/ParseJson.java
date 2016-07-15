package com.gov.iitnvli.httpcommunication.httpmanager;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ParseJson {
    private Activity activity;

    public ParseJson(Activity activity) {
        this.activity = activity;
    }

    public Object parseJsonData(String requestType, String response) {
        Log.e("Request Type: ", requestType);
        Log.e("Parsing Json: ", response);

        Gson gson = new GsonBuilder().create();

        //Parse your json response into the data model  //

        return null;
    }

    private boolean isValidResponseGeneral(Object responseObject) {
        if (responseObject == null) {
            Toast.makeText(activity, "Service currently unavailable. Please try again later", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
