package com.app.barber.ui.postauth.activities.barber.booking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.request.BookPaymentRequestModel;
import com.app.barber.models.request.PreBookingRequestModel;
import com.app.barber.models.response.BaseResponse;
import com.app.barber.models.response.BookingResponseModel;
import com.app.barber.models.response.CardListResponse;
import com.app.barber.models.response.PaymentResponseModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.adapters.AvaialbleTimeSlotsAdapter;
import com.app.barber.ui.postauth.activities.barber.BarberDetailActivity;
import com.app.barber.ui.postauth.activities.barber.PreBookingDetailResponse;
import com.app.barber.ui.postauth.activities.barber.barber_adapter.CardListAdapter;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberMVPView;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberPresenter;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;
import com.braintreepayments.cardform.view.CardForm;
import com.google.gson.Gson;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 18/12/18.
 */

public class PaymentActivity extends BaseActivity implements BarberMVPView {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.Confirm_proceed_btn)
    CustomTextView ConfirmProceedBtn;
    @BindView(R.id.card_btn)
    CustomTextView cardBtn;
    @BindView(R.id.cash_btn)
    CustomTextView cashBtn;
    @BindView(R.id.card_form)
    CardForm cardForm;
    @BindView(R.id.layout_card_details_holder)
    LinearLayout layoutCardDetailsHolder;
    @BindView(R.id.save_check_box)
    CheckBox saveCheckBox;
    @BindView(R.id.cash_text)
    CustomTextView cashText;
    @BindView(R.id.saved_cards_recyclar)
    RecyclerView savedCardsRecyclar;
    //    @BindView(R.id.card_input_widget)
