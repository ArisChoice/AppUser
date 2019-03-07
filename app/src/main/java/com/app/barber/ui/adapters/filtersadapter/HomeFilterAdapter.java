package com.app.barber.ui.adapters.filtersadapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.app.barber.R;
import com.app.barber.models.HomeFiltersModel;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.List;
import java.util.logging.Handler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeFilterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeFiltersModel> filterList;
    OnItemClickListener listener;
    Activity activity;

    public HomeFilterAdapter(Activity activity, List<HomeFiltersModel> feedsList, OnItemClickListener listener) {
        this.filterList = feedsList;
        this.listener = listener;
        this.activity = activity;
    }

    @Override
    public FilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_filte_adapter, parent, false);
        return new FilterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FilterViewHolder) {
            HomeFiltersModel positionData = filterList.get(position);
            ((FilterViewHolder) holder).filterName.setText(positionData.getFilterName());
            if (positionData.isSelected()) {
                ((FilterViewHolder) holder).filterName.setTextColor(activity.getResources().getColor(R.color.color_app_blue));
                ((FilterViewHolder) holder).filterName.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.check_, 0);
            } else {
                ((FilterViewHolder) holder).filterName.setTextColor(activity.getResources().getColor(R.color.color_grey));
                ((FilterViewHolder) holder).filterName.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
            }
        }
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    public void updateAll(List<HomeFiltersModel> posts) {
        this.filterList.clear();
        this.filterList.addAll(posts);
        notifyDataSetChanged();
    }

    public void addItem(HomeFiltersModel posts) {
        this.filterList.add(0, posts);
        notifyDataSetChanged();
    }


    public void updatePosition(HomeFiltersModel timeModel, int positio) {
        filterList.set(positio, timeModel);
        notifyDataSetChanged();
    }


    public List<HomeFiltersModel> getList() {
        return filterList;
    }

    public void setSelected(String soryBy) {
        for (int i = 0; i < filterList.size(); i++) {
            if (soryBy.equals(filterList.get(i).getFilterKey())) {
                filterList.get(i).setSelected(true);
            }
        }
        notifyDataSetChanged();
    }


    public class FilterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.filter_name)
        CustomTextView filterName;


        public FilterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({R.id.filter_name})
        public void onLCick(View v) {
            switch (v.getId()) {
                case R.id.filter_name:
                    getSelection(getAdapterPosition());
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.APAPTER_BOTTOM_DIALOG_CLICK, filterList.get(getAdapterPosition()));

                        }
                    }, GlobalValues.TIME_DURATIONS.SMALL);
                    break;
            }
        }

        public void setData(TimeSlotsModel slotData) {
            // User Detail
        }

        private void toggleRefreshing(boolean b) {
        }
    }

    private void getSelection(int adapterPosition) {
        for (int i = 0; i < filterList.size(); i++) {
            if (adapterPosition == i) {
                if (filterList.get(adapterPosition).isSelected()) {
                    filterList.get(i).setSelected(false);
                } else filterList.get(i).setSelected(true);
            } else filterList.get(i).setSelected(false);
        }
        notifyDataSetChanged();
    }
}