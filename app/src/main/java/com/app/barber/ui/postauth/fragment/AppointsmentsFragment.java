package com.app.barber.ui.postauth.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseFragment;
import com.app.barber.models.request.RequestMyAppointmentModel;
import com.app.barber.models.request.UpdateBookingRequestModel;
import com.app.barber.models.request.UpdateEditBookingStatusModel;
import com.app.barber.models.response.MyBookingsResponseMOdel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.postauth.activities.barber.BarberDetailActivity;
import com.app.barber.ui.postauth.activities.home.home_adapter.AppointmentsListAdapter;
import com.app.barber.ui.postauth.activities.home.homemvp.HomeAuthMVPView;
import com.app.barber.ui.postauth.activities.home.homemvp.HomeAuthPresenter;
import com.app.barber.ui.postauth.activities.socket_work.chat.ChatActivity;
import com.app.barber.util.FunctionalDialog;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.bus_event.CustomEvent;
import com.app.barber.util.iface.OnBottomDialogItemListener;
import com.app.barber.views.CustomTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.barber.core.BaseActivity.activitySwitcher;

/**
 * Created by harish on 25/10/18.
 */

public class AppointsmentsFragment extends BaseFragment implements HomeAuthMVPView {
    int PAGE_NO = 1;
    int PAGE_LENGTH = 20;
    //    @BindView(R.id.appointments_tabs)
//    TabLayout appointmentsTabs;

    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    @BindView(R.id.first_header_txt)
    CustomTextView firstHeaderTxt;
    @BindView(R.id.second_header_txt)
    CustomTextView secondHeaderTxt;
    @BindView(R.id.skip_header_txt)
    CustomTextView skipHeaderTxt;
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private AppointmentsListAdapter appointmnetAdapter;
    private HomeAuthPresenter presenter;
    private int TOTAL_COUNT = 20;//default 20


    @Override
    protected int getFragmentLayout() {
        return R.layout.layout_appointments_screen;
    }

    @Override
    public void UpdateData(int position, Bundle bundle) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((BarberApplication) getActivity().getApplication()).getMyComponent(getActivity()).inject(this);
        presenter = new HomeAuthPresenter(getActivity());
        presenter.attachView(this);
        View rootView = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this, rootView);
        firstHeaderTxt.setText(R.string.title_appointments);
        secondHeaderTxt.setText("0 " + getActivity().getString(R.string.str_appointments_));
