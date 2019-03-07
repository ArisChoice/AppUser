package com.app.barber.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by harish on 16/11/18.
 */

public class MyImagesResponseModel {


    /**
     * Message : Image list
     * Status : 201
     * Images : [{"m_Item1":"http://barber.xicom.info/Uploads/WorkSpaceImages/b9ede584-b3d3-4f5c-9594-269881c873bc.jpg","m_Item2":17}]
     */

    @SerializedName("Message")
    private String Message;
    @SerializedName("Status")
    private int Status;
    @SerializedName("Images")
    private List<ImagesBean> Images;

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

    public List<ImagesBean> getImages() {
        return Images;
    }

    public void setImages(List<ImagesBean> Images) {
        this.Images = Images;
    }

    public static class ImagesBean {
        /**
         * m_Item1 : http://barber.xicom.info/Uploads/WorkSpaceImages/b9ede584-b3d3-4f5c-9594-269881c873bc.jpg
         * m_Item2 : 17
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
