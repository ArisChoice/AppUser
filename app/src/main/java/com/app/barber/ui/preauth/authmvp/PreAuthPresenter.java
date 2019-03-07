package com.app.barber.ui.preauth.authmvp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.app.barber.core.BarberApplication;
import com.app.barber.models.ChangePasswordRequest;
import com.app.barber.models.request.LoginRequestModel;
import com.app.barber.models.request.RegisterRequestModel;
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

public class PreAuthPresenter extends BasePresenter<PreAuthMVPView> implements RestCallback {
    @Inject
    @Named(DaggerValues.NON_AUTH)
    RestService appService;
    @Inject
    public PreferenceManager mPref;
    Context context;
    private SharedPreferences sharedpreferences;

    public PreAuthPresenter(Context context) {
        this.context = context;
        BarberApplication.getMyComponent(context).inject(this);
    }

    @Override
    public void attachView(PreAuthMVPView mvpView) {
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
                getMvpView().onSuccessResponse(serviceMode, model.body());
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
        saveUserData(baseResponse.getUser());
//        activitySwitcher(activity, HomeActivity.class, null);
        activity.finish();
    }

    private void saveUserData(LoginResponseModel.UserBean user) {
        mPref.setUserData(new Gson().toJson(user));
        mPref.setPrefrencesBoolean(GlobalValues.KEYS.isLogedIn, true);
        Log.e(" presenter ", "" + new Gson().toJson(user));
    }

    public void validatePhoneNUmber(int apiType, RegisterRequestModel validateRequest, boolean b) {
        Call<BaseResponse> call = appService.verifyNumber(validateRequest);
        call.enqueue(new RestProcess<BaseResponse>(apiType, this, context, b));
    }
    public String getDeviceIdRegString() {
        //GET THIS TOKEN FROM PREF
        sharedpreferences = context.getSharedPreferences(mypreferenceNew, Context.MODE_PRIVATE);
        String tokedId = sharedpreferences.getString("pushToken", "");
        Log.d("getDeviceIdRegString", "    " + tokedId);
        return tokedId;
    }
}
