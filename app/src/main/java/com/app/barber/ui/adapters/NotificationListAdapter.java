package com.app.barber.ui.adapters;

import android.app.Activity;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.models.response.NotificationResponseModel;
import com.app.barber.util.CustomDate;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.NOtificationViewHolder> {

    Activity activity;
    ArrayList<NotificationResponseModel.ResponseBean> nList;
    OnItemClickListener listener;
    TypedArray icons;

    public NotificationListAdapter(Activity activity, ArrayList<NotificationResponseModel.ResponseBean> nList, OnItemClickListener listener) {
        this.activity = activity;
        this.listener = listener;
        this.nList = nList;
    }

    @Override
    public NOtificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_notification_adapter, parent, false);
        return new NOtificationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NOtificationViewHolder holder, int position) {
        NotificationResponseModel.ResponseBean positionData = nList.get(position);
        holder.notificationText.setText(positionData.getMessage());
        switch (positionData.getNotificationType()) {
            case GlobalValues.NotificationType.BookingCompleted:
                holder.notificationImage.setImageResource(R.drawable.scissors);
                holder.notificationImage.setBackgroundResource(R.drawable.circular_notifiaction_blue_background);
                holder.ststusText.setVisibility(View.VISIBLE);
                break;
            case GlobalValues.NotificationType.CalloutCompleted:
                holder.notificationImage.setImageResource(R.drawable.callout_);
                holder.notificationImage.setBackgroundResource(R.drawable.circular_pink_background);
                holder.ststusText.setVisibility(View.VISIBLE);
                break;
            case GlobalValues.NotificationType.BookingCanceled:
                holder.notificationImage.setImageResource(R.drawable.scissors);
                holder.notificationImage.setBackgroundResource(R.drawable.circular_notifiaction_blue_background);
                holder.ststusText.setVisibility(View.GONE);
                break;
        }
        holder.notificationTime.setText(CustomDate.getDateFromUTCTimestamp(Long.parseLong(positionData.getDateProcessed()), "MMM dd"));
       /* holder.notificationTime.setText(DateUtils.
                getRelativeTimeSpanString(CustomDate.getValidTimestamp(Long.parseLong(positionData.getDateProcessed().split(".")[0])),
                        System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS));*/

        /*if (positionData.isToday()) {
            holder.notificationTime.setText(CustomDate.getCurrentFormat(activity, positionData.getDateProcessed(), "hh:mm:ss", "hh"));
        } else
            holder.notificationTime.setText(CustomDate.getCurrentFormat(activity, positionData.getDateProcessed(), "MM/dd/yyyy", "dd,MM"));*/
    }

    @Override
    public int getItemCount() {
        return nList.size();
    }

    public void updateAll(List<NotificationResponseModel.ResponseBean> posts) {
        this.nList.clear();
        this.nList.addAll(posts);
        notifyDataSetChanged();
    }

    public void addItem(NotificationResponseModel.ResponseBean posts) {
//        this.slotsList.add(0, posts);
//        notifyDataSetChanged();
    }


    public class NOtificationViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.notification_img)
        ImageView notificationImage;

        @BindView(R.id.notification_text)
        CustomTextView notificationText;
        @BindView(R.id.notification_time)
        CustomTextView notificationTime;
        @BindView(R.id.ststus_text)
        CustomTextView ststusText;

        public NOtificationViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({R.id.notification_text, R.id.notification_img})
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.notification_img:
                case R.id.notification_text:
                    if (nList.get(getAdapterPosition()).getNotificationType() == GlobalValues.NotificationType.BookingCompleted ||
                            nList.get(getAdapterPosition()).getNotificationType() == GlobalValues.NotificationType.CalloutCompleted)
                        listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.NOTIFICATION_CLICK, nList.get(getAdapterPosition()));
                    break;
            }
        }


    }
}