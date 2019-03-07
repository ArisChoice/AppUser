package com.app.barber.ui.adapters;

import android.content.res.TypedArray;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.barber.R;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.models.response.MyImagesResponseModel;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.ImageUtility;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyStyleAdapter extends RecyclerView.Adapter<MyStyleAdapter.OptionViewHolder> {

    FragmentActivity activity;
    List<MyImagesResponseModel.ImagesBean> imagesList;
    OnItemClickListener listener;
    TypedArray icons;

    public MyStyleAdapter(FragmentActivity activity, List<MyImagesResponseModel.ImagesBean> imagesList, OnItemClickListener listener) {
        this.activity = activity;
        this.listener = listener;
        this.imagesList = imagesList;
    }

    @Override
    public OptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_mystyle_adapter, parent, false);
        return new OptionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OptionViewHolder holder, int position) {
        MyImagesResponseModel.ImagesBean positionData = imagesList.get(position);
        if (position == 0) {
            holder.addLay.setVisibility(View.VISIBLE);
            holder.imageHolder.setVisibility(View.GONE);
        } else {
            holder.addLay.setVisibility(View.GONE);
            holder.imageStyle.setImageURI(ImageUtility.getValidUrl(positionData.getMItem1()));
            holder.imageHolder.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public void updateAll(List<MyImagesResponseModel.ImagesBean> imagesList) {
        this.imagesList.clear();
        this.imagesList.add(null);
        this.imagesList.addAll(imagesList);
        notifyDataSetChanged();
    }

    public void addItem(TimeSlotsModel posts) {
//        this.slotsList.add(0, posts);
//        notifyDataSetChanged();
    }


    public class OptionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.my_style_image)
        SimpleDraweeView imageStyle;
        @BindView(R.id.add_image_btn_lay)
        LinearLayout addLay;
        @BindView(R.id.image_holder)
        RelativeLayout imageHolder;
        @BindView(R.id.remove_image_btn)
        CustomTextView removeImage;

        public OptionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({R.id.add_image_btn_lay, R.id.my_style_image, R.id.remove_image_btn})
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add_image_btn_lay:
                    listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.ADD_IMAGE, null);
                    break;
                case R.id.my_style_image:
                    listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.DETAILS, imagesList.get(getAdapterPosition()));
                    break;
                case R.id.remove_image_btn:
                    listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.REMOVE, imagesList.get(getAdapterPosition()));
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