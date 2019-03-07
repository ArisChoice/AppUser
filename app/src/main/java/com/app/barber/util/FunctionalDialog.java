package com.app.barber.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.models.BookingData;
import com.app.barber.models.HomeFiltersModel;
import com.app.barber.models.ModelDay;
import com.app.barber.models.request.EditBookingRequestModel;
import com.app.barber.models.request.RequestBarberModel;
import com.app.barber.models.request.UpdateEditBookingStatusModel;
import com.app.barber.models.response.LoginResponseModel;
import com.app.barber.models.response.MyBookingsResponseMOdel;
import com.app.barber.models.response.SpecialisationResponseModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.adapters.filtersadapter.HomeFilterAdapter;
import com.app.barber.ui.postauth.activities.barber.BarberDetailActivity;
import com.app.barber.ui.postauth.activities.barber.barber_adapter.SpecialisationAdapter;
import com.app.barber.ui.postauth.activities.barber.booking.BookingSheetFragment;
import com.app.barber.ui.postauth.activities.barber.ratesettings.RateBarberActivity;
import com.app.barber.ui.postauth.activities.home.home_adapter.AppointmentsListAdapter;
import com.app.barber.ui.postauth.activities.home.home_adapter.EditAppointmentsAdapter;
import com.app.barber.ui.weekcontroler.WeekViewAdapter;
import com.app.barber.util.iface.OnBottomDialogItemListener;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harish on 18/12/18.
 */

public class FunctionalDialog {

    private static final String TAG = FunctionalDialog.class.getName();

    /**
     * Dialog appointment received.
     */


