package com.app.barber.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by harish on 24/1/19.
 */

public class CardListResponse {

    /**
     * Message : Success
     * Status : 201
     * cards : {"object":"list","data":[{"id":"card_1Dw1b5JgY6uDwvPUFYZz8GzN","object":"card","AccountId":null,"address_city":null,"address_country":null,"address_line1":null,"address_line1_check":null,"address_line2":null,"address_state":null,"address_zip":null,"address_zip_check":null,"available_payout_methods":null,"brand":"Visa","country":"US","currency":null,"CustomerId":"cus_EMeQhsdASxjxat","cvc_check":"pass","default_for_currency":false,"deleted":false,"dynamic_last4":null,"exp_month":9,"exp_year":2020,"fingerprint":"ehnt5kGNUajnCqHu","funding":"credit","last4":"4242","metadata":{},"name":null,"RecipientId":null,"three_d_secure":null,"tokenization_method":null,"description":null,"iin":null,"issuer":null,"StripeResponse":null},{"id":"card_1Dw1cAJgY6uDwvPUmDGPLoA1","object":"card","AccountId":null,"address_city":null,"address_country":null,"address_line1":null,"address_line1_check":null,"address_line2":null,"address_state":null,"address_zip":null,"address_zip_check":null,"available_payout_methods":null,"brand":"Visa","country":"US","currency":null,"CustomerId":"cus_EMeQhsdASxjxat","cvc_check":"pass","default_for_currency":false,"deleted":false,"dynamic_last4":null,"exp_month":9,"exp_year":2020,"fingerprint":"Yi0cKtCZgw9LF4hY","funding":"unknown","last4":"1111","metadata":{},"name":null,"RecipientId":null,"three_d_secure":null,"tokenization_method":null,"description":null,"iin":null,"issuer":null,"StripeResponse":null}],"has_more":false,"total_count":0,"url":"/v1/customers/cus_EMeQhsdASxjxat/sources","StripeResponse":{"ResponseJson":"{\n  \"object\": \"list\",\n  \"data\": [\n    {\n      \"id\": \"card_1Dw1b5JgY6uDwvPUFYZz8GzN\",\n      \"object\": \"card\",\n      \"address_city\": null,\n      \"address_country\": null,\n      \"address_line1\": null,\n      \"address_line1_check\": null,\n      \"address_line2\": null,\n      \"address_state\": null,\n      \"address_zip\": null,\n      \"address_zip_check\": null,\n      \"brand\": \"Visa\",\n      \"country\": \"US\",\n      \"customer\": \"cus_EMeQhsdASxjxat\",\n      \"cvc_check\": \"pass\",\n      \"dynamic_last4\": null,\n      \"exp_month\": 9,\n      \"exp_year\": 2020,\n      \"fingerprint\": \"ehnt5kGNUajnCqHu\",\n      \"funding\": \"credit\",\n      \"last4\": \"4242\",\n      \"metadata\": {\n      },\n      \"name\": null,\n      \"tokenization_method\": null\n    },\n    {\n      \"id\": \"card_1Dw1cAJgY6uDwvPUmDGPLoA1\",\n      \"object\": \"card\",\n      \"address_city\": null,\n      \"address_country\": null,\n      \"address_line1\": null,\n      \"address_line1_check\": null,\n      \"address_line2\": null,\n      \"address_state\": null,\n      \"address_zip\": null,\n      \"address_zip_check\": null,\n      \"brand\": \"Visa\",\n      \"country\": \"US\",\n      \"customer\": \"cus_EMeQhsdASxjxat\",\n      \"cvc_check\": \"pass\",\n      \"dynamic_last4\": null,\n      \"exp_month\": 9,\n      \"exp_year\": 2020,\n      \"fingerprint\": \"Yi0cKtCZgw9LF4hY\",\n      \"funding\": \"unknown\",\n      \"last4\": \"1111\",\n      \"metadata\": {\n      },\n      \"name\": null,\n      \"tokenization_method\": null\n    }\n  ],\n  \"has_more\": false,\n  \"url\": \"/v1/customers/cus_EMeQhsdASxjxat/sources\"\n}\n","ObjectJson":"{\n  \"object\": \"list\",\n  \"data\": [\n    {\n      \"id\": \"card_1Dw1b5JgY6uDwvPUFYZz8GzN\",\n      \"object\": \"card\",\n      \"address_city\": null,\n      \"address_country\": null,\n      \"address_line1\": null,\n      \"address_line1_check\": null,\n      \"address_line2\": null,\n      \"address_state\": null,\n      \"address_zip\": null,\n      \"address_zip_check\": null,\n      \"brand\": \"Visa\",\n      \"country\": \"US\",\n      \"customer\": \"cus_EMeQhsdASxjxat\",\n      \"cvc_check\": \"pass\",\n      \"dynamic_last4\": null,\n      \"exp_month\": 9,\n      \"exp_year\": 2020,\n      \"fingerprint\": \"ehnt5kGNUajnCqHu\",\n      \"funding\": \"credit\",\n      \"last4\": \"42 01-24 11:54:09.684 24194-25250/com.app.trimcheck.customer D/OkHttp: 42\",\n      \"metadata\": {\n      },\n      \"name\": null,\n      \"tokenization_method\": null\n    },\n    {\n      \"id\": \"card_1Dw1cAJgY6uDwvPUmDGPLoA1\",\n      \"object\": \"card\",\n      \"address_city\": null,\n      \"address_country\": null,\n      \"address_line1\": null,\n      \"address_line1_check\": null,\n      \"address_line2\": null,\n      \"address_state\": null,\n      \"address_zip\": null,\n      \"address_zip_check\": null,\n      \"brand\": \"Visa\",\n      \"country\": \"US\",\n      \"customer\": \"cus_EMeQhsdASxjxat\",\n      \"cvc_check\": \"pass\",\n      \"dynamic_last4\": null,\n      \"exp_month\": 9,\n      \"exp_year\": 2020,\n      \"fingerprint\": \"Yi0cKtCZgw9LF4hY\",\n      \"funding\": \"unknown\",\n      \"last4\": \"1111\",\n      \"metadata\": {\n      },\n      \"name\": null,\n      \"tokenization_method\": null\n    }\n  ],\n  \"has_more\": false,\n  \"url\": \"/v1/customers/cus_EMeQhsdASxjxat/sources\"\n}\n","RequestId":"req_BqQJV3VLdbjfqc","RequestDate":"2019-01-24T11:54:09+05:30"}}
     */

