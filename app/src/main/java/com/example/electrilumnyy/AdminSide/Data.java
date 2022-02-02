package com.example.electrilumnyy.AdminSide;

public class Data {

    private String nama_lengkap, email, th_angkatan, jenis_pendidikan;

    Data( String nama_lengkap, String email, String th_angkatan, String jenis_pendidikan){
        this.setNama_lengkap(nama_lengkap);
        this.setEmail(email);
        this.setTh_angkatan(th_angkatan);
        this.setJenis_pendidikan(jenis_pendidikan);
    }


    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTh_angkatan() {
        return th_angkatan;
    }

    public void setTh_angkatan(String th_angkatan) {
        this.th_angkatan = th_angkatan;
    }

    public String getJenis_pendidikan() {
        return jenis_pendidikan;
    }

    public void setJenis_pendidikan(String jenis_pendidikan) {
        this.jenis_pendidikan = jenis_pendidikan;
    }
}
