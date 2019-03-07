package com.app.barber.models.request;

/**
 * Created by harish on 27/12/18.
 */

public class BookPaymentRequestModel {
    public String getStripeToken() {
        return StripeToken;
    }

    public void setStripeToken(String stripeToken) {
        StripeToken = stripeToken;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    String StripeToken;

    public String getCardId() {
        return CardId;
    }

    public void setCardId(String cardId) {
        CardId = cardId;
    }

    String CardId;
    int Amount;


}
