package com.example.guestapp8126.Models;

public class GuestLaundry {

    private String guestKey;
    private String guestId;
    private String guestAlamat;
    private String guestPhone;
    private String guestLatitude;
    private String guestLongitude;
    private String guestPhoto;
    private String guestName;

    public GuestLaundry(String guestId, String guestAlamat, String guestPhone, String guestLatitude, String guestLongitude, String guestPhoto, String guestName) {
        this.guestId = guestId;
        this.guestAlamat = guestAlamat;
        this.guestPhone = guestPhone;
        this.guestLatitude = guestLatitude;
        this.guestLongitude = guestLongitude;
        this.guestPhoto = guestPhoto;
        this.guestName = guestName;
    }

    public GuestLaundry() {
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

    public String getGuestLatitude() {
        return guestLatitude;
    }

    public void setGuestLatitude(String guestLatitude) {
        this.guestLatitude = guestLatitude;
    }

    public String getGuestLongitude() {
        return guestLongitude;
    }

    public void setGuestLongitude(String guestLongitude) {
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
