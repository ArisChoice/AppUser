package com.app.barber.util.bus_event;

/**
 * Created by harish on 31/1/19.
 */

public class CustomEvent<T> {
    public int getType() {
        return type;
    }

    int type;

    public Object getOj() {
        return oj;
    }

    Object oj;

    public CustomEvent(int type, Object oj) {
        this.type = type;
        this.oj = oj;
    }
}
