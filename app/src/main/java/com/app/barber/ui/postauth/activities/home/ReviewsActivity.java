package com.app.barber.ui.postauth.activities.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.ui.adapters.ReviewsListAdapter;
import com.app.barber.ui.postauth.activities.barber.EditReviewActivity;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 14/11/18.
 */

public class ReviewsActivity extends BaseActivity {
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
    private ReviewsListAdapter listAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.layout_reviews_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtTitleToolbar.setText(R.string.str_reviews);
        initRecyclar();
    }

    private void initRecyclar() {
        listAdapter = new ReviewsListAdapter(ReviewsActivity.this, null,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position, int type, Object t) {
                        activitySwitcher(ReviewsActivity.this, EditReviewActivity.class, null);
                    }
                });
        LinearLayoutManager layoutManager = new LinearLayoutManager(ReviewsActivity.this);
        recyclarViewLst.setLayoutManager(layoutManager);
        recyclarViewLst.setAdapter(listAdapter);
    }

    @OnClick(R.id.back_toolbar)
    public void onClick() {
        onBackPressed();
    }
}
