package com.example.guestapp8126.Models;

public class GuestLaundry {

    private String guestKey;
    private String guestId;
    private String guestAlamat;
    private String guestPhone;
    private double guestLatitude;
    private double guestLongitude;
    private String guestPhoto;
    private String guestName;
    private String guestMail;

    public GuestLaundry(String guestId, String guestAlamat, String guestPhone,
                        double guestLatitude, double guestLongitude, String guestPhoto,
                        String guestName, String guestMail) {
        this.guestId = guestId;
        this.guestAlamat = guestAlamat;
        this.guestPhone = guestPhone;
        this.guestLatitude = guestLatitude;
        this.guestLongitude = guestLongitude;
        this.guestPhoto = guestPhoto;
        this.guestName = guestName;
        this.guestMail = guestMail;
    }

    public GuestLaundry() {
    }

    public String getGuestMail() {
        return guestMail;
    }

    public void setGuestMail(String guestMail) {
        this.guestMail = guestMail;
    }

    public String getGuestKey() {
        return guestKey;
    }

    public void setGuestKey(String guestKey) {
        this.guestKey = guestKey;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getGuestAlamat() {
        return guestAlamat;
    }

    public void setGuestAlamat(String guestAlamat) {
        this.guestAlamat = guestAlamat;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    public double getGuestLatitude() {
        return guestLatitude;
    }

    public void setGuestLatitude(double guestLatitude) {
        this.guestLatitude = guestLatitude;
    }

    public double getGuestLongitude() {
        return guestLongitude;
    }

    public void setGuestLongitude(double guestLongitude) {
        this.guestLongitude = guestLongitude;
    }

    public String getGuestPhoto() {
        return guestPhoto;
    }

    public void setGuestPhoto(String guestPhoto) {
        this.guestPhoto = guestPhoto;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
}
