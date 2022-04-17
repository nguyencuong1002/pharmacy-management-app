package com.example.giuakyqlnt.Thuoc;

import java.io.Serializable;

public class Thuoc implements Serializable {
    private String MATHUOC;
    private String TENTHUOC;
    private String DVT;
    private Float DONGIA;
    private byte[] IMGTHUOC;
    public Thuoc() {
    }

    public Thuoc(String MATHUOC, String TENTHUOC, String DVT, Float DONGIA, byte[] IMGTHUOC) {
        this.MATHUOC = MATHUOC;
        this.TENTHUOC = TENTHUOC;
        this.DVT = DVT;
        this.DONGIA = DONGIA;
        this.IMGTHUOC = IMGTHUOC;
    }

    public String getMATHUOC() {
        return MATHUOC;
    }

    public void setMATHUOC(String MATHUOC) {
        this.MATHUOC = MATHUOC;
    }

    public String getTENTHUOC() { return TENTHUOC; }

    public void setTENTHUOC(String TENTHUOC) { this.TENTHUOC = TENTHUOC; }

    public String getDVT() { return DVT; }

    public void setDVT(String DVT) { this.DVT = DVT; }

    public Float getDONGIA() { return DONGIA; }

    public void setDONGIA(Float DONGIA) { this.DONGIA = DONGIA; }

    public byte[] getIMGTHUOC() {
        return IMGTHUOC;
    }

    public void setIMGTHUOC(byte[] IMGTHUOC) {
        this.IMGTHUOC = IMGTHUOC;
    }

    @Override
    public String toString() {
        return "Thuoc{" +
                "MATHUOC='" + MATHUOC + '\'' +
                ", TENTHUOC='" + TENTHUOC + '\'' +
                ", DVT='" + DVT + '\'' +
                ", DONGIA='" + DONGIA + '\'' +
                ", IMGTHUOC='" + IMGTHUOC + '\'' +
                '}';
    }
}