//    CardInputWidget cardInputWidget;
    private BarberPresenter presenter;
    private PreBookingRequestModel bookingRequestData;
    private PreBookingDetailResponse preBookingResponseData;
    private CardListAdapter cardsAdapter;
    private boolean isTipCharge;
    private String cardID = null;

    @Override
    public int getLayoutId() {
        return R.layout.payment_screen_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);
        presenter = new BarberPresenter(PaymentActivity.this);
        presenter.attachView(this);
        txtTitleToolbar.setText(R.string.str_payment);
        initRecyclar();
        getIntentData(getIntent());
        initCardForm();

    }

    private void initRecyclar() {
        cardsAdapter = new CardListAdapter(PaymentActivity.this, new ArrayList<CardListResponse.CardsBean.DataBean>(), new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object o) {
                switch (type) {
                    case GlobalValues.ClickOperations.DETAILS:
                        break;
                    case GlobalValues.ClickOperations.REMOVE:
                        CardListResponse.CardsBean.DataBean positiondata = (CardListResponse.CardsBean.DataBean) o;
                        Log.e(" onItemClick ", " remove " + positiondata.getLast4());
                        presenter.removeCard(NetworkConstatnts.RequestCode.API_REMOVE_SAVED_CARD, positiondata.getId(), true);
                        cardsAdapter.remove(position);
                        break;
                }
            }
        });
        LinearLayoutManager lManager = new LinearLayoutManager(PaymentActivity.this, LinearLayoutManager.VERTICAL, false);
        savedCardsRecyclar.setLayoutManager(lManager);
        savedCardsRecyclar.setAdapter(cardsAdapter);
    }

    private void getSavedCards() {
        presenter.getSavedCard(NetworkConstatnts.RequestCode.API_GET_SAVED_CARDS, null, true);
    }

    private void initCardForm() {
        CardForm cardForm = (CardForm) findViewById(R.id.card_form);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(false)
                .mobileNumberRequired(false)
                .mobileNumberExplanation("SMS is required on this number")
                .actionLabel("Purchase")
                .setup(PaymentActivity.this);
    }

    private void getIntentData(Intent intent) {
        isTipCharge = intent.getBooleanExtra(GlobalValues.KEYS.IS_TIP, false);
        if (!isTipCharge) {
            Serializable dataArgument = intent.getSerializableExtra(GlobalValues.KEYS.BOOKING_DATA);//Get pass data with its key value
            bookingRequestData = ((PreBookingRequestModel) dataArgument);
            Log.d(" getIntentData ", " 1 " + new Gson().toJson(bookingRequestData));

            Serializable dataResponse = intent.getSerializableExtra(GlobalValues.KEYS.PRE_BOOKING_RESPONSE_DATA);//Get pass data with its key value
            preBookingResponseData = ((PreBookingDetailResponse) dataResponse);
            bookingRequestData.setAmount(String.valueOf(preBookingResponseData.getList().getAmountWithOutTax()));

//        bookingRequestData.setBookingType(GlobalValues.BARBER_TYPES.BARBER);
            Log.d(" getIntentData ", " 11 " + new Gson().toJson(bookingRequestData));
            Log.d(" getIntentData ", "  2  " + new Gson().toJson(preBookingResponseData));
            if (((PreBookingDetailResponse) dataResponse).getList().getPaymentType() != null) {
                switch (((PreBookingDetailResponse) dataResponse).getList().getPaymentType()) {
//                case GlobalValues.PaymentTypes.BOTH:
//                    break;
                    case GlobalValues.PaymentTypes.CARD:
                        new Handler().postDelayed(() -> {
                            cardBtn.performClick();
                            cashBtn.setVisibility(View.GONE);
                            getSavedCards();
                        }, GlobalValues.TIME_DURATIONS.SMALL);
                        break;
                    case GlobalValues.PaymentTypes.CASH:
                        new Handler().postDelayed(() -> {
                            cashBtn.performClick();
                            cardBtn.setVisibility(View.GONE);
                            cashText.setText(getString(R.string.str_str_pay_) + " " + GlobalValues.Currency.POUNDS
                                    + " " + preBookingResponseData.getList().getAmountWithOutTax()
                                    + " " + getString(R.string.str_str_at_time_));
                        }, GlobalValues.TIME_DURATIONS.SMALL);

                        break;
                    default:
                        new Handler().postDelayed(() -> {
                            cardBtn.performClick();
                            cashText.setText(getString(R.string.str_str_pay_) + " " + GlobalValues.Currency.POUNDS
                                    + " " + preBookingResponseData.getList().getAmountWithOutTax()
                                    + " " + getString(R.string.str_str_at_time_));
                            getSavedCards();
                        }, GlobalValues.TIME_DURATIONS.SMALL);
                        break;
                }
            } else {
                new Handler().postDelayed(() -> {
                    cardBtn.performClick();
                    cashText.setText(getString(R.string.str_str_pay_) + " " + GlobalValues.Currency.POUNDS
                            + " " + preBookingResponseData.getList().getAmountWithOutTax()
                            + " " + getString(R.string.str_str_at_time_));
                    getSavedCards();
                }, GlobalValues.TIME_DURATIONS.SMALL);
            }
//        ConfirmProceedBtn.setText(R.string.str_proceed_to_pay);
        } else {
            new Handler().postDelayed(() -> {
                cardBtn.performClick();
                cashBtn.setVisibility(View.GONE);
                getSavedCards();
                ConfirmProceedBtn.setText(R.string.str_continue);
            }, GlobalValues.TIME_DURATIONS.SMALL);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @OnClick({R.id.back_toolbar, R.id.img_edit, R.id.Confirm_proceed_btn, R.id.card_btn, R.id.cash_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.img_edit:
                break;
            case R.id.card_btn:
                cardBtn.setBackgroundResource(R.drawable.rounder_blue_drawable);
                cardBtn.setTextColor(getResources().getColor(R.color.color_white));
                cashBtn.setBackgroundResource(android.R.color.transparent);
                cashBtn.setTextColor(getResources().getColor(R.color.color_grey));
                layoutCardDetailsHolder.setVisibility(View.VISIBLE);
                ConfirmProceedBtn.setText(R.string.str_proceed_to_pay);
                cashText.setVisibility(View.GONE);
                break;
            case R.id.cash_btn:
                cardBtn.setBackgroundResource(android.R.color.transparent);
                cardBtn.setTextColor(getResources().getColor(R.color.color_grey));
                cashBtn.setBackgroundResource(R.drawable.rounder_blue_drawable);
                cashBtn.setTextColor(getResources().getColor(R.color.color_white));
                layoutCardDetailsHolder.setVisibility(View.GONE);
                ConfirmProceedBtn.setText(R.string.str_cnfrm_);
                cashText.setVisibility(View.VISIBLE);
                break;
            case R.id.Confirm_proceed_btn:
                if (!isTipCharge) {
                    if (bookingRequestData != null)
                        if (ConfirmProceedBtn.getText().equals(getString(R.string.str_proceed_to_pay))) {
                            if (cardsAdapter.isSelected()) {
//                            BookPaymentRequestModel bRequest = new BookPaymentRequestModel();
//                            bRequest.setAmount(Integer.parseInt(bookingRequestData.getAmount()));
                                if (cardsAdapter.getselected() != null) {
//                                bRequest.setCardId(cardsAdapter.getselected().getId());
//                                presenter.bookingAppointmnetPaymentRequest(NetworkConstatnts.RequestCode.API_BOOK_BARBER_APPOINTMENT_PAID, bRequest, true);
                                    bookingRequestData.setCardId(cardsAdapter.getselected().getId());
                                    bookingRequestData.setPaymentMode(GlobalValues.PaymentModes.CARD);
                                    bookingRequestData.setAmountToCharge(preBookingResponseData.getList().getServices().get(((preBookingResponseData.getList().getServices().size() - 1))).getMItem2());
                                    presenter.bookingAppointmnetRequest(NetworkConstatnts.RequestCode.API_BOOK_BARBER_APPOINTMENT, bookingRequestData, true);

                                } else
                                    new CommonUtils().displayMessage(PaymentActivity.this, getString(R.string.errr_str_required_details));
                            } else {
                                if (cardForm.isValid()) {
                                    generatePaymentToken();
                                } else
                                    new CommonUtils().displayMessage(PaymentActivity.this, getString(R.string.errr_str_required_details));
                            }
                        } else {
                            bookingRequestData.setPaymentMode(GlobalValues.PaymentModes.CASH);
                            bookingRequestData.setAmountToCharge(preBookingResponseData.getList().getAmountWithOutTax());
                            presenter.bookingAppointmnetRequest(NetworkConstatnts.RequestCode.API_BOOK_BARBER_APPOINTMENT, bookingRequestData, true);
                        }
                } else {
                    Intent intent = new Intent();
                    if (cardsAdapter.getselected() != null)
                        intent.putExtra(GlobalValues.KEYS.CARD_ID, cardsAdapter.getselected().getId());
                    else if (cardID != null) {
                        intent.putExtra(GlobalValues.KEYS.CARD_ID, cardID);
                    }
                    setResult(RESULT_OK, intent);
                    onBackPressed();
                }
                break;
        }
    }

    private void generatePaymentToken() {
        showProgres();
        Card card = new Card(cardForm.getCardNumber(),
                Integer.parseInt(cardForm.getExpirationMonth()),
                Integer.parseInt(cardForm.getExpirationYear()), cardForm.getCvv());
//        Remember to validate the card object before you use it to save time.
        if (card.validateCard()) {
            Stripe stripe = new Stripe(PaymentActivity.this, NetworkConstatnts.STRIPE_TEST_KEY);
            stripe.createToken(
                    card,
                    new TokenCallback() {
                        public void onSuccess(Token token) {
                            hidePreogress();
                            // Send token to your server
                            BookPaymentRequestModel bRequest = new BookPaymentRequestModel();
                            bRequest.setStripeToken(token.getId());
                            presenter.saveCardDetail(NetworkConstatnts.RequestCode.API_SAVE_CARD, bRequest, false);

                           /* BookPaymentRequestModel bRequest = new BookPaymentRequestModel();
                            bRequest.setAmount(Integer.parseInt(bookingRequestData.getAmount()));
                            bRequest.setStripeToken(token.getId());



                            presenter.bookingAppointmnetPaymentRequest(NetworkConstatnts.RequestCode.API_BOOK_BARBER_APPOINTMENT_PAID, bRequest, true);
                            if (saveCheckBox.isChecked()) {
                                presenter.saveCard(NetworkConstatnts.RequestCode.API_SAVE_CARD,cardForm,true);
                            }*/
                        }

                        public void onError(Exception error) {
                            // Show localized error message
                            new CommonUtils().displayMessage(PaymentActivity.this, error.getLocalizedMessage());
                        }
                    }
            );
        } else  // Do not continue token creation.
            new CommonUtils().displayMessage(PaymentActivity.this, getString(R.string.srr_invalid_card));
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object o) {
        Bundle bundle;
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_BOOK_BARBER_APPOINTMENT:
                if (BarberDetailActivity.getInstance() != null) {//finish  BarberDetailActivity activity.
                    BarberDetailActivity.getInstance().finish();
                }
                if (ConfirmBookingActivity.getInstance() != null) {//finish  ConfirmBookingActivity activity.
                    ConfirmBookingActivity.getInstance().finish();
                }
                bundle = new Bundle();
                bundle.putSerializable(GlobalValues.KEYS.APPOINTMENT_STATUS_DETAIL, ((BookingResponseModel) o));
                bundle.putSerializable(GlobalValues.KEYS.PRE_BOOKING_RESPONSE_DATA, preBookingResponseData);
                bundle.putBoolean(GlobalValues.KEYS.IS_CARD_PAYMENT, checkStatus());
                activitySwitcher(PaymentActivity.this, BookingStatusActivity.class, bundle);
                finish();
                break;
            case NetworkConstatnts.RequestCode.API_BOOK_BARBER_APPOINTMENT_PAID:
                PaymentResponseModel paymentResponse = (PaymentResponseModel) o;
                bookingRequestData.setPaymentMode(GlobalValues.PaymentModes.CARD);
                bookingRequestData.setTransactionId(paymentResponse.getCharge().getId());
                bookingRequestData.setReceivedAmount(bookingRequestData.getAmount());
                presenter.bookingAppointmnetRequest(NetworkConstatnts.RequestCode.API_BOOK_BARBER_APPOINTMENT, bookingRequestData, true);
                break;
            case NetworkConstatnts.RequestCode.API_GET_SAVED_CARDS:
//                new CommonUtils().displayMessage(PaymentActivity.this, ((PaymentResponseModel) o).getMessage());
                CardListResponse responseData = (CardListResponse) o;
                if (responseData != null && responseData.getCards() != null && responseData.getCards().getData() != null && responseData.getCards().getData().size() > 0) {
                    cardsAdapter.updateAll(responseData.getCards().getData());
                    Log.d("onSuccess", " saved cards " + responseData.getCards().getData().size());
                    savedCardsRecyclar.setVisibility(View.VISIBLE);
                } else {
                    savedCardsRecyclar.setVisibility(View.GONE);
                }
                break;
            case NetworkConstatnts.RequestCode.API_REMOVE_SAVED_CARD:
                new CommonUtils().ShowToast(((BaseResponse) o).getMessage());
                break;
            case NetworkConstatnts.RequestCode.API_SAVE_CARD:
                BaseResponse baseRsponse = (BaseResponse) o;
                Log.e(" :: ", " " + baseRsponse.getCardId());
                if (baseRsponse.getCardId() != null && !baseRsponse.getCardId().equals("")) {
                    Log.e(" :: ", " " + new Gson().toJson(bookingRequestData));
                    cardID = baseRsponse.getCardId();
                    bookingRequestData.setCardId(baseRsponse.getCardId());
                    bookingRequestData.setPaymentMode(GlobalValues.PaymentModes.CARD);
                    bookingRequestData.setAmountToCharge(preBookingResponseData.getList().getServices().get(((preBookingResponseData.getList().getServices().size() - 1))).getMItem2());
                    presenter.bookingAppointmnetRequest(NetworkConstatnts.RequestCode.API_BOOK_BARBER_APPOINTMENT, bookingRequestData, true);
                }
                break;
        }
    }

    private boolean checkStatus() {
        if (bookingRequestData.getPaymentMode().equals(GlobalValues.PaymentModes.CARD)) {
            return true;
        } else return false;
    }

    @Override
    public void onfaliurResponse(int serviceMode, Object o) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_BOOK_BARBER_APPOINTMENT:
                if (BarberDetailActivity.getInstance() != null) {//finish  BarberDetailActivity activity.
                    BarberDetailActivity.getInstance().finish();
                }
                if (ConfirmBookingActivity.getInstance() != null) {//finish  ConfirmBookingActivity activity.
                    ConfirmBookingActivity.getInstance().finish();
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable(GlobalValues.KEYS.APPOINTMENT_STATUS_DETAIL, ((BookingResponseModel) o));
                bundle.putSerializable(GlobalValues.KEYS.PRE_BOOKING_RESPONSE_DATA, preBookingResponseData);
                bundle.putBoolean(GlobalValues.KEYS.IS_CARD_PAYMENT, checkStatus());
                activitySwitcher(PaymentActivity.this, BookingStatusActivity.class, bundle);
                finish();
                break;
            case NetworkConstatnts.RequestCode.API_BOOK_BARBER_APPOINTMENT_PAID:
                new CommonUtils().displayMessage(PaymentActivity.this, ((PaymentResponseModel) o).getMessage());
                break;

        }
    }

    @Override
    public void showProgres() {
        showLoading();
    }

    @Override
    public void hidePreogress() {
        hideLoading();
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
