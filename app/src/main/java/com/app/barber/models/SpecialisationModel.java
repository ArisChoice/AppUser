package com.app.barber.models;

/**
 * Created by harish on 1/11/18.
 */

public class SpecialisationModel {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    String name;
    String id;
    boolean isSelected;
}
