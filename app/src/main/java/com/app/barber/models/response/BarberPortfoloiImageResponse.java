package com.app.barber.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by harish on 22/1/19.
 */

public class BarberPortfoloiImageResponse {

    /**
     * Message : Image list
     * Status : 201
     * Response : [{"m_Item1":"http://barber.xicom.info/Uploads/WorkSpaceImages/911e6592-4d16-4064-9ecb-5c8f1f9f9723.jpg","m_Item2":5},{"m_Item1":"http://barber.xicom.info/Uploads/WorkSpaceImages/33777816-57a0-4499-8185-bff84a100c73.jpg","m_Item2":6}]
     */

    @SerializedName("Message")
    private String Message;
    @SerializedName("Status")
    private int Status;
    @SerializedName("Response")
    private List<ResponseBean> Response;

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

    public List<ResponseBean> getResponse() {
        return Response;
    }

    public void setResponse(List<ResponseBean> Response) {
        this.Response = Response;
    }

    public static class ResponseBean {
        /**
         * m_Item1 : http://barber.xicom.info/Uploads/WorkSpaceImages/911e6592-4d16-4064-9ecb-5c8f1f9f9723.jpg
         * m_Item2 : 5
         */

        @SerializedName("m_Item1")
        private String mItem1;
        @SerializedName("m_Item2")
        private int mItem2;

        public String getMItem1() {
            return mItem1;
        }

        public void setMItem1(String mItem1) {
            this.mItem1 = mItem1;
        }

        public int getMItem2() {
            return mItem2;
        }

        public void setMItem2(int mItem2) {
            this.mItem2 = mItem2;
        }
    }
}
