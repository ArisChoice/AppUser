package com.app.barber.net;

/**
 * Created by harish on 24/8/18.
 */

public interface NetworkConstatnts {
    String TOKEN_PREF = "mypref_Token_Customer";
    String STRIPE_TEST_KEY = "pk_test_bCRYp7Tm1SKmfdKToNO0z8GB" /*"pk_test_u8fIUtKG1iv0vNwPVi6CTEXY"*/;

    interface ResponseCode {
        int success = 201;
        int sessionExpred = 203;
        int faliure = 401;
    }

    interface URL {
        //        String BASE_URL = ""; //LIVE
        String BASE_URL = "http://barber.xicom.info/";//DEMO
    }

    interface KEYS {
        String secretKey = "J3H7F9J6FG";
        String deviceType = "DeviceType";
        String uniqueDeviceId = "UniqueDeviceId";
        // String deviceId = "DeviceID";
        String TimeStamp = "TimeStamp";
        String sessionToken = "SessionToken";
        String deviceToken = "DeviceToken";
        String userId = "userId";
        String sessionId = "SessionId";
        String ClientHash = "ClientHash";


        String userType = "UserType";
        String deviceId = "DeviceId";
        String timezone = "TimeZone";
    }

    interface API {
        String ABOUT_US = URL.BASE_URL + "Page/AboutUs";
        String FAQ = URL.BASE_URL + "Page/FAQ";
        String TERMS = URL.BASE_URL + "Page/TermsAndConditions";
        String CANCELLATION_POLICY = URL.BASE_URL + "home/Policy";
        String WEBSITE = URL.BASE_URL;


        String loginUser = "api/account/Login";
        String registerUser = "api/account/Register";
        String updateBarberType = "api/account/UpdateBarberType";
        String updateSpecialisationType = "api/account/UpdateSpecType";
        String updateAddress = "api/barber/AddAddress";
        String updateOpeningTime = "api/account/AddOpeningHours";
        String addService = "api/account/AddServices";
        String getServices = "api/account/GetServices";
        String ADD_WORKPLACE_PHOTOS = "api/account/PostWorkSpaceImages";
        String UPDATE_PROFILE = "api/account/UpdateProfile";
        String validateNumber = "api/account/ValidatePhoneNumber";
        String getMyImages = "api/account/PortfolioImages";
        String forgotPassword = "api/account/ForgotPassword";
        String getBarbers = "api/user/LoadHomePage";
        String getBarbersAuth = "api/user/LoadHomePageUser";
        String getBarberDetail = "api/user/GetBarberDetail";
        String getTimeSlots = "/api/user/TimeSlots";
        String getPreBookingDetails = "/api/user/PreBooking";
        String bookAppointmnet = "api/user/Bookings";
        String favUnfavBarber = "api/user/MakeFavourite";
        String getSavedFav = "api/user/FavouriteList";
        String getMyAppointments = "api/user/UserBookingList";
        String bookAppointmnetPaid = "api/payment/MakePayment";
        String rateThisBarber = "api/user/RatingAndReview";
        String barberRatings = "api/user/GetBarberRatingAndReviews";
        String GET_NOTIFIACTIONS_LIST = "api/barber/NotificationList";
        String GET_BOOKING_RATINGS = "api/user/GetBookingRatingAndReviews";
        String GET_RECENT_STATUS = "api/account/GetStatus";
        String GET_SPECIALISATION = "api/account/GetSpecializations";
        String SAVE_QB_DIALOG = "api/account/SaveQbDialog";
        String GET_CHAT_USERS = "api/account/GetChatParticipants";

        String SEARCH_BARBER = "api/User/SearchBarber";

        String UPDATE_BOOKING_STATUS = "api/User/ChangeBooking";
        String CHECK_QB_ID = "api/account/GetQBId";
        String UPDATE_CHAT_DIALOG_ID = "api/account/UpdateChatDialog";/*UpdateQuickBloxDialog*/
        String GET_BARBER_PORTFOLIO_IMAGES = "api/User/GetBarberPortfolio";
        String GET_CARD_LIST = "api/payment/GetCardList";
        String SAVE_CARD = "api/payment/SaveCard";
        String DELETE_CARD = "api/payment/DeleteCard";
        String GET_MY_ADDRESS = "api/account/GetAddress";
        String GET_PAYMENT_HISTORY_OUT = "api/account/GetPaymentsOut";
        String GET_PAYMENT_HISTORY_IN = "api/account/GetPaymentsIn";
        String EDIT_BOOKING_REQUEST = "api/account/EditBooking";
        String UPDATE_EDIT_REQUEST_STATUS = "api/account/ChageEditStatus";
        String GET_AVAILABLE_DISTRICTS = "api/account/GetServingDistricts";
        String DELETE_IMAGE = "api/account/DeletePortFolioImages";
        String GET_CONVERSATION_LIST = "api/account/GetChat";
        String SAVE_CHAT_DIALOG = "api/account/StartChat";

    }

