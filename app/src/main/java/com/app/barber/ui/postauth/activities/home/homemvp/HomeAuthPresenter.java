package com.app.barber.ui.postauth.activities.home.homemvp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.models.SpecialisationModel;
import com.app.barber.models.request.AddServiceModel;
import com.app.barber.models.request.HoursModel;
import com.app.barber.models.request.RequestMyAppointmentModel;
import com.app.barber.models.request.UpdateAddressRequestModel;
import com.app.barber.models.request.UpdateBookingRequestModel;
import com.app.barber.models.request.UpdateEditBookingStatusModel;
import com.app.barber.models.response.AddressListResponseModel;
import com.app.barber.models.response.BaseResponse;
import com.app.barber.models.response.CheckQbIdResponseModel;
import com.app.barber.models.response.DistricResponseModel;
import com.app.barber.models.response.LoginResponseModel;
import com.app.barber.models.response.MyBookingsResponseMOdel;
import com.app.barber.models.response.MyImagesResponseModel;
import com.app.barber.models.response.RecentStatusResponseModel;
import com.app.barber.models.response.SpecialisationResponseModel;
import com.app.barber.models.response.UpdateDataResponse;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.net.RestCallback;
import com.app.barber.net.RestProcess;
import com.app.barber.net.RestService;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.PreferenceManager;
import com.app.barber.util.di.DaggerValues;
import com.app.barber.util.mvp.BasePresenter;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by harish on 26/10/18.
 */

public class HomeAuthPresenter extends BasePresenter<HomeAuthMVPView> implements RestCallback {
    @Inject
    @Named(DaggerValues.AUTH)
    RestService appService;
    @Inject
    @Named(DaggerValues.NON_AUTH)
    RestService nonAuthappService;
    @Inject
    public PreferenceManager mPref;
    Context context;

    public HomeAuthPresenter(Context context) {
        this.context = context;
        BarberApplication.getMyComponent(context).inject(this);
    }

