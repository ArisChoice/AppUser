package com.app.barber.ui.postauth.activities.home.home_adapter;

import android.content.res.TypedArray;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.barber.R;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.models.response.AddressListResponseModel;
import com.app.barber.models.response.MyBookingsResponseMOdel;
import com.app.barber.ui.postauth.activities.home.AddressScreenActivity;
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


public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.OptionViewHolder> {

    FragmentActivity activity;
    ArrayList<AddressListResponseModel.ListBean> iList;
    OnItemClickListener listener;
    TypedArray icons;

    public AddressListAdapter(AddressScreenActivity activity, ArrayList<AddressListResponseModel.ListBean> iList, OnItemClickListener listener) {
        this.activity = activity;
        this.listener = listener;
        this.iList = iList;
    }


    @Override
    public OptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_address_adapter, parent, false);
        return new OptionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OptionViewHolder holder, int position) {
        AddressListResponseModel.ListBean posiData = iList.get(position);
        try {
            holder.addressName.setText(posiData.getAddressLine1() + "," + posiData.getAddressLine2() + "," + posiData.getCity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return iList.size();
    }

    public void updateAll(List<AddressListResponseModel.ListBean> posts) {
        this.iList.clear();
        this.iList.addAll(posts);
        notifyDataSetChanged();
    }

    public void addItem(AddressListResponseModel.ListBean posts) {
//        this.slotsList.add(0, posts);
//        notifyDataSetChanged();
    }


    public class OptionViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.address_name)
        CustomTextView addressName;
        @BindView(R.id.edit_btn)
        CustomTextView editBtn;

        public OptionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({R.id.edit_btn})
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.edit_btn:
                    listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.DETAILS, iList.get(getAdapterPosition()));
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