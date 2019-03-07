package com.app.barber.ui.weekcontroler;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.barber.R;
import com.app.barber.models.ModelDay;
import com.app.barber.models.TimeSlotsModel;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WeekViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ModelDay> specList;
    OnItemClickListener listener;
    Activity specialiseActivity;

    public WeekViewAdapter(Activity specialiseActivity, List<ModelDay> feedsList, OnItemClickListener listener) {
        this.specList = feedsList;
        this.listener = listener;
        this.specialiseActivity = specialiseActivity;
    }

    @Override
    public SlotsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_week_days_adapter, parent, false);
        return new SlotsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ModelDay positionData = specList.get(position);
        ((SlotsViewHolder) holder).weekDate.setText("" + positionData.getDate());
        ((SlotsViewHolder) holder).weekDay.setText("" + positionData.getDay());
        if (positionData.isSelected()) {
            Log.e("onBindViewHolder", " if  " + positionData.isSelected() + " " + positionData.getDate());
            ((SlotsViewHolder) holder).weekDate.setText("" + positionData.getDate());
            ((SlotsViewHolder) holder).weekDate.setTextColor(specialiseActivity.getResources().getColor(R.color.color_white));
            ((SlotsViewHolder) holder).weekDate.setBackgroundResource(R.drawable.circular_blue_background);
        } else {
            Log.e("onBindViewHolder", "   else " + positionData.isSelected() + " " + positionData.getDate());
            ((SlotsViewHolder) holder).weekDate.setText("" + positionData.getDate());
            ((SlotsViewHolder) holder).weekDate.setTextColor(specialiseActivity.getResources().getColor(R.color.color_grey));
            ((SlotsViewHolder) holder).weekDate.setBackgroundResource(R.drawable.rounded_white_background);
        }
    }

    @Override
    public int getItemCount() {
        return specList.size();
    }

    public void updateAll(List<ModelDay> posts) {
        this.specList.clear();
        this.specList.addAll(posts);
        notifyDataSetChanged();
    }

    public void addItem(ModelDay posts) {
        this.specList.add(0, posts);
        notifyDataSetChanged();
    }

    public void updateLikeData(int selectedPosition, String status, String postId) {


    }

    public void updatePosition(TimeSlotsModel o) {
    }

    /**
     * Set next upcoming days days
     *
     * @wNo day count
     */
    public void setCurrentWeek(int wNo) {
        ArrayList<ModelDay> modelList = new ArrayList<>();
        ModelDay mDay = new ModelDay();
        SimpleDateFormat sdfFull = new SimpleDateFormat("MM/dd/yyyy");//EEEE,MMMM YY
        SimpleDateFormat sdf = new SimpleDateFormat("EE");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
        for (int i = 0; i < wNo; i++) {
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, i);
            String day = sdf.format(calendar.getTime());
            String date = sdf1.format(calendar.getTime());
            String dateFull = sdfFull.format(calendar.getTime());
            Log.i("TAG", day + " " + date + "  full date " + dateFull);
            mDay = new ModelDay();
            mDay.setDate(Integer.parseInt(date));
            mDay.setDay(day.substring(0, 2));
            mDay.setFullDate(dateFull);
            if (i == 0) {
                mDay.setSelected(true);
                mDay.setDayName("Today");
            } else if (i == 1) {
                mDay.setDayName("Tomorrow");
            } else {
                mDay.setDayName(day);
            }
            modelList.add(mDay);
        }
        specList.clear();
        specList.addAll(modelList);
        notifyDataSetChanged();
    }

    public String getSelectedDate() {
        return specList.get(0).getFullDate();//default selected date
    }

    public void setselected(String date) {
        Log.e("setselected", "  " + date);
        for (int i = 0; i < specList.size(); i++) {
            if (specList.get(i).getFullDate().equals(date)) {
                specList.get(i).setSelected(true);
            } else specList.get(i).setSelected(false);
        }
    }

    public void disableCurrentdayDefaultSelection() {
        for (int i = 0; i < specList.size(); i++) {
            specList.get(i).setSelected(false);
        }
        notifyDataSetChanged();
    }


    public class SlotsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.week_day)
        CustomTextView weekDay;
        @BindView(R.id.week_date)
        CustomTextView weekDate;


        public SlotsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick({R.id.week_date})
        public void onLCick(View v) {
            switch (v.getId()) {
                case R.id.week_date:
                    listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.ADD_TIME_CLICK, specList.get(getAdapterPosition()));
                    updateSelection(getAdapterPosition());
                    break;
            }

//            listener.onItemClick(v, getAdapterPosition(), GlobalValues.ClickOperations.ADD_TIME_CLICK, specList.get(getAdapterPosition()));

        }
    }

    private void updateSelection(int adapterPosition) {
        for (int i = 0; i < specList.size(); i++) {
            if (i == adapterPosition)
                specList.get(i).setSelected(true);
            else specList.get(i).setSelected(false);
        }
        notifyDataSetChanged();
    }


    public void setData(TimeSlotsModel slotData) {
        // User Detail
    }

    private void toggleRefreshing(boolean b) {
    }


}
