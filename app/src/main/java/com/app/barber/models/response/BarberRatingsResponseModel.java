package com.app.barber.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by harish on 2/1/19.
 */

public class BarberRatingsResponseModel {


    /**
     * Message : Success
     * Status : 201
     * Response : {"AvgPuchRating":"4.06","AvgValueRating":"4.00","AvgHygieneRating":"3.56","AvgExpertiseRating":"3.50","AvgRating":"3.78","ReviewCount":"11","FiveStarRatingCount":"3","FourStarRatingCount":"4","ThreeStarRatingCount":"1","TwoStarRatingCount":"0","OneStarRatingCount":"0","ReviewList":[{"Review":"","FullName":"My User","Image":"http://barber.xicom.info/Uploads/ProfileImages/b610aa7c-5d71-466b-a79b-c5bc84d7bd4b.jpg","AvgRating":"4.50"},{"Review":"","FullName":"My User","Image":"http://barber.xicom.info/Uploads/ProfileImages/b610aa7c-5d71-466b-a79b-c5bc84d7bd4b.jpg","AvgRating":"4.13"},{"Review":"","FullName":"Test User","Image":"http://barber.xicom.info/Content/images/client.png","AvgRating":"4.25"},{"Review":"","FullName":"My User","Image":"http://barber.xicom.info/Uploads/ProfileImages/b610aa7c-5d71-466b-a79b-c5bc84d7bd4b.jpg","AvgRating":"3.88"},{"Review":"","FullName":"My User","Image":"http://barber.xicom.info/Uploads/ProfileImages/b610aa7c-5d71-466b-a79b-c5bc84d7bd4b.jpg","AvgRating":"3.88"},{"Review":"","FullName":"My User","Image":"http://barber.xicom.info/Uploads/ProfileImages/b610aa7c-5d71-466b-a79b-c5bc84d7bd4b.jpg","AvgRating":"4.13"},{"Review":"","FullName":"Test User","Image":"http://barber.xicom.info/Content/images/client.png","AvgRating":"4.25"},{"Review":"","FullName":"Test User","Image":"http://barber.xicom.info/Content/images/client.png","AvgRating":"1.13"},{"Review":"","FullName":"Test User","Image":"http://barber.xicom.info/Content/images/client.png","AvgRating":"NaN"},{"Review":"","FullName":"Test User","Image":"http://barber.xicom.info/Content/images/client.png","AvgRating":"NaN"},{"Review":"","FullName":"Test User","Image":"http://barber.xicom.info/Content/images/client.png","AvgRating":"3.88"}]}
     */

    @SerializedName("Message")
    private String Message;
    @SerializedName("Status")
    private int Status;
    @SerializedName("Response")
    private ResponseBean Response;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public ResponseBean getResponse() {
        return Response;
    }

    public void setResponse(ResponseBean Response) {
        this.Response = Response;
    }

    public static class ResponseBean {
        /**
         * AvgPuchRating : 4.06
         * AvgValueRating : 4.00
         * AvgHygieneRating : 3.56
         * AvgExpertiseRating : 3.50
         * AvgRating : 3.78
         * ReviewCount : 11
         * FiveStarRatingCount : 3
         * FourStarRatingCount : 4
         * ThreeStarRatingCount : 1
         * TwoStarRatingCount : 0
         * OneStarRatingCount : 0
         * ReviewList : [{"Review":"","FullName":"My User","Image":"http://barber.xicom.info/Uploads/ProfileImages/b610aa7c-5d71-466b-a79b-c5bc84d7bd4b.jpg","AvgRating":"4.50"},{"Review":"","FullName":"My User","Image":"http://barber.xicom.info/Uploads/ProfileImages/b610aa7c-5d71-466b-a79b-c5bc84d7bd4b.jpg","AvgRating":"4.13"},{"Review":"","FullName":"Test User","Image":"http://barber.xicom.info/Content/images/client.png","AvgRating":"4.25"},{"Review":"","FullName":"My User","Image":"http://barber.xicom.info/Uploads/ProfileImages/b610aa7c-5d71-466b-a79b-c5bc84d7bd4b.jpg","AvgRating":"3.88"},{"Review":"","FullName":"My User","Image":"http://barber.xicom.info/Uploads/ProfileImages/b610aa7c-5d71-466b-a79b-c5bc84d7bd4b.jpg","AvgRating":"3.88"},{"Review":"","FullName":"My User","Image":"http://barber.xicom.info/Uploads/ProfileImages/b610aa7c-5d71-466b-a79b-c5bc84d7bd4b.jpg","AvgRating":"4.13"},{"Review":"","FullName":"Test User","Image":"http://barber.xicom.info/Content/images/client.png","AvgRating":"4.25"},{"Review":"","FullName":"Test User","Image":"http://barber.xicom.info/Content/images/client.png","AvgRating":"1.13"},{"Review":"","FullName":"Test User","Image":"http://barber.xicom.info/Content/images/client.png","AvgRating":"NaN"},{"Review":"","FullName":"Test User","Image":"http://barber.xicom.info/Content/images/client.png","AvgRating":"NaN"},{"Review":"","FullName":"Test User","Image":"http://barber.xicom.info/Content/images/client.png","AvgRating":"3.88"}]
         */