    @SerializedName("Message")
    private String Message;
    @SerializedName("Status")
    private int Status;
    @SerializedName("cards")
    private CardsBean cards;

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

    public CardsBean getCards() {
        return cards;
    }

    public void setCards(CardsBean cards) {
        this.cards = cards;
    }

    public static class CardsBean {
        /**
         * object : list
         * data : [{"id":"card_1Dw1b5JgY6uDwvPUFYZz8GzN","object":"card","AccountId":null,"address_city":null,"address_country":null,"address_line1":null,"address_line1_check":null,"address_line2":null,"address_state":null,"address_zip":null,"address_zip_check":null,"available_payout_methods":null,"brand":"Visa","country":"US","currency":null,"CustomerId":"cus_EMeQhsdASxjxat","cvc_check":"pass","default_for_currency":false,"deleted":false,"dynamic_last4":null,"exp_month":9,"exp_year":2020,"fingerprint":"ehnt5kGNUajnCqHu","funding":"credit","last4":"4242","metadata":{},"name":null,"RecipientId":null,"three_d_secure":null,"tokenization_method":null,"description":null,"iin":null,"issuer":null,"StripeResponse":null},{"id":"card_1Dw1cAJgY6uDwvPUmDGPLoA1","object":"card","AccountId":null,"address_city":null,"address_country":null,"address_line1":null,"address_line1_check":null,"address_line2":null,"address_state":null,"address_zip":null,"address_zip_check":null,"available_payout_methods":null,"brand":"Visa","country":"US","currency":null,"CustomerId":"cus_EMeQhsdASxjxat","cvc_check":"pass","default_for_currency":false,"deleted":false,"dynamic_last4":null,"exp_month":9,"exp_year":2020,"fingerprint":"Yi0cKtCZgw9LF4hY","funding":"unknown","last4":"1111","metadata":{},"name":null,"RecipientId":null,"three_d_secure":null,"tokenization_method":null,"description":null,"iin":null,"issuer":null,"StripeResponse":null}]
         * has_more : false
         * total_count : 0
         * url : /v1/customers/cus_EMeQhsdASxjxat/sources
         * StripeResponse : {"ResponseJson":"{\n  \"object\": \"list\",\n  \"data\": [\n    {\n      \"id\": \"card_1Dw1b5JgY6uDwvPUFYZz8GzN\",\n      \"object\": \"card\",\n      \"address_city\": null,\n      \"address_country\": null,\n      \"address_line1\": null,\n      \"address_line1_check\": null,\n      \"address_line2\": null,\n      \"address_state\": null,\n      \"address_zip\": null,\n      \"address_zip_check\": null,\n      \"brand\": \"Visa\",\n      \"country\": \"US\",\n      \"customer\": \"cus_EMeQhsdASxjxat\",\n      \"cvc_check\": \"pass\",\n      \"dynamic_last4\": null,\n      \"exp_month\": 9,\n      \"exp_year\": 2020,\n      \"fingerprint\": \"ehnt5kGNUajnCqHu\",\n      \"funding\": \"credit\",\n      \"last4\": \"4242\",\n      \"metadata\": {\n      },\n      \"name\": null,\n      \"tokenization_method\": null\n    },\n    {\n      \"id\": \"card_1Dw1cAJgY6uDwvPUmDGPLoA1\",\n      \"object\": \"card\",\n      \"address_city\": null,\n      \"address_country\": null,\n      \"address_line1\": null,\n      \"address_line1_check\": null,\n      \"address_line2\": null,\n      \"address_state\": null,\n      \"address_zip\": null,\n      \"address_zip_check\": null,\n      \"brand\": \"Visa\",\n      \"country\": \"US\",\n      \"customer\": \"cus_EMeQhsdASxjxat\",\n      \"cvc_check\": \"pass\",\n      \"dynamic_last4\": null,\n      \"exp_month\": 9,\n      \"exp_year\": 2020,\n      \"fingerprint\": \"Yi0cKtCZgw9LF4hY\",\n      \"funding\": \"unknown\",\n      \"last4\": \"1111\",\n      \"metadata\": {\n      },\n      \"name\": null,\n      \"tokenization_method\": null\n    }\n  ],\n  \"has_more\": false,\n  \"url\": \"/v1/customers/cus_EMeQhsdASxjxat/sources\"\n}\n","ObjectJson":"{\n  \"object\": \"list\",\n  \"data\": [\n    {\n      \"id\": \"card_1Dw1b5JgY6uDwvPUFYZz8GzN\",\n      \"object\": \"card\",\n      \"address_city\": null,\n      \"address_country\": null,\n      \"address_line1\": null,\n      \"address_line1_check\": null,\n      \"address_line2\": null,\n      \"address_state\": null,\n      \"address_zip\": null,\n      \"address_zip_check\": null,\n      \"brand\": \"Visa\",\n      \"country\": \"US\",\n      \"customer\": \"cus_EMeQhsdASxjxat\",\n      \"cvc_check\": \"pass\",\n      \"dynamic_last4\": null,\n      \"exp_month\": 9,\n      \"exp_year\": 2020,\n      \"fingerprint\": \"ehnt5kGNUajnCqHu\",\n      \"funding\": \"credit\",\n      \"last4\": \"42 01-24 11:54:09.684 24194-25250/com.app.trimcheck.customer D/OkHttp: 42\",\n      \"metadata\": {\n      },\n      \"name\": null,\n      \"tokenization_method\": null\n    },\n    {\n      \"id\": \"card_1Dw1cAJgY6uDwvPUmDGPLoA1\",\n      \"object\": \"card\",\n      \"address_city\": null,\n      \"address_country\": null,\n      \"address_line1\": null,\n      \"address_line1_check\": null,\n      \"address_line2\": null,\n      \"address_state\": null,\n      \"address_zip\": null,\n      \"address_zip_check\": null,\n      \"brand\": \"Visa\",\n      \"country\": \"US\",\n      \"customer\": \"cus_EMeQhsdASxjxat\",\n      \"cvc_check\": \"pass\",\n      \"dynamic_last4\": null,\n      \"exp_month\": 9,\n      \"exp_year\": 2020,\n      \"fingerprint\": \"Yi0cKtCZgw9LF4hY\",\n      \"funding\": \"unknown\",\n      \"last4\": \"1111\",\n      \"metadata\": {\n      },\n      \"name\": null,\n      \"tokenization_method\": null\n    }\n  ],\n  \"has_more\": false,\n  \"url\": \"/v1/customers/cus_EMeQhsdASxjxat/sources\"\n}\n","RequestId":"req_BqQJV3VLdbjfqc","RequestDate":"2019-01-24T11:54:09+05:30"}
         */

