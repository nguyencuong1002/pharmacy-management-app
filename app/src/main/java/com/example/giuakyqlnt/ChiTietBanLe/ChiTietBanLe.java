package com.example.giuakyqlnt.ChiTietBanLe;

import java.io.Serializable;

public class ChiTietBanLe implements Serializable {
    private String SOHD;
    private String MATHUOC;
    private String SOLUONG;

    public ChiTietBanLe() {
    }

    public ChiTietBanLe(String SOHD, String MATHUOC, String SOLUONG) {
        this.SOHD = SOHD;
        this.MATHUOC = MATHUOC;
        this.SOLUONG = SOLUONG;
    }

    public String getSOHD() {
        return SOHD;
    }

    public void setSOHD(String SOHD) {
        this.SOHD = SOHD;
    }

    public String getMATHUOC() {
        return MATHUOC;
    }

    public void setMATHUOC(String MATHUOC) {
        this.MATHUOC = MATHUOC;
    }

    public String getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(String SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    @Override
    public String toString() {
        return "ChiTietBanLe{" +
                "SOHD='" + SOHD + '\'' +
                ", MATHUOC='" + MATHUOC + '\'' +
                ", SOLUONG='" + SOLUONG + '\'' +
                '}';
    }
}