        @SerializedName("AvgPuchRating")
        private String AvgPuchRating;
        @SerializedName("AvgValueRating")
        private String AvgValueRating;
        @SerializedName("AvgHygieneRating")
        private String AvgHygieneRating;
        @SerializedName("AvgExpertiseRating")
        private String AvgExpertiseRating;
        @SerializedName("AvgRating")
        private String AvgRating;
        @SerializedName("ReviewCount")
        private int ReviewCount;
        @SerializedName("FiveStarRatingCount")
        private int FiveStarRatingCount;
        @SerializedName("FourStarRatingCount")
        private int FourStarRatingCount;
        @SerializedName("ThreeStarRatingCount")
        private int ThreeStarRatingCount;
        @SerializedName("TwoStarRatingCount")
        private int TwoStarRatingCount;
        @SerializedName("OneStarRatingCount")
        private int OneStarRatingCount;
        @SerializedName("ReviewList")
        private List<ReviewListBean> ReviewList;

        public String getAvgPuchRating() {
            return AvgPuchRating;
        }

        public void setAvgPuchRating(String AvgPuchRating) {
            this.AvgPuchRating = AvgPuchRating;
        }

        public String getAvgValueRating() {
            return AvgValueRating;
        }

        public void setAvgValueRating(String AvgValueRating) {
            this.AvgValueRating = AvgValueRating;
        }

        public String getAvgHygieneRating() {
            return AvgHygieneRating;
        }

        public void setAvgHygieneRating(String AvgHygieneRating) {
            this.AvgHygieneRating = AvgHygieneRating;
        }

        public String getAvgExpertiseRating() {
            return AvgExpertiseRating;
        }

        public void setAvgExpertiseRating(String AvgExpertiseRating) {
            this.AvgExpertiseRating = AvgExpertiseRating;
        }

        public String getAvgRating() {
            return AvgRating;
        }

        public void setAvgRating(String AvgRating) {
            this.AvgRating = AvgRating;
        }

        public int getReviewCount() {
            return ReviewCount;
        }

        public void setReviewCount(int ReviewCount) {
            this.ReviewCount = ReviewCount;
        }

        public int getFiveStarRatingCount() {
            return FiveStarRatingCount;
        }

        public void setFiveStarRatingCount(int FiveStarRatingCount) {
            this.FiveStarRatingCount = FiveStarRatingCount;
        }

        public int getFourStarRatingCount() {
            return FourStarRatingCount;
        }

        public void setFourStarRatingCount(int FourStarRatingCount) {
            this.FourStarRatingCount = FourStarRatingCount;
        }

        public int getThreeStarRatingCount() {
            return ThreeStarRatingCount;
        }

        public void setThreeStarRatingCount(int ThreeStarRatingCount) {
            this.ThreeStarRatingCount = ThreeStarRatingCount;
        }

        public int getTwoStarRatingCount() {
            return TwoStarRatingCount;
        }

        public void setTwoStarRatingCount(int TwoStarRatingCount) {
            this.TwoStarRatingCount = TwoStarRatingCount;
        }

        public int getOneStarRatingCount() {
            return OneStarRatingCount;
        }

        public void setOneStarRatingCount(int OneStarRatingCount) {
            this.OneStarRatingCount = OneStarRatingCount;
        }

        public List<ReviewListBean> getReviewList() {
            return ReviewList;
        }

        public void setReviewList(List<ReviewListBean> ReviewList) {
            this.ReviewList = ReviewList;
        }

        public static class ReviewListBean {
            /**
             * Review :
             * FullName : My User
             * Image : http://barber.xicom.info/Uploads/ProfileImages/b610aa7c-5d71-466b-a79b-c5bc84d7bd4b.jpg
             * AvgRating : 4.50
             */

            @SerializedName("Review")
            private String Review;
            @SerializedName("FullName")
            private String FullName;
            @SerializedName("Image")
            private String Image;
            @SerializedName("AvgRating")
            private String AvgRating;

            public String getReview() {
                return Review;
            }

            public void setReview(String Review) {
                this.Review = Review;
            }

            public String getFullName() {
                return FullName;
            }

            public void setFullName(String FullName) {
                this.FullName = FullName;
            }

            public String getImage() {
                return Image;
            }

            public void setImage(String Image) {
                this.Image = Image;
            }

            public String getAvgRating() {
                return AvgRating;
            }

            public void setAvgRating(String AvgRating) {
                this.AvgRating = AvgRating;
            }
        }
    }
}
