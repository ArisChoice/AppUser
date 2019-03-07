package com.app.barber.ui.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.models.BookingData;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.models.response.ServiceListResponseModel;
import com.app.barber.ui.postauth.activities.barber.PreBookingDetailResponse;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BookedServiceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity serviceListActivity;
    List<PreBookingDetailResponse.ListBean.ServicesBean> serviceList;
    OnItemClickListener onItemClickListener;


    public BookedServiceListAdapter(Activity serviceListActivity, ArrayList<PreBookingDetailResponse.ListBean.ServicesBean> serviceList,
                                    OnItemClickListener onItemClickListener) {
        this.serviceListActivity = serviceListActivity;
        this.serviceList = serviceList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_booked_service_adapter, parent, false);
        return new ServiceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PreBookingDetailResponse.ListBean.ServicesBean positionData = serviceList.get(position);
        ((ServiceViewHolder) holder).selectedServiceName.setText(positionData.getMItem1());
        ((ServiceViewHolder) holder).selectedServicePrice.setText(positionData.getmItem3() + "" +positionData.getMItem2());
        if (position == (serviceList.size() - 1)) {
            ((ServiceViewHolder) holder).selectedServiceName.setTextColor(serviceListActivity.getResources().getColor(R.color.color_black));
            ((ServiceViewHolder) holder).selectedServicePrice.setTextColor( serviceListActivity.getResources().getColor(R.color.color_black));
        } else {
            ((ServiceViewHolder) holder).selectedServiceName.setTextColor(serviceListActivity.getResources().getColor(R.color.color_black));
            ((ServiceViewHolder) holder).selectedServicePrice.setTextColor(serviceListActivity.getResources().getColor(R.color.color_grey));
        }

    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public void updateAll(List<PreBookingDetailResponse.ListBean.ServicesBean> serviceList) {
        this.serviceList.clear();
        this.serviceList.addAll(serviceList);
        notifyDataSetChanged();
    }

    public void addItem(PreBookingDetailResponse.ListBean.ServicesBean posts) {
        this.serviceList.add(0, posts);
        notifyDataSetChanged();
    }

    public void updateLikeData(int selectedPosition, String status, String postId) {

    }

    public void remove(int positio) {
        serviceList.remove(positio);
        notifyDataSetChanged();
    }


    /**
     * get booking data.
     */


    public class ServiceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.selected_service_name)
        CustomTextView selectedServiceName;
        @BindView(R.id.selected_service_price)
        CustomTextView selectedServicePrice;


        public ServiceViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({})
        public void onLCick(View v) {
            switch (v.getId()) {
                //  onItemClickListener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.SERVICE_BOOK, null);
            }
        }

        public void setData(TimeSlotsModel slotData) {
            // User Detail
        }

        private void toggleRefreshing(boolean b) {
        }
    }

}