package com.app.barber.ui.postauth.activities.barber.fragments;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.barber.R;
import com.app.barber.core.BaseFragment;
import com.app.barber.models.response.ServiceListResponseModel;
import com.app.barber.ui.postauth.activities.barber.barber_adapter.BarberPortfolioAdapter;
import com.app.barber.ui.postauth.activities.barber.barber_adapter.BarberReviewsListAdapter;
import com.app.barber.ui.postauth.activities.barber.BarberDetailActivity;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.app.barber.core.BaseActivity.activitySwitcher;

/**
 * Created by harish on 25/10/18.
 */

public class DetailFragment extends BaseFragment {

    @BindView(R.id.cover_slider)
    ImageView coverSlider;
    @BindView(R.id.chk_fav_)
    CheckBox chkFav;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.txt_toolbar)
    CustomTextView txtToolbar;
    @BindView(R.id.chk_bx_hedaer)
    CheckBox chkBxHedaer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_Place)
    CustomTextView txtPlace;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.barber_info_btn)
    CustomTextView barberInfoBtn;
    @BindView(R.id.barber_info_lay)
    LinearLayout barberInfoLay;
    @BindView(R.id.review_btn)
    CustomTextView reviewBtn;
    @BindView(R.id.review_lay)
    LinearLayout reviewLay;
    @BindView(R.id.portfolio_Btn)
    CustomTextView portfolioBtn;
    @BindView(R.id.recyclar_review_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    @BindView(R.id.portfolio_lay)
    LinearLayout portfolioLay;
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarReviewViewLst;
    private BarberReviewsListAdapter barberReviewAdapter;
//    private BarberDetailPagerAdapter barberPagerAdapter;

    @Override
    protected int getFragmentLayout() {
        return R.layout.layout_detail_screen;
    }

    @Override
    public void UpdateData(int position, Bundle bundle) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this, rootView);
//        initTabs();
//        initPager();
        initRecyclar();
        initPortfolioRecyclar();
        return rootView;
    }

    private void initPortfolioRecyclar() {
        BarberPortfolioAdapter listAdapter = new BarberPortfolioAdapter(getActivity(), null,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position, int type, Object t) {
                        activitySwitcher(getActivity(), BarberDetailActivity.class, new Bundle());
                    }
                });
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclarReviewViewLst.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclarReviewViewLst.setAdapter(listAdapter);
        recyclarReviewViewLst.setNestedScrollingEnabled(false);
    }

    private void initRecyclar() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclarViewLst.setLayoutManager(layoutManager);
        barberReviewAdapter = new BarberReviewsListAdapter(getActivity(), new ArrayList<ServiceListResponseModel.ResponseBean>(10),
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int positio, int type, Object o) {
                        switch (type) {

                        }

                    }
                });
        recyclarViewLst.setAdapter(barberReviewAdapter);
        recyclarViewLst.setNestedScrollingEnabled(false);
    }
    
/*
    */

    /**
     * initialize pager
     *//*
    private void initPager() {
        barberPagerAdapter = new BarberDetailPagerAdapter(getActivity(), getChildFragmentManager());
        detailPager.setAdapter(barberPagerAdapter);
//        detailPager.setPagingEnabled(true);
        detailPager.setOffscreenPageLimit(2);
//        detailPager.swipeable = false;
        detailPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }*/

    /*private void initTabs() {
        detailScreenTabs.addTab(detailScreenTabs.newTab().setText(R.string.str_barber_info));
        detailScreenTabs.addTab(detailScreenTabs.newTab().setText(R.string.str_reviews));
        detailScreenTabs.addTab(detailScreenTabs.newTab().setText(R.string.str_portfolio));
        detailScreenTabs.setupWithViewPager(detailPager);
        detailScreenTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                detailPager.setCurrentItem();
                detailPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }*/
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @OnClick({R.id.chk_fav_, R.id.barber_info_btn, R.id.review_btn, R.id.portfolio_Btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chk_fav_:
                break;
            case R.id.barber_info_btn:
                if (barberInfoLay.getVisibility() == View.VISIBLE) {
//                    barberInfoLay.setVisibility(View.GONE);
                    CommonUtils.getInstance(getActivity()).slideDown(barberInfoLay);
                } else {
//                    barberInfoLay.setVisibility(View.VISIBLE);
                    CommonUtils.getInstance(getActivity()).slideUp(barberInfoLay);
                }
                break;
            case R.id.review_btn:
                if (reviewLay.getVisibility() == View.VISIBLE) {
//                    reviewLay.setVisibility(View.GONE);
                    CommonUtils.getInstance(getActivity()).slideDown(reviewLay);
                } else {
//                    reviewLay.setVisibility(View.VISIBLE);
                    CommonUtils.getInstance(getActivity()).slideUp(reviewLay);
                }
                break;
            case R.id.portfolio_Btn:
                if (portfolioLay.getVisibility() == View.VISIBLE) {
//                    portfolioLay.setVisibility(View.GONE);
                    CommonUtils.getInstance(getActivity()).slideDown(portfolioLay);
                } else {
//                    portfolioLay.setVisibility(View.VISIBLE);
                    CommonUtils.getInstance(getActivity()).slideUp(portfolioLay);
                }
                break;
        }
    }
}
