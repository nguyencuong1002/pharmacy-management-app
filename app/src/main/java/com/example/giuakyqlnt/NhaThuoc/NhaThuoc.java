package com.example.giuakyqlnt.NhaThuoc;

import java.io.Serializable;

public class NhaThuoc implements Serializable {
    private String maNT;
    private String tenNT;
    private String diaChi;

    public NhaThuoc() {
    }

    public NhaThuoc(String maNT, String tenNT, String diaChi) {
        this.maNT = maNT;
        this.tenNT = tenNT;
        this.diaChi = diaChi;
    }

    public String getMaNT() {
        return maNT;
    }

    public void setMaNT(String maNT) {
        this.maNT = maNT;
    }

    public String getTenNT() {
        return tenNT;
    }

    public void setTenNT(String tenNT) {
        this.tenNT = tenNT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "NhaThuoc{" +
                "maNT='" + maNT + '\'' +
                ", tenNT='" + tenNT + '\'' +
                ", diaChi='" + diaChi + '\'' +
                '}';
    }
}
