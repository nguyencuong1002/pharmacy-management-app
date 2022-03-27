package com.example.giuakyqlnt.HoaDon;

import java.io.Serializable;

public class HoaDon implements Serializable {
    private String soHD;
    private String ngayHD;
    private String maNT;

    public HoaDon() {
    }

    public HoaDon(String soHD, String ngayHD, String maNT) {
        this.soHD = soHD;
        this.ngayHD = ngayHD;
        this.maNT = maNT;
    }

    public String getSoHD() {
        return soHD;
    }

    public void setSoHD(String soHD) {
        this.soHD = soHD;
    }

    public String getNgayHD() {
        return ngayHD;
    }

    public void setNgayHD(String ngayHD) {
        this.ngayHD = ngayHD;
    }

    public String getMaNT() {
        return maNT;
    }

    public void setMaNT(String maNT) {
        this.maNT = maNT;
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "soHD='" + soHD + '\'' +
                ", ngayHD='" + ngayHD + '\'' +
                ", maNT='" + maNT + '\'' +
                '}';
    }
}
