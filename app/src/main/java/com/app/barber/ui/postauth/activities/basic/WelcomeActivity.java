package com.app.barber.ui.postauth.activities.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.response.LoginResponseModel;
import com.app.barber.models.response.UpdateDataResponse;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.postauth.activities.basic.basicmvp.BasicAuthMVPView;
import com.app.barber.ui.postauth.activities.basic.basicmvp.BssicAuthPresenter;
import com.app.barber.views.CustomTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 23/10/18.
 */

public class WelcomeActivity extends BaseActivity implements BasicAuthMVPView {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.text_type_one)
    CustomTextView textTypeOne;
    @BindView(R.id.img_check_type_one)
    ImageView imgCheckTypeOne;
    @BindView(R.id.type_barber_layout)
    LinearLayout typeBarberLayout;
    @BindView(R.id.text_type_two)
    CustomTextView textTypeTwo;
    @BindView(R.id.img_check_type_two)
    ImageView imgCheckTypeTwo;
    @BindView(R.id.type_callout_barber_layout)
    LinearLayout typeCalloutBarberLayout;
    @BindView(R.id.text_type_three)
    CustomTextView textTypeThree;
    @BindView(R.id.img_check_type_three)
    ImageView imgCheckTypeThree;
    @BindView(R.id.type_tranee_barber_layout)
    LinearLayout typeTraneeBarberLayout;
    @BindView(R.id.continue_btn)
    ImageView continueBtn;
    private BssicAuthPresenter presenter;
    private String selectedType;

    @Override
    public int getLayoutId() {
        return R.layout.welcome_screen_activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);

        presenter = new BssicAuthPresenter(this);
        presenter.attachView(this);
        backToolbar.setVisibility(View.INVISIBLE);
        txtTitleToolbar.setVisibility(View.VISIBLE);
        txtTitleToolbar.setText(R.string.str_welcome_txt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @OnClick({R.id.back_toolbar, R.id.type_barber_layout, R.id.type_callout_barber_layout, R.id.type_tranee_barber_layout, R.id.continue_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                break;
            case R.id.type_barber_layout:
                setTypeSelection(view.getId());
                break;
            case R.id.type_callout_barber_layout:
                setTypeSelection(view.getId());
                break;
            case R.id.type_tranee_barber_layout:
                setTypeSelection(view.getId());
                break;
            case R.id.continue_btn:
                if (selectedType != null)
                    presenter.updateBarberType(NetworkConstatnts.RequestCode.API_UPDATE_BARBER_TYPE, selectedType, true);
//
                break;
        }
    }


    private void setTypeSelection(int id) {
        switch (id) {
            case R.id.type_barber_layout://1
                if (imgCheckTypeOne.getVisibility() == View.GONE) {
                    typeBarberLayout.setBackgroundResource(R.drawable.rectangle_selected_type_drawable);
                    textTypeOne.setTextColor(getResources().getColor(R.color.colorPrimary));
                    imgCheckTypeOne.setVisibility(View.VISIBLE);
                } else {
                    typeBarberLayout.setBackgroundResource(R.drawable.rectangle_unselected_type_drawable);
                    textTypeOne.setTextColor(getResources().getColor(R.color.color_white));
                    imgCheckTypeOne.setVisibility(View.GONE);
                    refreshSelection();

                }
                getSelection();
                break;
            case R.id.type_callout_barber_layout://2
                if (imgCheckTypeTwo.getVisibility() == View.GONE) {
                    typeCalloutBarberLayout.setBackgroundResource(R.drawable.rectangle_selected_type_drawable);
                    textTypeTwo.setTextColor(getResources().getColor(R.color.colorPrimary));
                    imgCheckTypeTwo.setVisibility(View.VISIBLE);

                } else {
                    typeCalloutBarberLayout.setBackgroundResource(R.drawable.rectangle_unselected_type_drawable);
                    textTypeTwo.setTextColor(getResources().getColor(R.color.color_white));
                    imgCheckTypeTwo.setVisibility(View.GONE);
                    refreshSelection();
                }
                getSelection();
                break;
            case R.id.type_tranee_barber_layout://3
                if (imgCheckTypeThree.getVisibility() == View.GONE && imgCheckTypeOne.getVisibility() == View.VISIBLE || imgCheckTypeTwo.getVisibility() == View.VISIBLE) {
                    typeTraneeBarberLayout.setBackgroundResource(R.drawable.rectangle_selected_type_drawable);
                    textTypeThree.setTextColor(getResources().getColor(R.color.colorPrimary));
                    imgCheckTypeThree.setVisibility(View.VISIBLE);
                    refreshSelection();

                } else {
                    typeTraneeBarberLayout.setBackgroundResource(R.drawable.rectangle_unselected_type_drawable);
                    textTypeThree.setTextColor(getResources().getColor(R.color.color_white));
                    imgCheckTypeThree.setVisibility(View.GONE);
                }
                getSelection();
                break;
        }
    }

    private void getSelection() {
        if (imgCheckTypeOne.getVisibility() == View.VISIBLE) {
            selectedType = "1";
        } else if (imgCheckTypeTwo.getVisibility() == View.VISIBLE) {
            selectedType = "2";
        } else if (imgCheckTypeOne.getVisibility() == View.VISIBLE && imgCheckTypeTwo.getVisibility() == View.VISIBLE) {
            selectedType = "1,2";
        } else if (imgCheckTypeOne.getVisibility() == View.VISIBLE && imgCheckTypeTwo.getVisibility() == View.VISIBLE && imgCheckTypeThree.getVisibility() == View.VISIBLE) {
            selectedType = "1,2,3";
        }
    }

    private void refreshSelection() {
        if (imgCheckTypeOne.getVisibility() == View.GONE && imgCheckTypeTwo.getVisibility() == View.GONE) {
            typeTraneeBarberLayout.setBackgroundResource(R.drawable.rectangle_unselected_type_drawable);
            textTypeThree.setTextColor(getResources().getColor(R.color.color_white));
            imgCheckTypeThree.setVisibility(View.GONE);
            selectedType = null;
        }

    }

    @Override
    public void onSuccessResponse(int serviceMode, Object model) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_UPDATE_BARBER_TYPE:
                if (((UpdateDataResponse) model).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    LoginResponseModel.UserBean userData = getUserData();
                    userData.setBarberType(((UpdateDataResponse) model).getBarberType());
                    presenter.saveUserData(userData);
                    activitySwitcher(WelcomeActivity.this, SpecialiseActivity.class, null);
                }
                break;
        }
    }

    @Override
    public void onfaliurResponse(int serviceMode, Object o) {

    }

    @Override
    public void showProgres() {

    }

    @Override
    public void hidePreogress() {

    }

    @Override
    public void onSuccess(Object o, int type) {

    }

    @Override
    public void onError(String localizedMessage) {

    }

    @Override
    public void onException(Exception e) {

    }
}
