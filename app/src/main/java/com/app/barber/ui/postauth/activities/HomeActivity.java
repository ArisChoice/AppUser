package com.app.barber.ui.postauth.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.request.RequestBarberModel;
import com.app.barber.models.request.UpdateAddressRequestModel;
import com.app.barber.models.request.UpdateEditBookingStatusModel;
import com.app.barber.models.response.CheckQbIdResponseModel;
import com.app.barber.models.response.LoginResponseModel;
import com.app.barber.models.response.MyBookingsResponseMOdel;
import com.app.barber.models.response.NotificationResponseModel;
import com.app.barber.models.response.RecentStatusResponseModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.adapters.pageradapter.HomePagerAdapter;
import com.app.barber.ui.postauth.activities.barber.AddressSelectionActivity;
import com.app.barber.ui.postauth.activities.barber.ratesettings.RateBarberActivity;
import com.app.barber.ui.postauth.activities.home.homemvp.HomeAuthMVPView;
import com.app.barber.ui.postauth.activities.home.homemvp.HomeAuthPresenter;
import com.app.barber.ui.postauth.activities.settings.NotificationActivity;
import com.app.barber.ui.postauth.activities.socket_work.chat.ChatActivity;
import com.app.barber.ui.preauth.authrise.AuthanticationActivity;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.CurrentLocationSinglton;
import com.app.barber.util.Dialogs;
import com.app.barber.util.FunctionalDialog;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.PreferenceManager;
import com.app.barber.util.bus_event.CustomEvent;
import com.app.barber.util.iface.OnBottomDialogItemListener;
import com.app.barber.util.thread.GetLocationFromLatLong;
import com.app.barber.views.CustomTextView;
import com.app.barber.views.CustomViewPager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements HomeAuthMVPView {
    @Inject
    PreferenceManager mPref;

    @BindView(R.id.home_pager)
    CustomViewPager homePager;
    @BindView(R.id.navigation)
    BottomNavigationViewEx navigation;
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.drawer_layout)
    LinearLayout drawerLayout;
    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.barber_image)
    SimpleDraweeView barberImage;
    @BindView(R.id.barber_status)
    ImageView barberStatus;
    @BindView(R.id.rate_view_holder)
    LinearLayout rateViewHolder;
    private HomePagerAdapter homePagerAdapter;
    private HomeAuthPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);
        presenter = new HomeAuthPresenter(this);
        presenter.attachView(this);
        initPager();
        initBottomToolbar();
        getSpecialisations();
