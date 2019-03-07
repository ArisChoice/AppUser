package com.app.barber.ui.adapters;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.models.ImagePickerModel;
import com.app.barber.models.Model;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.models.response.ServiceListResponseModel;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SelectedImagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity serviceListActivity;
    ArrayList<ImagePickerModel> list;
    OnItemClickListener onItemClickListener;


    public SelectedImagesAdapter(Activity serviceListActivity, ArrayList<ImagePickerModel> list,
                                 OnItemClickListener onItemClickListener) {
        this.serviceListActivity = serviceListActivity;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }
    public List<ImagePickerModel> getImageList() {
        return list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case Model.TEXT_ADD:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.view_add_adapter, parent, false);
                return new AddViewHolder(itemView);
            case Model.SELECTED_IMAGE:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.view_images_adapter, parent, false);
                return new SelectedViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImagePickerModel positionData = list.get(position);
        switch (positionData.getType()) {
            case Model.TEXT_ADD:

                break;
            case Model.SELECTED_IMAGE:
                ((SelectedViewHolder) holder).imageView.setImageURI(Uri.parse(list.get(position).getImagePath()));
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        switch (list.get(position).getType()) {
            case 0:
                return Model.TEXT_ADD;
            case 1:
                return Model.SELECTED_IMAGE;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateAll(List<ImagePickerModel> item) {
        this.list.addAll(item);
        notifyDataSetChanged();
    }

    public void addItem(ImagePickerModel posts) {
        this.list.add(0, posts);
        notifyDataSetChanged();
    }

    public void updateLikeData(int selectedPosition, String status, String postId) {

    }

    public void remove(int positio) {
        list.remove(positio);
        notifyDataSetChanged();
    }


    public class SelectedViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.selected_image_view)
        ImageView imageView;


        public SelectedViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({R.id.selected_image_view})
        public void onLCick(View v) {
            switch (v.getId()) {
                case R.id.selected_image_view:
                    onItemClickListener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.SERVICE_DELETE, list.get(getAdapterPosition()));
                    break;
            }
        }

        public void setData(TimeSlotsModel slotData) {
            // User Detail
        }

        private void toggleRefreshing(boolean b) {
        }


    }

    public class AddViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.add_image)
        CustomTextView addImage;


        public AddViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({R.id.add_image})
        public void onLCick(View v) {
            switch (v.getId()) {
                case R.id.add_image:
                    onItemClickListener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.ADD_IMAGE, list.get(getAdapterPosition()));
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