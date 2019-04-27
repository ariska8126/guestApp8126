package com.example.guestapp8126.Models;

public class Distance {

    String photoPelapak;
    String namaLaundry;
    Float rate;
    String alamat;
    String jarak;

    public Distance() {
    }

    public Distance(String photoPelapak, String namaLaundry, Float rate, String alamat) {
        this.photoPelapak = photoPelapak;
        this.namaLaundry = namaLaundry;
        this.rate = rate;
        this.alamat = alamat;
    }

    public String getPhotoPelapak() {
        return photoPelapak;
    }

    public void setPhotoPelapak(String photoPelapak) {
        this.photoPelapak = photoPelapak;
    }

    public String getNamaLaundry() {
        return namaLaundry;
    }

    public void setNamaLaundry(String namaLaundry) {
        this.namaLaundry = namaLaundry;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJarak() {
        return jarak;
    }

    public void setJarak(String jarak) {
        this.jarak = jarak;
    }
}
