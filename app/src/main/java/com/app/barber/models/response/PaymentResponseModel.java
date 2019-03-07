package com.app.barber.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by harish on 28/12/18.
 */

public class PaymentResponseModel {

    /**
     * Message : Success
     * Status : 201
     * Charge : {"id":"ch_1DmEaLJgY6uDwvPUOy8KauwM","object":"charge","amount":6500,"amount_refunded":0,"ApplicationId":null,"ApplicationFeeId":null,"BalanceTransactionId":"txn_1DmEaLJgY6uDwvPUurM5YhwM","captured":true,"created":1545977561,"currency":"usd","CustomerId":null,"description":"testing","DestinationId":null,"DisputeId":null,"failure_code":null,"failure_message":null,"fraud_details":{},"InvoiceId":null,"livemode":false,"metadata":{},"OnBehalfOfId":null,"OrderId":null,"level3":null,"outcome":{"network_status":"approved_by_network","reason":null,"risk_level":"normal","risk_score":20,"RuleId":null,"seller_message":"Payment complete.","type":"authorized","StripeResponse":null},"paid":true,"PaymentIntentId":null,"receipt_email":null,"receipt_number":null,"refunded":false,"refunds":{"object":"list","data":[],"has_more":false,"total_count":0,"url":"/v1/charges/ch_1DmEaLJgY6uDwvPUOy8KauwM/refunds","StripeResponse":null},"ReviewId":null,"shipping":null,"source":{"id":"card_1DmEaJJgY6uDwvPU2JMrSfV7","object":"card","AccountId":null,"address_city":null,"address_country":null,"address_line1":null,"address_line1_check":null,"address_line2":null,"address_state":null,"address_zip":null,"address_zip_check":null,"available_payout_methods":null,"brand":"Visa","country":"US","currency":null,"CustomerId":null,"cvc_check":"pass","default_for_currency":false,"deleted":false,"dynamic_last4":null,"exp_month":8,"exp_year":2020,"fingerprint":"Yi0cKtCZgw9LF4hY","funding":"unknown","last4":"1111","metadata":{},"name":null,"RecipientId":null,"three_d_secure":null,"tokenization_method":null,"description":null,"iin":null,"issuer":null,"StripeResponse":null},"SourceTransferId":null,"statement_descriptor":null,"status":"succeeded","TransferId":null,"transfer_group":null,"authorization_code":null,"StripeResponse":{"ResponseJson":"{\n  \"id\": \"ch_1DmEaLJgY6uDwvPUOy8KauwM\",\n  \"object\": \"charge\",\n  \"amount\": 6500,\n  \"amount_refunded\": 0,\n  \"application\": null,\n  \"application_fee\": null,\n  \"balance_transaction\": \"txn_1DmEaLJgY6uDwvPUurM5YhwM\",\n  \"captured\": true,\n  \"created\": 1545977561,\n  \"currency\": \"usd\",\n  \"customer\": null,\n  \"description\": \"testing\",\n  \"destination\": null,\n  \"dispute\": null,\n  \"failure_code\": null,\n  \"failure_message\": null,\n  \"fraud_details\": {\n  },\n  \"invoice\": null,\n  \"livemode\": false,\n  \"metadata\": {\n  },\n  \"on_behalf_of\": null,\n  \"order\": null,\n  \"outcome\": {\n    \"network_status\": \"approved_by_network\",\n    \"reason\": null,\n    \"risk_level\": \"normal\",\n    \"risk_score\": 20,\n    \"seller_message\": \"Payment complete.\",\n    \"type\": \"authorized\"\n  },\n  \"paid\": true,\n  \"payment_intent\": null,\n  \"receipt_email\": null,\n  \"receipt_number\": null,\n  \"refunded\": false,\n  \"refunds\": {\n    \"object\": \"list\",\n    \"data\": [\n\n    ],\n    \"has_more\": false,\n    \"total_count\": 0,\n    \"url\": \"/v1/charges/ch_1DmEaLJgY6uDwvPUOy8KauwM/refunds\"\n  },\n  \"review\": null,\n  \"shipping\": null,\n  \"source\": {\n    \"id\": \"card_1DmEaJJgY6uDwvPU2JMrSfV7\",\n    \"object\": \"card\",\n    \"address_city\": null,\n    \"address_country\": null,\n    \"address_line1\": null,\n    \"address_line1_check\": null,\n    \"address_line2\": null,\n    \"address_state\": null,\n    \"address_zip\": null,\n    \"address_zip_check\": null,\n    \"brand\": \"Visa\",\n    \"country\": \"US\",\n    \"customer\": null,\n    \"cvc_check\": \"pass\",\n    \"dynamic_last4\": null,\n    \"exp_month\": 8,\n    \"exp_year\": 2020,\n    \"fingerprint\": \"Yi0cKtCZgw9LF4hY\",\n    \"funding\": \"unknown\",\n    \"last4\": \"1111\",\n    \"metadata\": {\n    },\n    \"name\": null,\n    \"tokenization_method\": null\n  },\n  \"source_transfer\": null,\n  \"statement_descriptor\": null,\n  \"status\": \"succeeded\",\n  \"transfer_group\": null\n}\n","ObjectJson":"{\n  \"id\": \"ch_1DmEaLJgY6uDwvPUOy8KauwM\",\n  12-28 11:42:41.581 626-4883/com.app.trimcheck.customer D/OkHttp:  \"object\": \"charge\",\n  \"amount\": 6500,\n  \"amount_refunded\": 0,\n  \"application\": null,\n  \"application_fee\": null,\n  \"balance_transaction\": \"txn_1DmEaLJgY6uDwvPUurM5YhwM\",\n  \"captured\": true,\n  \"created\": 1545977561,\n  \"currency\": \"usd\",\n  \"customer\": null,\n  \"description\": \"testing\",\n  \"destination\": null,\n  \"dispute\": null,\n  \"failure_code\": null,\n  \"failure_message\": null,\n  \"fraud_details\": {\n  },\n  \"invoice\": null,\n  \"livemode\": false,\n  \"metadata\": {\n  },\n  \"on_behalf_of\": null,\n  \"order\": null,\n  \"outcome\": {\n    \"network_status\": \"approved_by_network\",\n    \"reason\": null,\n    \"risk_level\": \"normal\",\n    \"risk_score\": 20,\n    \"seller_message\": \"Payment complete.\",\n    \"type\": \"authorized\"\n  },\n  \"paid\": true,\n  \"payment_intent\": null,\n  \"receipt_email\": null,\n  \"receipt_number\": null,\n  \"refunded\": false,\n  \"refunds\": {\n    \"object\": \"list\",\n    \"data\": [\n\n    ],\n    \"has_more\": false,\n    \"total_count\": 0,\n    \"url\": \"/v1/charges/ch_1DmEaLJgY6uDwvPUOy8KauwM/refunds\"\n  },\n  \"review\": null,\n  \"shipping\": null,\n  \"source\": {\n    \"id\": \"card_1DmEaJJgY6uDwvPU2JMrSfV7\",\n    \"object\": \"card\",\n    \"address_city\": null,\n    \"address_country\": null,\n    \"address_line1\": null,\n    \"address_line1_check\": null,\n    \"address_line2\": null,\n    \"address_state\": null,\n    \"address_zip\": null,\n    \"address_zip_check\": null,\n    \"brand\": \"Visa\",\n    \"country\": \"US\",\n    \"customer\": null,\n    \"cvc_check\": \"pass\",\n    \"dynamic_last4\": null,\n    \"exp_month\": 8,\n    \"exp_year\": 2020,\n    \"fingerprint\": \"Yi0cKtCZgw9LF4hY\",\n    \"funding\": \"unknown\",\n    \"last4\": \"1111\",\n    \"metadata\": {\n    },\n    \"name\": null,\n    \"tokenization_method\": null\n  },\n  \"source_transfer\": null,\n  \"statement_descriptor\": null,\n  \"status\": \"succeeded\",\n  \"transfer_group\": null\n}\n","RequestId":"req_Yh4YFcuPtwdsMv","RequestDate":"2018-12-28T11:42:41+05:30"}}
     */

