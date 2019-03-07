package com.app.barber.ui.postauth.activities.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.ui.adapters.PaymentsListAdapter;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 20/11/18.
 */

public class PaymentListActivity extends BaseActivity {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    private PaymentsListAdapter paymentAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.layout_payment_list_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtTitleToolbar.setText(R.string.str_payments);
        initRecyclar();
    }

    private void initRecyclar() {
        paymentAdapter = new PaymentsListAdapter(PaymentListActivity.this, null,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position, int type, Object t) {
                        activitySwitcher(PaymentListActivity.this, PaymentDetailActivity.class, null);
                    }
                });
        LinearLayoutManager layoutManager = new LinearLayoutManager(PaymentListActivity.this);
        recyclarViewLst.setLayoutManager(layoutManager);
        recyclarViewLst.setAdapter(paymentAdapter);
    }

    @OnClick(R.id.back_toolbar)
    public void onClick() {
        onBackPressed();
    }
}
