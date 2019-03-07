package com.app.barber.ui.adapters;

import android.content.res.TypedArray;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OptionListAdapter extends RecyclerView.Adapter<OptionListAdapter.OptionViewHolder> {

    FragmentActivity activity;
    String[] namelist;
    OnItemClickListener listener;
    TypedArray icons;

    public OptionListAdapter(FragmentActivity activity, String[] moreOptionModels, OnItemClickListener listener) {
        this.activity = activity;
        this.namelist = moreOptionModels;
        this.listener = listener;
        icons = activity.getResources().obtainTypedArray(R.array.option_images);
    }

    @Override
    public OptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_more_options_adapter, parent, false);
        return new OptionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OptionViewHolder holder, int position) {
//        MoreOptionModel positionDta = moreOptionModels.get(position);
        try {
            holder.optionName.setText(namelist[position]);
            holder.optionIcon.setImageResource(icons.getResourceId(position, -1));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return namelist.length;
    }

    public void updateAll(List<TimeSlotsModel> posts) {
//        this.slotsList.clear();
//        this.slotsList.addAll(posts);
//        notifyDataSetChanged();
    }

    public void addItem(TimeSlotsModel posts) {
//        this.slotsList.add(0, posts);
//        notifyDataSetChanged();
    }

    public void updateLikeData(int selectedPosition, String status, String postId) {

    }


    public class OptionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.option_icon)
        ImageView optionIcon;
        @BindView(R.id.option_name)
        CustomTextView optionName;

        public OptionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({R.id.option_name})
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.option_name:
                    Log.e("onClick", "   " + namelist[getAdapterPosition()]);
                    listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.MORE_OPRION_CLICK, namelist[getAdapterPosition()]);
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