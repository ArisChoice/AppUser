package com.app.barber.models.request;

/**
 * Created by harish on 26/12/18.
 */

public class RequestMyAppointmentModel {
    public int getPageNo() {
        return PageNo;
    }

    public void setPageNo(int pageNo) {
        PageNo = pageNo;
    }

    public int getRecordsPerPage() {
        return RecordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        RecordsPerPage = recordsPerPage;
    }

    int PageNo;
    int RecordsPerPage;

}
