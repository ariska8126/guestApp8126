package com.example.guestapp8126.Models;

public class Feedback {

    String feedbackKey;
    String idLaundry;
    String idGuest;
    String komentar;
    Float rate;
    String namaGuest;
    String orderKey;
    Object timeStamp;

    public Feedback() {
    }

    public Feedback(String idLaundry, String idGuest, String komentar,
                    String orderKey, Float rate, String namaGuest) {
        this.idLaundry = idLaundry;
        this.idGuest = idGuest;
        this.komentar = komentar;
        this.orderKey = orderKey;
        this.rate = rate;
        this.namaGuest = namaGuest;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    public String getFeedbackKey() {
        return feedbackKey;
    }

    public void setFeedbackKey(String feedbackKey) {
        this.feedbackKey = feedbackKey;
    }

    public String getIdLaundry() {
        return idLaundry;
    }

    public void setIdLaundry(String idLaundry) {
        this.idLaundry = idLaundry;
    }

    public String getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(String idGuest) {
        this.idGuest = idGuest;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getNamaGuest() {
        return namaGuest;
    }

    public void setNamaGuest(String namaGuest) {
        this.namaGuest = namaGuest;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}
