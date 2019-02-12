package com.example.cilodong_latihan02_recyclerview;

public class Siswa {
    String nama;
    Boolean IK, TI, SI;

    public Siswa() { }

    public Siswa(String nama, Boolean IK, Boolean TI, Boolean SI) {
        this.nama = nama;
        this.IK = IK;
        this.TI = TI;
        this.SI = SI;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Boolean getIK() {
        return IK;
    }

    public void setIK(Boolean IK) {
        this.IK = IK;
    }

    public Boolean getTI() {
        return TI;
    }

    public void setTI(Boolean TI) {
        this.TI = TI;
    }

    public Boolean getSI() {
        return SI;
    }

    public void setSI(Boolean SI) {
        this.SI = SI;
    }
}
