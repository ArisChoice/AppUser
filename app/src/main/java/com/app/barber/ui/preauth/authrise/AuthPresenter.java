package com.app.barber.ui.preauth.authrise;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.app.barber.core.BarberApplication;
import com.app.barber.models.ChangePasswordRequest;
import com.app.barber.models.request.LoginRequestModel;
import com.app.barber.models.request.RegisterRequestModel;
import com.app.barber.models.request.ValidatePhoneNumberModel;
import com.app.barber.models.response.BaseResponse;
import com.app.barber.models.response.LoginResponseModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.net.RestCallback;
import com.app.barber.net.RestProcess;
import com.app.barber.net.RestService;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.PreferenceManager;
import com.app.barber.util.di.DaggerValues;
import com.app.barber.util.mvp.BasePresenter;
import com.google.gson.Gson;


import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Response;

import static com.app.barber.net.firebase.MyFirebaseInstanceIDService.mypreferenceNew;

/**
 * Created by harish on 26/10/18.
 */

public class AuthPresenter extends BasePresenter<AuthMVPView> implements RestCallback {
    @Inject
    @Named(DaggerValues.NON_AUTH)
    RestService appService;
    @Inject
    public PreferenceManager mPref;
    Context context;
    private SharedPreferences sharedpreferences;

    public AuthPresenter(Context context) {
        this.context = context;
        BarberApplication.getMyComponent(context).inject(this);
    }

    @Override
    public void attachView(AuthMVPView mvpView) {
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
        Log.e("on Success", " " + new Gson().toJson(model));
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_REGISTER:
                getMvpView().onSuccessResponse(serviceMode, model.body());
                break;
            case NetworkConstatnts.RequestCode.API_LOGIN:
                getMvpView().onSuccessResponse(serviceMode, model.body());
                break;
            case NetworkConstatnts.RequestCode.API_VALIDATE_NUMBER:
                if (((BaseResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                } else {
                    getMvpView().onfaliurResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_FORGET_PASS:
                getMvpView().onSuccessResponse(serviceMode, model.body());
                break;
        }

    }

    @Override
    public void onLogout() {

    }

    public void signInRequest(int apiLogin, LoginRequestModel requestData, boolean b) {
        Call<LoginResponseModel> call = appService.loginUser(requestData);
        call.enqueue(new RestProcess<LoginResponseModel>(apiLogin, this, context, b));
    }

    public void signUpRequest(int apiType, RegisterRequestModel registerRequest, boolean b) {
        Call<BaseResponse> call = appService.registerUser(registerRequest);
        call.enqueue(new RestProcess<BaseResponse>(apiType, this, context, b));
    }

    public void forgotPassword(int apiType, ChangePasswordRequest registerRequest, boolean b) {
        Call<BaseResponse> call = appService.forgotPass(registerRequest);
        call.enqueue(new RestProcess<BaseResponse>(apiType, this, context, b));
    }

    public void navigateUser(Activity activity, LoginResponseModel baseResponse) {
//        activitySwitcher(activity, HomeActivity.class, null);
        saveUserData(baseResponse.getUser());
        activity.finish();
    }

    private void saveUserData(LoginResponseModel.UserBean user) {
        mPref.setUserData(new Gson().toJson(user));
        mPref.setPrefrencesBoolean(GlobalValues.KEYS.isLogedIn, true);
        Log.e(" presenter ", "" + new Gson().toJson(user));
    }

    public void validatePhoneNUmber(int apiType, ValidatePhoneNumberModel validateRequest, boolean b) {
        Call<BaseResponse> call = appService.validateNumber(validateRequest);
        call.enqueue(new RestProcess<BaseResponse>(apiType, this, context, b));
    }

    public String getDeviceIdRegString() {
        //GET THIS TOKEN FROM PREF
        sharedpreferences = context.getSharedPreferences(mypreferenceNew, Context.MODE_PRIVATE);
        String tokedId = sharedpreferences.getString("pushToken", "");
        Log.d("getDeviceIdRegString", "    " + tokedId);
        return tokedId;
    }


   /* public void loginToQb(FragmentActivity loginActivity,
                          LoginResponseModel.UserBean usr) {
        if (usr.getQBId() != null) {
            Performer<QBUser> ussr = QBUsers.getUser(Integer.parseInt(usr.getQBId()));
            ussr.performAsync(new QBEntityCallback<QBUser>() {
                @Override
                public void onSuccess(QBUser qbUser, Bundle bundle) {
                    final QBUser user = qbUser;
                    user.setPassword(NetworkConstatnts.QB.GLOBAL_PASSWORD);
                    QBUsers.signIn(user).performAsync(new QBEntityCallback<QBUser>() {
                        @Override
                        public void onSuccess(QBUser qbUser, Bundle bundle) {
//                  callback.onSuccess(qbUser, bundle);
                            Log.d(" loginToQb ", "  onSuccess  " + qbUser.getId());

                            qbUser.setPassword(NetworkConstatnts.QB.GLOBAL_PASSWORD);
                            Log.d(" loginToQb ", "  onSuccess  " + qbUser.getPassword());
                            SubscribeToNotification.getInstance().setSubscribeToNotification(context);
                            PreferenceManager.getInstance(context).saveQbUser(qbUser);

                        }

                        @Override
                        public void onError(QBResponseException e) {
                            Log.d(" loginToQb ", "  onError " + e.getLocalizedMessage());
                        }
                    });
                }

                @Override
                public void onError(QBResponseException e) {
                    Log.d(" loginToQb ", "  onError " + e.getLocalizedMessage());
                }
            });
        } //else new CommonUtils().ShowToast("Quickblox user not registered");

    }*/
}
