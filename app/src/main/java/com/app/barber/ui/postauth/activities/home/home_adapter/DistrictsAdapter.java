package com.app.barber.ui.postauth.activities.home.home_adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.app.barber.R;
import com.app.barber.models.response.DistricResponseModel;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DistrictsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private List<DistricResponseModel.ListBean> distList;
    OnItemClickListener listener;
    Activity specialiseActivity;
    private List<DistricResponseModel.ListBean> filterList;

    public DistrictsAdapter(Activity specialiseActivity, List<DistricResponseModel.ListBean> feedsList, OnItemClickListener listener) {
        this.distList = feedsList;
        this.listener = listener;
        this.specialiseActivity = specialiseActivity;
        this.filterList = feedsList;
    }

    @Override
    public SlotsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_zone_districts_adapter, parent, false);
        return new SlotsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DistricResponseModel.ListBean positionData = filterList.get(position);
        ((SlotsViewHolder) holder).districtText.setText(positionData.getText());
        if (positionData.isSelected()) {
            ((SlotsViewHolder) holder).districtText.setBackgroundResource(R.drawable.rectangle_white_border);
            ((SlotsViewHolder) holder).districtText.setTextColor(specialiseActivity.getResources().getColor(R.color.color_app_blue));
            ((SlotsViewHolder) holder).districtText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.check_, 0);
        } else {
            ((SlotsViewHolder) holder).districtText.setBackgroundResource(R.drawable.rectangle_grey_border);
            ((SlotsViewHolder) holder).districtText.setTextColor(specialiseActivity.getResources().getColor(R.color.color_black));
            ((SlotsViewHolder) holder).districtText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
        }

    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    public void updateAll(List<DistricResponseModel.ListBean> posts) {
        this.filterList.clear();
        this.filterList.addAll(posts);
        notifyDataSetChanged();
    }

    public void addItem(DistricResponseModel.ListBean posts) {
        this.filterList.add(0, posts);
        notifyDataSetChanged();
    }


    public String getselected() {
        String selectedType = null;
        try {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < filterList.size(); i++) {
                if (filterList.get(i).isSelected()) {
//                    builder.append(filterList.get(i).getValue() + ",");
                    builder.append(filterList.get(i).getText().split("\\s")[0] + ",");
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

    public DistricResponseModel.ListBean getItem(int position) {
        return filterList.get(position);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filterList = distList;
                } else {
                    List<DistricResponseModel.ListBean> filteredList = new ArrayList<>();
                    for (DistricResponseModel.ListBean row : distList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getText().toLowerCase().contains(charString.toLowerCase()) || row.getText().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    filterList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterList = (ArrayList<DistricResponseModel.ListBean>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void setSelected(String selectedDistricts) {
        String[] stringData = selectedDistricts.split(",");
        for (int i = 0; i < filterList.size(); i++) {
            for (int j = 0; j < stringData.length; j++) {
                if (stringData[j].equals(filterList.get(i).getValue())) {
                    filterList.get(i).setSelected(true);
                }
            }
        }
        notifyDataSetChanged();
    }


    public class SlotsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.district_text_text)
        CustomTextView districtText;


        public SlotsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({R.id.district_text_text})
        public void onLCick(View v) {
            //multi selection code
            /*notifyMultiSelection(getAdapterPosition())*/
            notifySingleSelection(getAdapterPosition());
            listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.DETAILS, null);


        }

        private void notifyMultiSelection(int adapterPosition) {
            if (filterList.get(getAdapterPosition()).isSelected()) {
                filterList.get(getAdapterPosition()).setSelected(false);
            } else {
                filterList.get(getAdapterPosition()).setSelected(true);
            }

        }

        private void notifySingleSelection(int adapterPosition) {
            for (int i = 0; i < filterList.size(); i++) {
                if (adapterPosition == i) {
                    if (filterList.get(i).isSelected())
                        filterList.get(i).setSelected(false);
                    else filterList.get(i).setSelected(true);
                } else {
                    filterList.get(i).setSelected(false);
                }
            }
            notifyDataSetChanged();
        }
    }
}
