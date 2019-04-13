package com.example.guestapp8126.Models;

import com.google.firebase.database.ServerValue;

public class Layanan {

    private String namaLayanan;
    private String deskripsi;
    private String biayaPerkilo;
    private Object timeStamp;
    private String serviceKey;
    private String userId;
    private String userPhoto;

    public Layanan(String namaLayanan, String deskripsi, String biayaPerkilo, String userId, String userPhoto) {
        this.namaLayanan = namaLayanan;
        this.deskripsi = deskripsi;
        this.biayaPerkilo = biayaPerkilo;
        this.timeStamp = ServerValue.TIMESTAMP;
        this.userId = userId;
        this.userPhoto = userPhoto;
    }

    public Layanan() {
    }

    public String getNamaLayanan() {
        return namaLayanan;
    }

    public void setNamaLayanan(String namaLayanan) {
        this.namaLayanan = namaLayanan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getBiayaPerkilo() {
        return biayaPerkilo;
    }

    public void setBiayaPerkilo(String biayaPerkilo) {
        this.biayaPerkilo = biayaPerkilo;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

}
