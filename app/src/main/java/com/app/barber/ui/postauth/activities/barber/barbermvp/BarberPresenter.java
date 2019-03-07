package com.app.barber.ui.postauth.activities.barber.barbermvp;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.models.AvailableSlotsModel;
import com.app.barber.models.RateData;
import com.app.barber.models.request.BookPaymentRequestModel;
import com.app.barber.models.request.EditBookingRequestModel;
import com.app.barber.models.request.PreBookingRequestModel;
import com.app.barber.models.request.RatingRequestModel;
import com.app.barber.models.request.RequestBarberModel;
import com.app.barber.models.request.RequestFavUnfavModel;
import com.app.barber.models.request.SaveQbDialogRequestModel;
import com.app.barber.models.response.BarberDetialResponse;
import com.app.barber.models.response.BarberListResponseModel;
import com.app.barber.models.response.BarberPortfoloiImageResponse;
import com.app.barber.models.response.BarberRatingsResponseModel;
import com.app.barber.models.response.BaseResponse;
import com.app.barber.models.response.BookingRatingResponseModel;
import com.app.barber.models.response.BookingResponseModel;
import com.app.barber.models.response.CardListResponse;
import com.app.barber.models.response.PaymentResponseModel;
import com.app.barber.models.response.ResponseAvailableSlotsModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.net.RestCallback;
import com.app.barber.net.RestProcess;
import com.app.barber.net.RestService;
import com.app.barber.ui.postauth.activities.barber.PreBookingDetailResponse;
import com.app.barber.ui.postauth.activities.barber.barber_adapter.HorizontalListAdapter;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.PreferenceManager;
import com.app.barber.util.di.DaggerValues;
import com.app.barber.util.mvp.BasePresenter;
import com.braintreepayments.cardform.view.CardForm;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by harish on 26/10/18.
 */

public class BarberPresenter extends BasePresenter<BarberMVPView> implements RestCallback {
    @Inject
    @Named(DaggerValues.NON_AUTH)
    RestService appService;
    @Inject
    @Named(DaggerValues.AUTH)
    RestService appServiceAuth;
    @Inject
    public PreferenceManager mPref;
    Context context;

    public BarberPresenter(Context context) {
        this.context = context;
        BarberApplication.getMyComponent(context).inject(this);
    }

