package com.app.barber.ui.postauth.activities.barber.barber_adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.app.barber.R;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.models.response.BarberListResponseModel;
import com.app.barber.ui.postauth.fragment.SavedFragment;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.PreferenceManager;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BarberListAdapter extends RecyclerView.Adapter<BarberListAdapter.OptionViewHolder> {

    FragmentActivity activity;
    List<BarberListResponseModel.ListBean> barberList;
    OnItemClickListener listener;
    String fromScreen;

    public BarberListAdapter(FragmentActivity activity, List<BarberListResponseModel.ListBean> barberList, String fromScreen, OnItemClickListener listener) {
        this.activity = activity;
        this.listener = listener;
        this.barberList = barberList;
        this.fromScreen = fromScreen;
    }

    @Override
    public OptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_barber_adapter, parent, false);
        return new OptionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OptionViewHolder holder, int position) {
        BarberListResponseModel.ListBean positionData = barberList.get(position);
        holder.barberName.setText(positionData.getFullName());
        holder.barberImage.setImageURI(positionData.getProfileImage());
        if (positionData.getCity() != null && !positionData.getCity().equals(""))
            holder.barberAddress.setText(positionData.getCity());
        else holder.barberAddress.setText("Not found");
        if (positionData.isFavourite()) {
            holder.isSaved.setChecked(true);
        } else holder.isSaved.setChecked(false);

        if (positionData.getBarberType() != null && positionData.getBarberType().contains("3")) {//is trainee
            holder.traineeText.setVisibility(View.VISIBLE);
        } else holder.traineeText.setVisibility(View.GONE);
        if (positionData.getAvgRating() != null) {
            holder.avgRating.setText(CommonUtils.formatDecimalPoint(positionData.getAvgRating(), 1) + " " + activity.getString(R.string.str_ratings));
        }
        if (positionData.getPuchRating() != null) {
            holder.punctualityRating.setText(CommonUtils.formatDecimalPoint(positionData.getPuchRating(), 1) + " " + activity.getString(R.string.str_punctuality));
        }
        if (positionData.getDistance() != null && !positionData.getDistance().equals("")) {
            holder.barberDistance.setText(positionData.getDistance() + GlobalValues.DistanceType.MILES);
            holder.barberDistance.setVisibility(View.VISIBLE);
        } else {
            holder.barberDistance.setVisibility(View.INVISIBLE);
        }
        if (positionData.isSuperBarber()) {
            holder.barrberStatus.setVisibility(View.VISIBLE);
        } else {
            holder.barrberStatus.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return barberList.size();
    }

    public void updateAll(List<BarberListResponseModel.ListBean> barberList) {
        this.barberList.clear();
        this.barberList.addAll(barberList);
        notifyDataSetChanged();
    }

    public void addItem(BarberListResponseModel.ListBean posts) {
        this.barberList.add(0, posts);
        notifyDataSetChanged();
    }

    public void addAll(List<BarberListResponseModel.ListBean> list) {
        barberList.addAll(list);
        notifyDataSetChanged();
    }

    public List<BarberListResponseModel.ListBean> getList() {
        return barberList;
    }

    public void removeAll() {
        barberList.clear();
        notifyDataSetChanged();
    }


    public class OptionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.barber_image)
        SimpleDraweeView barberImage;

        @BindView(R.id.barrber_status)
        CustomTextView barrberStatus;
        @BindView(R.id.barber_name)
        CustomTextView barberName;
        @BindView(R.id.barber_address)
        CustomTextView barberAddress;
        @BindView(R.id.barber_is_trainee_txt)
        CustomTextView traineeText;
        @BindView(R.id.chk_fav_)
        CheckBox isSaved;

        @BindView(R.id.avg_rating)
        CustomTextView avgRating;
        @BindView(R.id.punctuality_rating)
        CustomTextView punctualityRating;
        @BindView(R.id.barber_distance)
        CustomTextView barberDistance;


        public OptionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            barrberStatus.bringToFront();
            if (new PreferenceManager().getPrefrencesBoolean(GlobalValues.KEYS.isLogedIn))
                isSaved.setClickable(true);
            else isSaved.setClickable(false);

        }

        @OnClick({R.id.barber_image, R.id.chk_fav_, R.id.barber_name})
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.barber_name:
                case R.id.barber_image:
                    listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.SHOW_DETAIL, barberList.get(getAdapterPosition()));
                    break;
                case R.id.chk_fav_:
                    listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.FAV_UNFAV, barberList.get(getAdapterPosition()));
                    if (barberList.get(getAdapterPosition()).isFavourite()) {
                        barberList.get(getAdapterPosition()).setFavourite(false);
                        if (fromScreen.equals(SavedFragment.class.getName())) {
                            barberList.remove(getAdapterPosition());
                        }
                    } else barberList.get(getAdapterPosition()).setFavourite(true);
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