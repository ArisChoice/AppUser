package com.app.barber.util.bus_event;

public class Message{
    private final String message;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}