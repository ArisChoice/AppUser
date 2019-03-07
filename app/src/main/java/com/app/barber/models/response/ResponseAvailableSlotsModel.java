package com.app.barber.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harish on 17/12/18.
 */

public class ResponseAvailableSlotsModel {

    /**
     * Message : TimeSlotList
     * Status : 201
     * List : ["09:00 AM-09:50 AM","09:50 AM-10:40 AM","10:40 AM-11:30 AM","11:30 AM-12:20 PM","03:40 PM-04:30 PM","04:30 PM-05:20 PM","05:20 PM-06:10 PM"]
     */

    @SerializedName("Message")
    private String Message;
    @SerializedName("Status")
    private int Status;
    @SerializedName("List")
    private ArrayList<String> List;

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

    public ArrayList<String> getList() {
        return List;
    }

    public void setList(ArrayList<String> List) {
        this.List = List;
    }
}
