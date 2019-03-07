package com.app.barber.net;

import com.app.barber.core.BaseActivity;
import com.app.barber.models.AvailableSlotsModel;
import com.app.barber.models.ChangePasswordRequest;
import com.app.barber.models.request.AddServiceModel;
import com.app.barber.models.request.BookPaymentRequestModel;
import com.app.barber.models.request.EditBookingRequestModel;
import com.app.barber.models.request.HoursModel;
import com.app.barber.models.request.LoginRequestModel;
import com.app.barber.models.request.PreBookingRequestModel;
import com.app.barber.models.request.RatingRequestModel;
import com.app.barber.models.request.RegisterRequestModel;
import com.app.barber.models.request.RequestBarberModel;
import com.app.barber.models.request.RequestFavUnfavModel;
import com.app.barber.models.request.RequestMyAppointmentModel;
import com.app.barber.models.request.SaveQbDialogRequestModel;
import com.app.barber.models.request.UpdateAddressRequestModel;
import com.app.barber.models.request.UpdateBookingRequestModel;
import com.app.barber.models.request.UpdateChatDialogRequest;
import com.app.barber.models.request.UpdateEditBookingStatusModel;
import com.app.barber.models.request.ValidatePhoneNumberModel;
import com.app.barber.models.response.AddressListResponseModel;
import com.app.barber.models.response.BarberDetialResponse;
import com.app.barber.models.response.BarberListResponseModel;
import com.app.barber.models.response.BarberPortfoloiImageResponse;
import com.app.barber.models.response.BarberRatingsResponseModel;
import com.app.barber.models.response.BaseResponse;
import com.app.barber.models.response.BookingRatingResponseModel;
import com.app.barber.models.response.BookingResponseModel;
import com.app.barber.models.response.CardListResponse;
import com.app.barber.models.response.ChatUsersResponseModel;
import com.app.barber.models.response.CheckQbIdResponseModel;
import com.app.barber.models.response.ConversationResponseModel;
import com.app.barber.models.response.DistricResponseModel;
import com.app.barber.models.response.LoginResponseModel;
import com.app.barber.models.response.MyBookingsResponseMOdel;
import com.app.barber.models.response.MyImagesResponseModel;
import com.app.barber.models.response.NotificationResponseModel;
import com.app.barber.models.response.PaymentHistoryResponse;
import com.app.barber.models.response.PaymentResponseModel;
import com.app.barber.models.response.RecentStatusResponseModel;
import com.app.barber.models.response.ResponseAvailableSlotsModel;
import com.app.barber.models.response.ServiceListResponseModel;
import com.app.barber.models.response.SpecialisationResponseModel;
import com.app.barber.models.response.UpdateDataResponse;
import com.app.barber.ui.postauth.activities.barber.PreBookingDetailResponse;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by Harish on 2/3/15.
 */
public interface RestService {
    @POST(NetworkConstatnts.API.loginUser)
    Call<LoginResponseModel> loginUser(@Body LoginRequestModel requestData);

    @POST(NetworkConstatnts.API.registerUser)
    Call<BaseResponse> registerUser(@Body RegisterRequestModel registerRequest);

    @POST(NetworkConstatnts.API.updateBarberType)
    Call<UpdateDataResponse> updateBarberType(@Query("data") String typs);

    @POST(NetworkConstatnts.API.updateSpecialisationType)
    Call<UpdateDataResponse> updateSpecType(@Query("data") String typs);

    @POST(NetworkConstatnts.API.updateAddress)
    Call<UpdateDataResponse> updateUserAddress(@Body UpdateAddressRequestModel addressModel);

    @POST(NetworkConstatnts.API.updateOpeningTime)
    Call<UpdateDataResponse> updateOpeningTime(@Body List<HoursModel> addressModel);

    @POST(NetworkConstatnts.API.addService)
    Call<UpdateDataResponse> addService(@Body AddServiceModel addModel);

    @GET(NetworkConstatnts.API.getServices)
    Call<ServiceListResponseModel> getService();

    @Multipart
    @POST(NetworkConstatnts.API.ADD_WORKPLACE_PHOTOS)
    Call<BaseResponse> uploadImage(@Part List<MultipartBody.Part> list, @PartMap() Map<String, RequestBody> params);

    @Multipart
    @POST(NetworkConstatnts.API.UPDATE_PROFILE)
    Call<LoginResponseModel> uploadProfile(@Body() Map<String, RequestBody> params, @Part MultipartBody.Part partData);

    @POST(NetworkConstatnts.API.validateNumber)
    Call<BaseResponse> verifyNumber(@Body RegisterRequestModel validateRequest);

    @GET(NetworkConstatnts.API.getMyImages)
    Call<MyImagesResponseModel> getMyImages();

    @POST(NetworkConstatnts.API.forgotPassword)
    Call<BaseResponse> forgotPass(@Body ChangePasswordRequest pRequest);

    @POST(NetworkConstatnts.API.getBarbers)
    Call<BarberListResponseModel> getAvailableBarbers(@Body RequestBarberModel model);

    @GET(NetworkConstatnts.API.getBarberDetail)
    Call<BarberDetialResponse> getBarberDetail(@Query("BarberId") String s);

    @POST(NetworkConstatnts.API.getTimeSlots)
    Call<ResponseAvailableSlotsModel> getAvailableSlots(@Body AvailableSlotsModel avaialbeModel);

    @POST(NetworkConstatnts.API.getPreBookingDetails)
    Call<PreBookingDetailResponse> getPreBookingDetail(@Body PreBookingRequestModel reRequest);

