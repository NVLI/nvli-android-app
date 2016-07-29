package com.gov.iitnvli.utils;

import android.util.Log;

import com.gov.iitnvli.R;
import com.gov.iitnvli.home.LandingActivity;

/**
 * Created by Murtuza on 7/23/16.
 */
public class Utility {

    public static int getImageResBasedOnType(String key, LandingActivity activity) {
        String[] typeKeys = activity.getResources().getStringArray(R.array.tabArry);
        if (typeKeys[0].equalsIgnoreCase(key)) {
            return R.drawable.book_square;
        } else if (typeKeys[1].equalsIgnoreCase(key)) {
            return R.drawable.thesis_square;
        } else if (typeKeys[2].equalsIgnoreCase(key)) {
            return R.drawable.musem_square;
        } else if (typeKeys[3].equalsIgnoreCase(key)) {
            return R.drawable.archieve_square;
        } else if (typeKeys[4].equalsIgnoreCase(key)) {
            return R.drawable.video_square;
        } else if (typeKeys[5].equalsIgnoreCase(key)) {
            return R.drawable.manuscrip_square;
        } else if (typeKeys[6].equalsIgnoreCase(key)) {
            return R.drawable.newspaper_square;
        } else if (typeKeys[7].equalsIgnoreCase(key)) {
            return R.drawable.map_sqare;
        } else {
            return R.drawable.other_square;
        }
    }

    public static String checkAndGetKey(String key, LandingActivity activity) {
        String[] allKeys = activity.getResources().getStringArray(R.array.tabArry);
        String[] allValues = activity.getResources().getStringArray(R.array.tabLabel);
        String finalKey ="";
        for (int i= 0; i< allKeys.length; i++){
            if (key.equalsIgnoreCase(allKeys[i])){
                finalKey = allValues[i];
                break;
            }
        }
        return finalKey;
    }
}
