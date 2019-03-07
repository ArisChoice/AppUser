package com.app.barber.core.core_mvp;

import android.content.Context;
import android.util.Log;

import com.app.barber.core.BarberApplication;
import com.app.barber.models.response.BaseResponse;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.net.RestCallback;
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

public class CorePresenter extends BasePresenter<CoreMVPView> implements RestCallback {
    @Inject
    @Named(DaggerValues.NON_AUTH)
    RestService appService;
    @Inject
    @Named(DaggerValues.AUTH)
    RestService appServiceAuth;
    @Inject
    public PreferenceManager mPref;
    Context context;

    String TAG = CorePresenter.class.getSimpleName();

    public CorePresenter(Context context) {
        this.context = context;
        BarberApplication.getMyComponent(context).inject(this);
    }

    @Override
    public void attachView(CoreMVPView mvpView) {
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
            case NetworkConstatnts.RequestCode.API_SAVE_QB_DIALOG:
                if (((BaseResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                } else {
                    getMvpView().onfaliurResponse(serviceMode, model.body());
                }
                break;
        }

    }

    @Override
    public void onLogout() {

    }


 /*   public void loginToChat(QBUser user) {
        ChatHelper.getInstance().loginToChat(user, new QBEntityCallback<Void>() {
            @Override
            public void onSuccess(Void result, Bundle bundle) {
                Log.v(TAG, "Chat login onSuccess()" + user.getId());

//                ProgressDialogFragment.hide(getFragmentManager());
                mPref.saveQbUser(user);

                if (SubscribeToNotification.getInstance().isSubscribeToNotification(context))
                    SubscribeToNotification.getInstance().setSubscribeToNotification(context);
                ArrayList<QBUser> qbUsers = new ArrayList<>();
                QBUser qbUser = new QBUser();
                qbUser.setId(Integer.parseInt(String.valueOf(user.getId())));

                qbUsers.add(qbUser);
                createDialog(qbUsers);
            }

            @Override
            public void onError(QBResponseException e) {
//                ProgressDialogFragment.hide(getFragmentManager());
                Log.w(TAG, "Chat login onError(): " + e);

            }
        });
    }*/

    /*public void login(LoginResponseModel.UserBean userData) {
        if (userData.getQBId() != null) {
            Performer<QBUser> ussr = QBUsers.getUser(Integer.parseInt(userData.getQBId()));
            ussr.performAsync(new QBEntityCallback<QBUser>() {
                @Override
                public void onSuccess(QBUser qbUser, Bundle bundle) {
                    final QBUser user = qbUser;
                    user.setPassword(NetworkConstatnts.QB.GLOBAL_PASSWORD);
                    QBUsers.signIn(user).performAsync(new QBEntityCallback<QBUser>() {
                        @Override
                        public void onSuccess(QBUser qbUser, Bundle bundle) {
//                  callback.onSuccess(qbUser, bundle);
                            Log.d(TAG, "  onSuccess  " + qbUser.getId());

                            qbUser.setPassword(NetworkConstatnts.QB.GLOBAL_PASSWORD);
                            Log.d(TAG, "  onSuccess  " + qbUser.getPassword());
                            SubscribeToNotification.getInstance().setSubscribeToNotification(context);
                            PreferenceManager.getInstance(context).saveQbUser(qbUser);

                        }

                        @Override
                        public void onError(QBResponseException e) {
                            Log.d(TAG, "  onError " + e.getLocalizedMessage());
                        }
                    });
                }

                @Override
                public void onError(QBResponseException e) {
                    Log.d(TAG, "  onError " + e.getLocalizedMessage());
                }
            });
        }
    }*/
}