    @SerializedName("Message")
    private String Message;
    @SerializedName("Status")
    private int Status;
    @SerializedName("Charge")
    private ChargeBean Charge;

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

    public ChargeBean getCharge() {
        return Charge;
    }

    public void setCharge(ChargeBean Charge) {
        this.Charge = Charge;
    }

    public static class ChargeBean {
        /**
         * id : ch_1DmEaLJgY6uDwvPUOy8KauwM
         * object : charge
         * amount : 6500
         * amount_refunded : 0
         * ApplicationId : null
         * ApplicationFeeId : null
         * BalanceTransactionId : txn_1DmEaLJgY6uDwvPUurM5YhwM
         * captured : true
         * created : 1545977561
         * currency : usd
         * CustomerId : null
         * description : testing
         * DestinationId : null
         * DisputeId : null
         * failure_code : null
         * failure_message : null
         * fraud_details : {}
         * InvoiceId : null
         * livemode : false
         * metadata : {}
         * OnBehalfOfId : null
         * OrderId : null
         * level3 : null
         * outcome : {"network_status":"approved_by_network","reason":null,"risk_level":"normal","risk_score":20,"RuleId":null,"seller_message":"Payment complete.","type":"authorized","StripeResponse":null}
         * paid : true
         * PaymentIntentId : null
         * receipt_email : null
         * receipt_number : null
         * refunded : false
         * refunds : {"object":"list","data":[],"has_more":false,"total_count":0,"url":"/v1/charges/ch_1DmEaLJgY6uDwvPUOy8KauwM/refunds","StripeResponse":null}
         * ReviewId : null
         * shipping : null
         * source : {"id":"card_1DmEaJJgY6uDwvPU2JMrSfV7","object":"card","AccountId":null,"address_city":null,"address_country":null,"address_line1":null,"address_line1_check":null,"address_line2":null,"address_state":null,"address_zip":null,"address_zip_check":null,"available_payout_methods":null,"brand":"Visa","country":"US","currency":null,"CustomerId":null,"cvc_check":"pass","default_for_currency":false,"deleted":false,"dynamic_last4":null,"exp_month":8,"exp_year":2020,"fingerprint":"Yi0cKtCZgw9LF4hY","funding":"unknown","last4":"1111","metadata":{},"name":null,"RecipientId":null,"three_d_secure":null,"tokenization_method":null,"description":null,"iin":null,"issuer":null,"StripeResponse":null}
         * SourceTransferId : null
         * statement_descriptor : null
         * status : succeeded
         * TransferId : null
         * transfer_group : null
         * authorization_code : null
         * StripeResponse : {"ResponseJson":"{\n  \"id\": \"ch_1DmEaLJgY6uDwvPUOy8KauwM\",\n  \"object\": \"charge\",\n  \"amount\": 6500,\n  \"amount_refunded\": 0,\n  \"application\": null,\n  \"application_fee\": null,\n  \"balance_transaction\": \"txn_1DmEaLJgY6uDwvPUurM5YhwM\",\n  \"captured\": true,\n  \"created\": 1545977561,\n  \"currency\": \"usd\",\n  \"customer\": null,\n  \"description\": \"testing\",\n  \"destination\": null,\n  \"dispute\": null,\n  \"failure_code\": null,\n  \"failure_message\": null,\n  \"fraud_details\": {\n  },\n  \"invoice\": null,\n  \"livemode\": false,\n  \"metadata\": {\n  },\n  \"on_behalf_of\": null,\n  \"order\": null,\n  \"outcome\": {\n    \"network_status\": \"approved_by_network\",\n    \"reason\": null,\n    \"risk_level\": \"normal\",\n    \"risk_score\": 20,\n    \"seller_message\": \"Payment complete.\",\n    \"type\": \"authorized\"\n  },\n  \"paid\": true,\n  \"payment_intent\": null,\n  \"receipt_email\": null,\n  \"receipt_number\": null,\n  \"refunded\": false,\n  \"refunds\": {\n    \"object\": \"list\",\n    \"data\": [\n\n    ],\n    \"has_more\": false,\n    \"total_count\": 0,\n    \"url\": \"/v1/charges/ch_1DmEaLJgY6uDwvPUOy8KauwM/refunds\"\n  },\n  \"review\": null,\n  \"shipping\": null,\n  \"source\": {\n    \"id\": \"card_1DmEaJJgY6uDwvPU2JMrSfV7\",\n    \"object\": \"card\",\n    \"address_city\": null,\n    \"address_country\": null,\n    \"address_line1\": null,\n    \"address_line1_check\": null,\n    \"address_line2\": null,\n    \"address_state\": null,\n    \"address_zip\": null,\n    \"address_zip_check\": null,\n    \"brand\": \"Visa\",\n    \"country\": \"US\",\n    \"customer\": null,\n    \"cvc_check\": \"pass\",\n    \"dynamic_last4\": null,\n    \"exp_month\": 8,\n    \"exp_year\": 2020,\n    \"fingerprint\": \"Yi0cKtCZgw9LF4hY\",\n    \"funding\": \"unknown\",\n    \"last4\": \"1111\",\n    \"metadata\": {\n    },\n    \"name\": null,\n    \"tokenization_method\": null\n  },\n  \"source_transfer\": null,\n  \"statement_descriptor\": null,\n  \"status\": \"succeeded\",\n  \"transfer_group\": null\n}\n","ObjectJson":"{\n  \"id\": \"ch_1DmEaLJgY6uDwvPUOy8KauwM\",\n  12-28 11:42:41.581 626-4883/com.app.trimcheck.customer D/OkHttp:  \"object\": \"charge\",\n  \"amount\": 6500,\n  \"amount_refunded\": 0,\n  \"application\": null,\n  \"application_fee\": null,\n  \"balance_transaction\": \"txn_1DmEaLJgY6uDwvPUurM5YhwM\",\n  \"captured\": true,\n  \"created\": 1545977561,\n  \"currency\": \"usd\",\n  \"customer\": null,\n  \"description\": \"testing\",\n  \"destination\": null,\n  \"dispute\": null,\n  \"failure_code\": null,\n  \"failure_message\": null,\n  \"fraud_details\": {\n  },\n  \"invoice\": null,\n  \"livemode\": false,\n  \"metadata\": {\n  },\n  \"on_behalf_of\": null,\n  \"order\": null,\n  \"outcome\": {\n    \"network_status\": \"approved_by_network\",\n    \"reason\": null,\n    \"risk_level\": \"normal\",\n    \"risk_score\": 20,\n    \"seller_message\": \"Payment complete.\",\n    \"type\": \"authorized\"\n  },\n  \"paid\": true,\n  \"payment_intent\": null,\n  \"receipt_email\": null,\n  \"receipt_number\": null,\n  \"refunded\": false,\n  \"refunds\": {\n    \"object\": \"list\",\n    \"data\": [\n\n    ],\n    \"has_more\": false,\n    \"total_count\": 0,\n    \"url\": \"/v1/charges/ch_1DmEaLJgY6uDwvPUOy8KauwM/refunds\"\n  },\n  \"review\": null,\n  \"shipping\": null,\n  \"source\": {\n    \"id\": \"card_1DmEaJJgY6uDwvPU2JMrSfV7\",\n    \"object\": \"card\",\n    \"address_city\": null,\n    \"address_country\": null,\n    \"address_line1\": null,\n    \"address_line1_check\": null,\n    \"address_line2\": null,\n    \"address_state\": null,\n    \"address_zip\": null,\n    \"address_zip_check\": null,\n    \"brand\": \"Visa\",\n    \"country\": \"US\",\n    \"customer\": null,\n    \"cvc_check\": \"pass\",\n    \"dynamic_last4\": null,\n    \"exp_month\": 8,\n    \"exp_year\": 2020,\n    \"fingerprint\": \"Yi0cKtCZgw9LF4hY\",\n    \"funding\": \"unknown\",\n    \"last4\": \"1111\",\n    \"metadata\": {\n    },\n    \"name\": null,\n    \"tokenization_method\": null\n  },\n  \"source_transfer\": null,\n  \"statement_descriptor\": null,\n  \"status\": \"succeeded\",\n  \"transfer_group\": null\n}\n","RequestId":"req_Yh4YFcuPtwdsMv","RequestDate":"2018-12-28T11:42:41+05:30"}
         */

