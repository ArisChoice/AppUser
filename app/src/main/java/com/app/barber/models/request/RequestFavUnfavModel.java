package com.app.barber.models.request;

/**
 * Created by harish on 20/12/18.
 */

public class RequestFavUnfavModel {
    public int getBarberId() {
        return BarberId;
    }

    public void setBarberId(int barberId) {
        BarberId = barberId;
    }

    public boolean isAction() {
        return Action;
    }

    public void setAction(boolean action) {
        Action = action;
    }

    int BarberId;
    boolean Action;
}
