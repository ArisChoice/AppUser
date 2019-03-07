package com.app.barber.ui.postauth.activities.barber.barber_adapter;

import android.content.res.TypedArray;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.barber.R;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.models.response.BarberPortfoloiImageResponse;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.ImageUtility;
import com.app.barber.util.iface.OnItemClickListener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BarberPortfolioAdapter extends RecyclerView.Adapter<BarberPortfolioAdapter.OptionViewHolder> {

    FragmentActivity activity;
    ArrayList<BarberPortfoloiImageResponse.ResponseBean> list;
    OnItemClickListener listener;
    TypedArray icons;

    public BarberPortfolioAdapter(FragmentActivity activity, ArrayList<BarberPortfoloiImageResponse.ResponseBean> list, OnItemClickListener listener) {
        this.activity = activity;
        this.listener = listener;
        this.list = list;
    }

    @Override
    public OptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_portfolio_adapter, parent, false);
        return new OptionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OptionViewHolder holder, int position) {
        BarberPortfoloiImageResponse.ResponseBean positiondata = list.get(position);
        holder.portfolioImage.setImageURI(ImageUtility.getValidUrl(positiondata.getMItem1()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateAll(List<BarberPortfoloiImageResponse.ResponseBean> posts) {
        this.list.clear();
        this.list.addAll(posts);
        notifyDataSetChanged();
    }

    public void addItem(BarberPortfoloiImageResponse.ResponseBean posts) {
//        this.slotsList.add(0, posts);
//        notifyDataSetChanged();
    }


    public class OptionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.portfolio_image)
        SimpleDraweeView portfolioImage;


        public OptionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({R.id.portfolio_image})
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.portfolio_image:
                    listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.SHOW_DETAIL, list.get(getAdapterPosition()));
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