    public void openDialogAppointment(FragmentActivity activity, Object obj, final OnBottomDialogItemListener listener) {
        boolean isBookingCompleted;
        final Dialog mBottomSheetDialog = new Dialog(activity, R.style.MaterialDialogSheet);
        View child = activity.getLayoutInflater().inflate(R.layout.view_appointmnet_request_dialog, null);
        mBottomSheetDialog.setContentView(child);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
        final TextView customerName = child.findViewById(R.id._about_customer_name);
        final TextView customerNumber = child.findViewById(R.id._about_customer_number);
        final TextView timeSlot = child.findViewById(R.id.booking_time_slot);
        final TextView bookingDate = child.findViewById(R.id.booking_date);
        final TextView bookingDay = child.findViewById(R.id.booking_day);
        final TextView serviceDuration = child.findViewById(R.id.booking_duration);
        final TextView bookingServices = child.findViewById(R.id.booking_services);
        final TextView bookingPrice = child.findViewById(R.id.booking_price);
        final SimpleDraweeView customerImage = child.findViewById(R.id._about_customer_image);
        final TextView address = child.findViewById(R.id.booking_address);
        final TextView distance = child.findViewById(R.id.booking_distance);
        final TextView reorder = child.findViewById(R.id.complete_btn);
        final TextView cancelBtn = child.findViewById(R.id.cancel_btn);
        final TextView statusText = child.findViewById(R.id.status_Text);
        final TextView editText = child.findViewById(R.id.txt_edit);
        final TextView messageText = child.findViewById(R.id.message_text);
        final TextView appointmentStatus = child.findViewById(R.id.appointment_status);
        final ImageView messageBtn = child.findViewById(R.id.message_btn);
        final ImageView callBtn = child.findViewById(R.id.call_btn);
        final LinearLayout servicePriceHolder = child.findViewById(R.id.service_and_prise_holder);
        servicePriceHolder.setVisibility(View.VISIBLE);
        MyBookingsResponseMOdel.ListBean currentData = (MyBookingsResponseMOdel.ListBean) obj;
        if (currentData != null) {
            customerName.setText(currentData.getName());
            customerNumber.setText(currentData.getPhone());
            timeSlot.setText(currentData.getTimingSlot());
//          bookingDate.setText(CustomDate.formatThis(GlobalValues.DATE_FORMAT.STANDARD, currentData.getDateString()));
            customerImage.setImageURI(CommonUtils.getValidUrl(currentData.getProfileImage()));
            bookingServices.setText(currentData.getServiceNames());
            bookingPrice.setText(GlobalValues.Currency.POUNDS + currentData.getAmount());
            bookingDate.setText(CustomDate.getCurrentFormat(activity, currentData.getDateString(), "MM/dd/yyyy", "EEEE,MMMM dd"));
            bookingDay.setText(CustomDate.getCurrentFormat(activity, currentData.getDateString(), "MM/dd/yyyy", "EEEE"));

            try {
                if (currentData.getAddress() != null) {
                    address.setText(currentData.getAddress().getAddressLine1());
                    distance.setText(currentData.getDistance() + "m");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                serviceDuration.setText(currentData.getDuration() + " min");
            } catch (Exception e) {
                serviceDuration.setText("0 min");
            }
            appointmentStatus.setVisibility(View.VISIBLE);
            appointmentStatus.setText(currentData.getAppointmentStatus().split("\\s+")[0]);
            if (currentData.isCompleted()) {
                cancelBtn.setVisibility(View.GONE);
                statusText.setText(R.string.str_completed);
                statusText.setVisibility(View.VISIBLE);
                statusText.setBackgroundResource(R.drawable.rectangle_green_drawable);
                statusText.setTextColor(activity.getResources().getColor(R.color.color_white));
            }

            if (appointmentStatus.getText().toString().equals("Past") || currentData.isCanceled()) {//if past booking.
                cancelBtn.setVisibility(View.GONE);
                editText.setVisibility(View.GONE);
            } else {
                if (currentData.getBookingType() == GlobalValues.BARBER_TYPES.BARBER) {//if booking type qappointment
                    editText.setVisibility(View.VISIBLE);
                } else if (currentData.getBookingType() == GlobalValues.BARBER_TYPES.CALLOUT_BARBER && currentData.isConfirmed()) {//if booking type callout and accepted.
                    editText.setVisibility(View.VISIBLE);
                }
            }
            //If  remaining time is less then 3 hours.
            if (CustomDate.getTimeDifferenceinHours(CustomDate.getCurrentMonth(activity, GlobalValues.DateFormats.FULL_DATE_TIME),
                    currentData.getDateString() + " " + currentData.getTimingSlot().split("-")[0], GlobalValues.DateFormats.FULL_DATE_TIME) <= 3) {
                editText.setVisibility(View.GONE);
            }

            if (currentData.getEditData() != null && !currentData.getEditData().equals("")) {
                editText.setText(R.string.str_edited);
                editText.setEnabled(false);
                messageText.setVisibility(View.VISIBLE);
                timeSlot.setText(currentData.getEditData().split("@")[1]);
                bookingDate.setText(CustomDate.getCurrentFormat(activity, currentData.getEditData().split("@")[0], "MM/dd/yyyy", "EEEE,MMMM dd"));
                bookingDay.setText(CustomDate.getCurrentFormat(activity, currentData.getEditData().split("@")[0], "MM/dd/yyyy", "EEEE"));

                if (currentData.getEditData().split("@")[2].equals("" + GlobalValues.UserTypes.BARBER)) {
                    cancelBtn.setText(R.string.str_reject);
                    reorder.setText(R.string.str_accept_);
                    messageText.setText(currentData.getName() + " " + activity.getString(R.string.str_sent_you_edit_request));
                } else {
                    messageText.setText(R.string.str_you_sent_edit_request);
                    cancelBtn.setText(R.string.str_cancel_request);
                    reorder.setVisibility(View.GONE);
                }
            }
        }
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditBookingRequestModel eRequest = new EditBookingRequestModel();
                eRequest.setBarberId(currentData.getBarberId());
                eRequest.setBookingId(currentData.getId());
                eRequest.setBookingType(String.valueOf(currentData.getBookingType()));

                BookingData bData = new BookingData();
                bData.setBookingType(String.valueOf(currentData.getBookingType()));
                bData.setTotalAmount(currentData.getAmount());
                bData.setBarberId(currentData.getBarberId());
                bData.setBookedServicesId(currentData.getServiceId());
                BookingSheetFragment bottomSheetFragment = new BookingSheetFragment();
                Bundle data = new Bundle();//create bundle instance
                data.putBoolean(GlobalValues.KEYS.IS_EDIT, true);

                data.putSerializable(GlobalValues.KEYS.EDIT_REQUEST_DATA, eRequest);//Edit request data;
                data.putSerializable(GlobalValues.KEYS.BOOKING_DATA, bData);//Booking data;
                switch (currentData.getBookingType()) {
                    case GlobalValues.BARBER_TYPES.BARBER:
                        data.putInt(GlobalValues.KEYS.BOOKING_TYPE, GlobalValues.BARBER_TYPES.BARBER);//put string to pass with a key value
                        break;
                    case GlobalValues.BARBER_TYPES.CALLOUT_BARBER:
                        data.putInt(GlobalValues.KEYS.BOOKING_TYPE, GlobalValues.BARBER_TYPES.CALLOUT_BARBER);//put string to pass with a key value
                        break;

                }
                bottomSheetFragment.setArguments(data);
                bottomSheetFragment.show(activity.getSupportFragmentManager(), bottomSheetFragment.getTag());
                mBottomSheetDialog.dismiss();
            }
        });
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentLocationSinglton.getInstance().getCurrentLocation(activity, new CurrentLocationSinglton.CurrentLocationCallback() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            Log.d("Test", " lati " + location.getLatitude() + " long " + location.getLongitude());
                            new CommonUtils().navigateUsertoMap(activity, "" + location.getLatitude(), "" + location.getLongitude(),
                                    "" + currentData.getAddress().getLat(), "" + currentData.getAddress().getLong());
                        }
                    }

                    @Override
                    public void onFailure() {
                    }
                });
            }
        });
        cancelBtn.setOnClickListener(v -> {
            mBottomSheetDialog.dismiss();
            if (cancelBtn.getText().toString().equals(activity.getString(R.string.str_cancel_request))) {
                listener.onItemClick(child, 0, GlobalValues.RequestCodes.CANCEL_EDIT_REQUEST, currentData);//false
            } else if (cancelBtn.getText().toString().equals(activity.getString(R.string.str_reject))) {
                listener.onItemClick(child, 0, GlobalValues.RequestCodes.REJECT_REQUEST, currentData);//false
            } else {
                if (!currentData.isCanceled())
                    listener.onItemClick(child, 0, GlobalValues.RequestCodes.CANCEL, currentData);//false
                else if (currentData.isCanceled())
                    new CommonUtils().ShowToast("Booking already cancelled");
            }
        });
        reorder.setOnClickListener(v -> {
            mBottomSheetDialog.dismiss();
            if (reorder.getText().toString().equals(activity.getString(R.string.str_accept_))) {
                listener.onItemClick(child, 0, GlobalValues.RequestCodes.ACCEPT_REQUEST, currentData);//false
            } else {
                listener.onItemClick(child, 0, GlobalValues.RequestCodes.REORDER, null);//false
//                new CommonUtils().ShowToast("Working on this feature");

//                listener.onItemClick(child, 0, 1, currentData);//true
            }
        });
        messageBtn.setOnClickListener(v -> {
            listener.onItemClick(child, 0, GlobalValues.RequestCodes.MESSAGE, currentData);//true
        });
        callBtn.setOnClickListener(v -> {
            listener.onItemClick(child, 0, GlobalValues.RequestCodes.CALL, currentData);//true
        });
    }

    /**
     * Dialog appointment received.
     */
    public void openDialogRelevnceFilter(Activity activity, Object obj, final OnBottomDialogItemListener listener) {

        final Dialog mBottomSheetDialog = new Dialog(activity, R.style.MaterialDialogSheet);
        View child = activity.getLayoutInflater().inflate(R.layout.view_relevnce_filter_dialog, null);
        mBottomSheetDialog.setContentView(child);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
        RecyclerView recyclerViewList = child.findViewById(R.id.recyclar_view_lst);
        RequestBarberModel rModl = (RequestBarberModel) obj;

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(layoutManager);
        HomeFilterAdapter filterAdapter = new HomeFilterAdapter(activity, getAvailableFilters(), new OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int positio, int type, Object o) {

                rModl.getFilter().setSoryBy(((HomeFiltersModel) o).getFilterKey());
                listener.onItemClick(child, 0, 1, rModl);//true
                mBottomSheetDialog.dismiss();
            }
        });

        recyclerViewList.setAdapter(filterAdapter);
        if (rModl.getFilter() == null) {
            RequestBarberModel rModel = new RequestBarberModel();
            RequestBarberModel.Filter fModel = rModel.new Filter();
            fModel.setDate("");
            fModel.setDayAvailability("");
//            fModel.setDate(new CustomDate().getCurrentMonth(activity, GlobalValues.DateFormats.DEFAULT_FORMAT_DATE));//default
//            fModel.setDayAvailability(activity.getString(R.string.str_any_time));//default
            rModl.setFilter(fModel);
        } else {
            if (rModl.getFilter().getSoryBy() != null) {
                filterAdapter.setSelected(rModl.getFilter().getSoryBy());
            }
        }
    }


    private ArrayList<HomeFiltersModel> getAvailableFilters() {
        ArrayList<HomeFiltersModel> filterList = new ArrayList<>();
        String[] name = BarberApplication.getInstance().getResources().getStringArray(R.array.filter_names);//filter names
        String[] namekey = BarberApplication.getInstance().getResources().getStringArray(R.array.filter_names_key);//filter keys

        for (int i = 0; i < name.length; i++) {
            HomeFiltersModel fModel = new HomeFiltersModel();
            fModel.setFilterName(name[i]);
            fModel.setSelected(false);
            fModel.setFilterKey(namekey[i]);
            filterList.add(fModel);
        }
        return filterList;
    }

    /**
     * Dialog booking type.
     */
    int typeSelected;

    public void openDialogBookingType(Activity activity, Object obj, final OnBottomDialogItemListener listener) {

        final Dialog mBottomSheetDialog = new Dialog(activity, R.style.MaterialDialogSheet);
        View child = activity.getLayoutInflater().inflate(R.layout.view_booking_type_dialog, null);
        mBottomSheetDialog.setContentView(child);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.setCanceledOnTouchOutside(false);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
        CustomTextView barberType = child.findViewById(R.id.type_barber);
        CustomTextView barberCallout = child.findViewById(R.id.type_callout);
        CustomTextView continueBtn = child.findViewById(R.id.continue_btn);


        barberType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barberType.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.barber_icon_active, 0, 0);
                barberCallout.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.callout_barber_icon_normal, 0, 0);
                barberType.setTextColor(activity.getResources().getColor(R.color.color_app_blue));
                barberCallout.setTextColor(activity.getResources().getColor(R.color.color_grey));

                typeSelected = GlobalValues.BARBER_TYPES.BARBER;
                continueBtn.setVisibility(View.VISIBLE);

            }
        });
        barberCallout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barberType.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.barber_icon_normal, 0, 0);
                barberCallout.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.callout_barber_icon_active, 0, 0);
                barberType.setTextColor(activity.getResources().getColor(R.color.color_grey));
                barberCallout.setTextColor(activity.getResources().getColor(R.color.color_app_blue));

                typeSelected = GlobalValues.BARBER_TYPES.CALLOUT_BARBER;
                continueBtn.setVisibility(View.VISIBLE);
            }
        });
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
                listener.onItemClick(child, 0, typeSelected, null);
            }
        });
    }

    /**
     * Dialog filter
     */
    String paymentType = null;

    public void openDialogFilter(Activity activity, Object obj, final OnBottomDialogItemListener listener) {

        final Dialog mBottomSheetDialog = new Dialog(activity, R.style.MaterialDialogSheet);
        View child = activity.getLayoutInflater().inflate(R.layout.view_filter_dialog, null);
        mBottomSheetDialog.setContentView(child);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
        CheckBox superBarberChkbx = child.findViewById(R.id.chkboc_filter_super_only_barber);
        CheckBox isTraneechkbox = child.findViewById(R.id.chkboc_filter_trainee_only_barber);
        CustomTextView applyBtn = child.findViewById(R.id.apply_btn);
        CustomTextView resetBtn = child.findViewById(R.id.reset_filter_btn);
        CheckBox cardRbtn = child.findViewById(R.id.radio_btn_Card);
        CheckBox cashRbtn = child.findViewById(R.id.radio_btn_cash);
        // Seek bar for which we will set text color in code
        RangeSeekBar rangeSeekBarTextColorWithCode = (RangeSeekBar) child.findViewById(R.id.priceRange);
        rangeSeekBarTextColorWithCode.setTextAboveThumbsColorResource(R.color.color_app_blue);

        RangeSeekBar priceRange = child.findViewById(R.id.priceRange);
        RecyclerView stylerecyclar = child.findViewById(R.id.style_recyclar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        stylerecyclar.setLayoutManager(layoutManager);
        String styleData = new PreferenceManager().getSpecialisation();
        SpecialisationResponseModel styleModel = new Gson().fromJson(styleData, SpecialisationResponseModel.class);
        SpecialisationAdapter specAdapter = null;
        if (styleModel != null && styleModel.getResponse() != null && styleModel.getResponse().size() > 0) {
            specAdapter = new SpecialisationAdapter(activity, styleModel.getResponse(),
                    new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position, int type, Object o) {
//                    Log.e("Onitem click ", " " + specAdapter.getselected());

                        }
                    });
            stylerecyclar.setAdapter(specAdapter);
        }
        RequestBarberModel rModl = (RequestBarberModel) obj;
        if (rModl.getFilter() == null) {
            RequestBarberModel rModel = new RequestBarberModel();
            RequestBarberModel.Filter fModel = rModel.new Filter();
            fModel.setDate("");
            fModel.setDayAvailability("");
//            fModel.setDate(new CustomDate().getCurrentMonth(activity, GlobalValues.DateFormats.DEFAULT_FORMAT_DATE));//default
//            fModel.setDayAvailability(activity.getString(R.string.str_any_time));//default
            rModl.setFilter(fModel);
        } else {
            isTraneechkbox.setChecked(rModl.getFilter().isTrainee());
            superBarberChkbx.setChecked(rModl.getFilter().isSuperBarber());
            if (rModl.getFilter().getStyleType() != null && !rModl.getFilter().getStyleType().equals("") && specAdapter != null)
                specAdapter.setSelected(rModl.getFilter().getStyleType());
            if (rModl.getFilter().getPaymentType() != null && !rModl.getFilter().getPaymentType().equals("")) {
                if (rModl.getFilter().getPaymentType().contains("1")) {
                    cardRbtn.setChecked(true);
                } else if (rModl.getFilter().getPaymentType().contains("2")) {
                    cashRbtn.setChecked(true);
                } else if (rModl.getFilter().getPaymentType().contains("3")) {
                    cardRbtn.setChecked(true);
                    cashRbtn.setChecked(true);
                }
            }
            if (rModl.getFilter().getMinPrice() != null) {
                priceRange.setSelectedMinValue(Integer.parseInt(rModl.getFilter().getMinPrice()));
            }
            if (rModl.getFilter().getMaxPrice() != null) {
                priceRange.setSelectedMaxValue(Integer.parseInt(rModl.getFilter().getMaxPrice()));
            }
        }

        SpecialisationAdapter finalSpecAdapter = specAdapter;
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
                rModl.setFilter(null);
                listener.onItemClick(child, 0, 0, rModl);

            }
        });
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
                rModl.getFilter().setMinPrice("" + priceRange.getSelectedMinValue());
                rModl.getFilter().setMaxPrice("" + priceRange.getSelectedMaxValue());
                rModl.getFilter().setTrainee(isTraneechkbox.isChecked());
                rModl.getFilter().setSuperBarber(superBarberChkbx.isChecked());
                if (finalSpecAdapter != null && finalSpecAdapter.getItemCount() > 0)
                    rModl.getFilter().setStyleType(finalSpecAdapter.getselected());

                if (cardRbtn.isChecked()) {
                    paymentType = "1";
                    rModl.getFilter().setPaymentType(paymentType);
                }
                if (cashRbtn.isChecked()) {
                    paymentType = "2";
                    rModl.getFilter().setPaymentType(paymentType);
                }
                if (cardRbtn.isChecked() && cashRbtn.isChecked()) {
                    paymentType = "3";
                    rModl.getFilter().setPaymentType(paymentType);
                }
                if (!cardRbtn.isChecked() && !cashRbtn.isChecked()) {
                    paymentType = "3";
                    rModl.getFilter().setPaymentType(null);
                }
                listener.onItemClick(child, 0, 0, rModl);
            }
        });
    }

    /**
     * Dialog appointment received.
     */
    public void openDialogFilterDay(Activity activity, Object obj, final OnBottomDialogItemListener listener) {

        final Dialog mBottomSheetDialog = new Dialog(activity, R.style.MaterialDialogSheet);
        View child = activity.getLayoutInflater().inflate(R.layout.view_day_filter_dialog, null);
        mBottomSheetDialog.setContentView(child);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
        CustomTextView cnFrmBtn = child.findViewById(R.id.btn_cnfrm);
        CustomTextView anyTime = child.findViewById(R.id.btn_anytime);
        CustomTextView morningTime = child.findViewById(R.id.btn_morning);
        CustomTextView afterNoon = child.findViewById(R.id.btn_afternoon);
        RecyclerView weekRecyclar = child.findViewById(R.id.weekViewRecyclar);
        RequestBarberModel rModl = (RequestBarberModel) obj;

        WeekViewAdapter weekAdapter = new WeekViewAdapter(activity, new ArrayList<ModelDay>(), new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object o) {
                Log.e(" onItemClick ", " 1 -- " + position);

                ModelDay positionData = (ModelDay) o;
                Log.e(" onItemClick ", " 2 -- " + positionData.getFullDate());
                rModl.getFilter().setDate(positionData.getFullDate());
            }
        });
        LinearLayoutManager lManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        weekRecyclar.setLayoutManager(lManager);
        weekRecyclar.setAdapter(weekAdapter);
        weekAdapter.setCurrentWeek(56);//next 56 days visible
        if (rModl.getFilter() == null) {
            RequestBarberModel rModel = new RequestBarberModel();
            RequestBarberModel.Filter fModel = rModel.new Filter();
            fModel.setDate("");
            fModel.setDayAvailability("");
            weekAdapter.disableCurrentdayDefaultSelection();
//            fModel.setDate(new CustomDate().getCurrentMonth(activity, GlobalValues.DateFormats.DEFAULT_FORMAT_DATE));//default
//            fModel.setDayAvailability(activity.getString(R.string.str_any_time));//default
            rModl.setFilter(fModel);
        } else {
            if (rModl.getFilter().getDate() != null)
                weekAdapter.setselected(rModl.getFilter().getDate());

            if (rModl.getFilter().getDayAvailability() != null && !rModl.getFilter().getDayAvailability().equals(""))
                if (rModl.getFilter().getDayAvailability().equals(activity.getString(R.string.str_any_time))) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            anyTime.performClick();
                        }
                    }, GlobalValues.TIME_DURATIONS.SMALL);

                } else if (rModl.getFilter().getDayAvailability().equals(activity.getString(R.string.str_morning))) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            morningTime.performClick();
                        }
                    }, GlobalValues.TIME_DURATIONS.SMALL);

                } else if (rModl.getFilter().getDayAvailability().equals(activity.getString(R.string.str_evening))) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            afterNoon.performClick();
                        }
                    }, GlobalValues.TIME_DURATIONS.SMALL);

                } else {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            anyTime.performClick();
                        }
                    }, GlobalValues.TIME_DURATIONS.SMALL);

                }
        }
        anyTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anyTime.setBackgroundResource(R.drawable.rectangle_blue_drawable);
                morningTime.setBackgroundResource(R.drawable.rectangle_grey_border);
                afterNoon.setBackgroundResource(R.drawable.rectangle_grey_border);
                anyTime.setTextColor(activity.getResources().getColor(R.color.color_white));
                morningTime.setTextColor(activity.getResources().getColor(R.color.color_dark_grey));
                afterNoon.setTextColor(activity.getResources().getColor(R.color.color_dark_grey));
                rModl.getFilter().setDayAvailability(activity.getString(R.string.str_any_time));
            }
        });
        morningTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anyTime.setBackgroundResource(R.drawable.rectangle_grey_border);
                morningTime.setBackgroundResource(R.drawable.rectangle_blue_drawable);
                afterNoon.setBackgroundResource(R.drawable.rectangle_grey_border);
                anyTime.setTextColor(activity.getResources().getColor(R.color.color_dark_grey));
                morningTime.setTextColor(activity.getResources().getColor(R.color.color_white));
                afterNoon.setTextColor(activity.getResources().getColor(R.color.color_dark_grey));
                rModl.getFilter().setDayAvailability(activity.getString(R.string.str_morning));
            }
        });
        afterNoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anyTime.setBackgroundResource(R.drawable.rectangle_grey_border);
                morningTime.setBackgroundResource(R.drawable.rectangle_grey_border);
                afterNoon.setBackgroundResource(R.drawable.rectangle_blue_drawable);
                anyTime.setTextColor(activity.getResources().getColor(R.color.color_dark_grey));
                morningTime.setTextColor(activity.getResources().getColor(R.color.color_dark_grey));
                afterNoon.setTextColor(activity.getResources().getColor(R.color.color_white));
                rModl.getFilter().setDayAvailability(activity.getString(R.string.str_evening));
            }
        });


        cnFrmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mBottomSheetDialog.dismiss();
