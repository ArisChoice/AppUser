package com.app.barber.models.request;

import java.util.List;

/**
 * Created by harish on 1/11/18.
 */

public class HoursModel {

        String Day;
        String OpeningHours;

        public String getDay() {
            return Day;
        }

        public void setDay(String day) {
            Day = day;
        }

        public String getOpeningHours() {
            return OpeningHours;
        }

        public void setOpeningHours(String openingHours) {
            OpeningHours = openingHours;
        }

        public String getClosingHours() {
            return ClosingHours;
        }

        public void setClosingHours(String closingHours) {
            ClosingHours = closingHours;
        }

        String ClosingHours;

    }

