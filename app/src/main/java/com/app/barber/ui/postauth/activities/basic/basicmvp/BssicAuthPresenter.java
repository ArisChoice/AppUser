package com.app.barber.ui.postauth.activities.basic.basicmvp;

import android.content.Context;
import android.util.Log;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.models.SpecialisationModel;
import com.app.barber.models.request.AddServiceModel;
import com.app.barber.models.request.HoursModel;
import com.app.barber.models.request.UpdateAddressRequestModel;
import com.app.barber.models.response.BaseResponse;
import com.app.barber.models.response.LoginResponseModel;
import com.app.barber.models.response.ServiceListResponseModel;
import com.app.barber.models.response.UpdateDataResponse;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.net.RestCallback;
import com.app.barber.net.RestProcess;
import com.app.barber.net.RestService;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.PreferenceManager;
import com.app.barber.util.di.DaggerValues;
import com.app.barber.util.mvp.BasePresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by harish on 26/10/18.
 */

public class BssicAuthPresenter extends BasePresenter<BasicAuthMVPView> implements RestCallback {
    @Inject
    @Named(DaggerValues.AUTH)
    RestService appService;
    @Inject
    public PreferenceManager mPref;
    Context context;

    public BssicAuthPresenter(Context context) {
        this.context = context;
        BarberApplication.getMyComponent(context).inject(this);
    }

    @Override
    public void attachView(BasicAuthMVPView mvpView) {
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
            case NetworkConstatnts.RequestCode.API_UPDATE_BARBER_TYPE:
                if (((UpdateDataResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_UPDATE_SPEC_TYPE:
                if (((UpdateDataResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_UPDATE_ADDRESS:
                if (((UpdateDataResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_UPDATE_OPENING_TIME:
                if (((UpdateDataResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_ADD_SERVICE:
                if (((UpdateDataResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_GET_SERVICE:
                if (((ServiceListResponseModel) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_POST_WORKSPACE_IMAGES:
                if (((BaseResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
        }

    }

    @Override
    public void onLogout() {

    }


    public void saveUserData(LoginResponseModel.UserBean user) {
        try {
            mPref.setUserData(new Gson().toJson(user));
            mPref.setPrefrencesBoolean(GlobalValues.KEYS.isLogedIn, true);
            Log.e(" presenter ", "" + new Gson().toJson(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBarberType(int apiUpdateBarberType, String oType, boolean b) {
        Call<UpdateDataResponse> call = appService.updateBarberType(oType);
        call.enqueue(new RestProcess<UpdateDataResponse>(apiUpdateBarberType, this, context, b));
    }

    public void updateSpecType(int apiUpdateBarberType, String oType, boolean b) {
        Call<UpdateDataResponse> call = appService.updateSpecType(oType);
        call.enqueue(new RestProcess<UpdateDataResponse>(apiUpdateBarberType, this, context, b));
    }

    public ArrayList<SpecialisationModel> getSpecialisationList() {
        ArrayList<SpecialisationModel> list = new ArrayList<>();
        SpecialisationModel model = new SpecialisationModel();
        model.setName(context.getString(R.string.str_afro_caribbean));
        model.setId("1");
        model.setSelected(false);
        list.add(model);
        model = new SpecialisationModel();
        model.setName(context.getString(R.string.str_asian_hair));
        model.setId("2");
        model.setSelected(false);
        list.add(model);
        model = new SpecialisationModel();
        model.setName(context.getString(R.string.str_caucasian));
        model.setId("3");
        model.setSelected(false);
        list.add(model);
        return list;
    }

    public void uppdateUserAddress(int apiUpdateAddress, UpdateAddressRequestModel addressModel, boolean b) {
        Call<UpdateDataResponse> call = appService.updateUserAddress(addressModel);
        call.enqueue(new RestProcess<UpdateDataResponse>(apiUpdateAddress, this, context, b));
    }

    public void updateOpeningTime(int apiUpdateOpeningTime, List<HoursModel> updateTime, boolean b) {
        Log.e("updateOpening ", " " + new Gson().toJson(updateTime).toString());
        Call<UpdateDataResponse> call = appService.updateOpeningTime(updateTime);
        call.enqueue(new RestProcess<UpdateDataResponse>(apiUpdateOpeningTime, this, context, b));
    }

    public void addService(int apiAddService, AddServiceModel addModel, boolean b) {
        Call<UpdateDataResponse> call = appService.addService(addModel);
        call.enqueue(new RestProcess<UpdateDataResponse>(apiAddService, this, context, b));
    }

    public void getUserServices(int apiGetService, boolean b) {
        Call<ServiceListResponseModel> call = appService.getService();
        call.enqueue(new RestProcess<ServiceListResponseModel>(apiGetService, this, context, b));
    }

    /*public void postImages(int addWorkplacePhotos, List<MultipartBody.Part> list, boolean b) {
        Call<BaseResponse> call = appService.uploadImage(list, params);
        call.enqueue(new RestProcess<BaseResponse>(addWorkplacePhotos, this, context, b));
    }*/
}
