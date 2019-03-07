package com.app.barber.ui.postauth.activities.barber.ratesettings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.request.RatingRequestModel;
import com.app.barber.models.response.BookingRatingResponseModel;
import com.app.barber.models.response.NotificationResponseModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberMVPView;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberPresenter;
import com.app.barber.ui.postauth.activities.barber.booking.PaymentActivity;
import com.app.barber.ui.postauth.activities.barber.ratesettings.adapter.RatingsViewsAdapter;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.FunctionalDialog;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.ImageUtility;
import com.app.barber.util.iface.OnBottomDialogItemListener;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomEditText;
import com.app.barber.views.CustomTextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 31/12/18.
 */

public class RateBarberActivity extends BaseActivity implements BarberMVPView {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.barber_image)
    SimpleDraweeView barberImage;
    @BindView(R.id.barber_name)
    CustomTextView barberName;
    @BindView(R.id.barber_status)
    CustomTextView barberStatus;
    @BindView(R.id.barber_user_name)
    CustomTextView barberUserName;
    @BindView(R.id.punctulaty_ratings)
    CustomTextView punctulatyRatings;
    @BindView(R.id.punctuality_recyclar)
    RecyclerView punctualityRecyclar;
    @BindView(R.id.experties_ratings)
    CustomTextView expertiesRatings;
    @BindView(R.id.experties_recyclar)
    RecyclerView expertiesRecyclar;
    RatingsViewsAdapter rAdapterP, rAdapterE, rAdapterV, rAdapterH;
    @BindView(R.id.value_ratings)
    CustomTextView valueRatings;
    @BindView(R.id.value_recyclar)
    RecyclerView valueRecyclar;
    @BindView(R.id.hygin_ratings)
    CustomTextView hyginRatings;
    @BindView(R.id.hygin_recyclar)
    RecyclerView hyginRecyclar;
    @BindView(R.id.review_text)
    CustomEditText reviewText;
    @BindView(R.id.tip_txt_add)
    CustomTextView tipTxtAdd;
    @BindView(R.id.tip_txt_2)
    CustomTextView tipTxt2;
    @BindView(R.id.tip_txt_5)
    CustomTextView tipTxt5;
    @BindView(R.id.tip_txt_10)
    CustomTextView tipTxt10;
    @BindView(R.id.btn_submit)
    CustomTextView btnSubmit;
    @BindView(R.id.root_add_tip_lay)
    LinearLayout rootAddTipLay;
    @BindView(R.id.amount_field)
    CustomEditText amountField;
    private BarberPresenter presenter;
    int punctulatRating, expertRating, valuRating, hyginRating;
    private RatingRequestModel requestRating;
    private NotificationResponseModel.ResponseBean mData;
    private String cardID = null;
    private boolean isTipAdded;
    String tipAmount = null;

    /**
     * @return layout resource id
     */
    @Override
    public int getLayoutId() {
        return R.layout.layout_rate_barber_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);
        presenter = new BarberPresenter(this);
        presenter.attachView(this);
        txtTitleToolbar.setText(R.string.str_rate_your_barber);
        getIntentData(getIntent());
        initRatingObject();
        initPunctualityAdapter();
        initExpertAdapter();
        initValueAdapter();
        initHyginAdapter();
        amountField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    if (!amountField.getText().toString().equals("0") && !amountField.getText().toString().equals("00")) {
                        tipTxt2.setTextColor(getResources().getColor(R.color.color_grey));
                        tipTxt5.setTextColor(getResources().getColor(R.color.color_grey));
                        tipTxt10.setTextColor(getResources().getColor(R.color.color_grey));
                        tipTxtAdd.setTextColor(getResources().getColor(R.color.color_green));
                        requestRating.setAmount(amountField.getText().toString());
                        requestRating.setCardId(cardID);
                        isTipAdded = true;
                        tipAmount = s.toString();
                    } else {
                        isTipAdded = false;
                        requestRating.setAmount(null);
                        requestRating.setCardId(null);
                        tipTxtAdd.setTextColor(getResources().getColor(R.color.color_grey));
                    }
                } else {
                    requestRating.setAmount(null);
                    requestRating.setCardId(null);
                    isTipAdded = false;
                    tipTxtAdd.setTextColor(getResources().getColor(R.color.color_grey));
                }

            }
        });
    }

    private void getIntentData(Intent intent) {
        Serializable sData = intent.getSerializableExtra(GlobalValues.KEYS.NOTIFICATION_DATA);
        mData = ((NotificationResponseModel.ResponseBean) sData);
        getBookingRatingDetail();

    }

    private void getBookingRatingDetail() {
        presenter.getBookingRatings(NetworkConstatnts.RequestCode.API_BOOKING_RATINGS_REVIEW, Integer.parseInt(mData.getBookingId()),
                mData.getBarberId(), mData.getBookingType(), true);
    }

    private void initRatingObject() {
        requestRating = new RatingRequestModel();
        requestRating.setBarberId(mData.getBarberId());
        requestRating.setBookingId(Integer.parseInt(mData.getBookingId()));
        requestRating.setReview(reviewText.getText().toString());
        requestRating.setBookingType(mData.getBookingType());
        requestRating.setRatingList(presenter.getRatingTypes());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void initPunctualityAdapter() {
        rAdapterP = new RatingsViewsAdapter(RateBarberActivity.this, presenter.getRatingsList(), GlobalValues.RatingTypes.PUNCTUALITY, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object o) {
                Log.d("initPunctualityAdapter", "    " + position);
                punctulatRating = (position + 1);
                punctulatyRatings.setText((position + 1) + "/10");
                requestRating = presenter.setUpdatedRating(requestRating, punctulatRating, GlobalValues.RatingTypes.PUNCTUALITY);
            }
        });
        punctualityRecyclar.setLayoutManager(new GridLayoutManager(RateBarberActivity.this, 10));
        punctualityRecyclar.setAdapter(rAdapterP);
    }

    private void initExpertAdapter() {
        rAdapterE = new RatingsViewsAdapter(RateBarberActivity.this, presenter.getRatingsList(), GlobalValues.RatingTypes.EXPERTIES, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object o) {
                Log.d("initExpertAdapter", "    " + position);
                expertRating = (position + 1);
                expertiesRatings.setText((position + 1) + "/10");
                requestRating = presenter.setUpdatedRating(requestRating, expertRating, GlobalValues.RatingTypes.EXPERTIES);
            }
        });
        expertiesRecyclar.setLayoutManager(new GridLayoutManager(RateBarberActivity.this, 10));
        expertiesRecyclar.setAdapter(rAdapterE);
    }

    private void initValueAdapter() {
        rAdapterV = new RatingsViewsAdapter(RateBarberActivity.this, presenter.getRatingsList(), GlobalValues.RatingTypes.VALUE, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object o) {
                Log.d("initValueAdapter", "    " + position);
                valuRating = (position + 1);
                valueRatings.setText((position + 1) + "/10");
                requestRating = presenter.setUpdatedRating(requestRating, valuRating, GlobalValues.RatingTypes.VALUE);
            }
        });
        valueRecyclar.setLayoutManager(new GridLayoutManager(RateBarberActivity.this, 10));
        valueRecyclar.setAdapter(rAdapterV);
    }

    private void initHyginAdapter() {
        rAdapterH = new RatingsViewsAdapter(RateBarberActivity.this, presenter.getRatingsList(), GlobalValues.RatingTypes.HYGINENE, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object o) {
                Log.d("initHyginAdapter", "    " + position);
                hyginRating = (position + 1);
                hyginRatings.setText((position + 1) + "/10");
                requestRating = presenter.setUpdatedRating(requestRating, hyginRating, GlobalValues.RatingTypes.HYGINENE);
            }
        });
        hyginRecyclar.setLayoutManager(new GridLayoutManager(RateBarberActivity.this, 10));
        hyginRecyclar.setAdapter(rAdapterH);
    }

    @OnClick({R.id.back_toolbar, R.id.img_edit, R.id.btn_submit, R.id.tip_txt_10, R.id.tip_txt_5, R.id.tip_txt_2, R.id.tip_txt_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.img_edit:
                break;
            case R.id.btn_submit:
                Log.d("rating request", "    " + isTipAdded + "  " + new Gson().toJson(requestRating));
                if (isTipAdded) {
                    if (cardID != null && !cardID.equals("") && tipAmount != null && !tipAmount.equals("")) {
                        requestRating.setAmount(tipAmount);
                        requestRating.setCardId(cardID);
                        presenter.rateBarber(NetworkConstatnts.RequestCode.API_RATE_BARBER, requestRating, true);
                    } else {
                        new CommonUtils().ShowToast(getString(R.string.str_select_tip_amount));
                    }
                } else {
                    requestRating.setAmount(null);
                    requestRating.setCardId(null);
                    presenter.rateBarber(NetworkConstatnts.RequestCode.API_RATE_BARBER, requestRating, true);
                }

                break;
            case R.id.tip_txt_10:
                selectView(R.id.tip_txt_10);
                break;
            case R.id.tip_txt_5:
                selectView(R.id.tip_txt_5);
                break;
            case R.id.tip_txt_2:
                selectView(R.id.tip_txt_2);
                break;
            case R.id.tip_txt_add:
                selectView(R.id.tip_txt_add);
                break;
        }
    }

    private void selectView(int tip_txt) {
        switch (tip_txt) {
            case R.id.tip_txt_2:
                tipAmount = "2";
                requestRating.setCardId(cardID);
                tipTxt2.setTextColor(getResources().getColor(R.color.color_green));
                tipTxt5.setTextColor(getResources().getColor(R.color.color_grey));
                tipTxt10.setTextColor(getResources().getColor(R.color.color_grey));
                tipTxtAdd.setTextColor(getResources().getColor(R.color.color_grey));
                closeCustomTipView();
                chargeTip();
                isTipAdded = true;
                break;
            case R.id.tip_txt_5:
                tipAmount = "5";
                requestRating.setCardId(cardID);
                tipTxt2.setTextColor(getResources().getColor(R.color.color_grey));
                tipTxt5.setTextColor(getResources().getColor(R.color.color_green));
                tipTxt10.setTextColor(getResources().getColor(R.color.color_grey));
                tipTxtAdd.setTextColor(getResources().getColor(R.color.color_grey));
                closeCustomTipView();
                chargeTip();
                isTipAdded = true;
                break;
            case R.id.tip_txt_10:
                tipAmount = "10";
                requestRating.setCardId(cardID);
                tipTxt2.setTextColor(getResources().getColor(R.color.color_grey));
                tipTxt5.setTextColor(getResources().getColor(R.color.color_grey));
                tipTxt10.setTextColor(getResources().getColor(R.color.color_green));
                tipTxtAdd.setTextColor(getResources().getColor(R.color.color_grey));
                closeCustomTipView();
                chargeTip();
                isTipAdded = true;
                break;
            case R.id.tip_txt_add:
                requestRating.setAmount(null);
                requestRating.setCardId(null);
                tipTxt2.setTextColor(getResources().getColor(R.color.color_grey));
                tipTxt5.setTextColor(getResources().getColor(R.color.color_grey));
                tipTxt10.setTextColor(getResources().getColor(R.color.color_grey));
                if (amountField.getVisibility() == View.VISIBLE) {
                    closeCustomTipView();
                } else {
                    amountField.setVisibility(View.VISIBLE);
                    tipAmount = null;
                    chargeTip();
                }
                break;
        }

    }

    private void closeCustomTipView() {
        amountField.setVisibility(View.GONE);
        tipTxtAdd.setTextColor(getResources().getColor(R.color.color_grey));
        amountField.setText("");
    }

    private void chargeTip() {
        if (!isCardDetail()) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(GlobalValues.KEYS.IS_TIP, true);
            activitySwitcherResult(RateBarberActivity.this, PaymentActivity.class, bundle, GlobalValues.RequestCodes.TIP_PAY);
        }
    }

    private boolean isCardDetail() {
        if (cardID != null && !cardID.equals("")) {
            return true;
        } else return false;
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object o) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_RATE_BARBER:
                new CommonUtils().ShowToast(getString(R.string.str_thanks_for_review));
                onBackPressed();
                break;
            case NetworkConstatnts.RequestCode.API_BOOKING_RATINGS_REVIEW:
                BookingRatingResponseModel bookingRating = (BookingRatingResponseModel) o;
                if (bookingRating.getResponse() != null)
                    updateView(bookingRating.getResponse());
                break;
        }
    }

    private void updateView(BookingRatingResponseModel.ResponseBean response) {
        barberName.setText(response.getBarberName());
        barberImage.setImageURI(ImageUtility.getValidUrl(response.getProfileImage()));
        barberUserName.setText(response.getUserName());
        if (response.getReview() != null && !response.getReview().equals(""))
            reviewText.setText(response.getReview());
        punctulatyRatings.setText(response.getPuchRating() + "/10");
        expertiesRatings.setText(response.getExpertiseRating() + "/10");
        valueRatings.setText(response.getValueRating() + "/10");
        hyginRatings.setText(response.getHygieneRating() + "/10");

        //notifi adapters
        rAdapterP.updateRating(response.getPuchRating(), GlobalValues.RatingTypes.PUNCTUALITY);
        rAdapterE.updateRating(response.getExpertiseRating(), GlobalValues.RatingTypes.EXPERTIES);
        rAdapterV.updateRating(response.getValueRating(), GlobalValues.RatingTypes.VALUE);
        rAdapterH.updateRating(response.getHygieneRating(), GlobalValues.RatingTypes.HYGINENE);

        if (response.getPaymentType().equals("2")) {//Payment type cash
            rootAddTipLay.setVisibility(View.GONE);
        } else rootAddTipLay.setVisibility(View.VISIBLE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GlobalValues.RequestCodes.TIP_PAY:
                if (resultCode == RESULT_OK) {
                    cardID = data.getStringExtra(GlobalValues.KEYS.CARD_ID);
                    requestRating.setCardId(cardID);
                }
                break;
        }
    }
}