//                rModl.getFilter().setDate(weekAdapter.getSelectedDate());
                listener.onItemClick(child, 0, 0, rModl);
            }
        });
    }


    public void openDialogCalloutLocation(Activity activity, Object o, final OnBottomDialogItemListener listener) {
        final Dialog mBottomSheetDialog = new Dialog(activity, R.style.MaterialDialogSheet);
        View child = activity.getLayoutInflater().inflate(R.layout.view_callout_location_dialog, null);
        mBottomSheetDialog.setContentView(child);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
//        TIME = getList();
        final LinearLayout currentBtn = child.findViewById(R.id.current_location);
        final LinearLayout newBtn = child.findViewById(R.id.new_location);

        final ImageView currentImg = child.findViewById(R.id.select_current_img);
        final ImageView newImg = child.findViewById(R.id.select_new_img);
        RequestBarberModel rModl = ((RequestBarberModel) o);
        if (rModl.getFilter() == null) {
            RequestBarberModel rModel = new RequestBarberModel();
            RequestBarberModel.Filter fModel = rModel.new Filter();
            fModel.setDate(new CustomDate().getCurrentMonth(activity, GlobalValues.DateFormats.DEFAULT_FORMAT_DATE));//default
            fModel.setDayAvailability(activity.getString(R.string.str_any_time));//default
            rModl.setFilter(fModel);
        } else {
            /*if (rModl.getFilter().getBarberType() != null && !rModl.getFilter().getBarberType().equals("")) {
                if (rModl.getFilter().getBarberType().contains("1")) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentBtn.performClick();
                        }
                    }, GlobalValues.TIME_DURATIONS.SMALL);
                } else if (rModl.getFilter().getBarberType().contains("2")) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            newBtn.performClick();
                        }
                    }, GlobalValues.TIME_DURATIONS.SMALL);
                }
            }*/
        }
        currentBtn.setOnClickListener(v -> {
            mBottomSheetDialog.dismiss();
            if (currentImg.getVisibility() == View.VISIBLE) {
                currentImg.setVisibility(View.GONE);
            } else {
                currentImg.setVisibility(View.VISIBLE);
                newImg.setVisibility(View.GONE);
//                rModl.getFilter().setBarberType(String.valueOf(GlobalValues.BARBER_TYPES.BARBER));
            }

        });
        newBtn.setOnClickListener(v -> {
            mBottomSheetDialog.dismiss();
            if (newImg.getVisibility() == View.VISIBLE) {
                newImg.setVisibility(View.GONE);
            } else {
//                rModl.getFilter().setBarberType(String.valueOf(GlobalValues.BARBER_TYPES.CALLOUT_BARBER));
                newImg.setVisibility(View.VISIBLE);
                currentImg.setVisibility(View.GONE);
            }
        });
        /*confirmBtn.setOnClickListener(v -> {
            mBottomSheetDialog.dismiss();
            if (newImg.getVisibility() == View.GONE && currentImg.getVisibility() == View.GONE) {
                rModl.getFilter().setBarberType("");
            }
            listener.onItemClick(child, 0, 2, rModl);
        });*/
    }


    public AlertDialog createAppRatingDialog(Activity act) {
        AlertDialog dialog = new AlertDialog.Builder(act).setPositiveButton(act.getString(R.string.dialog_app_rate), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                openAppInPlayStore(act);

            }
        })./*setNegativeButton(act.getString(R.string.dialog_your_feedback), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                openFeedback(act);

            }
        }).*/setNeutralButton(act.getString(R.string.dialog_ask_later), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                paramAnonymousDialogInterface.dismiss();

            }
        }).setMessage(act.getString(R.string.rate_app_message)).setTitle(act.getString(R.string.rate_app_title)).create();
        return dialog;
    }

    public static void openAppInPlayStore(Activity paramContext) {
        paramContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GlobalValues.APPLICATION_PLAYSTORE_URL_CUSTOMER)));
    }

    EditAppointmentsAdapter appAdapter;

    public Dialog openEditRequestsDialog(final Activity activity, List<MyBookingsResponseMOdel.ListBean> barberEditList,
                                         LoginResponseModel.UserBean userData, final OnBottomDialogItemListener listener) {

        final Dialog mBottomSheetDialog = new Dialog(activity, R.style.MaterialDialogSheet);
        View child = activity.getLayoutInflater().inflate(R.layout.view_edit_requests_dialog, null);
        mBottomSheetDialog.setContentView(child);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();

        final RecyclerView confirm = child.findViewById(R.id.edit_recyclar_view);

        appAdapter = new EditAppointmentsAdapter((FragmentActivity) activity, FunctionalDialog.class.getSimpleName(), getValidDataList((ArrayList<MyBookingsResponseMOdel.ListBean>) barberEditList), new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object o) {
                switch (type) {
                    case GlobalValues.ClickOperations.APAPTER_BOTTOM_DIALOG_CLICK:
                        new FunctionalDialog().openDialogAppointment((FragmentActivity) activity, o, (view1, position1, type1, t) -> {
                            MyBookingsResponseMOdel.ListBean positionData = (MyBookingsResponseMOdel.ListBean) o;
                            switch (type1) {
                                case 0:
//                                    presenter.updateBookingStatus(NetworkConstatnts.RequestCode.API_UPDATE_BOOKING_STATUS,
//                                            new UpdateBookingRequestModel(positionData.getId(), false, positionData.getBookingType(), getUserData().getUserID()), false);
//                                    appointmentsAdapter.remove(position1);
                                    break;
                                case 1:

                                    break;
                                case GlobalValues.RequestCodes.MESSAGE:
                                    if (positionData.getQBdialogId() != null) {
//                                        Intent intent = new Intent(activity, ChatWorkActivity.class);
//                                        intent.putExtra(GlobalValues.KEYS.EXTRA_DIALOG_ID, positionData.getQBdialogId());
//                                        intent.putExtra(GlobalValues.KEYS.OTHER_IMAGE, positionData.getUserImage());
//                                        startActivity(intent);
                                        listener.onItemClick(child, 1, GlobalValues.RequestCodes.MESSAGE, positionData);//true
                                    }
                                    break;
                                case GlobalValues.RequestCodes.CALL:
                                    if (positionData.getPhone() != null)
//                                        callInit(positionData.getPhone());
                                        listener.onItemClick(child, 1, GlobalValues.RequestCodes.CALL, (String) positionData.getPhone());//true
                                    break;
                                case GlobalValues.RequestCodes.REJECT_REQUEST:
                                    UpdateEditBookingStatusModel uRequest = new UpdateEditBookingStatusModel();
                                    uRequest.setBarberId(positionData.getBarberId());
                                    uRequest.setBookingId(positionData.getId());
                                    uRequest.setBookingType(positionData.getBookingType());
                                    uRequest.setDate(positionData.getEditData().split("@")[0]);
                                    uRequest.setTimingSlot(positionData.getEditData().split("@")[1]);
                                    uRequest.setUserId(userData.getUserID());
                                    uRequest.setEditedBy(GlobalValues.UserTypes.CUSTOMER);
                                    uRequest.setStatus(false);
                                    listener.onItemClick(child, 1, GlobalValues.RequestCodes.REJECT_REQUEST, uRequest);//true
                                    appAdapter.remove(position);
                                    refreshAdapter(appAdapter);
                                    break;
                                case GlobalValues.RequestCodes.ACCEPT_REQUEST:
                                    uRequest = new UpdateEditBookingStatusModel();
                                    uRequest.setBarberId(positionData.getBarberId());
                                    uRequest.setBookingId(positionData.getId());
                                    uRequest.setBookingType(positionData.getBookingType());
                                    uRequest.setDate(positionData.getEditData().split("@")[0]);
                                    uRequest.setTimingSlot(positionData.getEditData().split("@")[1]);
                                    uRequest.setUserId(userData.getUserID());
                                    uRequest.setStatus(true);
                                    uRequest.setEditedBy(GlobalValues.UserTypes.CUSTOMER);
                                    listener.onItemClick(child, 1, GlobalValues.RequestCodes.ACCEPT_REQUEST, uRequest);//true
                                    appAdapter.remove(position);
                                    refreshAdapter(appAdapter);
                                    break;
                                case GlobalValues.RequestCodes.CANCEL_EDIT_REQUEST:
                                    uRequest = new UpdateEditBookingStatusModel();
                                    uRequest.setBarberId(positionData.getBarberId());
                                    uRequest.setBookingId(positionData.getId());
                                    uRequest.setBookingType(positionData.getBookingType());
                                    uRequest.setDate(positionData.getEditData().split("@")[0]);
                                    uRequest.setTimingSlot(positionData.getEditData().split("@")[1]);
                                    uRequest.setUserId(userData.getUserID());
                                    uRequest.setStatus(false);
                                    uRequest.setEditedBy(GlobalValues.UserTypes.CUSTOMER);
                                    listener.onItemClick(child, 1, GlobalValues.RequestCodes.CANCEL_EDIT_REQUEST, uRequest);//true
                                    appAdapter.remove(position);
                                    refreshAdapter(appAdapter);
                                    break;
                            }
                        });
                        break;
                }
            }

            private void refreshAdapter(EditAppointmentsAdapter appAdapter) {
                if (appAdapter != null && appAdapter.getItemCount() == 0) {
                    mBottomSheetDialog.dismiss();
                }
            }
        });
        confirm.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        confirm.setAdapter(appAdapter);
