package com.app.barber.ui.postauth.activities.barber.booking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.request.PreBookingRequestModel;
import com.app.barber.models.response.BookingResponseModel;
import com.app.barber.ui.postauth.activities.barber.PreBookingDetailResponse;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.views.CustomTextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 17/12/18.
 */

public class BookingStatusActivity extends BaseActivity {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.booking_status_imsge)
    ImageView bookingStatusImsge;
    @BindView(R.id.barber_image)
    SimpleDraweeView barberImage;
    @BindView(R.id.barber_name)
    CustomTextView barberName;
    @BindView(R.id.barber_user_name)
    CustomTextView barberUserName;
    @BindView(R.id.barber_status)
    CustomTextView barberStatus;
    @BindView(R.id.booking_address)
    CustomTextView bookingAddress;
    @BindView(R.id.booking_distance)
    CustomTextView bookingDistance;
    @BindView(R.id.booking_time_slot)
    CustomTextView bookingTimeSlot;
    @BindView(R.id.booking_duration)
    CustomTextView bookingDuration;
    @BindView(R.id.booking_date)
    CustomTextView bookingDate;
    @BindView(R.id.booking_day)
    CustomTextView bookingDay;
    @BindView(R.id.back_home_btn)
    CustomTextView backHomeBtn;
    @BindView(R.id.booking_status)
    CustomTextView bookingStatus;
    @BindView(R.id.booking_id)
    CustomTextView bookingId;
    @BindView(R.id.booked_services)
    CustomTextView bookedServices;
    @BindView(R.id.booked_price)
    CustomTextView bookedPrice;
    private BookingResponseModel bookingDetails;
    private boolean bookingPaymentType;//recent booking mode.

    @Override
    public int getLayoutId() {
        return R.layout.booking_status_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getintentData(getIntent());
    }

    private void getintentData(Intent intent) {
        bookingPaymentType = intent.getBooleanExtra(GlobalValues.KEYS.IS_CARD_PAYMENT, false);//Get pass data with its key value
        Serializable dataArgument = intent.getSerializableExtra(GlobalValues.KEYS.APPOINTMENT_STATUS_DETAIL);//Get pass data with its key value
        bookingDetails = ((BookingResponseModel) dataArgument);
        Log.d(" getIntentData ", " 1 " + new Gson().toJson(bookingDetails));
        if (bookingDetails != null && bookingDetails.getStatus() != 0) {
            switch (bookingDetails.getStatus()) {
                case 201:
                    bookingStatusImsge.setImageResource(R.drawable.g_check);
                    bookingStatus.setText(R.string.str_booking_success);
                    bookingId.setVisibility(View.VISIBLE);
                    bookingId.setText(getString(R.string.str_booking_id) + " " + bookingDetails.getResponse().getBookingUniqueId());
                    break;
                case 401:
                    bookingStatusImsge.setImageResource(R.drawable.g_failed);
                    bookingStatus.setText(R.string.str_booking_failed);
                    bookingId.setVisibility(View.GONE);
                    break;
            }
            if (bookingDetails.getResponse() != null)
                setupData(bookingDetails);
        } else {
            Serializable preData = intent.getSerializableExtra(GlobalValues.KEYS.PRE_BOOKING_RESPONSE_DATA);//Get pass data with its key value
            PreBookingDetailResponse bookingRequestData = ((PreBookingDetailResponse) preData);
            if (bookingRequestData != null && bookingRequestData.getList() != null)
                setupPreBookingData(bookingRequestData);
            bookingStatusImsge.setImageResource(R.drawable.g_failed);
            bookingStatus.setText(R.string.str_booking_failed);
            bookingId.setVisibility(View.GONE);
        }
    }

    private void setupPreBookingData(PreBookingDetailResponse bookingRequestData) {
        try {
            barberImage.setImageURI(CommonUtils.getValidUrl(bookingRequestData.getList().getProfileImage()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        barberName.setText(bookingRequestData.getList().getFullName());
        barberUserName.setText(bookingRequestData.getList().getUserName());
        bookingTimeSlot.setText(bookingRequestData.getList().getTimeSlot());
        bookingDate.setText(bookingRequestData.getList().getDate());
        if (bookingRequestData.getList().getAddress() != null && bookingRequestData.getList().getAddress().getAddressLine1() != null)
            bookingAddress.setText(bookingRequestData.getList().getAddress().getAddressLine1());
        else bookingAddress.setText(R.string.str_not_found);
        bookingDuration.setText(bookingRequestData.getList().getTotalDuration() + "min");
        try {
            bookingDay.setText(bookingRequestData.getList().getDate().split(",")[0]);
            bookedServices.setText(getServicesNames(bookingDetails.getResponse().getServices()));
        } catch (Exception e) {
        }

        try {
            if (bookingPaymentType)
                bookedPrice.setText(bookingRequestData.getList().getServices().get(bookingRequestData.getList().getServices().size() - 1).getmItem3() + "" +
                        bookingRequestData.getList().getServices().get(bookingRequestData.getList().getServices().size() - 1).getMItem2());
            else bookedPrice.setText(bookingRequestData.getList().getAmountWithOutTax());
        } catch (Exception e) {
            bookedPrice.setText(bookingRequestData.getList().getServices().get(bookingRequestData.getList().getServices().size() - 1).getmItem3() + "" +
                    bookingRequestData.getList().getServices().get(bookingRequestData.getList().getServices().size() - 1).getMItem2());
        }
        if (bookingRequestData.getList().isSuperBarber()) {
            barberStatus.setVisibility(View.VISIBLE);
        } else {
            barberStatus.setVisibility(View.GONE);
        }
    }

    private void setupData(BookingResponseModel bookingDetails) {
        try {
            barberImage.setImageURI(CommonUtils.getValidUrl(bookingDetails.getResponse().getProfileImage()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        barberName.setText(bookingDetails.getResponse().getFullName());
        barberUserName.setText(bookingDetails.getResponse().getUserName());
        bookingTimeSlot.setText(bookingDetails.getResponse().getTimeSlot());
        bookingDate.setText(bookingDetails.getResponse().getDate());
        if (bookingDetails.getResponse().getAddress() != null && bookingDetails.getResponse().getAddress().getAddressLine1() != null)
            bookingAddress.setText(bookingDetails.getResponse().getAddress().getAddressLine1());
        else bookingAddress.setText(R.string.str_not_found);
        bookingDuration.setText(bookingDetails.getResponse().getTotalDuration() + "min");
        try {
            bookingDay.setText(bookingDetails.getResponse().getDate().split(",")[0]);
        } catch (Exception e) {
        }
        bookedServices.setText(getServicesNames(bookingDetails.getResponse().getServices()));
        try {
            if (bookingPaymentType)
                bookedPrice.setText(bookingDetails.getResponse().getServices().get(bookingDetails.getResponse().getServices().size() - 1).getMItem3() + "" +
                        bookingDetails.getResponse().getServices().get(bookingDetails.getResponse().getServices().size() - 1).getMItem2());
            else bookedPrice.setText(bookingDetails.getResponse().getAmountWithOutTax());
        } catch (Exception e) {
            bookedPrice.setText(bookingDetails.getResponse().getServices().get(bookingDetails.getResponse().getServices().size() - 1).getMItem3() + "" +
                    bookingDetails.getResponse().getServices().get(bookingDetails.getResponse().getServices().size() - 1).getMItem2());
        }

        if (bookingDetails.getResponse().isSuperBarber()) {
            barberStatus.setVisibility(View.VISIBLE);
        } else {
            barberStatus.setVisibility(View.GONE);
        }
    }

    private String getServicesNames(List<BookingResponseModel.ResponseBean.ServicesBean> services) {
        String selectedType = null;
        try {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < services.size(); i++) {
                if (i < (services.size() - 1))
                    builder.append(services.get(i).getMItem1() + ",");
            }
            selectedType = builder.toString();
            if (selectedType != null && selectedType.length() > 0 && selectedType.charAt(selectedType.length() - 1) == ',') {
                selectedType = selectedType.substring(0, selectedType.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectedType;
    }

    @OnClick({R.id.back_toolbar, R.id.back_home_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.back_home_btn:
                onBackPressed();
                break;
        }
    }
}