    @Override
    public void attachView(BarberMVPView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void onFailure(Call call, Throwable t, int serviceMode) {

    }

    @Override
    public void onSuccess(Call call, Response model, int serviceMode) {
        Log.e("on Success", " " + model.body());
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_GET_BARBERS_AUTH:
            case NetworkConstatnts.RequestCode.API_SEARCH_BARBERS:
            case NetworkConstatnts.RequestCode.API_GET_BARBERS:
                if (((BarberListResponseModel) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_BARBER_DETAIL:
                if (((BarberDetialResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_AVAILABLE_SLOTS:
                if (((ResponseAvailableSlotsModel) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                } else {
                    getMvpView().onfaliurResponse(serviceMode, null);
                    new CommonUtils().ShowToast(((ResponseAvailableSlotsModel) model.body()).getMessage());
                }
                break;
            case NetworkConstatnts.RequestCode.API_GET_PRE_BOOKING_DETAIL:
                if (((PreBookingDetailResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                } else {
                    getMvpView().onfaliurResponse(serviceMode, null);
                }
                break;
            case NetworkConstatnts.RequestCode.API_BOOK_BARBER_APPOINTMENT:
                if (((BookingResponseModel) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                } else {
                    getMvpView().onfaliurResponse(NetworkConstatnts.RequestCode.API_BOOK_BARBER_APPOINTMENT, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_FAV_UNFAV:
                if (((BaseResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                } else {
                    getMvpView().onfaliurResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_SAVED_BARBER_LIST:
                if (((BarberListResponseModel) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                } else getMvpView().onfaliurResponse(serviceMode, model.body());
                break;
            case NetworkConstatnts.RequestCode.API_BOOK_BARBER_APPOINTMENT_PAID:
                if (((PaymentResponseModel) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                } else {
                    getMvpView().onfaliurResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_RATE_BARBER:
                if (((BaseResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                } else {
                    getMvpView().onfaliurResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_BARBER_RATINGS:
                if (((BarberRatingsResponseModel) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                } else {
                    getMvpView().onfaliurResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_BOOKING_RATINGS_REVIEW:
                if (((BookingRatingResponseModel) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                } else {
                    getMvpView().onfaliurResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_SAVE_QB_DIALOG:
                if (((BaseResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                } else {
                    getMvpView().onfaliurResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_GET_IMAGES:
                if (((BaseResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                } else {
                    getMvpView().onfaliurResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_GET_BARBER_PORTFOLIO_IMAGE:
                if (((BarberPortfoloiImageResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                } else {
                    getMvpView().onfaliurResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_GET_SAVED_CARDS:
                if (((CardListResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                } else {
                    getMvpView().onfaliurResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_SAVE_CARD:
                if (((BaseResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_REMOVE_SAVED_CARD:
                if (((BaseResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_EDIT_BOOKING_REQUEST:
                if (((BaseResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;
            case NetworkConstatnts.RequestCode.API_SAVE_DIALOG:
                if (((BaseResponse) model.body()).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    getMvpView().onSuccessResponse(serviceMode, model.body());
                }
                break;

        }

    }

    @Override
    public void onLogout() {

    }


    public void getBarberList(int apiGetBarbers, RequestBarberModel model, boolean b) {
        Call<BarberListResponseModel> call;
        if (apiGetBarbers == NetworkConstatnts.RequestCode.API_GET_BARBERS_AUTH)
            call = appServiceAuth.getAvailableBarbersAuth(model);
        else
            call = appService.getAvailableBarbers(model);
        call.enqueue(new RestProcess<>(apiGetBarbers, this, context, b));
    }

    public void getBarberDetail(int apiBarberDetail, int barberId, boolean b) {
        Call<BarberDetialResponse> call;
        if (mPref.getPrefrencesBoolean(GlobalValues.KEYS.isLogedIn))
            call = appServiceAuth.getBarberDetail("" + barberId);
        else call = appService.getBarberDetail("" + barberId);
        call.enqueue(new RestProcess(apiBarberDetail, this, context, b));
    }

    public void getAvailableSlots(int apiAvailableSlots, AvailableSlotsModel avaialbeModel, boolean b) {
        Call<ResponseAvailableSlotsModel> call = appService.getAvailableSlots(avaialbeModel);
        call.enqueue(new RestProcess(apiAvailableSlots, this, context, b));
    }

    public void bookingDetailRequest(int apiGetPreBookingDetail, PreBookingRequestModel reRequest, boolean b) {
        Call<PreBookingDetailResponse> call = appService.getPreBookingDetail(reRequest);
        call.enqueue(new RestProcess(apiGetPreBookingDetail, this, context, b));
    }

    public void bookingAppointmnetRequest(int apiBookBarberAppointment, PreBookingRequestModel reRequest, boolean b) {
        Call<BookingResponseModel> call = appServiceAuth.bookAppointmnet(reRequest);
        call.enqueue(new RestProcess(apiBookBarberAppointment, this, context, b));
    }

    public void favUnfav(int apiFavUnfav, RequestFavUnfavModel setModel, boolean b) {
        Call<BaseResponse> call = appServiceAuth.favUnfavBarber(setModel);
        call.enqueue(new RestProcess(apiFavUnfav, this, context, b));
    }

    public void getSavedList(int apiSavedBarberList, boolean b) {
        Call<BarberListResponseModel> call = appServiceAuth.getFavList();
        call.enqueue(new RestProcess(apiSavedBarberList, this, context, b));
    }

    public void bookingAppointmnetPaymentRequest(int apiBookBarberAppointmentPaid, BookPaymentRequestModel bRequest, boolean b) {
        Call<PaymentResponseModel> call = appServiceAuth.bookAppointmnet(bRequest);
        call.enqueue(new RestProcess(apiBookBarberAppointmentPaid, this, context, b));
    }

    public ArrayList<RateData> getRatingsList() {
        ArrayList<RateData> rList = new ArrayList<>();
        RateData rData = new RateData();
        for (int i = 1; i < 11; i++) {
            rData = new RateData();
            rData.setRatingNum(i);
            rData.setSelected(false);
            rList.add(rData);
        }
        return rList;
    }

    public List<RatingRequestModel.Ratings> getRatingTypes() {
        List<RatingRequestModel.Ratings> ratingTypeList = new ArrayList<>();
        RatingRequestModel.Ratings rModel = new RatingRequestModel.Ratings();

        rModel.setRatingType(GlobalValues.RatingTypes.PUNCTUALITY);
        ratingTypeList.add(rModel);

        rModel = new RatingRequestModel.Ratings();
        rModel.setRatingType(GlobalValues.RatingTypes.EXPERTIES);
        ratingTypeList.add(rModel);

        rModel = new RatingRequestModel.Ratings();
        rModel.setRatingType(GlobalValues.RatingTypes.VALUE);
        ratingTypeList.add(rModel);

        rModel = new RatingRequestModel.Ratings();
        rModel.setRatingType(GlobalValues.RatingTypes.HYGINENE);
        ratingTypeList.add(rModel);

        return ratingTypeList;
    }

    public RatingRequestModel setUpdatedRating(RatingRequestModel requestRating, int rating, int rType) {
        for (int i = 0; i < requestRating.getRatingList().size(); i++) {
            if (requestRating.getRatingList().get(i).getRatingType() == rType) {
                requestRating.getRatingList().get(i).setRating(rating);
            }
        }
        return requestRating;
    }

    public void rateBarber(int apiRateBarber, RatingRequestModel requestRating, boolean b) {
        Call<BaseResponse> call = appServiceAuth.rateBarber(requestRating);
        call.enqueue(new RestProcess(apiRateBarber, this, context, b));
    }

    public void getBarberRatings(int apiBarberRatings, int userID, boolean b) {
        Call<BarberRatingsResponseModel> call = appServiceAuth.getBarberRatings(userID);
        call.enqueue(new RestProcess(apiBarberRatings, this, context, b));
    }

    public void updateUserMarker(Location location, GoogleMap mMap) {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.map_pin_blue);
        // Add a marker in Sydney and move the camera
        if (mMap != null) {
            mMap.clear();
            mMap.setIndoorEnabled(true);
            // Adding new item to the ArrayList
            LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(currentLocation).title("You").icon(icon));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17f));
        }

    }

    public void getBookingRatings(int apiBookingRatingsReview, int bookingId, int barberId, int bookingType, boolean b) {
        Call<BookingRatingResponseModel> call = appServiceAuth.getBookingsRatings(bookingId, barberId, bookingType);
        call.enqueue(new RestProcess(apiBookingRatingsReview, this, context, b));
    }

    public void updateMarkers(List<BarberListResponseModel.ListBean> list, GoogleMap mMap) {
        try {
            if (mMap != null) {
                mMap.clear();
                BitmapDescriptor icon;
                for (int i = 0; i < list.size(); i++) {
                    // Adding new item to the ArrayList
                    if (list.get(i).isFavourite()) {
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.map_pin_fav);
                    } else icon = BitmapDescriptorFactory.fromResource(R.drawable.map_pin_blue);
                    LatLng currentLocation = new LatLng(CommonUtils.getValidCordinates(list.get(i).getLat()), CommonUtils.getValidCordinates(list.get(i).getLong()));
                    if (currentLocation != null) {
                        mMap.addMarker(new MarkerOptions().position(currentLocation).title(list.get(i).getFullName()).snippet(String.valueOf(list.get(i).getBarberId())).icon(icon));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17f));
                    }
                }
//                updateCameraFocus(list, mMap);
            }
        } catch (Exception e) {

        }


//        new MapWork(list, mMap).execute();
    }

    //Set   camera  focus  to show all markers.
    private void updateCameraFocus(List<BarberListResponseModel.ListBean> list, GoogleMap mMap) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        if (list != null) {
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {

                    if (list.get(i).getLat() != null && !list.get(i).getLat().equals("")) {
//                        Log.d("getWishlistDistance ", " " + util.getDistanceinKm(currentLocation.longitude, currentLocation.latitude,
//                                Double.parseDouble(list.get(i).getLong()),
//                                Double.parseDouble(list.get(i).getLat())));
                        builder.include(new LatLng(Double.parseDouble(list.get(i).getLat()),
                                Double.parseDouble(list.get(i).getLong())));
                    }
                }
                LatLngBounds bounds = builder.build();
                int width = context.getResources().getDisplayMetrics().widthPixels;
                int height = context.getResources().getDisplayMetrics().heightPixels;
                int padding = (int) (width * 0.20); // offset from edges of the map 10% of screen

                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
                mMap.animateCamera(cu);
            }

        }
    }

    public String getopeningTimesString(List<BarberDetialResponse.InfoBean.OpeningHoursBean> openingHours) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < openingHours.size(); i++) {
            if (openingHours.get(i).getOpeningHours() == null) {
                builder.append(openingHours.get(i).getDay() + ": " + "closed " + '\n');

            } else {
                builder.append(openingHours.get(i).getDay() + ": " + openingHours.get(i).getOpeningHours() + " - " + openingHours.get(i).getClosingHours() + '\n');
            }
        }
        return builder.toString();
    }

    public String getCalloutTimesString(List<BarberDetialResponse.InfoBean.CallOutHoursBean> openingHours) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < openingHours.size(); i++) {
            if (openingHours.get(i).getOpeningHours() == null) {
                builder.append(openingHours.get(i).getDay() + ": " + "closed " + '\n');

            } else {
                builder.append(openingHours.get(i).getDay() + ": " + openingHours.get(i).getOpeningHours() + " - " + openingHours.get(i).getClosingHours() + '\n');
            }
        }
        return builder.toString();
    }

  /*  public void initQbuser() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    try {
                        QBChatService.getInstance().login(PreferenceManager.getInstance(context).getQbUser());
                    } catch (XMPPException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SmackException e) {
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }*/


    public void saveQbDialog(int apiSaveQbDialog, SaveQbDialogRequestModel dialogId, boolean b) {
        Call<BaseResponse> call = appServiceAuth.saveDialogDetails(dialogId);
        call.enqueue(new RestProcess(apiSaveQbDialog, this, context, b));
    }

    public void getSerachBarberList(int apiSearchBarbers, RequestBarberModel modelRequest, boolean b) {

        Call<BarberListResponseModel> call;
        call = appService.getSearchBarbers(modelRequest);
        call.enqueue(new RestProcess<>(apiSearchBarbers, this, context, b));

    }

    public void scrollto(HorizontalListAdapter horizontalList, RecyclerView list, String snippet, GoogleMap mMap) {
        if (snippet != null && !snippet.equals("")) {
            for (int i = 0; i < horizontalList.getList().size(); i++) {
                if (snippet.equals(horizontalList.getList().get(i).getBarberId() + "")) {
                    list.scrollToPosition(i);
                }
            }
        }
    }

    public void changeCameraFocus(int firstVisibleItemPosition, List<BarberListResponseModel.ListBean> list, GoogleMap mMap) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                BarberListResponseModel.ListBean positionData = list.get(firstVisibleItemPosition);
                if (positionData != null && positionData.getLat() != null && !positionData.getLat().equals("")) {
                    if (mMap != null) {
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(positionData.getLat()),
                                Double.parseDouble(positionData.getLong())), 20));
                        // Zoom in, animating the camera.
                        mMap.animateCamera(CameraUpdateFactory.zoomIn());
                        // Zoom out to zoom level 10, animating with a duration of 3 seconds.
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(20), 4000, null);
                    }

                }
            }
        });
    }

    public void getPortfolioImage(int apiGetPortfolioImage, int o, boolean b) {
        Call<BarberPortfoloiImageResponse> call = appServiceAuth.getPortfolioImages(o);
        call.enqueue(new RestProcess(apiGetPortfolioImage, this, context, b));
    }

    public void getSavedCard(int apiGetSavedCards, Object o, boolean b) {
        Call<CardListResponse> call = appServiceAuth.getSavedCards();
        call.enqueue(new RestProcess(apiGetSavedCards, this, context, b));
    }

    public void saveCard(CardForm cardForm) {
        Card card = new Card(cardForm.getCardNumber(),
                Integer.parseInt(cardForm.getExpirationMonth()),
                Integer.parseInt(cardForm.getExpirationYear()), cardForm.getCvv());
//        Remember to validate the card object before you use it to save time.
        if (card.validateCard()) {
            Stripe stripe = new Stripe(context, NetworkConstatnts.STRIPE_TEST_KEY);
            stripe.createToken(
                    card,
                    new TokenCallback() {
                        public void onSuccess(Token token) {
                            // Send token to your server
                            Log.e("onSuccess", " Token: " + token.getId());
                            BookPaymentRequestModel bRequest = new BookPaymentRequestModel();
                            bRequest.setStripeToken(token.getId());
                            saveCardDetail(NetworkConstatnts.RequestCode.API_SAVE_CARD, bRequest, false);
                        }

                        public void onError(Exception error) {
                            // Show localized error message
                            new CommonUtils().ShowToast(error.getLocalizedMessage());
                        }
                    }
            );


        }
    }

    public void saveCardDetail(int apiSaveCard, BookPaymentRequestModel bRequest, boolean b) {
        Call<BaseResponse> call = appServiceAuth.saveCard(bRequest);
        call.enqueue(new RestProcess(apiSaveCard, this, context, b));
    }

    public void removeCard(int apiRemoveSavedCard, String id, boolean b) {
        Call<BaseResponse> call = appServiceAuth.deleteCard(id);
        call.enqueue(new RestProcess(apiRemoveSavedCard, this, context, b));
    }

    public void editBookingRequest(int apiEditBookingRequest, EditBookingRequestModel editRequestDataModel, boolean b) {
        Call<BaseResponse> call = appServiceAuth.editBookingRequest(editRequestDataModel);
        call.enqueue(new RestProcess(apiEditBookingRequest, this, context, b));
    }

    public void saveDialog(int apiSaveDialog, int barberId, int userID1, boolean b) {
        Call<BaseResponse> call = appServiceAuth.saveDialog(barberId, userID1);
        call.enqueue(new RestProcess(apiSaveDialog, this, context, b));
    }

/*    private class MapWork extends AsyncTask {
        List<BarberListResponseModel.ListBean> list;
        GoogleMap mMap;

        public MapWork(List<BarberListResponseModel.ListBean> list, GoogleMap mMap) {
            this.list = list;
            this.mMap = mMap;
        }

        *//**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param objects The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     *//*
        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                if (mMap != null) {
                    mMap.clear();
                    BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.map_pin_blue);
                    for (int i = 0; i < list.size(); i++) {
                        // Adding new item to the ArrayList
                        LatLng currentLocation = new LatLng(CommonUtils.getValidCordinates(list.get(i).getLat()), CommonUtils.getValidCordinates(list.get(i).getLong()));
                        if (currentLocation != null) {
                            mMap.addMarker(new MarkerOptions().position(currentLocation).title(list.get(i).getFullName()).snippet(String.valueOf(list.get(i).getBarberId())).icon(icon));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17f));

                        }
                    }
                    updateCameraFocus(list, mMap);
                }
            } catch (Exception e) {

            }
            return null;
        }
    }*/
}

