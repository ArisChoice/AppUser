package com.app.barber.ui.postauth.activities.barber.barber_adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.models.response.CardListResponse;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CardListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity serviceListActivity;
    List<CardListResponse.CardsBean.DataBean> serviceList;
    OnItemClickListener onItemClickListener;


    public CardListAdapter(Activity serviceListActivity, List<CardListResponse.CardsBean.DataBean> serviceList, OnItemClickListener onItemClickListener) {
        this.serviceListActivity = serviceListActivity;
        this.serviceList = serviceList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_cards_adapter, parent, false);
        return new ServiceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ServiceViewHolder) {
            CardListResponse.CardsBean.DataBean positionData = serviceList.get(position);
            ((ServiceViewHolder) holder).cardNumber.setText(serviceListActivity.getString(R.string.sample_cardno_) + " " + positionData.getLast4());
            if (positionData.isSelected()) {
                ((ServiceViewHolder) holder).selectionImage.setChecked(true);
            } else ((ServiceViewHolder) holder).selectionImage.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public void updateAll(List<CardListResponse.CardsBean.DataBean> serviceList) {
        this.serviceList.clear();
        this.serviceList.addAll(serviceList);
        notifyDataSetChanged();
    }

    public void addItem(CardListResponse.CardsBean.DataBean posts) {
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
     * check service added or not
     */
    public boolean serviceSelected() {
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).isSelected()) {
                return true;
            }
        }
        return false;
    }

    public CardListResponse.CardsBean.DataBean getselected() {
        if (serviceList.size() > 0)
            for (int i = 0; i < serviceList.size(); i++) {
                if (serviceList.get(i).isSelected()) {
                    return serviceList.get(i);
                }
            }
        return null;
    }

    public boolean isSelected() {
        if (serviceList.size() > 0)
            for (int i = 0; i < serviceList.size(); i++) {
                if (serviceList.get(i).isSelected()) {
                    return true;
                }
            }
        return false;
    }


    public class ServiceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.selection_image)
        CheckBox selectionImage;

        @BindView(R.id.card_remove)
        ImageView cardRemove;

        @BindView(R.id.card_number)
        CustomTextView cardNumber;


        public ServiceViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({R.id.card_number, R.id.card_remove})
        public void onLCick(View v) {
            switch (v.getId()) {
                case R.id.card_number:
                    updateSelection(getAdapterPosition());
                    onItemClickListener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.DETAILS, null);
                    break;
                case R.id.card_remove:
                    onItemClickListener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.REMOVE, serviceList.get(getAdapterPosition()));

                    break;
            }
        }

    }

    private void removeCard(int adapterPosition) {
        serviceList.remove(adapterPosition);
        notifyDataSetChanged();
    }

    private void updateSelection(int adapterPosition) {
        if (serviceList.get(adapterPosition).isSelected()) {
            serviceList.get(adapterPosition).setSelected(false);
        } else {
            for (int i = 0; i < serviceList.size(); i++) {
                if (adapterPosition == i) {
                    serviceList.get(i).setSelected(true);
                } else serviceList.get(i).setSelected(false);
            }
        }
        notifyDataSetChanged();
    }
}