    @POST(NetworkConstatnts.API.bookAppointmnet)
    Call<BookingResponseModel> bookAppointmnet(@Body PreBookingRequestModel reRequest);

    @POST(NetworkConstatnts.API.favUnfavBarber)
    Call<BaseResponse> favUnfavBarber(@Body RequestFavUnfavModel setModel);

    @POST(NetworkConstatnts.API.getSavedFav)
    Call<BarberListResponseModel> getFavList();

    @POST(NetworkConstatnts.API.getBarbersAuth)
    Call<BarberListResponseModel> getAvailableBarbersAuth(@Body RequestBarberModel model);

    @POST(NetworkConstatnts.API.getMyAppointments)
    Call<MyBookingsResponseMOdel> getMyAppointments(@Body RequestMyAppointmentModel mRequest);

    @POST(NetworkConstatnts.API.bookAppointmnetPaid)
    Call<PaymentResponseModel> bookAppointmnet(@Body BookPaymentRequestModel reRequest);

    @POST(NetworkConstatnts.API.rateThisBarber)
    Call<BaseResponse> rateBarber(@Body RatingRequestModel requestRating);

    @GET(NetworkConstatnts.API.barberRatings)
    Call<BarberRatingsResponseModel> getBarberRatings(@Query("BarberId") int userID);

    @POST(NetworkConstatnts.API.GET_NOTIFIACTIONS_LIST)
    Call<NotificationResponseModel> getNotificationsList();

    @GET(NetworkConstatnts.API.GET_BOOKING_RATINGS)
    Call<BookingRatingResponseModel> getBookingsRatings(@Query("BookingId") int requestRating, @Query("BarberId") int barberId, @Query("BookingType") int bookingType);

    @GET(NetworkConstatnts.API.GET_RECENT_STATUS)
    Call<RecentStatusResponseModel> checkRecentStatus();

    @GET(NetworkConstatnts.API.GET_SPECIALISATION)
    Call<SpecialisationResponseModel> getSpecialisationList();


    @POST(NetworkConstatnts.API.SAVE_QB_DIALOG)
    Call<BaseResponse> saveDialogDetails(@Body SaveQbDialogRequestModel dialogId);

    @GET(NetworkConstatnts.API.GET_CHAT_USERS)
    Call<ChatUsersResponseModel> getChatUsers(@Query("Type") int barber);

    @POST(NetworkConstatnts.API.SEARCH_BARBER)
    Call<BarberListResponseModel> getSearchBarbers(@Body RequestBarberModel model);

    @POST(NetworkConstatnts.API.validateNumber)
    Call<BaseResponse> validateNumber(@Body ValidatePhoneNumberModel validateRequest);

    @POST(NetworkConstatnts.API.UPDATE_BOOKING_STATUS)
    Call<BaseResponse> updateBookingStatus(@Body UpdateBookingRequestModel uModel);

    @GET(NetworkConstatnts.API.CHECK_QB_ID)
    Call<CheckQbIdResponseModel> chcekQbId();

    @POST(NetworkConstatnts.API.UPDATE_CHAT_DIALOG_ID)
    Call<BaseResponse> updateChatDialog(@Body UpdateChatDialogRequest cRequest);

    @GET(NetworkConstatnts.API.GET_BARBER_PORTFOLIO_IMAGES)
    Call<BarberPortfoloiImageResponse> getPortfolioImages(@Query("BarberId") int o);

    @GET(NetworkConstatnts.API.GET_CARD_LIST)
    Call<CardListResponse> getSavedCards();

    @POST(NetworkConstatnts.API.SAVE_CARD)
    Call<BaseResponse> saveCard(@Body BookPaymentRequestModel bRequest);

    @DELETE(NetworkConstatnts.API.DELETE_CARD)
    Call<BaseResponse> deleteCard(@Query("cardId") String id);

    @GET(NetworkConstatnts.API.GET_MY_ADDRESS)
    Call<AddressListResponseModel> getMyAddress();

    @GET(NetworkConstatnts.API.GET_PAYMENT_HISTORY_IN)
    Call<PaymentHistoryResponse> getPayments();

    @GET(NetworkConstatnts.API.GET_PAYMENT_HISTORY_OUT)
    Call<PaymentHistoryResponse> getPaymentsOut();

    @POST(NetworkConstatnts.API.EDIT_BOOKING_REQUEST)
    Call<BaseResponse> editBookingRequest(@Body EditBookingRequestModel editRequestDataModel);

    @POST(NetworkConstatnts.API.UPDATE_EDIT_REQUEST_STATUS)
    Call<BaseResponse> editBookingStatus(@Body UpdateEditBookingStatusModel updateEditBookingStatusModel);

    @GET(NetworkConstatnts.API.GET_AVAILABLE_DISTRICTS)
    Call<DistricResponseModel> getAvailableDistricts();

    @DELETE(NetworkConstatnts.API.DELETE_IMAGE)
    Call<BaseResponse> deleteUserImage(@Query("Id") String mItem1);

    @GET(NetworkConstatnts.API.GET_CONVERSATION_LIST)
    Call<ConversationResponseModel> getConversationList(@Query("DialogId") String o, @Query("UserId") String userID, @Query("UserToDeliver") String otherUserId);

    @GET(NetworkConstatnts.API.SAVE_CHAT_DIALOG)
    Call<BaseResponse> saveDialog(@Query("BarberId")int barberId, @Query("UserId") int userID1);
}