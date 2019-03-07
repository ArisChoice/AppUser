package com.app.barber.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConversationResponseModel {

    /**
     * Message : List
     * Status : 201
     * Object : {"UserId":17,"UserToDeliver":169,"UserName":"Yuki","Image":"/Content/images/client.png","ChatList":[{"Message":"Chandigarh I I Chandigarh have a good good time to","Sender":169,"Recipient":17},{"Message":"Gkg","Sender":169,"Recipient":17},{"Message":"Wueur","Sender":169,"Recipient":17},{"Message":"Djdj","Sender":169,"Recipient":17},{"Message":"Asya","Sender":169,"Recipient":17},{"Message":"Message","Sender":169,"Recipient":17},{"Message":"Han Chandigarh Han Chandigarh dont know","Sender":169,"Recipient":17},{"Message":"Heue","Sender":169,"Recipient":17},{"Message":"Iiw","Sender":169,"Recipient":17},{"Message":"Han","Sender":169,"Recipient":17},{"Message":"Chandigarh Han Chandigarh Han","Sender":169,"Recipient":17},{"Message":"Han","Sender":169,"Recipient":17},{"Message":"Think","Sender":169,"Recipient":17},{"Message":"Ggod","Sender":169,"Recipient":17},{"Message":"Good","Sender":169,"Recipient":17},{"Message":"Aa rha","Sender":169,"Recipient":17},{"Message":"Aarha","Sender":169,"Recipient":17},{"Message":"Smile  asya","Sender":169,"Recipient":17},{"Message":"Aaya","Sender":169,"Recipient":17},{"Message":"Fhfh","Sender":169,"Recipient":17},{"Message":"Bshhsje","Sender":169,"Recipient":17},{"Message":"Jdjd","Sender":169,"Recipient":17},{"Message":"Bbz","Sender":169,"Recipient":17},{"Message":"Hhd","Sender":169,"Recipient":17},{"Message":"Han","Sender":169,"Recipient":17},{"Message":"Nhi","Sender":169,"Recipient":17},{"Message":"Han","Sender":169,"Recipient":17},{"Message":"😁","Sender":169,"Recipient":17},{"Message":"I Han Han Han Chandigarh","Sender":169,"Recipient":17},{"Message":"Uui","Sender":169,"Recipient":17},{"Message":"Nice","Sender":169,"Recipient":17},{"Message":"Message","Sender":169,"Recipient":17},{"Message":"Aaya","Sender":169,"Recipient":17},{"Message":"Chandigarh Han With Chandigarh Han Chandigarh Han","Sender":169,"Recipient":17},{"Message":"Chandigarh With a few minutes walk","Sender":169,"Recipient":17},{"Message":"I have tried to","Sender":169,"Recipient":17},{"Message":"I I","Sender":169,"Recipient":17},{"Message":"Hsjd","Sender":169,"Recipient":17},{"Message":"Jjs","Sender":169,"Recipient":17},{"Message":"Eueur","Sender":169,"Recipient":17},{"Message":"dfdsfsdfds","Sender":17,"Recipient":169},{"Message":"sdfsdfsdfsdf","Sender":17,"Recipient":169},{"Message":"fdsfsdf","Sender":17,"Recipient":169},{"Message":"fsdfsdf","Sender":17,"Recipient":169},{"Message":"dfsdfd","Sender":17,"Recipient":169},{"Message":"sdfsdf","Sender":17,"Recipient":169},{"Message":"dfsdfsdfasd","Sender":17,"Recipient":169},{"Message":"dfsdfdsjflsdjfd","Sender":17,"Recipient":169},{"Message":"dfhsdkjfhsdf","Sender":17,"Recipient":169},{"Message":"dfkjsdfhsd","Sender":17,"Recipient":169},{"Message":"","Sender":17,"Recipient":169},{"Message":"dfdsfds","Sender":17,"Recipient":169},{"Message":"aya","Sender":17,"Recipient":169},{"Message":"msg aya kya?","Sender":17,"Recipient":169},{"Message":"hmmm","Sender":17,"Recipient":169},{"Message":"mubarkan","Sender":17,"Recipient":169},{"Message":"dfdsfksdhfhsdf","Sender":17,"Recipient":169},{"Message":"dfksdhfsd","Sender":17,"Recipient":169},{"Message":"dfsdfsd","Sender":17,"Recipient":169},{"Message":"ok","Sender":17,"Recipient":169}]}
     */

    @SerializedName("Message")
    private String Message;
    @SerializedName("Status")
    private int Status;
    @SerializedName("Object")
    private ObjectBean Object;

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

    public ObjectBean getObject() {
        return Object;
    }

    public void setObject(ObjectBean Object) {
        this.Object = Object;
    }

    public static class ObjectBean {
        /**
         * UserId : 17
         * UserToDeliver : 169
         * UserName : Yuki
         * Image : /Content/images/client.png
         * ChatList : [{"Message":"Chandigarh I I Chandigarh have a good good time to","Sender":169,"Recipient":17},{"Message":"Gkg","Sender":169,"Recipient":17},{"Message":"Wueur","Sender":169,"Recipient":17},{"Message":"Djdj","Sender":169,"Recipient":17},{"Message":"Asya","Sender":169,"Recipient":17},{"Message":"Message","Sender":169,"Recipient":17},{"Message":"Han Chandigarh Han Chandigarh dont know","Sender":169,"Recipient":17},{"Message":"Heue","Sender":169,"Recipient":17},{"Message":"Iiw","Sender":169,"Recipient":17},{"Message":"Han","Sender":169,"Recipient":17},{"Message":"Chandigarh Han Chandigarh Han","Sender":169,"Recipient":17},{"Message":"Han","Sender":169,"Recipient":17},{"Message":"Think","Sender":169,"Recipient":17},{"Message":"Ggod","Sender":169,"Recipient":17},{"Message":"Good","Sender":169,"Recipient":17},{"Message":"Aa rha","Sender":169,"Recipient":17},{"Message":"Aarha","Sender":169,"Recipient":17},{"Message":"Smile  asya","Sender":169,"Recipient":17},{"Message":"Aaya","Sender":169,"Recipient":17},{"Message":"Fhfh","Sender":169,"Recipient":17},{"Message":"Bshhsje","Sender":169,"Recipient":17},{"Message":"Jdjd","Sender":169,"Recipient":17},{"Message":"Bbz","Sender":169,"Recipient":17},{"Message":"Hhd","Sender":169,"Recipient":17},{"Message":"Han","Sender":169,"Recipient":17},{"Message":"Nhi","Sender":169,"Recipient":17},{"Message":"Han","Sender":169,"Recipient":17},{"Message":"😁","Sender":169,"Recipient":17},{"Message":"I Han Han Han Chandigarh","Sender":169,"Recipient":17},{"Message":"Uui","Sender":169,"Recipient":17},{"Message":"Nice","Sender":169,"Recipient":17},{"Message":"Message","Sender":169,"Recipient":17},{"Message":"Aaya","Sender":169,"Recipient":17},{"Message":"Chandigarh Han With Chandigarh Han Chandigarh Han","Sender":169,"Recipient":17},{"Message":"Chandigarh With a few minutes walk","Sender":169,"Recipient":17},{"Message":"I have tried to","Sender":169,"Recipient":17},{"Message":"I I","Sender":169,"Recipient":17},{"Message":"Hsjd","Sender":169,"Recipient":17},{"Message":"Jjs","Sender":169,"Recipient":17},{"Message":"Eueur","Sender":169,"Recipient":17},{"Message":"dfdsfsdfds","Sender":17,"Recipient":169},{"Message":"sdfsdfsdfsdf","Sender":17,"Recipient":169},{"Message":"fdsfsdf","Sender":17,"Recipient":169},{"Message":"fsdfsdf","Sender":17,"Recipient":169},{"Message":"dfsdfd","Sender":17,"Recipient":169},{"Message":"sdfsdf","Sender":17,"Recipient":169},{"Message":"dfsdfsdfasd","Sender":17,"Recipient":169},{"Message":"dfsdfdsjflsdjfd","Sender":17,"Recipient":169},{"Message":"dfhsdkjfhsdf","Sender":17,"Recipient":169},{"Message":"dfkjsdfhsd","Sender":17,"Recipient":169},{"Message":"","Sender":17,"Recipient":169},{"Message":"dfdsfds","Sender":17,"Recipient":169},{"Message":"aya","Sender":17,"Recipient":169},{"Message":"msg aya kya?","Sender":17,"Recipient":169},{"Message":"hmmm","Sender":17,"Recipient":169},{"Message":"mubarkan","Sender":17,"Recipient":169},{"Message":"dfdsfksdhfhsdf","Sender":17,"Recipient":169},{"Message":"dfksdhfsd","Sender":17,"Recipient":169},{"Message":"dfsdfsd","Sender":17,"Recipient":169},{"Message":"ok","Sender":17,"Recipient":169}]
         */

        @SerializedName("UserId")
        private int UserId;
        @SerializedName("UserToDeliver")
        private int UserToDeliver;
        @SerializedName("UserName")
        private String UserName;
        @SerializedName("Image")
        private String Image;
        @SerializedName("ChatList")
        private List<ChatListBean> ChatList;

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public int getUserToDeliver() {
            return UserToDeliver;
        }

        public void setUserToDeliver(int UserToDeliver) {
            this.UserToDeliver = UserToDeliver;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String Image) {
            this.Image = Image;
        }

        public List<ChatListBean> getChatList() {
            return ChatList;
        }

        public void setChatList(List<ChatListBean> ChatList) {
            this.ChatList = ChatList;
        }

        public static class ChatListBean {
            /**
             * Message : Chandigarh I I Chandigarh have a good good time to
             * Sender : 169
             * Recipient : 17
             */

            @SerializedName("Message")
            private String Message;
            @SerializedName("Sender")
            private int Sender;
            @SerializedName("Recipient")
            private int Recipient;

            public String getMessage() {
                return Message;
            }

            public void setMessage(String Message) {
                this.Message = Message;
            }

            public int getSender() {
                return Sender;
            }

            public void setSender(int Sender) {
                this.Sender = Sender;
            }

            public int getRecipient() {
                return Recipient;
            }

            public void setRecipient(int Recipient) {
                this.Recipient = Recipient;
            }
        }
    }
}
