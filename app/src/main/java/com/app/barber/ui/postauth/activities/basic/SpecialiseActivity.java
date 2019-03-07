package com.app.barber.ui.postauth.activities.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.SpecialisationModel;
import com.app.barber.models.response.LoginResponseModel;
import com.app.barber.models.response.UpdateDataResponse;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.adapters.SpecialisationAdapter;
import com.app.barber.ui.postauth.activities.basic.basicmvp.BasicAuthMVPView;
import com.app.barber.ui.postauth.activities.basic.basicmvp.BssicAuthPresenter;
import com.app.barber.util.PreferenceManager;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 23/10/18.
 */

public class SpecialiseActivity extends BaseActivity implements BasicAuthMVPView {
    @Inject
    PreferenceManager mPref;
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
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    @BindView(R.id.skip_btn)
    CustomTextView skipBtn;
    private BssicAuthPresenter presenter;
    private String selectedType = null;
    ArrayList<SpecialisationModel> specList;
    private LinearLayoutManager layoutManager;
    private SpecialisationAdapter specAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.specialise_screen_activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);
        presenter = new BssicAuthPresenter(this);
        presenter.attachView(this);
        specList = presenter.getSpecialisationList();
        backToolbar.setVisibility(View.VISIBLE);
        txtTitleToolbar.setVisibility(View.VISIBLE);
        txtTitleToolbar.setText(R.string.str_welcome_txt);
        initAdapter();
    }

    private void initAdapter() {
        layoutManager = new LinearLayoutManager(SpecialiseActivity.this);
        recyclarViewLst.setLayoutManager(layoutManager);
        specAdapter = new SpecialisationAdapter(SpecialiseActivity.this, specList, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object o) {


            }
        });
        recyclarViewLst.setAdapter(specAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @OnClick({R.id.back_toolbar, R.id.type_barber_layout, R.id.type_callout_barber_layout, R.id.type_tranee_barber_layout, R.id.continue_btn, R.id.skip_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.type_barber_layout:
//                setTypeSelection(view.getId());
                break;
            case R.id.type_callout_barber_layout:
//                setTypeSelection(view.getId());
                break;
            case R.id.type_tranee_barber_layout:
//                setTypeSelection(view.getId());
                break;
            case R.id.continue_btn:
                Log.e("continue_btn", " " + getselected());
                if (selectedType != null && !selectedType.equals(""))
                    presenter.updateSpecType(NetworkConstatnts.RequestCode.API_UPDATE_SPEC_TYPE, selectedType, true);
                else
                    activitySwitcher(SpecialiseActivity.this, AddressActivity.class, null);
                break;
            case R.id.skip_btn:
                activitySwitcher(SpecialiseActivity.this, AddressActivity.class, null);
                break;
        }
    }

    private String getselected() {

        try {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < specList.size(); i++) {
                if (specList.get(i).isSelected()) {
                    builder.append(specList.get(i).getId() + ",");
                }
            }
            selectedType = builder.toString();
            if (selectedType != null && selectedType.length() > 0 && selectedType.charAt(selectedType.length() - 1) == ',') {
                selectedType = selectedType.substring(0, selectedType.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectedType;
    }
/*
    private void setTypeSelection(int id) {
        switch (id) {
            case R.id.type_barber_layout:
                if (imgCheckTypeOne.getVisibility() == View.GONE) {
                    typeBarberLayout.setBackgroundResource(R.drawable.rectangle_selected_type_drawable);
                    textTypeOne.setTextColor(getResources().getColor(R.color.colorPrimary));
                    imgCheckTypeOne.setVisibility(View.VISIBLE);
                } else {
                    typeBarberLayout.setBackgroundResource(R.drawable.rectangle_unselected_type_drawable);
                    textTypeOne.setTextColor(getResources().getColor(R.color.color_white));
                    imgCheckTypeOne.setVisibility(View.GONE);
                }
                getSelection();
                break;
            case R.id.type_callout_barber_layout:
                if (imgCheckTypeTwo.getVisibility() == View.GONE) {
                    typeCalloutBarberLayout.setBackgroundResource(R.drawable.rectangle_selected_type_drawable);
                    textTypeTwo.setTextColor(getResources().getColor(R.color.colorPrimary));
                    imgCheckTypeTwo.setVisibility(View.VISIBLE);
                } else {
                    typeCalloutBarberLayout.setBackgroundResource(R.drawable.rectangle_unselected_type_drawable);
                    textTypeTwo.setTextColor(getResources().getColor(R.color.color_white));
                    imgCheckTypeTwo.setVisibility(View.GONE);
                }
                getSelection();
                break;
            case R.id.type_tranee_barber_layout:
                if (imgCheckTypeThree.getVisibility() == View.GONE) {
                    typeTraneeBarberLayout.setBackgroundResource(R.drawable.rectangle_selected_type_drawable);
                    textTypeThree.setTextColor(getResources().getColor(R.color.colorPrimary));
                    imgCheckTypeThree.setVisibility(View.VISIBLE);
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
        } else if (imgCheckTypeThree.getVisibility() == View.VISIBLE) {
            selectedType = "3";
        } else if (imgCheckTypeOne.getVisibility() == View.VISIBLE && imgCheckTypeTwo.getVisibility() == View.VISIBLE) {
            selectedType = "1,2";
        } else if (imgCheckTypeOne.getVisibility() == View.VISIBLE && imgCheckTypeThree.getVisibility() == View.VISIBLE) {
            selectedType = "1,3";
        } else if (imgCheckTypeTwo.getVisibility() == View.VISIBLE && imgCheckTypeThree.getVisibility() == View.VISIBLE) {
            selectedType = "2,3";
        } else if (imgCheckTypeOne.getVisibility() == View.VISIBLE && imgCheckTypeTwo.getVisibility() == View.VISIBLE && imgCheckTypeThree.getVisibility() == View.VISIBLE) {
            selectedType = "1,2,3";
        } else selectedType = null;

    }*/

    @Override
    public void onSuccessResponse(int serviceMode, Object model) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_UPDATE_SPEC_TYPE:
                if (((UpdateDataResponse) model).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    LoginResponseModel.UserBean userData = getUserData();
                    userData.setSpecializaions(((UpdateDataResponse) model).getSpecType());
                    presenter.saveUserData(userData);
                    activitySwitcher(SpecialiseActivity.this, SearchAddressActivity.class, null);
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
