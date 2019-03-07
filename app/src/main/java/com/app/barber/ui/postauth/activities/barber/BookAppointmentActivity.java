package com.app.barber.ui.postauth.activities.barber;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.views.CustomTextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 21/11/18.
 */

public class BookAppointmentActivity extends BaseActivity {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.calendar_vw)
    CalendarView calendarVw;
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    @BindView(R.id.add_service_btn)
    CustomTextView addServiceBtn;
    @BindView(R.id.cnfrm_btn)
    CustomTextView cnfrmBtn;

    @Override
    public int getLayoutId() {
        return R.layout.layout_bookbarber_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtTitleToolbar.setText(R.string.str_book_appointment);
        initCalendar();
    }

    private void initCalendar() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        long endOfMonth = calendar.getTimeInMillis();
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        long startOfMonth = calendar.getTimeInMillis();
        calendarVw.setMaxDate(endOfMonth);
        calendarVw.setMinDate(startOfMonth);
    }

    @OnClick({R.id.back_toolbar, R.id.img_edit, R.id.add_service_btn, R.id.cnfrm_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.img_edit:
                break;
            case R.id.add_service_btn:
                break;
            case R.id.cnfrm_btn:
                break;
        }
    }
}
