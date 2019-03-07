package com.app.barber.net;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.Dialogs;
import com.google.gson.Gson;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Harish on 28/12/16.
 */

public class RestProcess<T> implements Callback<T>/*, OnAlertDialogItemListener*/ {

    private final Dialogs dialogs;
    ProgressDialog mProgressDialog;
    //    AlertDialogs dialogs;
    RestCallback callback;
    int serviceCode;
    Activity activity;
    boolean isDialogShow;
//    Util util;

    public RestProcess(int serviceCode, RestCallback restCallback, Activity activity, boolean isDialogShow) {
        this.callback = restCallback;
        this.activity = activity;
        this.serviceCode = serviceCode;
        this.isDialogShow = isDialogShow;
        dialogs = Dialogs.getInstance(activity);
        if (isDialogShow) {
            hideLoading();
            mProgressDialog = dialogs.showLoadingDialog(activity);
        }
    }

    public RestProcess(int serviceCode, RestCallback restCallback, Context activity, boolean isDialogShow) {
        this.callback = restCallback;
        this.serviceCode = serviceCode;
        this.isDialogShow = isDialogShow;
        dialogs = Dialogs.getInstance(activity);
        if (isDialogShow) {
            hideLoading();
            mProgressDialog = dialogs.showLoadingDialog(activity);
        }
    }

    public void hideLoading() {
        dialogs.DismissDialog(mProgressDialog);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        hideLoading();
        try {
            if (call != null) {
                if (response.code() == 500) {

                } else if (response.code() == 203) {
//                new com.app.barber.util.CommonUtils().LogoutUser();
                } else {
                    callback.onSuccess(call, response, serviceCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.e("", t.getMessage());

        hideLoading();
        if (t instanceof SocketTimeoutException) {
            Log.e("SocketTimeoutException ", t.getMessage());
        } else if (t instanceof UnknownHostException) {
            Log.e("UnknownHostException ", t.getMessage());
            new CommonUtils().ShowToast(BarberApplication.getInstance().getString(R.string.error_no_network));
        } else if (callback != null) {
            callback.onFailure(call, t, serviceCode);
        }


    }

    private void exit() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(startMain);
        activity.finish();
    }
}