package com.example.guestapp8126.Models;

public class Transaksi {

    Object timeStamp;
    String transaksiKey;
    String idLaundry;
    String idGuest;
    String layanan;
    String deskripsi;
    Boolean antarJemput;
    Boolean setrika;
    String pewangi;
    String namaGuest;
    Double latitudeGuest;
    Double longitudeGuest;
    Double latitudeLaundry;
    Double longitudeLaundry;
    String namaLaundry;
    String proses;
    Double berat;
    Double biaya;
    String status;
    String alamatLaundry;
    String prosesSetrika;

    public Transaksi() {
    }

    public Transaksi(String idLaundry, String idGuest, String layanan, String deskripsi) {
        this.idLaundry = idLaundry;
        this.idGuest = idGuest;
        this.layanan = layanan;
        this.deskripsi = deskripsi;
    }

    //    public Transaksi(String idLaundry, String idGuest, String layanan,
//                     String deskripsi, Boolean antarJemput, Boolean setrika,
//                     String pewangi, String namaGuest, Double latitudeGuest,
//                     Double longitudeGuest, Double latitudeLaundry,
//                     Double longitudeLaundry, String namaLaundry, String proses,
//                      String alamatLaundry, String prosesSetrika) {
//
//        this.idLaundry = idLaundry;
//        this.idGuest = idGuest;
//        this.layanan = layanan;
//        this.deskripsi = deskripsi;
//        this.antarJemput = antarJemput;
//        this.setrika = setrika;
//        this.pewangi = pewangi;
//        this.namaGuest = namaGuest;
//        this.latitudeGuest = latitudeGuest;
//        this.longitudeGuest = longitudeGuest;
//        this.latitudeLaundry = latitudeLaundry;
//        this.longitudeLaundry = longitudeLaundry;
//        this.namaLaundry = namaLaundry;
//        this.proses = proses;
//          this.alamatLaundry = alamatLaundry;
//    this.prosesSetrika = prosesSetrika;
//    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTransaksiKey() {
        return transaksiKey;
    }

    public void setTransaksiKey(String transaksiKey) {
        this.transaksiKey = transaksiKey;
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

    public String getLayanan() {
        return layanan;
    }

    public void setLayanan(String layanan) {
        this.layanan = layanan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Boolean getAntarJemput() {
        return antarJemput;
    }

    public void setAntarJemput(Boolean antarJemput) {
        this.antarJemput = antarJemput;
    }

    public Boolean getSetrika() {
        return setrika;
    }

    public void setSetrika(Boolean setrika) {
        this.setrika = setrika;
    }

    public String getPewangi() {
        return pewangi;
    }

    public void setPewangi(String pewangi) {
        this.pewangi = pewangi;
    }

    public String getNamaGuest() {
        return namaGuest;
    }

    public void setNamaGuest(String namaGuest) {
        this.namaGuest = namaGuest;
    }

    public Double getLatitudeGuest() {
        return latitudeGuest;
    }

    public void setLatitudeGuest(Double latitudeGuest) {
        this.latitudeGuest = latitudeGuest;
    }

    public Double getLongitudeGuest() {
        return longitudeGuest;
    }

    public void setLongitudeGuest(Double longitudeGuest) {
        this.longitudeGuest = longitudeGuest;
    }

    public Double getLatitudeLaundry() {
        return latitudeLaundry;
    }

    public void setLatitudeLaundry(Double latitudeLaundry) {
        this.latitudeLaundry = latitudeLaundry;
    }

    public Double getLongitudeLaundry() {
        return longitudeLaundry;
    }

    public void setLongitudeLaundry(Double longitudeLaundry) {
        this.longitudeLaundry = longitudeLaundry;
    }

    public String getNamaLaundry() {
        return namaLaundry;
    }

    public void setNamaLaundry(String namaLaundry) {
        this.namaLaundry = namaLaundry;
    }

    public String getProses() {
        return proses;
    }

    public void setProses(String proses) {
        this.proses = proses;
    }

    public Double getBerat() {
        return berat;
    }

    public void setBerat(Double berat) {
        this.berat = berat;
    }

    public Double getBiaya() {
        return biaya;
    }

    public void setBiaya(Double biaya) {
        this.biaya = biaya;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}