    @Override
    public void attachView(HomeAuthMVPView mvpView) {
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
            case NetworkConstatnts.RequestCode.API_UPDATE_PROFILE:
                if (((LoginResponseModel) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_POST_WORKSPACE_IMAGES:
                if (((BaseResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_GET_IMAGES:
                if (((MyImagesResponseModel) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.MY_ALL_BOOKINGS:
                if (((MyBookingsResponseMOdel) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_CHECK_RECENT_STATUS:
                try {
                    if (((RecentStatusResponseModel) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                        getMvpView().onSuccessResponse(serviceMode, model.body());
                    } else if (((RecentStatusResponseModel) model.body()).getStatus() == 0) {//when session get expired
                        new CommonUtils().ShowToast(context.getString(R.string.str_session_expired_plesae_relogin));
                        onLogout();
                    }
                } catch (Exception e) {

                }
                break;
            case NetworkConstatnts.RequestCode.API_GET_SPECIALISATION:
                if (((SpecialisationResponseModel) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                    mPref.setSpecialisation(new Gson().toJson(((SpecialisationResponseModel) model.body())));
                }
                break;
            case NetworkConstatnts.RequestCode.API_CHECK_QB_ID:
                if (((CheckQbIdResponseModel) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_GET_USER_ADDRESS:
                if (((AddressListResponseModel) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_EDIT_BOOKING_STATUS:
                if (((BaseResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_AVAILABLE_GET_DISTRICTS:
                if (((DistricResponseModel) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_DELETE_IMAGE:
                if (((BaseResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
        }

    }

    @Override
    public void onLogout() {
        new CommonUtils().LogoutUser();
    }


    public void saveUserData(LoginResponseModel.UserBean user) {
        mPref.setUserData(new Gson().toJson(user));
        mPref.setPrefrencesBoolean(GlobalValues.KEYS.isLogedIn, true);
        Log.e(" presenter ", "" + new Gson().toJson(user));
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


    public void updateProfileRequest(int updateProfile, Map<String, RequestBody> params, MultipartBody.Part partData, boolean b) {
        Call<LoginResponseModel> call = appService.uploadProfile(params, partData);
        call.enqueue(new RestProcess<LoginResponseModel>(updateProfile, this, context, b));
    }

    public void postImages(int apiPostWorkspaceImages, Map<String, RequestBody> params, List<MultipartBody.Part> list, boolean b) {
        Call<BaseResponse> call = appService.uploadImage(list, params);
        call.enqueue(new RestProcess<BaseResponse>(apiPostWorkspaceImages, this, context, b));
    }

    public void getMyStyleList(int apiGetImages, Object o, boolean b) {
        Call<MyImagesResponseModel> call = appService.getMyImages();
        call.enqueue(new RestProcess<MyImagesResponseModel>(apiGetImages, this, context, b));
    }

    public void getMyBookings(int myAllBookings, RequestMyAppointmentModel mRequest, boolean b) {
        Call<MyBookingsResponseMOdel> call = appService.getMyAppointments(mRequest);
        call.enqueue(new RestProcess<MyBookingsResponseMOdel>(myAllBookings, this, context, b));
    }

    public void checkRecentStatus(int apiCheckRecentStatus, Object o, boolean b) {
        Call<RecentStatusResponseModel> call = appService.checkRecentStatus();
        call.enqueue(new RestProcess(apiCheckRecentStatus, this, context, b));
    }

    public void getSpecialisationList(int apiGetSpecialisation, String s, boolean b) {
        Call<SpecialisationResponseModel> call = appService.getSpecialisationList();
        call.enqueue(new RestProcess<SpecialisationResponseModel>(apiGetSpecialisation, this, context, b));
    }

    public void updateBookingRequest(int apiUpdateBookingStatus, UpdateBookingRequestModel uModel, boolean b) {
        Call<BaseResponse> call = appService.updateBookingStatus(uModel);
        call.enqueue(new RestProcess(apiUpdateBookingStatus, this, context, b));
    }

    public void checkQbId(int apiCheckQbId, Object o, boolean b) {
        Call<CheckQbIdResponseModel> call = appService.chcekQbId();
        call.enqueue(new RestProcess<>(apiCheckQbId, this, context, b));
    }

    /*public void initQbUser(LoginResponseModel.UserBean userData) {
        Performer<QBUser> ussr = QBUsers.getUser(Integer.parseInt(userData.getQBId()));
        ussr.performAsync(new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser qbUser, Bundle bundle) {
                final QBUser user = qbUser;
                user.setPassword(NetworkConstatnts.QB.GLOBAL_PASSWORD);
                QBUsers.signIn(user).performAsync(new QBEntityCallback<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle bundle) {
//                callback.onSuccess(qbUser, bundle);
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
    }*/

    public void getMyAddress(int apiGetUserAddress, Object o, boolean b) {
        Call<AddressListResponseModel> call = appService.getMyAddress();
        call.enqueue(new RestProcess<>(apiGetUserAddress, this, context, b));
    }

    public void updateEditStatus(int apiEditBookingStatus, UpdateEditBookingStatusModel updateEditBookingStatusModel, boolean b) {
        Call<BaseResponse> call = appService.editBookingStatus(updateEditBookingStatusModel);
        call.enqueue(new RestProcess<>(apiEditBookingStatus, this, context, b));
    }

    public void getAvailableDistricts(int apiAvailableGetDistricts, boolean b) {
        Call<DistricResponseModel> call = appService.getAvailableDistricts();
        call.enqueue(new RestProcess<>(apiAvailableGetDistricts, this, context, b));
    }

    public void deleteImage(int apiDeleteImage, String mItem1, boolean b) {
        Call<BaseResponse> call = appService.deleteUserImage(mItem1);
        call.enqueue(new RestProcess<>(apiDeleteImage, this, context, b));
    }
}

