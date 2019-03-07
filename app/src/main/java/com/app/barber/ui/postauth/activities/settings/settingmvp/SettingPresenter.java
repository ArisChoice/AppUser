package com.app.barber.ui.postauth.activities.settings.settingmvp;

import android.content.Context;
import android.util.Log;

import com.app.barber.core.BarberApplication;
import com.app.barber.models.response.LoginResponseModel;
import com.app.barber.models.response.MyImagesResponseModel;
import com.app.barber.models.response.NotificationResponseModel;
import com.app.barber.models.response.PaymentHistoryResponse;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.net.RestCallback;
import com.app.barber.net.RestProcess;
import com.app.barber.net.RestService;
import com.app.barber.util.PreferenceManager;
import com.app.barber.util.di.DaggerValues;
import com.app.barber.util.mvp.BasePresenter;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by harish on 26/10/18.
 */

public class SettingPresenter extends BasePresenter<SettingMVPView> implements RestCallback {
    @Inject
    @Named(DaggerValues.AUTH)
    RestService appService;
    @Inject
    public PreferenceManager mPref;
    Context context;

    public SettingPresenter(Context context) {
        this.context = context;
        BarberApplication.getMyComponent(context).inject(this);
    }

    @Override
    public void attachView(SettingMVPView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void onFailure(Call call, Throwable t, int serviceMode) {

    }

    @Override
    public void onSuccess(Call call, Response model, int serviceMode) {
        Log.e("on Success", " " + model.body());
        switch (serviceMode) {

            case NetworkConstatnts.RequestCode.API_NOTIFICATION_LIST:
                if (((NotificationResponseModel) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_GET_PAYMENT_HISTORY_IN:
                if (((PaymentHistoryResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_GET_PAYMENT_HISTORY_OUT:
                if (((PaymentHistoryResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;

        }

    }

    @Override
    public void onLogout() {

    }

    public void getAllNotifications(int apiNotificationList, Object o, boolean b) {
        Call<NotificationResponseModel> call = appService.getNotificationsList();
        call.enqueue(new RestProcess(apiNotificationList, this, context, b));
    }
    public void getPaymentHistory(int apiGetPaymentHistory, Object o, boolean b) {
        switch (apiGetPaymentHistory) {
            case NetworkConstatnts.RequestCode.API_GET_PAYMENT_HISTORY_IN:
                Call<PaymentHistoryResponse> call = appService.getPayments();
                call.enqueue(new RestProcess(apiGetPaymentHistory, this, context, b));
                break;
            case NetworkConstatnts.RequestCode.API_GET_PAYMENT_HISTORY_OUT:
                Call<PaymentHistoryResponse> call1 = appService.getPaymentsOut();
                call1.enqueue(new RestProcess(apiGetPaymentHistory, this, context, b));
                break;


        }
    }
}
