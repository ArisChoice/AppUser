package com.app.barber.ui.adapters;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.app.barber.R;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class TimeSlotsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TimeSlotsModel> slotsList;
    OnItemClickListener listener;


    public TimeSlotsAdapter(List<TimeSlotsModel> feedsList, OnItemClickListener listener) {
        this.slotsList = feedsList;
        this.listener = listener;
    }

    @Override
    public SlotsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_time_slots_adapter, parent, false);
        return new SlotsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SlotsViewHolder) holder).txtDay.setText(slotsList.get(position).getDay());
        if (slotsList.get(position).getOpeningHours() != null && !slotsList.get(position).getOpeningHours().equals("")) {
            ((SlotsViewHolder) holder).txtTime.setText(slotsList.get(position).getOpeningHours() + " - " + slotsList.get(position).getClosingHours());
            ((SlotsViewHolder) holder).slotCheckbox.setChecked(true);
        } else {
            ((SlotsViewHolder) holder).txtTime.setText("closed");
            ((SlotsViewHolder) holder).slotCheckbox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public void updateAll(List<TimeSlotsModel> posts) {
        this.slotsList.clear();
        this.slotsList.addAll(posts);
        notifyDataSetChanged();
    }

    public void addItem(TimeSlotsModel posts) {
        this.slotsList.add(0, posts);
        notifyDataSetChanged();
    }

    public void updateLikeData(int selectedPosition, String status, String postId) {

    }

    public void updatePosition(TimeSlotsModel timeModel, int positio) {
        slotsList.set(positio, timeModel);
        notifyDataSetChanged();
    }

    public String getItemName(int positio) {
        return slotsList.get(positio).getDay();
    }

    public List<TimeSlotsModel> getList() {
        return slotsList;
    }


    public class SlotsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.slot_checkbox)
        CheckBox slotCheckbox;

        @BindView(R.id.slot_day)
        CustomTextView txtDay;
        @BindView(R.id.slot_time)
        CustomTextView txtTime;
        @BindView(R.id.slot_add)
        CustomTextView addBtn;


        public SlotsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({R.id.slot_add})
        public void onLCick(View v) {
            switch (v.getId()) {
                case R.id.slot_add:
                    listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.ADD_TIME_CLICK, slotsList.get(getAdapterPosition()));
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