        @SerializedName("object")
        private String object;
        @SerializedName("has_more")
        private boolean hasMore;
        @SerializedName("total_count")
        private int totalCount;
        @SerializedName("url")
        private String url;
        @SerializedName("StripeResponse")
        private StripeResponseBean StripeResponse;
        @SerializedName("data")
        private List<DataBean> data;

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public boolean isHasMore() {
            return hasMore;
        }

        public void setHasMore(boolean hasMore) {
            this.hasMore = hasMore;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public StripeResponseBean getStripeResponse() {
            return StripeResponse;
        }

        public void setStripeResponse(StripeResponseBean StripeResponse) {
            this.StripeResponse = StripeResponse;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class StripeResponseBean {
            /**
             * ResponseJson : {
             * "object": "list",
             * "data": [
             * {
             * "id": "card_1Dw1b5JgY6uDwvPUFYZz8GzN",
             * "object": "card",
             * "address_city": null,
             * "address_country": null,
             * "address_line1": null,
             * "address_line1_check": null,
             * "address_line2": null,
             * "address_state": null,
             * "address_zip": null,
             * "address_zip_check": null,
             * "brand": "Visa",
             * "country": "US",
             * "customer": "cus_EMeQhsdASxjxat",
             * "cvc_check": "pass",
             * "dynamic_last4": null,
             * "exp_month": 9,
             * "exp_year": 2020,
             * "fingerprint": "ehnt5kGNUajnCqHu",
             * "funding": "credit",
             * "last4": "4242",
             * "metadata": {
             * },
             * "name": null,
             * "tokenization_method": null
             * },
             * {
             * "id": "card_1Dw1cAJgY6uDwvPUmDGPLoA1",
             * "object": "card",
             * "address_city": null,
             * "address_country": null,
             * "address_line1": null,
             * "address_line1_check": null,
             * "address_line2": null,
             * "address_state": null,
             * "address_zip": null,
             * "address_zip_check": null,
             * "brand": "Visa",
             * "country": "US",
             * "customer": "cus_EMeQhsdASxjxat",
             * "cvc_check": "pass",
             * "dynamic_last4": null,
             * "exp_month": 9,
             * "exp_year": 2020,
             * "fingerprint": "Yi0cKtCZgw9LF4hY",
             * "funding": "unknown",
             * "last4": "1111",
             * "metadata": {
             * },
             * "name": null,
             * "tokenization_method": null
             * }
             * ],
             * "has_more": false,
             * "url": "/v1/customers/cus_EMeQhsdASxjxat/sources"
             * }
             * <p>
             * ObjectJson : {
             * "object": "list",
             * "data": [
             * {
             * "id": "card_1Dw1b5JgY6uDwvPUFYZz8GzN",
             * "object": "card",
             * "address_city": null,
             * "address_country": null,
             * "address_line1": null,
             * "address_line1_check": null,
             * "address_line2": null,
             * "address_state": null,
             * "address_zip": null,
             * "address_zip_check": null,
             * "brand": "Visa",
             * "country": "US",
             * "customer": "cus_EMeQhsdASxjxat",
             * "cvc_check": "pass",
             * "dynamic_last4": null,
             * "exp_month": 9,
             * "exp_year": 2020,
             * "fingerprint": "ehnt5kGNUajnCqHu",
             * "funding": "credit",
             * "last4": "42 01-24 11:54:09.684 24194-25250/com.app.trimcheck.customer D/OkHttp: 42",
             * "metadata": {
             * },
             * "name": null,
             * "tokenization_method": null
             * },
             * {
             * "id": "card_1Dw1cAJgY6uDwvPUmDGPLoA1",
             * "object": "card",
             * "address_city": null,
             * "address_country": null,
             * "address_line1": null,
             * "address_line1_check": null,
             * "address_line2": null,
             * "address_state": null,
             * "address_zip": null,
             * "address_zip_check": null,
             * "brand": "Visa",
             * "country": "US",
             * "customer": "cus_EMeQhsdASxjxat",
             * "cvc_check": "pass",
             * "dynamic_last4": null,
             * "exp_month": 9,
             * "exp_year": 2020,
             * "fingerprint": "Yi0cKtCZgw9LF4hY",
             * "funding": "unknown",
             * "last4": "1111",
             * "metadata": {
             * },
             * "name": null,
             * "tokenization_method": null
             * }
             * ],
             * "has_more": false,
             * "url": "/v1/customers/cus_EMeQhsdASxjxat/sources"
             * }
             * <p>
             * RequestId : req_BqQJV3VLdbjfqc
             * RequestDate : 2019-01-24T11:54:09+05:30
             */

            @SerializedName("ResponseJson")
            private String ResponseJson;
            @SerializedName("ObjectJson")
            private String ObjectJson;
            @SerializedName("RequestId")
            private String RequestId;
            @SerializedName("RequestDate")
            private String RequestDate;

            public String getResponseJson() {
                return ResponseJson;
            }

            public void setResponseJson(String ResponseJson) {
                this.ResponseJson = ResponseJson;
            }

            public String getObjectJson() {
                return ObjectJson;
            }

            public void setObjectJson(String ObjectJson) {
                this.ObjectJson = ObjectJson;
            }

            public String getRequestId() {
                return RequestId;
            }

            public void setRequestId(String RequestId) {
                this.RequestId = RequestId;
            }

            public String getRequestDate() {
                return RequestDate;
            }

            public void setRequestDate(String RequestDate) {
                this.RequestDate = RequestDate;
            }
        }

        public static class DataBean {
            /**
             * id : card_1Dw1b5JgY6uDwvPUFYZz8GzN
             * object : card
             * AccountId : null
             * address_city : null
             * address_country : null
             * address_line1 : null
             * address_line1_check : null
             * address_line2 : null
             * address_state : null
             * address_zip : null
             * address_zip_check : null
             * available_payout_methods : null
             * brand : Visa
             * country : US
             * currency : null
             * CustomerId : cus_EMeQhsdASxjxat
             * cvc_check : pass
             * default_for_currency : false
             * deleted : false
             * dynamic_last4 : null
             * exp_month : 9
             * exp_year : 2020
             * fingerprint : ehnt5kGNUajnCqHu
             * funding : credit
             * last4 : 4242
             * metadata : {}
             * name : null
             * RecipientId : null
             * three_d_secure : null
             * tokenization_method : null
             * description : null
             * iin : null
             * issuer : null
             * StripeResponse : null
             */

            @SerializedName("id")
            private String id;
            @SerializedName("object")
            private String object;
            @SerializedName("AccountId")
            private Object AccountId;
            @SerializedName("address_city")
            private Object addressCity;
            @SerializedName("address_country")
            private Object addressCountry;
            @SerializedName("address_line1")
            private Object addressLine1;
            @SerializedName("address_line1_check")
            private Object addressLine1Check;
            @SerializedName("address_line2")
            private Object addressLine2;
            @SerializedName("address_state")
            private Object addressState;
            @SerializedName("address_zip")
            private Object addressZip;
            @SerializedName("address_zip_check")
            private Object addressZipCheck;
            @SerializedName("available_payout_methods")
            private Object availablePayoutMethods;
            @SerializedName("brand")
            private String brand;
            @SerializedName("country")
            private String country;
            @SerializedName("currency")
            private Object currency;
            @SerializedName("CustomerId")
            private String CustomerId;
            @SerializedName("cvc_check")
            private String cvcCheck;
            @SerializedName("default_for_currency")
            private boolean defaultForCurrency;
            @SerializedName("deleted")
            private boolean deleted;
            @SerializedName("dynamic_last4")
            private Object dynamicLast4;
            @SerializedName("exp_month")
            private int expMonth;
            @SerializedName("exp_year")
            private int expYear;
            @SerializedName("fingerprint")
            private String fingerprint;
            @SerializedName("funding")
            private String funding;
            @SerializedName("last4")
            private String last4;
            @SerializedName("metadata")
            private MetadataBean metadata;
            @SerializedName("name")
            private Object name;
            @SerializedName("RecipientId")
            private Object RecipientId;
            @SerializedName("three_d_secure")
            private Object threeDSecure;
            @SerializedName("tokenization_method")
            private Object tokenizationMethod;
            @SerializedName("description")
            private Object description;
            @SerializedName("iin")
            private Object iin;
            @SerializedName("issuer")
            private Object issuer;
            @SerializedName("StripeResponse")
            private Object StripeResponse;
            private boolean selected;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getObject() {
                return object;
            }

            public void setObject(String object) {
                this.object = object;
            }

            public Object getAccountId() {
                return AccountId;
            }

            public void setAccountId(Object AccountId) {
                this.AccountId = AccountId;
            }

            public Object getAddressCity() {
                return addressCity;
            }

            public void setAddressCity(Object addressCity) {
                this.addressCity = addressCity;
            }

            public Object getAddressCountry() {
                return addressCountry;
            }

            public void setAddressCountry(Object addressCountry) {
                this.addressCountry = addressCountry;
            }

            public Object getAddressLine1() {
                return addressLine1;
            }

            public void setAddressLine1(Object addressLine1) {
                this.addressLine1 = addressLine1;
            }

            public Object getAddressLine1Check() {
                return addressLine1Check;
            }

            public void setAddressLine1Check(Object addressLine1Check) {
                this.addressLine1Check = addressLine1Check;
            }

            public Object getAddressLine2() {
                return addressLine2;
            }

            public void setAddressLine2(Object addressLine2) {
                this.addressLine2 = addressLine2;
            }

            public Object getAddressState() {
                return addressState;
            }

            public void setAddressState(Object addressState) {
                this.addressState = addressState;
            }

            public Object getAddressZip() {
                return addressZip;
            }

            public void setAddressZip(Object addressZip) {
                this.addressZip = addressZip;
            }

            public Object getAddressZipCheck() {
                return addressZipCheck;
            }

            public void setAddressZipCheck(Object addressZipCheck) {
                this.addressZipCheck = addressZipCheck;
            }

            public Object getAvailablePayoutMethods() {
                return availablePayoutMethods;
            }

            public void setAvailablePayoutMethods(Object availablePayoutMethods) {
                this.availablePayoutMethods = availablePayoutMethods;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public Object getCurrency() {
                return currency;
            }

            public void setCurrency(Object currency) {
                this.currency = currency;
            }

            public String getCustomerId() {
                return CustomerId;
            }

            public void setCustomerId(String CustomerId) {
                this.CustomerId = CustomerId;
            }

            public String getCvcCheck() {
                return cvcCheck;
            }

            public void setCvcCheck(String cvcCheck) {
                this.cvcCheck = cvcCheck;
            }

            public boolean isDefaultForCurrency() {
                return defaultForCurrency;
            }

            public void setDefaultForCurrency(boolean defaultForCurrency) {
                this.defaultForCurrency = defaultForCurrency;
            }

            public boolean isDeleted() {
                return deleted;
            }

            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
            }

            public Object getDynamicLast4() {
                return dynamicLast4;
            }

            public void setDynamicLast4(Object dynamicLast4) {
                this.dynamicLast4 = dynamicLast4;
            }

            public int getExpMonth() {
                return expMonth;
            }

            public void setExpMonth(int expMonth) {
                this.expMonth = expMonth;
            }

            public int getExpYear() {
                return expYear;
            }

            public void setExpYear(int expYear) {
                this.expYear = expYear;
            }

            public String getFingerprint() {
                return fingerprint;
            }

            public void setFingerprint(String fingerprint) {
                this.fingerprint = fingerprint;
            }

            public String getFunding() {
                return funding;
            }

            public void setFunding(String funding) {
                this.funding = funding;
            }

            public String getLast4() {
                return last4;
            }

            public void setLast4(String last4) {
                this.last4 = last4;
            }

            public MetadataBean getMetadata() {
                return metadata;
            }

            public void setMetadata(MetadataBean metadata) {
                this.metadata = metadata;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public Object getRecipientId() {
                return RecipientId;
            }

            public void setRecipientId(Object RecipientId) {
                this.RecipientId = RecipientId;
            }

            public Object getThreeDSecure() {
                return threeDSecure;
            }

            public void setThreeDSecure(Object threeDSecure) {
                this.threeDSecure = threeDSecure;
            }

            public Object getTokenizationMethod() {
                return tokenizationMethod;
            }

            public void setTokenizationMethod(Object tokenizationMethod) {
                this.tokenizationMethod = tokenizationMethod;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public Object getIin() {
                return iin;
            }

            public void setIin(Object iin) {
                this.iin = iin;
            }

            public Object getIssuer() {
                return issuer;
            }

            public void setIssuer(Object issuer) {
                this.issuer = issuer;
            }

            public Object getStripeResponse() {
                return StripeResponse;
            }

            public void setStripeResponse(Object StripeResponse) {
                this.StripeResponse = StripeResponse;
            }

            public boolean isSelected() {
                return selected;
            }

            public void setSelected(boolean selected) {
                this.selected = selected;
            }

            public static class MetadataBean {
            }
        }
    }
}
