package com.app.barber.ui.postauth.activities.barber;

import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.BookingData;
import com.app.barber.models.request.RequestFavUnfavModel;
import com.app.barber.models.request.SaveQbDialogRequestModel;
import com.app.barber.models.response.BarberDetialResponse;
import com.app.barber.models.response.BarberListResponseModel;
import com.app.barber.models.response.BarberRatingsResponseModel;
import com.app.barber.models.response.BaseResponse;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.adapters.BarberReviewAdapter;
import com.app.barber.ui.adapters.pageradapter.BarberPagerAdapter;
import com.app.barber.ui.postauth.activities.barber.barber_adapter.ServiceListAdapter;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberMVPView;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberPresenter;
import com.app.barber.ui.postauth.activities.barber.booking.BookingSheetFragment;
import com.app.barber.ui.postauth.activities.socket_work.chat.ChatActivity;
import com.app.barber.util.AppBarStateChangeListener;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.CurrentLocationSinglton;
import com.app.barber.util.FunctionalDialog;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.bus_event.CustomEvent;
import com.app.barber.util.iface.OnBottomDialogItemListener;
import com.app.barber.views.CustomTextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 15/11/18.
 */

public class BarberDetailActivity extends BaseActivity implements BarberMVPView {
    private static BarberDetailActivity instance;
    @BindView(R.id.service_list)
    RecyclerView serviceList;
    @BindView(R.id.rootLayout)
    CoordinatorLayout rootLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.txt_title)
    CustomTextView txtTitle;
    @BindView(R.id.txt_toolbar)
    CustomTextView txtToolbar;
    @BindView(R.id.total_service_count)
    CustomTextView totalServiceCount;
    @BindView(R.id.total_booking_amount)
    CustomTextView totalBookingAmount;
    @BindView(R.id.book_apointment)
    CustomTextView bookApointment;
    @BindView(R.id.booking_layout)
    LinearLayout bookingLayout;
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.barber_Name)
    CustomTextView barberName;
    @BindView(R.id.barber_user_name)
    CustomTextView barberUserName;
    @BindView(R.id.barber_status)
    CustomTextView barberStatus;
    @BindView(R.id.barber_image)
    SimpleDraweeView barberImage;
    @BindView(R.id.barber_location)
    ImageView barberLocation;
    @BindView(R.id.barber_address)
    CustomTextView barberAddress;
    @BindView(R.id.barber_openining_status)
    CustomTextView barberOpeniningStatus;
    @BindView(R.id.barber_opening_hours)
    CustomTextView barberOpeningHours;
    @BindView(R.id.five_start_progress)
    ContentLoadingProgressBar fiveStartProgress;
    @BindView(R.id.five_start_counts)
    CustomTextView fiveStartCounts;
    @BindView(R.id.four_start_progress)
    ContentLoadingProgressBar fourStartProgress;
    @BindView(R.id.four_start_counts)
    CustomTextView fourStartCounts;
    @BindView(R.id.three_start_progress)
    ContentLoadingProgressBar threeStartProgress;
    @BindView(R.id.three_start_counts)
    CustomTextView threeStartCounts;
    @BindView(R.id.two_start_progress)
    ContentLoadingProgressBar twoStartProgress;
    @BindView(R.id.two_start_counts)
    CustomTextView twoStartCounts;
    @BindView(R.id.one_start_progress)
    ContentLoadingProgressBar oneStartProgress;
    @BindView(R.id.one_start_counts)
    CustomTextView oneStartCounts;
    @BindView(R.id.cover_slider)
    SimpleDraweeView coverSlider;
    @BindView(R.id._about_barber_image)
    SimpleDraweeView AboutBarberImage;
    @BindView(R.id._about_barber_name)
    CustomTextView AboutBarberName;
    @BindView(R.id._about_barber_number)
    CustomTextView AboutBarberNumber;
    @BindView(R.id._about_barber_desd)
    CustomTextView AboutBarberDesd;
    @BindView(R.id.barner_type_holder_view)
    LinearLayout barnerTypeHolderView;
    @BindView(R.id.review_recyclar)
    RecyclerView reviewRecyclar;
    @BindView(R.id.chk_fav_)
    CheckBox chkFav;
    @BindView(R.id.avg_rate_txt_count)
    CustomTextView avgRateTxtCount;
    @BindView(R.id.punctuality_rate_txt_count)
    CustomTextView punctualityRateTxtCount;
    @BindView(R.id.reviews_txt_count)
    CustomTextView reviewsTxtCount;
    @BindView(R.id.image_expertise)
    ImageView imageExpertise;
    @BindView(R.id.avg_rating_count_txt_main)
    CustomTextView avgRatingCountTxtMain;
    @BindView(R.id.rating_bar_barber_detail)
    RatingBar ratingBarBarberDetail;
    @BindView(R.id.total_ratings_count_txt)
    CustomTextView totalRatingsCountTxt;
    @BindView(R.id.experties_count_txt_main)
    CustomTextView expertiesCountTxtMain;
    @BindView(R.id.value_count_txt_main)
    CustomTextView valueCountTxtMain;
    @BindView(R.id.hygenic_count_txt_main)
    CustomTextView hygenicCountTxtMain;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drop_arrouw_timings)
    ImageView dropArrouwTimings;
    @BindView(R.id.chk_bx_hedaer)
    CheckBox chkHedaer;
    @BindView(R.id.message_btn)
    ImageView messageBtn;
    @BindView(R.id.call_btn)
    ImageView callBtn;
    @BindView(R.id.total_images)
    CustomTextView totalImages;
    @BindView(R.id.drop_arrouw_callout_timings)
    ImageView dropArrouwCalloutTimings;
    @BindView(R.id.layout_regular_timimgs)
    LinearLayout layoutRegularTimimgs;
    @BindView(R.id.barber_callout_status)
    CustomTextView barberCalloutStatus;
    @BindView(R.id.barber_callout_hours)
    CustomTextView barberCalloutHours;
    @BindView(R.id.layout_callout_timimgs)
    LinearLayout layoutCalloutTimimgs;
    @BindView(R.id.cancellation_policy_button)
    CustomTextView cancellationPolicyButton;

    private BarberPagerAdapter barberPagerAdapter;
    private BarberPresenter presenter;
    private BarberListResponseModel.ListBean barberDataModel;
    private BarberDetialResponse.InfoBean responseModel;
    private LinearLayoutManager layoutManager;
    private ServiceListAdapter serviceAdapter;
    private BarberReviewAdapter reviewAdapter;
    private boolean isBarUp;
    private BookingData bookingData = null;
    private boolean isCalloutAvailable;
    private boolean isDown, isDownCallout;
    private int barberID;
    private boolean isReorder;
    private String selectedServices;

    @Override
    public int getLayoutId() {
        return R.layout.layout_barber_detail_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);
        presenter = new BarberPresenter(this);
        getIntentData(getIntent());
        presenter.attachView(this);
        setToolbarlistner();
        getBarberDetail();
        initServiceRecyclar();
        initReviewsRecyclar();

    }

    private void initLookingType() {
        new FunctionalDialog().openDialogBookingType(BarberDetailActivity.this, null, new OnBottomDialogItemListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object t) {
                switch (type) {
                    case GlobalValues.BARBER_TYPES.BARBER:
//                        data.putInt(GlobalValues.KEYS.BOOKING_TYPE, GlobalValues.BARBER_TYPES.BARBER);//put string to pass with a key value
                        serviceAdapter.refreshList(GlobalValues.BARBER_TYPES.BARBER);
                        bookApointment.setText(R.string.str_book_appointment_);
                        break;
                    case GlobalValues.BARBER_TYPES.CALLOUT_BARBER:
//                        data.putInt(GlobalValues.KEYS.BOOKING_TYPE, GlobalValues.BARBER_TYPES.CALLOUT_BARBER);//put string to pass with a key value
                        serviceAdapter.refreshList(GlobalValues.BARBER_TYPES.CALLOUT_BARBER);
                        isCalloutAvailable = true;
                        bookApointment.setText(R.string.str_book_callout);
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().postSticky(new CustomEvent(GlobalValues.EVENTS.CHECK_OB_ID, null));//check qbid exist
    }

    private void initReviewsRecyclar() {
        layoutManager = new LinearLayoutManager(BarberDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
        reviewRecyclar.setLayoutManager(layoutManager);
        reviewAdapter = new BarberReviewAdapter(BarberDetailActivity.this, new ArrayList<BarberRatingsResponseModel.ResponseBean.ReviewListBean>(10),
                (view, positio, type, o) -> {
                    switch (type) {
//                            case GlobalValues.ClickOperations.SERVICE_DELETE:
//                                break;
//                            case GlobalValues.ClickOperations.SERVICE_BOOK:
//                                notifyView();
//                                break;
                    }
                });
        reviewRecyclar.setAdapter(reviewAdapter);
        reviewRecyclar.setNestedScrollingEnabled(false);
    }

    private void initServiceRecyclar() {
        layoutManager = new LinearLayoutManager(BarberDetailActivity.this);
        serviceList.setLayoutManager(layoutManager);
        serviceAdapter = new ServiceListAdapter(BarberDetailActivity.this, new ArrayList<>(10),
                (view, positio, type, o) -> {
                    switch (type) {
                        case GlobalValues.ClickOperations.SERVICE_DELETE:
                            break;
                        case GlobalValues.ClickOperations.SERVICE_BOOK:
                            notifyView();
                            break;
                    }
                });
        serviceList.setAdapter(serviceAdapter);
        serviceList.setNestedScrollingEnabled(false);
    }

    private void notifyView() {
        if (serviceAdapter.serviceSelected()) {
            bookingLayout.setVisibility(View.VISIBLE);
            bookingData = serviceAdapter.getBookingData();
            Log.e("notifyView", "  " + new Gson().toJson(bookingData));
            if (bookingData != null) {
                totalBookingAmount.setText("€" + bookingData.getTotalAmount());
                totalServiceCount.setText("" + bookingData.getTotalServices());
                bookingData.setBarberId(barberID);
            }
        } else bookingLayout.setVisibility(View.GONE);

    }

    private void getIntentData(Intent intent) {
        Serializable barberData = intent.getSerializableExtra(GlobalValues.KEYS.BARBER_DETAIL);
        barberID = intent.getIntExtra(GlobalValues.KEYS.BARBER_ID, 0);
        isReorder = intent.getBooleanExtra(GlobalValues.KEYS.isReorder, false);
        if (barberData != null) {
            barberDataModel = (BarberListResponseModel.ListBean) barberData;
            barberID = barberDataModel.getBarberId();
        }
        if (isReorder) {
            selectedServices = intent.getStringExtra(GlobalValues.KEYS.SERVICE_DETAIL);
        }

    }

    private void getBarberDetail() {
        presenter.getBarberDetail(NetworkConstatnts.RequestCode.API_BARBER_DETAIL, barberID, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
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


    @OnClick({R.id.back_toolbar, R.id.chk_bx_hedaer, R.id.share_img_btn, R.id.cover_slider, R.id.booking_layout, R.id.barber_opening_hours,
            R.id.drop_arrouw_timings, R.id.message_btn, R.id.call_btn, R.id.barber_location,
            R.id.total_images, R.id.barber_callout_hours, R.id.cancellation_policy_button, R.id.drop_arrouw_callout_timings})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.chk_bx_hedaer:
                if (mPref.getPrefrencesBoolean(GlobalValues.KEYS.isLogedIn)) {
                    RequestFavUnfavModel setModel = new RequestFavUnfavModel();
                    if (responseModel.isFavourite())
                        setModel.setAction(false);
                    else setModel.setAction(true);
                    setModel.setBarberId(responseModel.getUserID());
                    presenter.favUnfav(NetworkConstatnts.RequestCode.API_FAV_UNFAV, setModel, false);
                }
                break;
            case R.id.message_btn:
                if (mPref.getPrefrencesBoolean(GlobalValues.KEYS.isLogedIn)) {
//                    presenter.initQbuser();
                    if (responseModel.getChatDialog() != null && !responseModel.getChatDialog().equals("")) {
                        Intent intent = new Intent(BarberDetailActivity.this, ChatActivity.class);
                        intent.putExtra(GlobalValues.KEYS.EXTRA_DIALOG_ID, responseModel.getChatDialog());
                        intent.putExtra(GlobalValues.KEYS.OTHER_IMAGE, responseModel.getProfileImage());
                        intent.putExtra(GlobalValues.KEYS.USER_ID, "" + responseModel.getUserID());
                        startActivity(intent);
                    } else {
//                        initChat(getRequiredVales(), responseModel.getQBId(), getUserData().getQBId());
                        initChat(getRequiredVales(), responseModel.getUserID(), getUserData().getUserID());
                    }
                }
                break;
            case R.id.barber_location:
                getDirection();
                break;
            case R.id.total_images:
                Bundle bundle = new Bundle();
                bundle.putInt(GlobalValues.KEYS.BARBER_ID, barberID);
                bundle.putString(GlobalValues.KEYS.BARBER_NAME, responseModel.getFullName().toString());
                activitySwitcher(BarberDetailActivity.this, BarberGalleryActivity.class, bundle);

                break;
            case R.id.share_img_btn:
                initShare("TrimCheck");
                break;
            case R.id.call_btn:
                if (responseModel.getPhoneNumber() != null)
                    callInit(responseModel.getPhoneNumber());
                break;
            case R.id.barber_opening_hours:
            case R.id.drop_arrouw_timings:
                if (isDown) {
                    isDown = false;
                    dropArrouwTimings.setImageResource(R.drawable.arrow_down_ico);
                    barberOpeningHours.setMaxLines(1);
                } else {
                    dropArrouwTimings.setImageResource(R.drawable.arrow_right_copy);
                    barberOpeningHours.setMaxLines(7);
                    isDown = true;
                }
                break;
            case R.id.barber_callout_hours:
            case R.id.drop_arrouw_callout_timings:
                if (isDownCallout) {
                    isDownCallout = false;
                    dropArrouwCalloutTimings.setImageResource(R.drawable.arrow_down_ico);
                    barberCalloutHours.setMaxLines(1);
                } else {
                    dropArrouwCalloutTimings.setImageResource(R.drawable.arrow_right_copy);
                    barberCalloutHours.setMaxLines(7);
                    isDownCallout = true;
                }
                break;
            case R.id.cover_slider:

                break;
            case R.id.booking_layout:
                BookingSheetFragment bottomSheetFragment = new BookingSheetFragment();
                Bundle data = new Bundle();//create bundle instance
                data.putSerializable(GlobalValues.KEYS.BOOKING_DATA, bookingData);//put string to pass with a key value
                if (bookApointment.getText().toString().equals(getString(R.string.str_book))) {
                    new FunctionalDialog().openDialogBookingType(BarberDetailActivity.this, null, new OnBottomDialogItemListener() {
                        @Override
                        public void onItemClick(View view, int position, int type, Object t) {
                            switch (type) {
                                case GlobalValues.BARBER_TYPES.BARBER:
                                    data.putInt(GlobalValues.KEYS.BOOKING_TYPE, GlobalValues.BARBER_TYPES.BARBER);//put string to pass with a key value
                                    break;
                                case GlobalValues.BARBER_TYPES.CALLOUT_BARBER:
                                    data.putInt(GlobalValues.KEYS.BOOKING_TYPE, GlobalValues.BARBER_TYPES.CALLOUT_BARBER);//put string to pass with a key value
                                    break;
                            }
                            bottomSheetFragment.setArguments(data);
                            bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                        }
                    });

                } else if (bookApointment.getText().toString().equals(getString(R.string.str_book_callout))) {
                    data.putInt(GlobalValues.KEYS.BOOKING_TYPE, GlobalValues.BARBER_TYPES.CALLOUT_BARBER);//put string to pass with a key value
                    bottomSheetFragment.setArguments(data);
                    bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                } else {
                    data.putInt(GlobalValues.KEYS.BOOKING_TYPE, GlobalValues.BARBER_TYPES.BARBER);//put string to pass with a key value
                    bottomSheetFragment.setArguments(data);
                    bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                }

                break;
            case R.id.cancellation_policy_button:
                new FunctionalDialog().openCancellationPolicyDialog(BarberDetailActivity.this, new OnBottomDialogItemListener() {
                    @Override
                    public void onItemClick(View view, int position, int type, Object t) {

                    }
                });
                break;
        }
    }

    private void initChat(SaveQbDialogRequestModel requiredVales, int barberId, int userID1) {
        presenter.saveDialog(NetworkConstatnts.RequestCode.API_SAVE_DIALOG, barberId, userID1, true);
    }

    private void getDirection() {
        CurrentLocationSinglton.getInstance().getCurrentLocation(BarberDetailActivity.this, new CurrentLocationSinglton.CurrentLocationCallback() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Log.d("Test", " lati " + location.getLatitude() + " long " + location.getLongitude());
                    if (responseModel.getUserAddresses() != null && responseModel.getUserAddresses().getLat() != null && !responseModel.getUserAddresses().getLat().equals("")) {
                        new CommonUtils().navigateUsertoMap(BarberDetailActivity.this, "" + location.getLatitude(), "" + location.getLongitude(),
                                "" + responseModel.getUserAddresses().getLat(), "" + responseModel.getUserAddresses().get_long());
                    } else {
                        new CommonUtils().ShowToast(getString(R.string.str_error));
                    }
                }
            }

            @Override
            public void onFailure() {
            }
        });

    }

  /*  public void initChat(SaveQbDialogRequestModel requiredVales, String qbId, String id) {
        showLoading();
        try {
            *//*ArrayList<Integer> occupantIdsList = new ArrayList<Integer>();
            occupantIdsList.add(Integer.valueOf(id));
            occupantIdsList.add(Integer.valueOf(qbId));*//*

//            QBChatDialog dialog = new QBChatDialog();
//            dialog.setName("Chat with Yuki and me");
//            dialog.setPhoto("1786");
//            dialog.setType(QBDialogType.GROUP);
//            dialog.setOccupantsIds(occupantIdsList);

//or just use DialogUtils
//for creating PRIVATE dialog
            QBChatDialog dialog = DialogUtils.buildPrivateDialog(Integer.parseInt(qbId));

//for creating GROUP dialog
//            QBChatDialog dialog = DialogUtils.buildDialog("Chat with Yuki and me", QBDialogType.GROUP, occupantIdsList);

            QBRestChatService.createChatDialog(dialog).performAsync(new QBEntityCallback<QBChatDialog>() {
                @Override
                public void onSuccess(QBChatDialog result, Bundle params) {
//                    Log.e(" onSuccess ", "  " + result.getDialogId());
//                    Intent intent = new Intent(context, ChatWorkActivity.class);
//                    intent.putExtra(GlobalValues.KEYS.EXTRA_DIALOG_ID, result.getDialogId());
//                    context.startActivity(intent);
//
                    requiredVales.setDialogId(result.getDialogId());
                    responseModel.setQBDialogId(result.getDialogId());
                    hideLoading();
                    presenter.saveQbDialog(NetworkConstatnts.RequestCode.API_SAVE_QB_DIALOG, requiredVales, true);
                }

                @Override
                public void onError(QBResponseException responseException) {

                }
            });
        } catch (Exception e) {
//            cUtils.ShowToast(getString(R.string.msg_error));
        }
    }*/

    private SaveQbDialogRequestModel getRequiredVales() {
        SaveQbDialogRequestModel sModel = new SaveQbDialogRequestModel();
        sModel.setBarberId(responseModel.getUserID());
        sModel.setUserId(getUserData().getUserID());

        return sModel;
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object o) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_BARBER_DETAIL:
                BarberDetialResponse responseData = (BarberDetialResponse) o;
                responseModel = responseData.getInfo();
                updateUserDeatailView(responseModel);
                if (responseModel != null && responseModel.getServices() != null && responseModel.getServices().size() > 0) {
                    serviceAdapter.updateAll(responseModel.getServices());
                    if (isReorder) {
                        String[] selectedItems = selectedServices.split(",");
                        for (int i = 0; i < selectedItems.length; i++) {
                            serviceAdapter.setselected(selectedItems[i]);
                        }
                        notifyView();
                    }
//                    initLookingType();
                    getBarberRatings();
                }
                break;
            case NetworkConstatnts.RequestCode.API_BARBER_RATINGS:
                BarberRatingsResponseModel responseD = (BarberRatingsResponseModel) o;
                updateRatingsViews(responseD);
                break;
            case NetworkConstatnts.RequestCode.API_FAV_UNFAV:
//                new CommonUtils().ShowToast(((BaseResponse) o).getMessage());
                break;
            /*case NetworkConstatnts.RequestCode.API_SAVE_QB_DIALOG:
                Intent intent = new Intent(BarberDetailActivity.this, ChatWorkActivity.class);
                intent.putExtra(GlobalValues.KEYS.EXTRA_DIALOG_ID, responseModel.getQBDialogId());
                intent.putExtra(GlobalValues.KEYS.OTHER_IMAGE, responseModel.getProfileImage());
                startActivity(intent);
                break;*/
            case NetworkConstatnts.RequestCode.API_SAVE_DIALOG:
                Intent intent = new Intent(BarberDetailActivity.this, ChatActivity.class);
                intent.putExtra(GlobalValues.KEYS.EXTRA_DIALOG_ID, ((BaseResponse) o).getId());
                intent.putExtra(GlobalValues.KEYS.OTHER_IMAGE, responseModel.getProfileImage());
                intent.putExtra(GlobalValues.KEYS.USER_ID, "" + responseModel.getUserID());
                startActivity(intent);
                break;

        }

    }

    private void updateRatingsViews(BarberRatingsResponseModel responseD) {
        if (responseD != null) {
            avgRateTxtCount.setText(CommonUtils.formatDecimalPoint(responseD.getResponse().getAvgRating(), 1));
            punctualityRateTxtCount.setText(CommonUtils.formatDecimalPoint(responseD.getResponse().getAvgPuchRating(), 1));
            reviewsTxtCount.setText("" + responseD.getResponse().getReviewCount());
            avgRatingCountTxtMain.setText(CommonUtils.formatDecimalPoint(responseD.getResponse().getAvgRating(), 1));
            totalRatingsCountTxt.setText(responseD.getResponse().getReviewCount() + " Ratings");
            ratingBarBarberDetail.setRating(Float.parseFloat(responseD.getResponse().getAvgRating()));
            expertiesCountTxtMain.setText(CommonUtils.formatDecimalPoint(responseD.getResponse().getAvgExpertiseRating(), 1));
            valueCountTxtMain.setText(CommonUtils.formatDecimalPoint(responseD.getResponse().getAvgExpertiseRating(), 1));
            hygenicCountTxtMain.setText(CommonUtils.formatDecimalPoint(responseD.getResponse().getAvgHygieneRating(), 1));
            try {
                if (responseD.getResponse().getReviewCount() > 0) {
                    oneStartCounts.setText("" + responseD.getResponse().getOneStarRatingCount());
                    oneStartProgress.setProgress((int) (((float) responseD.getResponse().getOneStarRatingCount() / (float) responseD.getResponse().getReviewCount()) * 100));

                    twoStartCounts.setText("" + responseD.getResponse().getTwoStarRatingCount());
                    twoStartProgress.setProgress((int) (((float) responseD.getResponse().getTwoStarRatingCount() / (float) responseD.getResponse().getReviewCount()) * 100));

                    threeStartCounts.setText("" + responseD.getResponse().getThreeStarRatingCount());
                    threeStartProgress.setProgress((int) (((float) responseD.getResponse().getThreeStarRatingCount() / (float) responseD.getResponse().getReviewCount()) * 100));

                    fourStartCounts.setText("" + responseD.getResponse().getFourStarRatingCount());
                    fourStartProgress.setProgress((int) (((float) responseD.getResponse().getFourStarRatingCount() / (float) responseD.getResponse().getReviewCount()) * 100));

                    fiveStartCounts.setText("" + responseD.getResponse().getFiveStarRatingCount());
                    fiveStartProgress.setProgress((int) (((float) responseD.getResponse().getFiveStarRatingCount() / (float) responseD.getResponse().getReviewCount()) * 100));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (responseD.getResponse().getReviewList() != null && responseD.getResponse().getReviewList().size() > 0) {
                reviewAdapter.updateList(responseD.getResponse().getReviewList());
            }
        }
    }

    private void getBarberRatings() {
        presenter.getBarberRatings(NetworkConstatnts.RequestCode.API_BARBER_RATINGS, responseModel.getUserID(), false);
    }

    private void updateUserDeatailView(BarberDetialResponse.InfoBean responseModel) {
        barberName.setText(responseModel.getFullName());
        if (!responseModel.getUserName().contains("@"))
            barberUserName.setText("@" + responseModel.getUserName());
        else
            barberUserName.setText(responseModel.getUserName());
        AboutBarberName.setText(responseModel.getFullName());
        AboutBarberNumber.setText(responseModel.getPhoneNumber());
        if (responseModel.getDescription() != null)
            AboutBarberDesd.setText(responseModel.getDescription());
        else AboutBarberDesd.setText(R.string.str_no_description);

        try {
            coverSlider.setImageURI(CommonUtils.getValidUrl(responseModel.getBannerImage()));
        } catch (Exception e) {

        }
        try {
            barberImage.setImageURI(CommonUtils.getValidUrl(responseModel.getProfileImage()));
            AboutBarberImage.setImageURI(CommonUtils.getValidUrl(responseModel.getProfileImage()));
            barberOpeningHours.setText(presenter.getopeningTimesString(responseModel.getOpeningHours()));
        } catch (Exception e) {

        }
        try {
            barberCalloutHours.setText(presenter.getCalloutTimesString(responseModel.getCallOutHours()));
        } catch (Exception e) {

        }
        if (mPref.getPrefrencesBoolean(GlobalValues.KEYS.isLogedIn))
            chkHedaer.setClickable(true);
        else chkHedaer.setClickable(false);
        chkHedaer.setChecked(responseModel.isFavourite());
        if (responseModel.getUserAddresses() != null) {
            barberAddress.setText(responseModel.getUserAddresses().getAddressLine1() + " " + responseModel.getUserAddresses().getCity());
        }
        if (responseModel.getPortfolioImageCount() != 0) {
            totalImages.setText(responseModel.getPortfolioImageCount() + " " + getString(R.string.str_photos));
        }

        barnerTypeHolderView.setVisibility(View.GONE);//Below  view  is currently hidden.
        if (responseModel.getBarberTypes() != null && responseModel.getBarberTypes().size() > 0) {
            for (int i = 0; i < responseModel.getBarberTypes().size(); i++) {
                View view = LayoutInflater.from(this).inflate(R.layout.text_view, null);
                TextView textV = (TextView) view.findViewById(R.id.custom_text);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(2, 2, 2, 2);
                textV.setLayoutParams(params);
                textV.setText(responseModel.getBarberTypes().get(i).getMItem2());
                barnerTypeHolderView.addView(view);
                if (responseModel.getBarberTypes().get(i).getMItem1().equals("2")) {
                    isCalloutAvailable = true;
                    bookApointment.setText(R.string.str_book);
                }
                if (responseModel.getBarberTypes().get(i).getMItem1().equals("2") && responseModel.getBarberTypes().size() == 1) {
                    isCalloutAvailable = true;
                    bookApointment.setText(R.string.str_book_callout);
                }
            }
            if (responseModel.getBarberType().contains("1") && responseModel.getBarberType().contains("2")) {
                layoutCalloutTimimgs.setVisibility(View.VISIBLE);
                layoutRegularTimimgs.setVisibility(View.VISIBLE);
            } else {
                if (responseModel.getBarberType().contains("1")) {
                    layoutRegularTimimgs.setVisibility(View.VISIBLE);
                } else if (responseModel.getBarberType().contains("2")) {
                    layoutCalloutTimimgs.setVisibility(View.VISIBLE);
                }
            }
        }
        if (responseModel.isSuperBarber()) {
            barberStatus.setVisibility(View.VISIBLE);
        } else {
            barberStatus.setVisibility(View.GONE);
        }

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

    public static BarberDetailActivity getInstance() {
        return instance;
    }
}
