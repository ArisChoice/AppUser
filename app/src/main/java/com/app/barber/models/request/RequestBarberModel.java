package com.app.barber.models.request;

/**
 * Created by harish on 26/11/18.
 */

public class RequestBarberModel {



    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLong() {
        return Long;
    }

    public void setLong(String aLong) {
        Long = aLong;
    }

    String Lat;
    String Long;

    public String getSearch() {
        return Search;
    }

    public void setSearch(String search) {
        Search = search;
    }

    String Search;
    int PageNo;

    public int getPageNo() {
        return PageNo;
    }

    public void setPageNo(int pageNo) {
        PageNo = pageNo;
    }

    public String getRecordsPerPage() {
        return RecordsPerPage;
    }

    public void setRecordsPerPage(String recordsPerPage) {
        RecordsPerPage = recordsPerPage;
    }

    String RecordsPerPage;

    public Filter getFilter() {
        return Filter;
    }

    public void setFilter(Filter filter) {
        this.Filter = filter;
    }

    public Filter Filter;

    public class Filter

    {
        String BarberType;
        String PaymentType;
        String StyleType;
        String MinPrice;

        public String getDistricts() {
            return Districts;
        }

        public void setDistricts(String districts) {
            Districts = districts;
        }

        String Districts;

        public String getPaymentType() {
            return PaymentType;
        }

        public void setPaymentType(String paymentType) {
            PaymentType = paymentType;
        }

        public String getStyleType() {
            return StyleType;
        }

        public void setStyleType(String styleType) {
            StyleType = styleType;
        }

        public String getMinPrice() {
            return MinPrice;
        }

        public void setMinPrice(String minPrice) {
            MinPrice = minPrice;
        }

        public String getMaxPrice() {
            return MaxPrice;
        }

        public void setMaxPrice(String maxPrice) {
            MaxPrice = maxPrice;
        }

        public boolean isTrainee() {
            return IsTrainee;
        }

        public void setTrainee(boolean trainee) {
            IsTrainee = trainee;
        }

        public String getSoryBy() {
            return SortBy;
        }

        public void setSoryBy(String soryBy) {
            SortBy = soryBy;
        }

        public String getDayAvailability() {
            return DayAvailability;
        }

        public void setDayAvailability(String dayAvailability) {
            DayAvailability = dayAvailability;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
        }

        String MaxPrice;
        boolean IsTrainee;

        public boolean isSuperBarber() {
            return IsSuperBarber;
        }

        public void setSuperBarber(boolean superBarber) {
            IsSuperBarber = superBarber;
        }

        boolean IsSuperBarber;
        String SortBy;
        String DayAvailability;
        String Date;

        public String getBarberType() {
            return BarberType;
        }

        public void setBarberType(String barberType) {
            BarberType = barberType;
        }

    }

}
