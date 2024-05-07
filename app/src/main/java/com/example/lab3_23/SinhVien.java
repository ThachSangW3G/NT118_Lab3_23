package com.example.lab3_23;

public class SinhVien {
    private String mssv;
    private String hoten;
    private String lop;

    public SinhVien(String mssv, String hoten, String lop) {
        this.mssv = mssv;
        this.hoten = hoten;
        this.lop = lop;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
}
