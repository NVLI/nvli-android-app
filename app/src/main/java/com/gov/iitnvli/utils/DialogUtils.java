package com.gov.iitnvli.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.gov.iitnvli.R;
import com.gov.iitnvli.callbacks.AlertCallback;


/**
 * Created by Murtuza on 12/23/15.
 */
public class DialogUtils {

    public static void showAlert(Context context, String message) {
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setWindowAnimations(R.style.DropVertical);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_alert_dialog);

        // set the custom dialog components - text, image and button

        TextView messageTV = (TextView) dialog.findViewById(R.id.custom_alert_single_message);
        messageTV.setText(message);

        Button okButton = (Button) dialog.findViewById(R.id.custom_alert_ok);
        // if button is clicked, close the custom dialogAlertCallback
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public static void ShowConfirmationDialog(String message, Activity activity, final AlertCallback alertCallback) {

        final Dialog dialog = new Dialog(activity);
        dialog.getWindow().setWindowAnimations(R.style.DropVertical);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_alert_ok_cancel);

        TextView messageTV = (TextView) dialog.findViewById(R.id.txt_ok_cancel_message);
        messageTV.setText(message);
        dialog.show();
        Button cancel = (Button) dialog.findViewById(R.id.btn_alert_cancel);

        // if decline button is clicked, close the custom dialog
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertCallback.getButtonClick(false, dialog);
            }
        });
        Button okbutton = (Button) dialog.findViewById(R.id.btn_alert_ok);
       // if ok button is clicked, close the custom dialog
        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertCallback.getButtonClick(true, dialog);
            }
        });
    }

}