//       updateMenuFonts();
        initLocation();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessage(CustomEvent event) {
        switch (event.getType()) {
            case GlobalValues.EVENTS.CHECK_OB_ID:
                if (mPref.getPrefrencesBoolean(GlobalValues.KEYS.isLogedIn)) {
                    checkQb();
                }
                break;
        }
        EventBus.getDefault().removeStickyEvent(event); // don't forget to remove the sticky event if youre done with it
    }

    private void initLocation() {
        CurrentLocationSinglton.getInstance().getCurrentLocation(this, new CurrentLocationSinglton.CurrentLocationCallback() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    new GetLocationFromLatLong(HomeActivity.this, location.getLatitude(), location.getLongitude(), null).execute();
                }
            }

            @Override
            public void onFailure() {
            }
        });
    }

    private void getSpecialisations() {
        presenter.getSpecialisationList(NetworkConstatnts.RequestCode.API_GET_SPECIALISATION, "", false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    private void initBottomToolbar() {
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.enableAnimation(false);
        navigation.enableShiftingMode(false);
        navigation.enableItemShiftingMode(false);
//        navigation.setTextSize(0f);
        navigation.setIconSize(20, 20);
        navigation.setTextVisibility(false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshUserAuth();
        new CommonUtils().hideSoftKeyboard(HomeActivity.this, null);
    }

    private void refreshUserAuth() {
        if (mPref.getPrefrencesBoolean(GlobalValues.KEYS.isLogedIn)) {
            checkQb();
            checkRecentStatus();
        }
    }

    private void checkQb() {
        if (getUserData() != null && getUserData().getQBId() == null || getUserData().getQBId().equals(""))
            checkQuickBloxid();
    }

    private void checkQuickBloxid() {
        presenter.checkQbId(NetworkConstatnts.RequestCode.API_CHECK_QB_ID, null, false);
    }

    private void checkRecentStatus() {
        presenter.checkRecentStatus(NetworkConstatnts.RequestCode.API_CHECK_RECENT_STATUS, null, false);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            //reference to the item of the menu

            switch (item.getItemId()) {
                case R.id.navigation_explore:
                    homePager.setCurrentItem(0);
                    backToolbar.setVisibility(View.INVISIBLE);
                    txtTitleToolbar.setText(R.string.title_explor);
                    toolbarCommon.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_saved:
                    if (mPref.getPrefrencesBoolean(GlobalValues.KEYS.isLogedIn)) {
                        toolbarCommon.setVisibility(View.GONE);
                        homePager.setCurrentItem(1);
                        backToolbar.setVisibility(View.INVISIBLE);
                        txtTitleToolbar.setText(R.string.title_saved);
                    } else {
                        activitySwitcher(HomeActivity.this, AuthanticationActivity.class, null);
                    }
                    return true;
                case R.id.navigation_appointments:
                    if (mPref.getPrefrencesBoolean(GlobalValues.KEYS.isLogedIn)) {
                        toolbarCommon.setVisibility(View.GONE);
                        homePager.setCurrentItem(2);
                        backToolbar.setVisibility(View.INVISIBLE);
                        txtTitleToolbar.setText(R.string.title_appointments);
                    } else {
                        activitySwitcher(HomeActivity.this, AuthanticationActivity.class, null);
                    }
                    return true;
                case R.id.navigation_Message:
                    if (mPref.getPrefrencesBoolean(GlobalValues.KEYS.isLogedIn)) {
                        toolbarCommon.setVisibility(View.GONE);
                        homePager.setCurrentItem(3);
                        txtTitleToolbar.setText(R.string.title_inbox);
                    } else {
                        activitySwitcher(HomeActivity.this, AuthanticationActivity.class, null);
                    }
                    return true;
                case R.id.navigation_more:
                    if (mPref.getPrefrencesBoolean(GlobalValues.KEYS.isLogedIn)) {
                        homePager.setCurrentItem(4);
                        toolbarCommon.setVisibility(View.GONE);
                    } else {
                        activitySwitcher(HomeActivity.this, AuthanticationActivity.class, null);
                    }
                    return true;
            }
            return false;
        }
    };

    /**
     * initialize pager
     */
    private void initPager() {
        toolbarCommon.setVisibility(View.GONE);
        homePagerAdapter = new HomePagerAdapter(getApplicationContext(), getSupportFragmentManager());
        homePager.setAdapter(homePagerAdapter);
        homePager.setPagingEnabled(true);
        homePager.setOffscreenPageLimit(0);
        homePager.swipeable = false;
        homePager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //double tap to close app
    boolean doubleBackToExitPressedOnce;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(HomeActivity.this, R.string.back_alert_message, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 3500);
    }


    @OnClick({R.id.back_toolbar, R.id.img_edit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                break;
            case R.id.img_edit:
                break;
        }
    }

    Dialog dialog1;

    @Override
    public void onSuccessResponse(int serviceMode, Object o) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_CHECK_QB_ID:
                CheckQbIdResponseModel rData = (CheckQbIdResponseModel) o;
                LoginResponseModel.UserBean userData = getUserData();
                userData.setQBId(String.valueOf(rData.getId()));
                presenter.saveUserData(userData);
//                presenter.initQbUser(userData);
                break;
            case NetworkConstatnts.RequestCode.API_CHECK_RECENT_STATUS:
                RecentStatusResponseModel statusData = (RecentStatusResponseModel) o;
                if (statusData != null && statusData.getResponse() != null) {
                    if (statusData.getResponse().getBookingList() != null && statusData.getResponse().getBookingList().size() > 0) {
                        showRecentBookingRatingVew(statusData.getResponse().getBookingList().get(0));
                    } else {
                        navigation.setVisibility(View.VISIBLE);
                        rateViewHolder.setVisibility(View.GONE);
                        if (statusData.getResponse().getBarberEditList() != null && statusData.getResponse().getBarberEditList().size() > 0) {
                            if (dialog1 == null)
                                dialog1 = new FunctionalDialog().openEditRequestsDialog(HomeActivity.this, statusData.getResponse().getBarberEditList(), getUserData(), new OnBottomDialogItemListener() {
                                    @Override
                                    public void onItemClick(View view, int position, int type, Object t) {
                                        UpdateEditBookingStatusModel requestData;
                                        MyBookingsResponseMOdel.ListBean positionData;
                                        switch (type) {
                                            case GlobalValues.RequestCodes.CALL:
                                                positionData = (MyBookingsResponseMOdel.ListBean) t;
                                                if (positionData.getPhone() != null)
                                                    callInit(positionData.getPhone());
                                                break;
                                            case GlobalValues.RequestCodes.MESSAGE:
                                                positionData = (MyBookingsResponseMOdel.ListBean) t;
                                                if (positionData.getChatDialog() != null) {
                                                    Intent intent = new Intent(HomeActivity.this, ChatActivity.class);
                                                    intent.putExtra(GlobalValues.KEYS.EXTRA_DIALOG_ID, positionData.getChatDialog());
                                                    intent.putExtra(GlobalValues.KEYS.OTHER_IMAGE, positionData.getProfileImage());
                                                    intent.putExtra(GlobalValues.KEYS.USER_ID, "" + positionData.getBarberId());
                                                    startActivity(intent);
                                                }
                                                break;
                                            case GlobalValues.RequestCodes.REJECT_REQUEST:
                                                requestData = (UpdateEditBookingStatusModel) t;
                                                presenter.updateEditStatus(NetworkConstatnts.RequestCode.API_EDIT_BOOKING_STATUS, requestData, true);
                                                break;
                                            case GlobalValues.RequestCodes.ACCEPT_REQUEST:
                                                requestData = (UpdateEditBookingStatusModel) t;
                                                presenter.updateEditStatus(NetworkConstatnts.RequestCode.API_EDIT_BOOKING_STATUS, requestData, true);
                                                break;
                                            case GlobalValues.RequestCodes.CANCEL_EDIT_REQUEST:
                                                requestData = (UpdateEditBookingStatusModel) t;
                                                presenter.updateEditStatus(NetworkConstatnts.RequestCode.API_EDIT_BOOKING_STATUS, requestData, true);
                                                break;
                                        }
                                    }
                                });
                        }


                    }
                }
                break;
        }
    }

    /**
     * Show recent rating view for recent bookings;
     */
    private void showRecentBookingRatingVew(RecentStatusResponseModel.ResponseBean.BookingListBean bookingListBean) {
        navigation.setVisibility(View.GONE);
        rateViewHolder.setVisibility(View.VISIBLE);
        barberImage.setImageURI(bookingListBean.getBarbarProfilePic());
        rateViewHolder.setOnClickListener(v -> {
            NotificationResponseModel.ResponseBean dataM = new NotificationResponseModel.ResponseBean();
            dataM.setBarberId(bookingListBean.getBarberId());
            dataM.setBookingId(String.valueOf(bookingListBean.getBookingId()));
            dataM.setBookingType(bookingListBean.getBookingType());

            Bundle bundle = new Bundle();
            bundle.putSerializable(GlobalValues.KEYS.NOTIFICATION_DATA, dataM);
            activitySwitcher(HomeActivity.this, RateBarberActivity.class, bundle);
        });
    }

    @Override
    public void onfaliurResponse(int serviceMode, Object o) {

    }

    @Override
    public void showProgres() {

    }

    @Override
    public void hidePreogress() {

    }

    @Override
    public void onSuccess(Object o, int type) {

    }

    @Override
    public void onError(String localizedMessage) {

    }

    @Override
    public void onException(Exception e) {

    }


}
