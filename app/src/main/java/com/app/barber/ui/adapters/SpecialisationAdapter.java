package com.app.barber.ui.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.barber.R;
import com.app.barber.models.SpecialisationModel;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.ui.postauth.activities.basic.SpecialiseActivity;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SpecialisationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SpecialisationModel> specList;
    OnItemClickListener listener;
    Activity specialiseActivity;

    public SpecialisationAdapter(Activity specialiseActivity, List<SpecialisationModel> feedsList, OnItemClickListener listener) {
        this.specList = feedsList;
        this.listener = listener;
        this.specialiseActivity = specialiseActivity;
    }

    @Override
    public SlotsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_specialisation_adapter, parent, false);
        return new SlotsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SpecialisationModel positionData = specList.get(position);
        ((SlotsViewHolder) holder).textType.setText(positionData.getName());
        if (positionData.isSelected()) {
            ((SlotsViewHolder) holder).typeMainLayout.setBackgroundResource(R.drawable.rectangle_selected_type_drawable);
            ((SlotsViewHolder) holder).textType.setTextColor(specialiseActivity.getResources().getColor(R.color.colorPrimary));
            ((SlotsViewHolder) holder).img_check_type.setVisibility(View.VISIBLE);
        } else {
            ((SlotsViewHolder) holder).typeMainLayout.setBackgroundResource(R.drawable.rectangle_unselected_type_drawable);
            ((SlotsViewHolder) holder).textType.setTextColor(specialiseActivity.getResources().getColor(R.color.color_white));
            ((SlotsViewHolder) holder).img_check_type.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return specList.size();
    }

    public void updateAll(List<SpecialisationModel> posts) {
        this.specList.clear();
        this.specList.addAll(posts);
        notifyDataSetChanged();
    }

    public void addItem(SpecialisationModel posts) {
        this.specList.add(0, posts);
        notifyDataSetChanged();
    }

    public void updateLikeData(int selectedPosition, String status, String postId) {

    }


    public class SlotsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_check_type)
        ImageView img_check_type;

        @BindView(R.id.text_type_)
        CustomTextView textType;
        @BindView(R.id.type_main_layout)
        LinearLayout typeMainLayout;


        public SlotsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({R.id.type_main_layout})
        public void onLCick(View v) {
            if (specList.get(getAdapterPosition()).isSelected()) {
                specList.get(getAdapterPosition()).setSelected(false);
            } else {
                specList.get(getAdapterPosition()).setSelected(true);
            }
            notifyDataSetChanged();
//            listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.ADD_TIME_CLICK, specList.get(getAdapterPosition()));

        }
    }

    public void setData(TimeSlotsModel slotData) {
        // User Detail
    }

    private void toggleRefreshing(boolean b) {
    }


}
