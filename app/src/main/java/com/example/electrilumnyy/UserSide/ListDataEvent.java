package com.example.electrilumnyy.UserSide;

public class ListDataEvent {
    private String nama_event, deskripsi_event, tgl_event, lokasi_event;

    ListDataEvent(String nama_event, String deskripsi_event, String tgl_event, String lokasi_event){
        this.setNama_event(nama_event);
        this.setDeskripsi_event(deskripsi_event);
        this.setTgl_event(tgl_event);
        this.setLokasi_event(lokasi_event);
    }

    public String getNama_event() {
        return nama_event;
    }

    public void setNama_event(String nama_event) {
        this.nama_event = nama_event;
    }

    public String getDeskripsi_event() {
        return deskripsi_event;
    }

    public void setDeskripsi_event(String deskripsi_event) {
        this.deskripsi_event = deskripsi_event;
    }

    public String getTgl_event() {
        return tgl_event;
    }

    public void setTgl_event(String tgl_event) {
        this.tgl_event = tgl_event;
    }

    public String getLokasi_event() {
        return lokasi_event;
    }

    public void setLokasi_event(String lokasi_event) {
        this.lokasi_event = lokasi_event;
    }
}
