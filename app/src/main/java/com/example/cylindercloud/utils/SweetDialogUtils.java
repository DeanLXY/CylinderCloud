package com.example.cylindercloud.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;

import com.example.cylindercloud.ui.IActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by wxj on 2015-9-20.
 */
public class SweetDialogUtils {
    public static final class AlertType {
        public static final int NORMAL_TYPE = 0;
        public static final int ERROR_TYPE = 1;
        public static final int SUCCESS_TYPE = 2;
        public static final int WARNING_TYPE = 3;
        public static final int CUSTOM_IMAGE_TYPE = 4;
        public static final int PROGRESS_TYPE = 5;
    }

    public static Dialog show(Context context, String... args) {
        if (args == null)
            return show(context, AlertType.NORMAL_TYPE);
        return show(context, AlertType.NORMAL_TYPE, args[0]);
    }

    public static Dialog show(Context context, int alertType, String... args) {
        if (context != null && context instanceof IActivity) {
            if (alertType == AlertType.PROGRESS_TYPE)
                return showProgressDialog(context, "正在加载");
            else if (alertType == AlertType.NORMAL_TYPE)
                return showNormalDialog(context, args[0]);
        }
        return null;
    }

    protected static Dialog showProgressDialog(Context context, String text) {
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(text);
        pDialog.setCancelable(false);
        pDialog.show();
        return pDialog;
    }

    protected static Dialog showNormalDialog(Context context, String content) {
        Dialog dialog = new SweetAlertDialog(context)
                .setContentText(content);
        dialog.show();
        return dialog;
    }

    protected static Dialog showListenerDialog(Context context, String title, String content, String cancleBtn, String confirmBtn) {
        //  Show the cancel button and bind listener to it：
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setCancelText(cancleBtn)
                .setConfirmText(confirmBtn)
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                });
        dialog.show();
        return dialog;
    }
}
