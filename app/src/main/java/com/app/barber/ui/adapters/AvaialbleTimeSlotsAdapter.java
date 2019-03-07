package com.app.barber.ui.adapters;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.app.barber.R;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.models.response.ResponseAvailableSlotsModel;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AvaialbleTimeSlotsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> slotsList;
    OnItemClickListener listener;
    FragmentActivity activity;
    int selectedPosition;

    public AvaialbleTimeSlotsAdapter(FragmentActivity activity, List<String> feedsList, OnItemClickListener listener) {
        this.slotsList = feedsList;
        this.listener = listener;
        this.activity = activity;
    }

    @Override
    public SlotsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_available_time_slots_adapter, parent, false);
        return new SlotsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SlotsViewHolder) holder).timeSlots.setText(slotsList.get(position));
        if (selectedPosition == position) {
            ((SlotsViewHolder) holder).timeSlots.setBackgroundResource(R.drawable.rectangle_blue_drawable);
            ((SlotsViewHolder) holder).timeSlots.setTextColor(activity.getResources().getColor(R.color.color_white));
        } else {
            ((SlotsViewHolder) holder).timeSlots.setBackgroundResource(R.drawable.rectangle_grey_border);
            ((SlotsViewHolder) holder).timeSlots.setTextColor(activity.getResources().getColor(R.color.color_grey));

        }
    }

    @Override
    public int getItemCount() {
        return slotsList.size();
    }

    public void updateAll(List<String> posts) {
        this.slotsList.clear();
        this.slotsList.addAll(posts);
        notifyDataSetChanged();
    }

    public void addItem(String posts) {
        this.slotsList.add(0, posts);
        notifyDataSetChanged();
    }

    public void updateLikeData(int selectedPosition, String status, String postId) {

    }

    public void updatePosition(String timeModel, int positio) {
        slotsList.set(positio, timeModel);
        notifyDataSetChanged();
    }


    public List<String> getList() {
        return slotsList;
    }

    public void clear() {
        slotsList.clear();
        notifyDataSetChanged();
    }


    public class SlotsViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.time_slots)
        CustomTextView timeSlots;


        public SlotsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({R.id.time_slots})
        public void onLCick(View v) {
            switch (v.getId()) {
                case R.id.time_slots:
                    selectedPosition = getAdapterPosition();
                    listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.ADD_TIME_CLICK, slotsList.get(getAdapterPosition()));
                    notifyDataSetChanged();
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