        @SerializedName("id")
        private String id;
        @SerializedName("object")
        private String object;
        @SerializedName("amount")
        private int amount;
        @SerializedName("amount_refunded")
        private int amountRefunded;
        @SerializedName("ApplicationId")
        private Object ApplicationId;
        @SerializedName("ApplicationFeeId")
        private Object ApplicationFeeId;
        @SerializedName("BalanceTransactionId")
        private String BalanceTransactionId;
        @SerializedName("captured")
        private boolean captured;
        @SerializedName("created")
        private int created;
        @SerializedName("currency")
        private String currency;
        @SerializedName("CustomerId")
        private Object CustomerId;
        @SerializedName("description")
        private String description;
        @SerializedName("DestinationId")
        private Object DestinationId;
        @SerializedName("DisputeId")
        private Object DisputeId;
        @SerializedName("failure_code")
        private Object failureCode;
        @SerializedName("failure_message")
        private Object failureMessage;
        @SerializedName("fraud_details")
        private FraudDetailsBean fraudDetails;
        @SerializedName("InvoiceId")
        private Object InvoiceId;
        @SerializedName("livemode")
        private boolean livemode;
        @SerializedName("metadata")
        private MetadataBean metadata;
        @SerializedName("OnBehalfOfId")
        private Object OnBehalfOfId;
        @SerializedName("OrderId")
        private Object OrderId;
        @SerializedName("level3")
        private Object level3;
        @SerializedName("outcome")
        private OutcomeBean outcome;
        @SerializedName("paid")
        private boolean paid;
        @SerializedName("PaymentIntentId")
        private Object PaymentIntentId;
        @SerializedName("receipt_email")
        private Object receiptEmail;
        @SerializedName("receipt_number")
        private Object receiptNumber;
        @SerializedName("refunded")
        private boolean refunded;
        @SerializedName("refunds")
        private RefundsBean refunds;
        @SerializedName("ReviewId")
        private Object ReviewId;
        @SerializedName("shipping")
        private Object shipping;
        @SerializedName("source")
        private SourceBean source;
        @SerializedName("SourceTransferId")
        private Object SourceTransferId;
        @SerializedName("statement_descriptor")
        private Object statementDescriptor;
        @SerializedName("status")
        private String status;
        @SerializedName("TransferId")
        private Object TransferId;
        @SerializedName("transfer_group")
        private Object transferGroup;
        @SerializedName("authorization_code")
        private Object authorizationCode;
        @SerializedName("StripeResponse")
        private StripeResponseBean StripeResponse;

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

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAmountRefunded() {
            return amountRefunded;
        }

