package com.app.barber.ui.postauth.activities.barber.barber_adapter;

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
import com.app.barber.models.response.SpecialisationResponseModel;
import com.app.barber.ui.postauth.activities.basic.SpecialiseActivity;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SpecialisationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SpecialisationResponseModel.ResponseBean> specList;
    OnItemClickListener listener;
    Activity specialiseActivity;

    public SpecialisationAdapter(Activity specialiseActivity, List<SpecialisationResponseModel.ResponseBean> feedsList, OnItemClickListener listener) {
        this.specList = feedsList;
        this.listener = listener;
        this.specialiseActivity = specialiseActivity;
    }

    @Override
    public SlotsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_style_adapter, parent, false);
        return new SlotsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SpecialisationResponseModel.ResponseBean positionData = specList.get(position);
        ((SlotsViewHolder) holder).textType.setText(positionData.getType());
        if (positionData.isSelected()) {
            ((SlotsViewHolder) holder).typeMainLayout.setBackgroundResource(R.drawable.rounder_blue_drawable);
            ((SlotsViewHolder) holder).textType.setTextColor(specialiseActivity.getResources().getColor(R.color.color_white));
        } else {
            ((SlotsViewHolder) holder).typeMainLayout.setBackgroundResource(R.drawable.rectangle_grey_border);
            ((SlotsViewHolder) holder).textType.setTextColor(specialiseActivity.getResources().getColor(R.color.color_black));
        }
    }

    @Override
    public int getItemCount() {
        return specList.size();
    }

    public void updateAll(List<SpecialisationResponseModel.ResponseBean> posts) {
        this.specList.clear();
        this.specList.addAll(posts);
        notifyDataSetChanged();
    }

    public void addItem(SpecialisationResponseModel.ResponseBean posts) {
        this.specList.add(0, posts);
        notifyDataSetChanged();
    }

    public void updateLikeData(int selectedPosition, String status, String postId) {

    }

    public String getselected() {
        String selectedType = null;
        try {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < specList.size(); i++) {
                if (specList.get(i).isSelected()) {
                    builder.append(specList.get(i).getId() + ",");
                }
            }
            selectedType = builder.toString();
            if (selectedType != null && selectedType.length() > 0 && selectedType.charAt(selectedType.length() - 1) == ',') {
                selectedType = selectedType.substring(0, selectedType.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectedType;
    }

    public void setSelected(String styleType) {
        String[] sType = styleType.split(",");
        for (int i = 0; i < specList.size(); i++) {
            for (int j = 0; j < sType.length; j++) {
                if (specList.get(i).getId() == Integer.parseInt(sType[j])) {
                    specList.get(i).setSelected(true);
                }
            }

        }
        notifyDataSetChanged();
    }


    public class SlotsViewHolder extends RecyclerView.ViewHolder {


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
            listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.SERVICE_DETAIL, null);

        }
    }

    public void setData(TimeSlotsModel slotData) {
        // User Detail
    }

    private void toggleRefreshing(boolean b) {
    }


}