//        appAdapter.notifiBookigStatusHeaders();
        return mBottomSheetDialog;
    }

    private ArrayList<MyBookingsResponseMOdel.ListBean> getValidDataList(ArrayList<MyBookingsResponseMOdel.ListBean> barberEditList) {
        if (barberEditList.size() > 0) {
            for (int i = 0; i < barberEditList.size(); i++) {
                barberEditList.get(i).setAppointmentStatus(CustomDate.getdateStatus(barberEditList.get(i).getDateString() + " " + barberEditList.get(i).getTimingSlot().split("-")[0]));

            }
        }
        return barberEditList;
    }

    /**
     * Dialog cancel appointment.
     */

    public void openDialogCancelAppointment(FragmentActivity activity, Object obj, final OnBottomDialogItemListener listener) {
        final Dialog mBottomSheetDialog = new Dialog(activity, R.style.MaterialDialogSheet);
        View child = activity.getLayoutInflater().inflate(R.layout.view_two_buttons_dialog, null);
        mBottomSheetDialog.setContentView(child);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
        final TextView messageText = child.findViewById(R.id.message_text);
        final TextView cancelBtn = child.findViewById(R.id.cancel_btn);
        final TextView editBtn = child.findViewById(R.id.edit_btn);
        final ImageView closeImg = child.findViewById(R.id.close_btn);

        MyBookingsResponseMOdel.ListBean currentData = (MyBookingsResponseMOdel.ListBean) obj;
        //Cancel  booking charges if remaining time is less than 90 min.
        if (CustomDate.getTimeDifferenceinMinutes(CustomDate.getCurrentMonth(activity, GlobalValues.DateFormats.FULL_DATE_TIME),
                currentData.getDateString() + " " + currentData.getTimingSlot().split("-")[0], GlobalValues.DateFormats.FULL_DATE_TIME) <= 90) {
            messageText.setText(activity.getString(R.string.str_you_will_be_charged) + " " +
                    GlobalValues.Currency.POUNDS + currentData.getCancellationCharge()
                    + " " + activity.getString(R.string.str_cancellation_fee));
        } else {
            messageText.setText(R.string.str_booking_cancel_message);
        }
        //Edit request button VISIBLE  only if remainig time is 2 hours and booking is  confirmed.
        if (currentData.getEditData() != null && !currentData.getEditData().equals("")) {//edit request is already placed (barber/user)
            editBtn.setVisibility(View.GONE);
        } else {
            if (CustomDate.getTimeDifferenceinMinutes(CustomDate.getCurrentMonth(activity, GlobalValues.DateFormats.FULL_DATE_TIME),
                    currentData.getDateString() + " " + currentData.getTimingSlot().split("-")[0],
                    GlobalValues.DateFormats.FULL_DATE_TIME) >= 180 && currentData.isConfirmed()) {
                editBtn.setVisibility(View.VISIBLE);
            } else {
                editBtn.setVisibility(View.GONE);
            }
        }

        editBtn.setOnClickListener(v -> {
            EditBookingRequestModel eRequest = new EditBookingRequestModel();
            eRequest.setBarberId(currentData.getBarberId());
            eRequest.setBookingId(currentData.getId());
            eRequest.setBookingType(String.valueOf(currentData.getBookingType()));

            BookingData bData = new BookingData();
            bData.setBookingType(String.valueOf(currentData.getBookingType()));
            bData.setTotalAmount(currentData.getAmount());
            bData.setBarberId(currentData.getBarberId());
            bData.setBookedServicesId(currentData.getServiceId());
            BookingSheetFragment bottomSheetFragment = new BookingSheetFragment();
            Bundle data = new Bundle();//create bundle instance
            data.putBoolean(GlobalValues.KEYS.IS_EDIT, true);

            data.putSerializable(GlobalValues.KEYS.EDIT_REQUEST_DATA, eRequest);//Edit request data;
            data.putSerializable(GlobalValues.KEYS.BOOKING_DATA, bData);//Booking data;
            switch (currentData.getBookingType()) {
                case GlobalValues.BARBER_TYPES.BARBER:
                    data.putInt(GlobalValues.KEYS.BOOKING_TYPE, GlobalValues.BARBER_TYPES.BARBER);//put string to pass with a key value
                    break;
                case GlobalValues.BARBER_TYPES.CALLOUT_BARBER:
                    data.putInt(GlobalValues.KEYS.BOOKING_TYPE, GlobalValues.BARBER_TYPES.CALLOUT_BARBER);//put string to pass with a key value
                    break;

            }
            bottomSheetFragment.setArguments(data);
            bottomSheetFragment.show(activity.getSupportFragmentManager(), bottomSheetFragment.getTag());
            mBottomSheetDialog.dismiss();
        });
        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });
        cancelBtn.setOnClickListener(v -> {
            mBottomSheetDialog.dismiss();
            listener.onItemClick(child, 0, GlobalValues.RequestCodes.CANCEL, currentData);//false
        });

    }

   /* public void openCustomTipDialog(RateBarberActivity activity, OnBottomDialogItemListener listener) {
        final Dialog mBottomSheetDialog = new Dialog(activity, R.style.MaterialDialogSheet);
        View child = activity.getLayoutInflater().inflate(R.layout.view_custom_tip_dialog, null);
        mBottomSheetDialog.setContentView(child);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.CENTER);
        mBottomSheetDialog.show();
        final TextView continueBtn = child.findViewById(R.id.continue_btn);
        final EditText ammountField = child.findViewById(R.id.amount_field);


        ammountField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    if (!ammountField.getText().toString().equals("0") && !ammountField.getText().toString().equals("00")) {
                        continueBtn.setVisibility(View.VISIBLE);
                    } else {
                        continueBtn.setVisibility(View.GONE);
                    }
                }

            }
        });
        continueBtn.setOnClickListener(v -> {
            mBottomSheetDialog.dismiss();
            listener.onItemClick(child, 0, GlobalValues.RequestCodes.CANCEL, (String) ammountField.getText().toString());//false
        });
    }*/

    public void openCancellationPolicyDialog(FragmentActivity activity, OnBottomDialogItemListener listener) {
        final Dialog mBottomSheetDialog = new Dialog(activity, R.style.MaterialDialogSheet);
        View child = activity.getLayoutInflater().inflate(R.layout.view_web_dialog, null);
        mBottomSheetDialog.setContentView(child);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.CENTER);
        mBottomSheetDialog.show();
        final WebView webView = child.findViewById(R.id.cancellation_policy_wbiew);
        final ProgressBar progressBar = child.findViewById(R.id.progress_bar);
        final TextView cancelBtn = child.findViewById(R.id.cancel_btn);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(NetworkConstatnts.API.CANCELLATION_POLICY);
        webView.setWebViewClient(new MyBrowser(progressBar));
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });

    }

    private class MyBrowser extends WebViewClient {
        ProgressBar progressBar;

        public MyBrowser(ProgressBar progressBar) {
            this.progressBar = progressBar;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }
}
