package com.example.logindemo;

public class Checkout {
    private String Bookid;
    private String Carz;
    private String Timez;
    private String Datez;
    private String Pickupz;

    public Checkout() {

    }

    public Checkout(String carz, String timez, String datez, String pickupz , String bookid) {
        Carz = carz;
        Timez = timez;
        Datez = datez;
        Pickupz = pickupz;
        Bookid = bookid;
    }

    public void setBookid(String bookid) {
        Bookid = bookid;
    }

    public void setCarz(String carz) {
        Carz = carz;
    }

    public void setTimez(String timez) {
        Timez = timez;
    }

    public void setDatez(String datez) {
        Datez = datez;
    }

    public void setPickupz(String pickupz) {
        Pickupz = pickupz;
    }

    public String getPickupz() {
        return Pickupz;
    }

    public String getBookid() {
        return Bookid;
    }

    public String getTimez() {
        return Timez;
    }

    public String getDatez() {
        return Datez;
    }

    public String getCarz() {
        return Carz;
    }

}