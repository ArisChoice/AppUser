package com.app.barber.ui.adapters;

import android.content.res.TypedArray;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.barber.R;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PaymentsListAdapter extends RecyclerView.Adapter<PaymentsListAdapter.OptionViewHolder> {

    FragmentActivity activity;
    String[] namelist;
    OnItemClickListener listener;
    TypedArray icons;

    public PaymentsListAdapter(FragmentActivity activity, String[] moreOptionModels, OnItemClickListener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public OptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_payment_adapter, parent, false);
        return new OptionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OptionViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 2;
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

        @BindView(R.id.view_lay)
        LinearLayout viewLay;

        public OptionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({R.id.view_lay})
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.view_lay:
//                    Log.e("onClick", "   " + namelist[getAdapterPosition()]);
                    listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.MORE_OPRION_CLICK, null);
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