        public void setAmountRefunded(int amountRefunded) {
            this.amountRefunded = amountRefunded;
        }

        public Object getApplicationId() {
            return ApplicationId;
        }

        public void setApplicationId(Object ApplicationId) {
            this.ApplicationId = ApplicationId;
        }

        public Object getApplicationFeeId() {
            return ApplicationFeeId;
        }

        public void setApplicationFeeId(Object ApplicationFeeId) {
            this.ApplicationFeeId = ApplicationFeeId;
        }

        public String getBalanceTransactionId() {
            return BalanceTransactionId;
        }

        public void setBalanceTransactionId(String BalanceTransactionId) {
            this.BalanceTransactionId = BalanceTransactionId;
        }

        public boolean isCaptured() {
            return captured;
        }

        public void setCaptured(boolean captured) {
            this.captured = captured;
        }

        public int getCreated() {
            return created;
        }

        public void setCreated(int created) {
            this.created = created;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public Object getCustomerId() {
            return CustomerId;
        }

        public void setCustomerId(Object CustomerId) {
            this.CustomerId = CustomerId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getDestinationId() {
            return DestinationId;
        }

        public void setDestinationId(Object DestinationId) {
            this.DestinationId = DestinationId;
        }

        public Object getDisputeId() {
            return DisputeId;
        }

        public void setDisputeId(Object DisputeId) {
            this.DisputeId = DisputeId;
        }

        public Object getFailureCode() {
            return failureCode;
        }

        public void setFailureCode(Object failureCode) {
            this.failureCode = failureCode;
        }

        public Object getFailureMessage() {
            return failureMessage;
        }

        public void setFailureMessage(Object failureMessage) {
            this.failureMessage = failureMessage;
        }

        public FraudDetailsBean getFraudDetails() {
            return fraudDetails;
        }

        public void setFraudDetails(FraudDetailsBean fraudDetails) {
            this.fraudDetails = fraudDetails;
        }

        public Object getInvoiceId() {
            return InvoiceId;
        }

        public void setInvoiceId(Object InvoiceId) {
            this.InvoiceId = InvoiceId;
        }

        public boolean isLivemode() {
            return livemode;
        }

        public void setLivemode(boolean livemode) {
            this.livemode = livemode;
        }

        public MetadataBean getMetadata() {
            return metadata;
        }

        public void setMetadata(MetadataBean metadata) {
            this.metadata = metadata;
        }

        public Object getOnBehalfOfId() {
            return OnBehalfOfId;
        }

        public void setOnBehalfOfId(Object OnBehalfOfId) {
            this.OnBehalfOfId = OnBehalfOfId;
        }

        public Object getOrderId() {
            return OrderId;
        }

        public void setOrderId(Object OrderId) {
            this.OrderId = OrderId;
        }

        public Object getLevel3() {
            return level3;
        }

        public void setLevel3(Object level3) {
            this.level3 = level3;
        }

        public OutcomeBean getOutcome() {
            return outcome;
        }

        public void setOutcome(OutcomeBean outcome) {
            this.outcome = outcome;
        }

        public boolean isPaid() {
            return paid;
        }

        public void setPaid(boolean paid) {
            this.paid = paid;
        }

        public Object getPaymentIntentId() {
            return PaymentIntentId;
        }

        public void setPaymentIntentId(Object PaymentIntentId) {
            this.PaymentIntentId = PaymentIntentId;
        }

        public Object getReceiptEmail() {
            return receiptEmail;
        }

        public void setReceiptEmail(Object receiptEmail) {
            this.receiptEmail = receiptEmail;
        }

        public Object getReceiptNumber() {
            return receiptNumber;
        }

        public void setReceiptNumber(Object receiptNumber) {
            this.receiptNumber = receiptNumber;
        }

        public boolean isRefunded() {
            return refunded;
        }

        public void setRefunded(boolean refunded) {
            this.refunded = refunded;
        }

        public RefundsBean getRefunds() {
            return refunds;
        }

        public void setRefunds(RefundsBean refunds) {
            this.refunds = refunds;
        }

        public Object getReviewId() {
            return ReviewId;
        }

        public void setReviewId(Object ReviewId) {
            this.ReviewId = ReviewId;
        }

        public Object getShipping() {
            return shipping;
        }

        public void setShipping(Object shipping) {
            this.shipping = shipping;
        }

        public SourceBean getSource() {
            return source;
        }

        public void setSource(SourceBean source) {
            this.source = source;
        }

        public Object getSourceTransferId() {
            return SourceTransferId;
        }

        public void setSourceTransferId(Object SourceTransferId) {
            this.SourceTransferId = SourceTransferId;
        }

        public Object getStatementDescriptor() {
            return statementDescriptor;
        }

        public void setStatementDescriptor(Object statementDescriptor) {
            this.statementDescriptor = statementDescriptor;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getTransferId() {
            return TransferId;
        }

        public void setTransferId(Object TransferId) {
            this.TransferId = TransferId;
        }

        public Object getTransferGroup() {
            return transferGroup;
        }

        public void setTransferGroup(Object transferGroup) {
            this.transferGroup = transferGroup;
        }

        public Object getAuthorizationCode() {
            return authorizationCode;
        }

        public void setAuthorizationCode(Object authorizationCode) {
            this.authorizationCode = authorizationCode;
        }

        public StripeResponseBean getStripeResponse() {
            return StripeResponse;
        }

        public void setStripeResponse(StripeResponseBean StripeResponse) {
            this.StripeResponse = StripeResponse;
        }

        public static class FraudDetailsBean {
        }

        public static class MetadataBean {
        }

        public static class OutcomeBean {
            /**
             * network_status : approved_by_network
             * reason : null
             * risk_level : normal
             * risk_score : 20
             * RuleId : null
             * seller_message : Payment complete.
             * type : authorized
             * StripeResponse : null
             */

            @SerializedName("network_status")
            private String networkStatus;
            @SerializedName("reason")
            private Object reason;
            @SerializedName("risk_level")
            private String riskLevel;
            @SerializedName("risk_score")
            private int riskScore;
            @SerializedName("RuleId")
            private Object RuleId;
            @SerializedName("seller_message")
            private String sellerMessage;
            @SerializedName("type")
            private String type;
            @SerializedName("StripeResponse")
            private Object StripeResponse;

            public String getNetworkStatus() {
                return networkStatus;
            }

            public void setNetworkStatus(String networkStatus) {
                this.networkStatus = networkStatus;
            }

            public Object getReason() {
                return reason;
            }

            public void setReason(Object reason) {
                this.reason = reason;
            }

            public String getRiskLevel() {
                return riskLevel;
            }

            public void setRiskLevel(String riskLevel) {
                this.riskLevel = riskLevel;
            }

            public int getRiskScore() {
                return riskScore;
            }

            public void setRiskScore(int riskScore) {
                this.riskScore = riskScore;
            }

            public Object getRuleId() {
                return RuleId;
            }

            public void setRuleId(Object RuleId) {
                this.RuleId = RuleId;
            }

            public String getSellerMessage() {
                return sellerMessage;
            }

            public void setSellerMessage(String sellerMessage) {
                this.sellerMessage = sellerMessage;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getStripeResponse() {
                return StripeResponse;
            }

            public void setStripeResponse(Object StripeResponse) {
                this.StripeResponse = StripeResponse;
            }
        }

        public static class RefundsBean {
            /**
             * object : list
             * data : []
             * has_more : false
             * total_count : 0
             * url : /v1/charges/ch_1DmEaLJgY6uDwvPUOy8KauwM/refunds
             * StripeResponse : null
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
            private Object StripeResponse;
            @SerializedName("data")
            private List<?> data;

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

            public Object getStripeResponse() {
                return StripeResponse;
            }

            public void setStripeResponse(Object StripeResponse) {
                this.StripeResponse = StripeResponse;
            }

            public List<?> getData() {
                return data;
            }

            public void setData(List<?> data) {
                this.data = data;
            }
        }

        public static class SourceBean {
            /**
             * id : card_1DmEaJJgY6uDwvPU2JMrSfV7
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
             * CustomerId : null
             * cvc_check : pass
             * default_for_currency : false
             * deleted : false
             * dynamic_last4 : null
             * exp_month : 8
             * exp_year : 2020
             * fingerprint : Yi0cKtCZgw9LF4hY
             * funding : unknown
             * last4 : 1111
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
            private Object CustomerId;
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
            private MetadataBeanX metadata;
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

            public Object getCustomerId() {
                return CustomerId;
            }

            public void setCustomerId(Object CustomerId) {
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

            public MetadataBeanX getMetadata() {
                return metadata;
            }

            public void setMetadata(MetadataBeanX metadata) {
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

            public static class MetadataBeanX {
            }
        }

        public static class StripeResponseBean {
            /**
             * ResponseJson : {
             "id": "ch_1DmEaLJgY6uDwvPUOy8KauwM",
             "object": "charge",
             "amount": 6500,
             "amount_refunded": 0,
             "application": null,
             "application_fee": null,
             "balance_transaction": "txn_1DmEaLJgY6uDwvPUurM5YhwM",
             "captured": true,
             "created": 1545977561,
             "currency": "usd",
             "customer": null,
             "description": "testing",
             "destination": null,
             "dispute": null,
             "failure_code": null,
             "failure_message": null,
             "fraud_details": {
             },
             "invoice": null,
             "livemode": false,
             "metadata": {
             },
             "on_behalf_of": null,
             "order": null,
             "outcome": {
             "network_status": "approved_by_network",
             "reason": null,
             "risk_level": "normal",
             "risk_score": 20,
             "seller_message": "Payment complete.",
             "type": "authorized"
             },
             "paid": true,
             "payment_intent": null,
             "receipt_email": null,
             "receipt_number": null,
             "refunded": false,
             "refunds": {
             "object": "list",
             "data": [

             ],
             "has_more": false,
             "total_count": 0,
             "url": "/v1/charges/ch_1DmEaLJgY6uDwvPUOy8KauwM/refunds"
             },
             "review": null,
             "shipping": null,
             "source": {
             "id": "card_1DmEaJJgY6uDwvPU2JMrSfV7",
             "object": "card",
             "address_city": null,
             "address_country": null,
             "address_line1": null,
             "address_line1_check": null,
             "address_line2": null,
             "address_state": null,
             "address_zip": null,
             "address_zip_check": null,
             "brand": "Visa",
             "country": "US",
             "customer": null,
             "cvc_check": "pass",
             "dynamic_last4": null,
             "exp_month": 8,
             "exp_year": 2020,
             "fingerprint": "Yi0cKtCZgw9LF4hY",
             "funding": "unknown",
             "last4": "1111",
             "metadata": {
             },
             "name": null,
             "tokenization_method": null
             },
             "source_transfer": null,
             "statement_descriptor": null,
             "status": "succeeded",
             "transfer_group": null
             }

             * ObjectJson : {
             "id": "ch_1DmEaLJgY6uDwvPUOy8KauwM",
             12-28 11:42:41.581 626-4883/com.app.trimcheck.customer D/OkHttp:  "object": "charge",
             "amount": 6500,
             "amount_refunded": 0,
             "application": null,
             "application_fee": null,
             "balance_transaction": "txn_1DmEaLJgY6uDwvPUurM5YhwM",
             "captured": true,
             "created": 1545977561,
             "currency": "usd",
             "customer": null,
             "description": "testing",
             "destination": null,
             "dispute": null,
             "failure_code": null,
             "failure_message": null,
             "fraud_details": {
             },
             "invoice": null,
             "livemode": false,
             "metadata": {
             },
             "on_behalf_of": null,
             "order": null,
             "outcome": {
             "network_status": "approved_by_network",
             "reason": null,
             "risk_level": "normal",
             "risk_score": 20,
             "seller_message": "Payment complete.",
             "type": "authorized"
             },
             "paid": true,
             "payment_intent": null,
             "receipt_email": null,
             "receipt_number": null,
             "refunded": false,
             "refunds": {
             "object": "list",
             "data": [

             ],
             "has_more": false,
             "total_count": 0,
             "url": "/v1/charges/ch_1DmEaLJgY6uDwvPUOy8KauwM/refunds"
             },
             "review": null,
             "shipping": null,
             "source": {
             "id": "card_1DmEaJJgY6uDwvPU2JMrSfV7",
             "object": "card",
             "address_city": null,
             "address_country": null,
             "address_line1": null,
             "address_line1_check": null,
             "address_line2": null,
             "address_state": null,
             "address_zip": null,
             "address_zip_check": null,
             "brand": "Visa",
             "country": "US",
             "customer": null,
             "cvc_check": "pass",
             "dynamic_last4": null,
             "exp_month": 8,
             "exp_year": 2020,
             "fingerprint": "Yi0cKtCZgw9LF4hY",
             "funding": "unknown",
             "last4": "1111",
             "metadata": {
             },
             "name": null,
             "tokenization_method": null
             },
             "source_transfer": null,
             "statement_descriptor": null,
             "status": "succeeded",
             "transfer_group": null
             }

             * RequestId : req_Yh4YFcuPtwdsMv
             * RequestDate : 2018-12-28T11:42:41+05:30
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
    }
}
