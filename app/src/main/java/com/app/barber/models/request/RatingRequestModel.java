package com.app.barber.models.request;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * Created by harish on 31/12/18.
 */

public class RatingRequestModel {
    public int getBarberId() {
        return BarberId;
    }

    public void setBarberId(int barberId) {
        BarberId = barberId;
    }

    public int getBookingId() {
        return BookingId;
    }

    public void setBookingId(int bookingId) {
        BookingId = bookingId;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }


    int BarberId;
    int BookingId;
    String Review;
    String CardId;

    public String getCardId() {
        return CardId;
    }

    public void setCardId(String cardId) {
        CardId = cardId;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    String Amount;

    public List<Ratings> getRatingList() {
        return RatingList;
    }

    public void setRatingList(List<Ratings> ratingList) {
        RatingList = ratingList;
    }

    List<Ratings> RatingList;

    public int getBookingType() {
        return BookingType;
    }

    public void setBookingType(int bookingType) {
        BookingType = bookingType;
    }

    int BookingType;

    public static class Ratings {
        public int getRatingType() {
            return RatingType;
        }

        public void setRatingType(int ratingType) {
            RatingType = ratingType;
        }

        public int getRating() {
            return Rating;
        }

        public void setRating(int rating) {
            Rating = rating;
        }

        int RatingType;//  Expertise = 1,Hygiene = 2, Value = 3,Punctuality = 4

        int Rating;

    }
}
