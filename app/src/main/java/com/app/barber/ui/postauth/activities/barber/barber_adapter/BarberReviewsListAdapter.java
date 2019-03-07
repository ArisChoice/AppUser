package com.app.barber.ui.postauth.activities.barber.barber_adapter;

import android.content.res.TypedArray;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.barber.R;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.models.response.ServiceListResponseModel;
import com.app.barber.util.iface.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class BarberReviewsListAdapter extends RecyclerView.Adapter<BarberReviewsListAdapter.OptionViewHolder> {

    FragmentActivity activity;
    String[] namelist;
    OnItemClickListener listener;
    TypedArray icons;

    public BarberReviewsListAdapter(FragmentActivity activity, ArrayList<ServiceListResponseModel.ResponseBean> moreOptionModels, OnItemClickListener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public OptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_barber_reviews_adapter, parent, false);
        return new OptionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OptionViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 20;
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


    public class OptionViewHolder extends RecyclerView.ViewHolder {

        /*  @BindView(R.id.option_icon)
          ImageView optionIcon;
          @BindView(R.id.option_name)
          CustomTextView optionName;
  */
        public OptionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({})
        public void onClick(View v) {
            switch (v.getId()) {
//                case R.id.option_name:
//                    Log.e("onClick", "   " + namelist[getAdapterPosition()]);
//                    listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.MORE_OPRION_CLICK, namelist[getAdapterPosition()]);
//                    break;

            }
        }

        public void setData(TimeSlotsModel slotData) {
            // User Detail


        }

        private void toggleRefreshing(boolean b) {

        }


    }
}