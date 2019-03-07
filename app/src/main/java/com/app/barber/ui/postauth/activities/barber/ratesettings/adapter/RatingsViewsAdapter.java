package com.app.barber.ui.postauth.activities.barber.ratesettings.adapter;

import android.content.res.TypedArray;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.barber.R;
import com.app.barber.models.RateData;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RatingsViewsAdapter extends RecyclerView.Adapter<RatingsViewsAdapter.OptionViewHolder> {

    FragmentActivity activity;
    OnItemClickListener listener;
    ArrayList<RateData> rList;
    int type;

    public RatingsViewsAdapter(FragmentActivity activity, ArrayList<RateData> rList, int type, OnItemClickListener listener) {
        this.activity = activity;
        this.listener = listener;
        this.rList = rList;
        this.type = type;
    }

    @Override
    public OptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_rate_adapter, parent, false);
        return new OptionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OptionViewHolder holder, int position) {
        RateData positionData = rList.get(position);
        if (positionData.isSelected()) {
            switch (type) {
                case GlobalValues.RatingTypes.PUNCTUALITY:
                    holder.viewTxt.setBackgroundResource(R.drawable.rounder_green_drawable);
                    break;
                case GlobalValues.RatingTypes.EXPERTIES:
                    holder.viewTxt.setBackgroundResource(R.drawable.rounder_light_green_drawable);
                    break;
                case GlobalValues.RatingTypes.VALUE:
                    holder.viewTxt.setBackgroundResource(R.drawable.rounder_yellow_drawable);
                    break;
                case GlobalValues.RatingTypes.HYGINENE:
                    holder.viewTxt.setBackgroundResource(R.drawable.rounded_orange_clay_background);
                    break;
            }

        } else holder.viewTxt.setBackgroundResource(R.drawable.rounded_grey_background);


    }

    @Override
    public int getItemCount() {
        return rList.size();
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

    public void updateRating(String puchRating, int type) {
        this.type = type;
        for (int i = 0; i < rList.size(); i++) {
            if (rList.get(i).getRatingNum() == Integer.parseInt(puchRating)) {
                updateSelected(i);
            }

        }
       /* switch (type) {
            case GlobalValues.RatingTypes.PUNCTUALITY:
                updateSelected(getAdapterPosition());
                break;
            case GlobalValues.RatingTypes.EXPERTIES:
                break;
            case GlobalValues.RatingTypes.VALUE:
                break;
            case GlobalValues.RatingTypes.HYGINENE:
                break;
        }*/
    }


    public class OptionViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.view_txt)
        TextView viewTxt;

        public OptionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }

        @OnClick({R.id.view_txt})
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.view_txt:
                    listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.MORE_OPRION_CLICK, null);
                    updateSelected(getAdapterPosition());
                    break;

            }
        }

    }

    private void updateSelected(int adapterPosition) {
        for (int i = 0; i < rList.size(); i++) {
            if (i <= adapterPosition) {
                rList.get(i).setSelected(true);
            } else rList.get(i).setSelected(false);
        }
        notifyDataSetChanged();
    }
}