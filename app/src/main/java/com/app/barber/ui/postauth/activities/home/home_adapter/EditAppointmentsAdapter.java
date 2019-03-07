package com.app.barber.ui.postauth.activities.home.home_adapter;

import android.content.res.TypedArray;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.barber.R;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.models.response.MyBookingsResponseMOdel;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.CustomDate;
import com.app.barber.util.FunctionalDialog;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.ImageUtility;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class EditAppointmentsAdapter extends RecyclerView.Adapter<EditAppointmentsAdapter.EditViewHolder> {

    FragmentActivity activity;
    ArrayList<MyBookingsResponseMOdel.ListBean> iList;
    OnItemClickListener listener;
    TypedArray icons;
    String nFrom;

    public EditAppointmentsAdapter(FragmentActivity activity, String nFrom, ArrayList<MyBookingsResponseMOdel.ListBean> iList, OnItemClickListener listener) {
        this.activity = activity;
        this.listener = listener;
        this.iList = iList;
        this.nFrom = nFrom;
    }

    @Override
    public EditViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_edit_appointments_adapter, parent, false);
        return new EditViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EditViewHolder holder, int position) {
        MyBookingsResponseMOdel.ListBean positionData = iList.get(position);
        holder.userName.setText(positionData.getName());
        holder.userImageVw.setImageURI(CommonUtils.getValidUrl(positionData.getProfileImage()));
        holder.userService.setText(positionData.getServiceNames());
        holder.currenTimeSlotText.setText(positionData.getTimingSlot());
        holder.requestedTimeSlotText.setText(positionData.getEditData().split("@")[1]);
        holder.payableApountTxt.setText(GlobalValues.Currency.POUNDS + positionData.getAmount());
        holder.currentDateTxt.setText(CustomDate.formatThis(GlobalValues.DateFormats.DEFAULT_FORMAT_DATE, positionData.getDateString(), "dd MMM yyyy"));
        try {
            holder.requestedDateTxt.setText(CustomDate.formatThis(GlobalValues.DateFormats.DEFAULT_FORMAT_DATE, positionData.getEditData().split("@")[0], "dd MMM yyyy"));
        } catch (Exception e) {

        }
            /* if (positionData.getBookingType() == GlobalValues.BookingTypes.CALLOUT) {
                    ((AppointmentsViewHolder) holder).timeRemainingTxt.setVisibility(View.VISIBLE);
                    ((AppointmentsViewHolder) holder).timeRemainingTxt.setText(serviceListActivity.getString(R.string.str_callout_));
                    ((AppointmentsViewHolder) holder).timeRemainingTxt.setBackgroundResource(R.drawable.rectangle_pink_border);
                } else {
                    ((AppointmentsViewHolder) holder).timeRemainingTxt.setVisibility(View.GONE);
                }*/

    }

    @Override
    public int getItemCount() {
        return iList.size();
    }

    public void updateAll(List<MyBookingsResponseMOdel.ListBean> posts) {
        this.iList.clear();
        this.iList.addAll(posts);
        notifyDataSetChanged();
    }

    public void addItem(MyBookingsResponseMOdel.ListBean posts) {
//        this.slotsList.add(0, posts);
//        notifyDataSetChanged();
    }

    public void remove(int positio) {
        iList.remove(positio);
        notifyDataSetChanged();
    }

    public void notifiBookigStatusHeaders() {
        if (iList.size() > 0) {
            for (int i = 0; i < iList.size(); i++) {
                iList.get(i).setAppointmentStatus(CustomDate.getdateStatus(iList.get(i).getDateString() + " " + iList.get(i).getTimingSlot().split("-")[0]));

            }
        }
    }

    public void addAll(List<MyBookingsResponseMOdel.ListBean> list) {
        iList.addAll(list);
        notifyDataSetChanged();
    }

    public class EditViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.user_name)
        CustomTextView userName;
        @BindView(R.id.user_service)
        CustomTextView userService;
        @BindView(R.id.current_time_slot_txt)
        CustomTextView currenTimeSlotText;
        @BindView(R.id.requested_time_slot_txt)
        CustomTextView requestedTimeSlotText;

        @BindView(R.id.payable_apount_txt)
        CustomTextView payableApountTxt;

        @BindView(R.id.current_date_txt)
        CustomTextView currentDateTxt;

        @BindView(R.id.requested_date_txt)
        CustomTextView requestedDateTxt;

        @BindView(R.id.user_image_vw)
        SimpleDraweeView userImageVw;

        @BindView(R.id.booking_view)
        LinearLayout bookingView;

        @BindView(R.id.payment_type)
        CustomTextView paymentType;

        public EditViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            paymentType.setVisibility(View.GONE);
        }

        @OnClick({R.id.booking_view, R.id.user_image_vw, R.id.user_service})
        public void onLCick(View v) {
            switch (v.getId()) {
                case R.id.booking_view:
                case R.id.user_image_vw:
                case R.id.user_service:
                    listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.APAPTER_BOTTOM_DIALOG_CLICK, iList.get(getAdapterPosition()));
                    break;
            }
        }

        public void setData(TimeSlotsModel slotData) {
            // User Detail


        }

        private void toggleRefreshing(boolean b) {

        }


    }
}