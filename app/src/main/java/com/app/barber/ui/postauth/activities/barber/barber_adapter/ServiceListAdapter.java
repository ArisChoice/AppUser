package com.app.barber.ui.postauth.activities.barber.barber_adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.models.BookingData;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.models.response.ServiceListResponseModel;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ServiceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity serviceListActivity;
    List<ServiceListResponseModel.ResponseBean> serviceList;
    OnItemClickListener onItemClickListener;
    List<ServiceListResponseModel.ResponseBean> tempServiceList;

    public ServiceListAdapter(Activity serviceListActivity, List<ServiceListResponseModel.ResponseBean> serviceList, OnItemClickListener onItemClickListener) {
        this.serviceListActivity = serviceListActivity;
        this.serviceList = serviceList;
        this.onItemClickListener = onItemClickListener;
        tempServiceList = serviceList;
    }

    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_service_adapter, parent, false);
        return new ServiceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ServiceListResponseModel.ResponseBean positionData = tempServiceList.get(position);
        if (holder instanceof ServiceViewHolder) {
            ((ServiceViewHolder) holder).serviceName.setText(positionData.getServiceName().toString());
            ((ServiceViewHolder) holder).serviceCost.setText(GlobalValues.Currency.POUNDS + positionData.getPrice());
            ((ServiceViewHolder) holder).serviceTime.setText(positionData.getDuration() + "m");
            if (positionData.isSelected()) {
                ((ServiceViewHolder) holder).bookBtn.setText(R.string.remove);
                ((ServiceViewHolder) holder).bookBtn.setTextColor(serviceListActivity.getResources().getColor(R.color.color_black));
            } else {
                ((ServiceViewHolder) holder).bookBtn.setText(R.string.str_add);
                ((ServiceViewHolder) holder).bookBtn.setTextColor(serviceListActivity.getResources().getColor(R.color.color_app_blue));
            }

            if (positionData.getType() == 1) {
                ((ServiceViewHolder) holder).serviceFor.setText(R.string.str_walk_in);
                ((ServiceViewHolder) holder).serviceFor.setBackgroundResource(R.drawable.rectangle_dark_blue_drawable);
            } else {
                ((ServiceViewHolder) holder).serviceFor.setText(R.string.str_callout_);
                ((ServiceViewHolder) holder).serviceFor.setBackgroundResource(R.drawable.rectangle_pink_drawable);
            }
        }

    }

    @Override
    public int getItemCount() {
        return tempServiceList.size();
    }

    public void updateAll(List<ServiceListResponseModel.ResponseBean> serviceList) {
        this.tempServiceList.clear();
        this.tempServiceList.addAll(serviceList);
        notifyDataSetChanged();
    }

    public void addItem(ServiceListResponseModel.ResponseBean posts) {
        this.tempServiceList.add(0, posts);
        notifyDataSetChanged();
    }

    public void updateLikeData(int selectedPosition, String status, String postId) {

    }

    public void remove(int positio) {
        tempServiceList.remove(positio);
        notifyDataSetChanged();
    }

    /**
     * check service added or not
     */
    public boolean serviceSelected() {
        for (int i = 0; i < tempServiceList.size(); i++) {
            if (tempServiceList.get(i).isSelected()) {
                return true;
            }
        }
        return false;
    }

    /**
     * get booking data.
     */
    public BookingData getBookingData() {
        BookingData bookingData = null;
        int bookedItems = 0;
        int totalAmount = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tempServiceList.size(); i++) {
            if (tempServiceList.get(i).isSelected()) {
                bookingData = new BookingData();
                bookedItems++;
                totalAmount = (totalAmount + tempServiceList.get(i).getPrice());
                builder.append(tempServiceList.get(i).getId() + ",");
                bookingData.setTotalServices(bookedItems);
                bookingData.setBookedServicesId(builder.toString().substring(0, builder.toString().length() - 1).toString());
                bookingData.setTotalAmount(totalAmount);
            }
        }
        return bookingData;
    }

    public void setselected(String selectedItem) {
        for (int i = 0; i < tempServiceList.size(); i++) {
            if (selectedItem.equals("" + tempServiceList.get(i).getId())) {
                tempServiceList.get(i).setSelected(true);
            }
        }
        notifyDataSetChanged();
    }

    public void refreshList(int type) {
        tempServiceList = new ArrayList<>();
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).getType() == type) {
                tempServiceList.add(serviceList.get(i));
            }
        }
        notifyDataSetChanged();
    }


    public class ServiceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_service_detail)
        ImageView imgServiceDetail;

        @BindView(R.id.service_name)
        CustomTextView serviceName;
        @BindView(R.id.service_time)
        CustomTextView serviceTime;
        @BindView(R.id.service_cost)
        CustomTextView serviceCost;
        @BindView(R.id.book_btn)
        CustomTextView bookBtn;
        @BindView(R.id.service_for)
        CustomTextView serviceFor;

        public ServiceViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({R.id.img_service_detail, R.id.book_btn})
        public void onLCick(View v) {
            switch (v.getId()) {
                case R.id.img_service_detail:
                    onItemClickListener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.SERVICE_DETAIL, null);
                    break;
                case R.id.book_btn:
                    updateSelection(getAdapterPosition());
                    onItemClickListener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.SERVICE_BOOK, null);
                    break;
            }
        }

        public void setData(TimeSlotsModel slotData) {
            // User Detail
        }

        private void toggleRefreshing(boolean b) {
        }


    }

    private void updateSelection(int adapterPosition) {
        if (tempServiceList.get(adapterPosition).isSelected()) {
            tempServiceList.get(adapterPosition).setSelected(false);
        } else tempServiceList.get(adapterPosition).setSelected(true);
        notifyDataSetChanged();
    }
}