//        initTabs();
        initRecyclar();
        getMyAppointments();
        initSwipeRefresh();
        return rootView;
    }

    private void initSwipeRefresh() {
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(true);
                PAGE_NO = 1;
                getMyAppointments();
            }
        });
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
        Log.e("onMessage", " " + event.getType());
        switch (event.getType()) {
            case GlobalValues.EVENTS.EDIT_CALLBACK:
                PAGE_NO = 1;
                getMyAppointments();
                break;
        }
        EventBus.getDefault().removeStickyEvent(event); // don't forget to remove the sticky event if youre done with it
    }

    private void getMyAppointments() {
        RequestMyAppointmentModel mRequest = new RequestMyAppointmentModel();
        mRequest.setPageNo(PAGE_NO);
        mRequest.setRecordsPerPage(PAGE_LENGTH);
        presenter.getMyBookings(NetworkConstatnts.RequestCode.MY_ALL_BOOKINGS, mRequest, false);
    }

    private void initRecyclar() {
        appointmnetAdapter = new AppointmentsListAdapter(getActivity(), null, new ArrayList<>(),
                (view, position, type, t) -> {
                    switch (type) {
                        case GlobalValues.ClickOperations.APAPTER_BOTTOM_DIALOG_CLICK:
                            new FunctionalDialog().openDialogAppointment(getActivity(), t, (view1, position1, type1, t1) -> {
                                MyBookingsResponseMOdel.ListBean positionData = (MyBookingsResponseMOdel.ListBean) t1;
                                switch (type1) {
                                    case GlobalValues.RequestCodes.MESSAGE:
                                        if (positionData.getChatDialog() != null) {
                                            Intent intent = new Intent(getActivity(), ChatActivity.class);
                                            intent.putExtra(GlobalValues.KEYS.EXTRA_DIALOG_ID, positionData.getChatDialog());
                                            intent.putExtra(GlobalValues.KEYS.OTHER_IMAGE, positionData.getProfileImage());
                                            intent.putExtra(GlobalValues.KEYS.USER_ID, ""+positionData.getBarberId());
                                            startActivity(intent);
                                        }
                                        break;
                                    case GlobalValues.RequestCodes.CALL:
                                        if (positionData.getPhone() != null)
                                            callInit(positionData.getPhone());
                                        break;
                                    case GlobalValues.RequestCodes.CANCEL:
                                        new FunctionalDialog().openDialogCancelAppointment(getActivity(), positionData, new OnBottomDialogItemListener() {
                                            @Override
                                            public void onItemClick(View view, int position, int type, Object t) {
                                                switch (type) {
                                                    case GlobalValues.RequestCodes.CANCEL:
                                                        UpdateBookingRequestModel uModel = new UpdateBookingRequestModel(positionData.getId(), true, positionData.getBookingType(), positionData.getBarberId());
                                                        presenter.updateBookingRequest(NetworkConstatnts.RequestCode.API_UPDATE_BOOKING_STATUS, uModel, true);
                                                        new Handler().postDelayed(() -> {
                                                            positionData.setCanceled(true);
                                                            appointmnetAdapter.notifyDataSetChanged();
                                                        }, GlobalValues.TIME_DURATIONS.SMALL);
                                                        break;

                                                }
                                            }
                                        });

                                        break;
                                    case GlobalValues.RequestCodes.REJECT_REQUEST:
                                        UpdateEditBookingStatusModel uRequest = new UpdateEditBookingStatusModel();
                                        uRequest.setBarberId(positionData.getBarberId());
                                        uRequest.setBookingId(positionData.getId());
                                        uRequest.setBookingType(positionData.getBookingType());
                                        uRequest.setDate(positionData.getEditData().split("@")[0]);
                                        uRequest.setTimingSlot(positionData.getEditData().split("@")[1]);
                                        uRequest.setUserId(getUserData().getUserID());
                                        uRequest.setEditedBy(GlobalValues.UserTypes.CUSTOMER);
                                        uRequest.setStatus(false);
                                        presenter.updateEditStatus(NetworkConstatnts.RequestCode.API_EDIT_BOOKING_STATUS, uRequest, true);
                                        break;
                                    case GlobalValues.RequestCodes.ACCEPT_REQUEST:
                                        uRequest = new UpdateEditBookingStatusModel();
                                        uRequest.setBarberId(positionData.getBarberId());
                                        uRequest.setBookingId(positionData.getId());
                                        uRequest.setBookingType(positionData.getBookingType());
                                        uRequest.setDate(positionData.getEditData().split("@")[0]);
                                        uRequest.setTimingSlot(positionData.getEditData().split("@")[1]);
                                        uRequest.setUserId(getUserData().getUserID());
                                        uRequest.setStatus(true);
                                        uRequest.setEditedBy(GlobalValues.UserTypes.CUSTOMER);
                                        presenter.updateEditStatus(NetworkConstatnts.RequestCode.API_EDIT_BOOKING_STATUS, uRequest, true);
                                        break;
                                    case GlobalValues.RequestCodes.CANCEL_EDIT_REQUEST:
                                        uRequest = new UpdateEditBookingStatusModel();
                                        uRequest.setBarberId(positionData.getBarberId());
                                        uRequest.setBookingId(positionData.getId());
                                        uRequest.setBookingType(positionData.getBookingType());
                                        uRequest.setDate(positionData.getEditData().split("@")[0]);
                                        uRequest.setTimingSlot(positionData.getEditData().split("@")[1]);
                                        uRequest.setUserId(getUserData().getUserID());
                                        uRequest.setStatus(false);
                                        uRequest.setEditedBy(GlobalValues.UserTypes.CUSTOMER);
                                        presenter.updateEditStatus(NetworkConstatnts.RequestCode.API_EDIT_BOOKING_STATUS, uRequest, true);
                                        break;
                                    case GlobalValues.RequestCodes.REORDER:
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable(GlobalValues.KEYS.BARBER_DETAIL, null);
                                        bundle.putInt(GlobalValues.KEYS.BARBER_ID, ((MyBookingsResponseMOdel.ListBean) t).getBarberId());
                                        bundle.putBoolean(GlobalValues.KEYS.isReorder, true);
                                        bundle.putString(GlobalValues.KEYS.SERVICE_DETAIL, ((MyBookingsResponseMOdel.ListBean) t).getServiceId());
                                        activitySwitcher(getActivity(), BarberDetailActivity.class, bundle);
                                        break;
                                }
                            });
                            break;
                    }
                });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclarViewLst.setLayoutManager(layoutManager);
        recyclarViewLst.setAdapter(appointmnetAdapter);


        recyclarViewLst.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             * Callback method to be invoked when the RecyclerView has been scrolled. This will be
             * called after the scroll has completed.
             * <p>
             * This callback will also be called if visible item range changes after a layout
             * calculation. In that case, dx and dy will be 0.
             *
             * @param recyclerView The RecyclerView which scrolled.
             * @param dx           The amount of horizontal scroll.
             * @param dy           The amount of vertical scroll.
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("on Scroll ", "1  " + layoutManager.findLastVisibleItemPosition());
                Log.e("on Scroll ", "2  " + layoutManager.findFirstCompletelyVisibleItemPosition());


                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0 && TOTAL_COUNT > appointmnetAdapter.getItemCount()) {
                    PAGE_NO++;
                    Log.e("on Scroll ", "  load more  " + layoutManager.findFirstCompletelyVisibleItemPosition());
                    getMyAppointments();
                }
            }


        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onSuccessResponse(int serviceMode, Object o) {
        swipeRefresh.setRefreshing(false);
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.MY_ALL_BOOKINGS:
                MyBookingsResponseMOdel responseData = (MyBookingsResponseMOdel) o;
                TOTAL_COUNT = responseData.getCount();
                if (responseData.getList() != null && responseData.getList().size() > 0) {
                    if (PAGE_NO == 1) {
                        appointmnetAdapter.updateAll(responseData.getList());
                    } else appointmnetAdapter.addAll(responseData.getList());

                    appointmnetAdapter.notifiBookigStatusHeaders();
                    secondHeaderTxt.setText(appointmnetAdapter.getItemCount() + " " + getActivity().getString(R.string.str_appointments_));
                    noListDataText.setVisibility(View.GONE);
                    recyclarViewLst.setVisibility(View.VISIBLE);
                } else {
                    noListDataText.setText(R.string.str_no_bookings);
                    noListDataText.setVisibility(View.VISIBLE);
                    recyclarViewLst.setVisibility(View.GONE);
                }
                break;
            case NetworkConstatnts.RequestCode.API_EDIT_BOOKING_STATUS:
                PAGE_NO = 1;
                getMyAppointments();
                break;
        }
    }

    @Override
    public void onfaliurResponse(int serviceMode, Object o) {
        swipeRefresh.setRefreshing(false);
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
