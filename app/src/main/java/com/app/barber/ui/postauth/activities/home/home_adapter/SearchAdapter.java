/*
package com.app.barber.ui.postauth.activities.home.home_adapter;

import android.content.res.TypedArray;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.barber.R;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.models.response.MyBookingsResponseMOdel;
import com.app.barber.util.CustomDate;
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


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.OptionViewHolder> {

    FragmentActivity activity;
    ArrayList<MyBookingsResponseMOdel.ListBean> iList;
    OnItemClickListener listener;
    TypedArray icons;

    public SearchAdapter(FragmentActivity activity, ArrayList<MyBookingsResponseMOdel.ListBean> iList, OnItemClickListener listener) {
        this.activity = activity;
        this.listener = listener;
        this.iList = iList;
    }

    @Override
    public OptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_barber_adapter, parent, false);
        return new OptionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OptionViewHolder holder, int position) {
        MyBookingsResponseMOdel.ListBean positionData = iList.get(position);
        if (positionData.getAppointmentStatus() != null) {
            holder.headerText.setText(positionData.getAppointmentStatus());
            if (position != 0)
                if (positionData.getAppointmentStatus().equals(iList.get(position - 1).getAppointmentStatus()))
                    holder.headerText.setVisibility(View.GONE);
                else holder.headerText.setVisibility(View.VISIBLE);
            else holder.headerText.setVisibility(View.VISIBLE);
        }
        holder.barberName.setText(positionData.getName());
        holder.timeSlotTxt.setText(positionData.getTimingSlot());
        holder.payableAmountTxt.setText("$" + positionData.getAmount());
        holder.barberImageVw.setImageURI(ImageUtility.getValidUrl(positionData.getProfileImage()));
        holder.barberService.setText(positionData.getServiceId());
        holder.bookingDay.setText(CustomDate.getCurrentFormat(activity, positionData.getDateString(), "MM/dd/yyyy", "EEE"));
        holder.bookingDate.setText(CustomDate.getCurrentFormat(activity, positionData.getDateString(), "MM/dd/yyyy", "dd"));
        holder.bookingMonth.setText(CustomDate.getCurrentFormat(activity, positionData.getDateString(), "MM/dd/yyyy", "MMM"));
        if (positionData.getBookingType() == 2) {//callout request=2,appointment==1
            holder.bookingType.setText(R.string.str_callout);
            holder.bookingType.setVisibility(View.VISIBLE);
        } else {
            holder.bookingType.setText("");
            holder.bookingType.setVisibility(View.GONE);
        }
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

    public void notifiBookigStatusHeaders() {
        if (iList.size() > 0) {
            for (int i = 0; i < iList.size(); i++) {
                iList.get(i).setAppointmentStatus(CustomDate.getdateStatus(iList.get(i).getDateString()));

            }
        }
    }


    public class OptionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.barber_image_vw)
        SimpleDraweeView barberImageVw;
        @BindView(R.id.barber_name)
        CustomTextView barberName;
        @BindView(R.id.barber_service)
        CustomTextView barberService;
        @BindView(R.id.time_slot_txt)
        CustomTextView timeSlotTxt;
        @BindView(R.id.payable_apount_txt)
        CustomTextView payableAmountTxt;

        @BindView(R.id.header_text)
        CustomTextView headerText;

        @BindView(R.id.booking_Day)
        CustomTextView bookingDay;
        @BindView(R.id.booking_Date)
        CustomTextView bookingDate;
        @BindView(R.id.booking_Month)
        CustomTextView bookingMonth;
        @BindView(R.id.booking_type)
        CustomTextView bookingType;

        public OptionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({R.id.barber_name})
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.barber_name:
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
}*/
