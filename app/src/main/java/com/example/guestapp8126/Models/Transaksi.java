package com.example.guestapp8126.Models;

public class Transaksi {
    private String transaksiID;
    private String namaLaundry;
    private String alamatLaundry;
    private String statusCucian;
    private String layanan;
    private Object timeStamp;
    private String namaKonsumen;
    private String konsumenID;
    private String laundryID;
    private String detailCucian;
    private String getTransaksiKey;

    public Transaksi(String transaksiID, String namaLaundry, String alamatLaundry, String statusCucian,
                     String layanan, String namaKonsumen, String konsumenID, String laundryID, String detailCucian) {
        this.transaksiID = transaksiID;
        this.namaLaundry = namaLaundry;
        this.alamatLaundry = alamatLaundry;
        this.statusCucian = statusCucian;
        this.layanan = layanan;
        this.namaKonsumen = namaKonsumen;
        this.konsumenID = konsumenID;
        this.laundryID = laundryID;
        this.detailCucian = detailCucian;
    }

    public Transaksi() {
    }

    public String getTransaksiID() {
        return transaksiID;
    }

    public void setTransaksiID(String transaksiID) {
        this.transaksiID = transaksiID;
    }

    public String getNamaLaundry() {
        return namaLaundry;
    }

    public void setNamaLaundry(String namaLaundry) {
        this.namaLaundry = namaLaundry;
    }

    public String getAlamatLaundry() {
        return alamatLaundry;
    }

    public void setAlamatLaundry(String alamatLaundry) {
        this.alamatLaundry = alamatLaundry;
    }

    public String getStatusCucian() {
        return statusCucian;
    }

    public void setStatusCucian(String statusCucian) {
        this.statusCucian = statusCucian;
    }

    public String getLayanan() {
        return layanan;
    }

    public void setLayanan(String layanan) {
        this.layanan = layanan;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNamaKonsumen() {
        return namaKonsumen;
    }

    public void setNamaKonsumen(String namaKonsumen) {
        this.namaKonsumen = namaKonsumen;
    }

    public String getKonsumenID() {
        return konsumenID;
    }

    public void setKonsumenID(String konsumenID) {
        this.konsumenID = konsumenID;
    }

    public String getLaundryID() {
        return laundryID;
    }

    public void setLaundryID(String laundryID) {
        this.laundryID = laundryID;
    }

    public String getDetailCucian() {
        return detailCucian;
    }

    public void setDetailCucian(String detailCucian) {
        this.detailCucian = detailCucian;
    }

    public String getGetTransaksiKey() {
        return getTransaksiKey;
    }

    public void setGetTransaksiKey(String getTransaksiKey) {
        this.getTransaksiKey = getTransaksiKey;
    }
}