    interface Params {
        String firstName = "FirstName";
        String email = "Email";
        String lastName = "LastName";
        String desc = "Description";

        String fullname = "FullName";
        String shopName = "ShopName";
        String facebook = "FbUrl";
        String twitter = "TwtUrl";
        String insta = "InstaUrl";
        String other = "OtherUrl";
        String mobile = "mobile";
        String userType = "UserType";
        String value = "type";
    }

    interface RequestCode {
        int API_LOGIN = 1;
        int API_REGISTER = 2;
        int API_FORGET_PASSWORD = 3;
        int API_UPDATE_BARBER_TYPE = 4;
        int API_UPDATE_SPEC_TYPE = 5;
        int API_UPDATE_ADDRESS = 6;
        int API_UPDATE_SERVICE = 7;
        int API_UPDATE_OPENING_TIME = 8;
        int API_ADD_SERVICE = 9;
        int API_GET_SERVICE = 10;
        int API_POST_WORKSPACE_IMAGES = 11;
        int API_UPDATE_PROFILE = 12;
        int API_VALIDATE_NUMBER = 13;
        int API_GET_IMAGES = 14;
        int API_FORGET_PASS = 15;
        int API_GET_BARBERS = 16;
        int API_BARBER_DETAIL = 17;
        int API_AVAILABLE_SLOTS = 18;
        int API_GET_PRE_BOOKING_DETAIL = 19;
        int API_BOOK_BARBER_APPOINTMENT = 20;
        int API_FAV_UNFAV = 21;
        int API_SAVED_BARBER_LIST = 22;
        int API_GET_BARBERS_AUTH = 23;
        int MY_ALL_BOOKINGS = 24;
        int API_BOOK_BARBER_APPOINTMENT_PAID = 25;
        int API_RATE_BARBER = 26;

        int API_BARBER_RATINGS = 27;

        int API_NOTIFICATION_LIST = 28;
        int API_BOOKING_RATINGS_REVIEW = 29;
        int API_CHECK_RECENT_STATUS = 30;
        int API_GET_SPECIALISATION = 31;
        int API_SAVE_QB_DIALOG = 32;
        int API_GET_CHAT_PARTICIPENT = 33;
        int API_SEARCH_BARBERS = 34;
        int API_UPDATE_BOOKING_STATUS = 35;
        int API_CHECK_QB_ID = 36;
        int API_UPDTAE_CHAT_DIALOG = 37;
        int API_GET_PORTFOLIO_IMAGE = 38;

        int API_GET_BARBER_PORTFOLIO_IMAGE = 40;

        int API_GET_SAVED_CARDS = 41;
        int API_SAVE_CARD = 42;

        int API_REMOVE_SAVED_CARD = 43;

        int API_GET_USER_ADDRESS = 44;
        int API_GET_PAYMENT_HISTORY_IN = 45;
        int API_GET_PAYMENT_HISTORY_OUT = 46;
        int API_EDIT_BOOKING_REQUEST = 47;
        int API_EDIT_BOOKING_STATUS = 48;

        int API_AVAILABLE_GET_DISTRICTS = 49;

        int API_DELETE_IMAGE = 50;
        int API_CONVERSATION_LIST = 51;
        int API_SAVE_DIALOG = 52;
    }

    interface QB {
        String APP_ID = "75356";
        String ACCOUNT_ID = "102713";
        String AUTH_KEY = "cjjqmG272xj5XYE";
        String AUTH_SECRET = "yfQ-BFEFhCy9Gwf";
        String ACCOUNT_KEY = "_3bTSswpeMf9H4KcwN9S";
        String GLOBAL_PASSWORD = "trimuser";
    }

    public interface DeviceType {
        String Android = "1";
    }
}
