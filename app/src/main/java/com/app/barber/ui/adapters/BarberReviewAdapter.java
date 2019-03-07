package com.app.barber.ui.adapters;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.barber.R;
import com.app.barber.models.ChatMessageModel;
import com.app.barber.models.response.BarberRatingsResponseModel;
import com.app.barber.models.response.ServiceListResponseModel;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Sunny on 3/4/2017.
 */

public class BarberReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> /*implements
        StickyHeaderAdapter<ConversationAdapter.HeaderViewHolder> */ {

    private static final String TAG = BarberReviewAdapter.class.getSimpleName();

    Activity chatWindowsActivity;
    private LayoutInflater mInflater;
    private boolean isDeleteSelected;
    ArrayList<BarberRatingsResponseModel.ResponseBean.ReviewListBean> reviewList;
    OnItemClickListener onItemClickListener;

    public BarberReviewAdapter(Activity chatWindowsActivity, ArrayList<BarberRatingsResponseModel.ResponseBean.ReviewListBean> reviewList, OnItemClickListener onItemClickListener) {
        this.chatWindowsActivity = chatWindowsActivity;
        this.reviewList = reviewList;
        this.onItemClickListener = onItemClickListener;
        mInflater = LayoutInflater.from(chatWindowsActivity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_barber_review_layout, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int post) {
        BarberRatingsResponseModel.ResponseBean.ReviewListBean positionData = reviewList.get(post);
        if (holder instanceof ReviewViewHolder) {
            ((ReviewViewHolder) holder).userImage.setImageURI(positionData.getImage());
            ((ReviewViewHolder) holder).userName.setText(positionData.getFullName());
            ((ReviewViewHolder) holder).reviewText.setText(positionData.getReview());
        }
    }


    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public void updateList(List<BarberRatingsResponseModel.ResponseBean.ReviewListBean> newData) {
        reviewList.clear();
        reviewList.addAll(newData);
        notifyDataSetChanged();
    }

    public void add(BarberRatingsResponseModel.ResponseBean.ReviewListBean item) {
        reviewList.add(item);
        notifyDataSetChanged();
    }

    public void addList(BarberRatingsResponseModel.ResponseBean.ReviewListBean items, int posit) {
        reviewList.add(posit, items);
        notifyDataSetChanged();
    }

    public List<BarberRatingsResponseModel.ResponseBean.ReviewListBean> getList() {
        return reviewList;
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        @BindView(R.id.user_name)
        CustomTextView userName;
        @BindView(R.id.review_text)
        CustomTextView reviewText;
        @BindView(R.id.user_image)
        SimpleDraweeView userImage;

        public ReviewViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View view) {

        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }

}
