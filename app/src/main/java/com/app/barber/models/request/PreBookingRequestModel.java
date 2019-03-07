package com.app.barber.models.request;

import java.io.Serializable;

/**
 * Created by harish on 17/12/18.
 */

public class PreBookingRequestModel implements Serializable {
    public String getServiceId() {
        return ServiceId;
    }

    public void setServiceId(String serviceId) {
        ServiceId = serviceId;
    }

    public int getBarberId() {
        return BarberId;
    }

    public void setBarberId(int barberId) {
        BarberId = barberId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTimingSlot() {
        return TimingSlot;
    }

    public void setTimingSlot(String timingSlot) {
        TimingSlot = timingSlot;
    }

    String ServiceId;
    int BarberId;

    public String getBookingType() {
        return BookingType;
    }

    public void setBookingType(String bookingType) {
        BookingType = bookingType;
    }

    String BookingType;
    String Date;//MM/dd/yyyy
    String TimingSlot;

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    String Amount;

    public String getPaymentMode() {
        return PaymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        PaymentMode = paymentMode;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(String transactionId) {
        TransactionId = transactionId;
    }

    public String getReceivedAmount() {
        return ReceivedAmount;
    }

    public void setReceivedAmount(String receivedAmount) {
        ReceivedAmount = receivedAmount;
    }

    String PaymentMode;
    String TransactionId;
    String ReceivedAmount;

    public String getCardId() {
        return CardId;
    }

    public void setCardId(String cardId) {
        CardId = cardId;
    }

    String CardId;

    public String getAmountToCharge() {
        return AmountToCharge;
    }

    public void setAmountToCharge(String amountToCharge) {
        AmountToCharge = amountToCharge;
    }

    String AmountToCharge;


}
