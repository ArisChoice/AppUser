package com.app.barber.ui.postauth.activities.barber.booking;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.BookingData;
import com.app.barber.models.request.PreBookingRequestModel;
import com.app.barber.models.response.BookingResponseModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.adapters.BookedServiceListAdapter;
import com.app.barber.ui.postauth.activities.barber.AddressSelectionActivity;
import com.app.barber.ui.postauth.activities.barber.BarberDetailActivity;
import com.app.barber.ui.postauth.activities.barber.PreBookingDetailResponse;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberMVPView;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberPresenter;
import com.app.barber.ui.preauth.authrise.AuthanticationActivity;
import com.app.barber.util.AppBarStateChangeListener;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.PreferenceManager;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomEditText;
import com.app.barber.views.CustomTextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 17/12/18.
 */

public class ConfirmBookingActivity extends BaseActivity implements BarberMVPView {
    @Inject
    PreferenceManager mPref;
    @BindView(R.id.cover_slider)
    SimpleDraweeView coverSlider;
    @BindView(R.id.chk_fav_)
    CheckBox chkFav;
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_toolbar)
    CustomTextView txtToolbar;
    @BindView(R.id.chk_bx_hedaer)
    CheckBox chkBxHedaer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_title)
    CustomTextView txtTitle;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.barber_image)
    SimpleDraweeView barberImage;
    @BindView(R.id.barber_name)
    CustomTextView barberName;
    @BindView(R.id.barber_user_name)
    CustomTextView barberUserName;
    @BindView(R.id.barber_status)
    CustomTextView barberStatus;
    @BindView(R.id.booking_time_slot)
    CustomTextView bookingTimeSlot;
    @BindView(R.id.booking_duration)
    CustomTextView bookingDuration;
    @BindView(R.id.booking_date)
    CustomTextView bookingDate;
    @BindView(R.id.booking_day)
    CustomTextView bookingDay;
    @BindView(R.id.booking_address)
    CustomTextView bookingAddress;
    @BindView(R.id.booking_distance)
    CustomTextView bookingDistance;
    @BindView(R.id.booking_promo_code_edtxt)
    CustomEditText bookingPromoCodeEdtxt;
    @BindView(R.id.booking_remove_promo_btn)
    ImageView bookingRemovePromoBtn;
    @BindView(R.id.booking_promo_apply_btn)
    CustomTextView bookingPromoApplyBtn;
    @BindView(R.id.booking_cnfirm_btn)
    CustomTextView bookingCnfirmBtn;
    @BindView(R.id.rootLayout)
    CoordinatorLayout rootLayout;
    @BindView(R.id.selected_service_list)
    RecyclerView selectedServiceList;
    @BindView(R.id.total_images)
    CustomTextView totalImages;
    @BindView(R.id.share_img_btn)
    ImageView shareImgBtn;
    private BarberPresenter presenter;
    private BookingData bookingDataModel;
    private boolean isBarUp;
    private BookedServiceListAdapter serviceAdapter;
    private PreBookingDetailResponse bookingDetailResponse;
    public static ConfirmBookingActivity instance;

    public static ConfirmBookingActivity getInstance() {
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_cnfirm_booking_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);
        presenter = new BarberPresenter(ConfirmBookingActivity.this);
        presenter.attachView(this);
        txtToolbar.setText(R.string.str_booking_detail);
        totalImages.setVisibility(View.GONE);
        shareImgBtn.setVisibility(View.GONE);
        chkBxHedaer.setVisibility(View.GONE);
        getIntentData(getIntent());
        setToolbarlistner();
        initAdapter();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(ConfirmBookingActivity.this);
        selectedServiceList.setLayoutManager(layoutManager);
        serviceAdapter = new BookedServiceListAdapter(ConfirmBookingActivity.this, new ArrayList<PreBookingDetailResponse.ListBean.ServicesBean>(),
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int positio, int type, Object o) {
                        switch (type) {

                        }
                    }
                });
        selectedServiceList.setAdapter(serviceAdapter);
    }

    private void setToolbarlistner() {
        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                switch (state.ordinal()) {
                    case 0:
                        txtToolbar.setVisibility(View.GONE);
                        isBarUp = false;
                        break;
                    case 1:
                        isBarUp = true;
                        txtToolbar.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void getIntentData(Intent intent) {
        Serializable dataArgument = intent.getSerializableExtra(GlobalValues.KEYS.BOOKING_DATA);//Get pass data with its key value
        bookingDataModel = ((BookingData) dataArgument);
        Log.e(" getIntentData ", "  " + new Gson().toJson(bookingDataModel));
        getBookingDetail();
    }

    private void getBookingDetail() {
        PreBookingRequestModel reRequest = new PreBookingRequestModel();
        reRequest.setBarberId(bookingDataModel.getBarberId());
        reRequest.setServiceId(bookingDataModel.getBookedServicesId());
        reRequest.setTimingSlot(bookingDataModel.getSelectedSlot());
        reRequest.setDate(bookingDataModel.getSelectedDate());
        presenter.bookingDetailRequest(NetworkConstatnts.RequestCode.API_GET_PRE_BOOKING_DETAIL, reRequest, true);
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object o) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_GET_PRE_BOOKING_DETAIL:
                setRequsetedDetails((PreBookingDetailResponse) o);
                break;
            case NetworkConstatnts.RequestCode.API_BOOK_BARBER_APPOINTMENT:

                if (BarberDetailActivity.getInstance() != null) {//finish  BarberDetailActivity activity.
                    BarberDetailActivity.getInstance().finish();
                }
                if (ConfirmBookingActivity.getInstance() != null) {//finish  ConfirmBookingActivity activity.
                    ConfirmBookingActivity.getInstance().finish();
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable(GlobalValues.KEYS.APPOINTMENT_STATUS_DETAIL, ((BookingResponseModel) o));
                bundle.putBoolean(GlobalValues.KEYS.IS_CARD_PAYMENT, false);
                bundle.putSerializable(GlobalValues.KEYS.PRE_BOOKING_RESPONSE_DATA, bookingDetailResponse);
                activitySwitcher(ConfirmBookingActivity.this, BookingStatusActivity.class, bundle);
                finish();
                break;

        }
    }

    private void setRequsetedDetails(PreBookingDetailResponse o) {
        try {
            bookingDetailResponse = o;
            bookingDataModel.setSelectedSlot(bookingDetailResponse.getList().getTimeSlot());
            try {
                barberImage.setImageURI(CommonUtils.getValidUrl(o.getList().getProfileImage()));
                coverSlider.setImageURI(CommonUtils.getValidUrl(o.getList().getBannerImage()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            barberName.setText(o.getList().getFullName());
            barberUserName.setText(o.getList().getUserName());
            bookingTimeSlot.setText(o.getList().getTimeSlot());
            bookingDate.setText(o.getList().getDate());
            if (o.getList().getAddress() != null && o.getList().getAddress().getAddressLine1() != null)
                bookingAddress.setText(o.getList().getAddress().getAddressLine1());
            else bookingAddress.setText(R.string.str_not_found);
            if (o.getList().getServices() != null && o.getList().getServices().size() > 0)
                serviceAdapter.updateAll(o.getList().getServices());
            bookingDuration.setText(o.getList().getTotalDuration() + "min");
            bookingDay.setText(o.getList().getDate().split(",")[0]);

            if (o.getList().isSuperBarber()) {
                barberStatus.setVisibility(View.VISIBLE);
            } else {
                barberStatus.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onfaliurResponse(int serviceMode, Object o) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_BOOK_BARBER_APPOINTMENT:
                if (BarberDetailActivity.getInstance() != null) {//finish  BarberDetailActivity activity.
                    BarberDetailActivity.getInstance().finish();
                }
                if (ConfirmBookingActivity.getInstance() != null) {//finish  ConfirmBookingActivity activity.
                    ConfirmBookingActivity.getInstance().finish();
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable(GlobalValues.KEYS.APPOINTMENT_STATUS_DETAIL, ((BookingResponseModel) o));
                bundle.putSerializable(GlobalValues.KEYS.PRE_BOOKING_RESPONSE_DATA, bookingDetailResponse);
                bundle.putBoolean(GlobalValues.KEYS.IS_CARD_PAYMENT, false);
                activitySwitcher(ConfirmBookingActivity.this, BookingStatusActivity.class, bundle);
                finish();
                break;
        }
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

    @OnClick({R.id.chk_fav_, R.id.back_toolbar, R.id.booking_remove_promo_btn, R.id.booking_promo_apply_btn, R.id.booking_cnfirm_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chk_fav_:
                break;
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.booking_remove_promo_btn:
                break;
            case R.id.booking_promo_apply_btn:
                break;
            case R.id.booking_cnfirm_btn:
                if (mPref.getPrefrencesBoolean(GlobalValues.KEYS.isLogedIn)) {// Check is user logedin.
                    if (bookingDataModel.getBookingType().equals("" + GlobalValues.BARBER_TYPES.BARBER)) {//if booking is appointment booking
                        goNext();
                    } else if (bookingDataModel.getBookingType().equals("" + GlobalValues.BARBER_TYPES.CALLOUT_BARBER)) {//if booking is callout booking
                        if (!getUserData().isAddressAdded()) {//check user address exist.
                            mPref.setPrefrencesBoolean(GlobalValues.KEYS.isAddressFromSetting, true);
                            Bundle bundle = new Bundle();
                            bundle.putBoolean(GlobalValues.KEYS.isAddressFromSetting, true);
                            activitySwitcher(ConfirmBookingActivity.this, AddressSelectionActivity.class, bundle);
                        } else {
                            goNext();
                        }
                    }
                } else {
                    activitySwitcher(ConfirmBookingActivity.this, AuthanticationActivity.class, null);
                }
                break;
        }
    }

    private void goNext() {
        if (bookingDetailResponse != null && bookingDetailResponse.getList() != null)
            if (bookingDetailResponse.getList().getBarberType().contains("3")) {//barber type 3= tranee;
                PreBookingRequestModel bookingRequestData = bookAppointment();
                bookingRequestData.setPaymentMode("");
                bookingRequestData.setAmount("0");
                presenter.bookingAppointmnetRequest(NetworkConstatnts.RequestCode.API_BOOK_BARBER_APPOINTMENT, bookingRequestData, true);
            } else {
                Bundle bundle = new Bundle();
                bundle.putSerializable(GlobalValues.KEYS.BOOKING_DATA, bookAppointment());
                bundle.putSerializable(GlobalValues.KEYS.PRE_BOOKING_RESPONSE_DATA, bookingDetailResponse);
                activitySwitcher(ConfirmBookingActivity.this, PaymentActivity.class, bundle);
            }
    }

    private PreBookingRequestModel bookAppointment() {
        PreBookingRequestModel reRequest = new PreBookingRequestModel();
        reRequest.setBarberId(bookingDataModel.getBarberId());
        reRequest.setServiceId(bookingDataModel.getBookedServicesId());
        reRequest.setTimingSlot(bookingDataModel.getSelectedSlot());
        reRequest.setDate(bookingDataModel.getSelectedDate());
        reRequest.setBookingType(bookingDataModel.getBookingType());
//        reRequest.setUserId(getUserData().getUserID());
//        reRequest.setPaymentType(bookingDetailResponse.getList().getPaymentType());
//        presenter.bookingAppointmnetRequest(NetworkConstatnts.RequestCode.API_BOOK_BARBER_APPOINTMENT, reRequest, true);
        return reRequest;
    }


}
