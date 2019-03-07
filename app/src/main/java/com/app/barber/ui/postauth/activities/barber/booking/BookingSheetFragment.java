package com.app.barber.ui.postauth.activities.barber.booking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.models.AvailableSlotsModel;
import com.app.barber.models.BookingData;
import com.app.barber.models.ModelDay;
import com.app.barber.models.request.EditBookingRequestModel;
import com.app.barber.models.response.BaseResponse;
import com.app.barber.models.response.LoginResponseModel;
import com.app.barber.models.response.ResponseAvailableSlotsModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.adapters.AvaialbleTimeSlotsAdapter;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberMVPView;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberPresenter;
import com.app.barber.ui.weekcontroler.WeekViewAdapter;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.PreferenceManager;
import com.app.barber.util.bus_event.CustomEvent;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BookingSheetFragment extends BottomSheetDialogFragment implements BarberMVPView {
    @Inject
    PreferenceManager mPref;
    @BindView(R.id.weekViewRecyclar)
    RecyclerView weekViewRecyclar;
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    @BindView(R.id.cnfirm_Booking)
    CustomTextView cnfirmBooking;
    Unbinder unbinder;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.booking_type_txt)
    CustomTextView bookingTypeTxt;
    private BarberPresenter presenter;
    private BookingData bookingDataModel;
    private AvaialbleTimeSlotsAdapter slotsAdapter;
    private int bookingType;
    private boolean isEdit;
    private int appointmentId;//appointment id  while  editing
    private EditBookingRequestModel editRequestDataModel;
    private BookingSheetFragment bottomSheetFragment;
    private WeekViewAdapter weekAdapter;


    public BookingSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bottomSheetFragment = this;
        View view = inflater.inflate(R.layout.fragment_booking_sheet_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((BarberApplication) getActivity().getApplication()).getMyComponent(getActivity()).inject(this);
        presenter = new BarberPresenter(getActivity());
        presenter.attachView(this);
        initWeekView();
        getBundleData();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void getBundleData() {
        isEdit = getArguments().getBoolean(GlobalValues.KEYS.IS_EDIT);

        Serializable dataArgument = getArguments().getSerializable(GlobalValues.KEYS.BOOKING_DATA);//Get pass data with its key value
        bookingType = getArguments().getInt(GlobalValues.KEYS.BOOKING_TYPE);
        bookingDataModel = ((BookingData) dataArgument);
        bookingDataModel.setBookingType("" + bookingType);
        Log.e("getBundleData", "  " + new Gson().toJson(bookingDataModel) + " " + bookingType);
        getAvailableSlots(new CommonUtils().getCurrentDate());
        if (bookingType == GlobalValues.BARBER_TYPES.BARBER) {
            bookingTypeTxt.setText(R.string.str_book_appointment);
        } else bookingTypeTxt.setText(R.string.str_book_for_callout);


        if (isEdit) {
            appointmentId = getArguments().getInt(GlobalValues.KEYS.APPOINTMENT_ID);
            Serializable editArgument = getArguments().getSerializable(GlobalValues.KEYS.EDIT_REQUEST_DATA);//Get pass data with its key value
            editRequestDataModel = ((EditBookingRequestModel) editArgument);
            editRequestDataModel.setDate(weekAdapter.getSelectedDate());
        }
    }

    private void getAvailableSlots(String currentDate) {
        progressBar.setVisibility(View.VISIBLE);
        AvailableSlotsModel avaialbeModel = new AvailableSlotsModel();
        avaialbeModel.setBarberId(bookingDataModel.getBarberId());
        avaialbeModel.setDate(currentDate);
        avaialbeModel.setServiceId(bookingDataModel.getBookedServicesId());
        avaialbeModel.setBookingType(bookingType);
        presenter.getAvailableSlots(NetworkConstatnts.RequestCode.API_AVAILABLE_SLOTS, avaialbeModel, false);

    }

    private void initWeekView() {
        weekAdapter = new WeekViewAdapter(getActivity(), new ArrayList<ModelDay>(), new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object o) {
                Log.e(" onItemClick ", "  -- " + position);
                if (isEdit) editRequestDataModel.setDate(((ModelDay) o).getFullDate());
                else {
                    bookingDataModel.setSelectedDate(((ModelDay) o).getFullDate());
                    slotsAdapter.clear();
                }
                getAvailableSlots(new CommonUtils().getNewDate(position));
            }
        });
        LinearLayoutManager lManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        weekViewRecyclar.setLayoutManager(lManager);
        weekViewRecyclar.setAdapter(weekAdapter);
        weekAdapter.setCurrentWeek(35);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bookingDataModel.setSelectedDate(weekAdapter.getSelectedDate());//default selected date.
            }
        }, GlobalValues.TIME_DURATIONS.MEDIUS);

        slotsAdapter = new AvaialbleTimeSlotsAdapter(getActivity(), new ArrayList<String>(), new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object o) {
                if (isEdit) editRequestDataModel.setTimingSlot((String) o);
                else bookingDataModel.setSelectedSlot((String) o);
            }
        });
        lManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclarViewLst.setLayoutManager(lManager);
        recyclarViewLst.setAdapter(slotsAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.cnfirm_Booking)
    public void onClick() {
        if (isEdit) {
            if (editRequestDataModel != null && editRequestDataModel.getTimingSlot() != null && !editRequestDataModel.getDate().equals("")) {
                editRequestDataModel.setEditedBy(GlobalValues.UserTypes.CUSTOMER);
                editRequestDataModel.setServiceIds(bookingDataModel.getBookedServicesId());
                editRequestDataModel.setUserId(new Gson().fromJson(mPref.getUserData(), LoginResponseModel.UserBean.class).getUserID());
                presenter.editBookingRequest(NetworkConstatnts.RequestCode.API_EDIT_BOOKING_REQUEST, editRequestDataModel, false);
            } else new CommonUtils().ShowToast(getString(R.string.error_select_booking_slot));

        } else {
            if (bookingDataModel != null && bookingDataModel.getSelectedSlot() != null && !bookingDataModel.getSelectedSlot().equals("")) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(GlobalValues.KEYS.BOOKING_DATA, bookingDataModel);
                activitySwitcher(getActivity(), ConfirmBookingActivity.class, bundle);
            } else new CommonUtils().ShowToast(getString(R.string.error_select_booking_slot));
        }
    }


    public static void activitySwitcher(Activity from, Class<?> to, Bundle bundle) {

        Intent intent = new Intent(from, to);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        from.startActivity(intent);
        from.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object o) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_AVAILABLE_SLOTS:
                progressBar.setVisibility(View.GONE);
                ResponseAvailableSlotsModel responseModel = (ResponseAvailableSlotsModel) o;
                if (responseModel != null && responseModel.getList() != null && responseModel.getList().size() > 0) {
                    slotsAdapter.updateAll(responseModel.getList());
                    noListDataText.setVisibility(View.GONE);
                    recyclarViewLst.setVisibility(View.VISIBLE);
                } else {
                    slotsAdapter.clear();
                    bookingDataModel.setSelectedSlot(null);
                    noListDataText.setText(R.string.str_no_slots_available);
                    noListDataText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
                    noListDataText.setVisibility(View.VISIBLE);
                    recyclarViewLst.setVisibility(View.GONE);
                }
                break;
            case NetworkConstatnts.RequestCode.API_EDIT_BOOKING_REQUEST:
                new CommonUtils().ShowToast(((BaseResponse) o).getMessage());
                EventBus.getDefault().postSticky(new CustomEvent(GlobalValues.EVENTS.EDIT_CALLBACK, null));
                bottomSheetFragment.dismiss();
                break;
        }
    }

    @Override
    public void onfaliurResponse(int serviceMode, Object o) {
        progressBar.setVisibility(View.GONE);
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