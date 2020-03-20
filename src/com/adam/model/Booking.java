package com.adam.model;

import java.util.Date;

public class Booking {
    private int bookid;
    private int facid;
    private int memid;
    private String date;
    private int slots;

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getFacid() {
        return facid;
    }

    public void setFacid(int facid) {
        this.facid = facid;
    }

    public int getMemid() {
        return memid;
    }

    public void setMemid(int memid) {
        this.memid = memid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }
}
