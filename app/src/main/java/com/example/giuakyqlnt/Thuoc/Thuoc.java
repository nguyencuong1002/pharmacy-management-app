package com.example.giuakyqlnt.Thuoc;

import java.io.Serializable;

public class Thuoc implements Serializable {
    private String MATHUOC;
    private String TENTHUOC;
    private String DVT;
    private String DONGIA;

    public Thuoc() {
    }

    public Thuoc(String MATHUOC, String TENTHUOC, String DVT, String DONGIA) {
        this.MATHUOC = MATHUOC;
        this.TENTHUOC = TENTHUOC;
        this.DVT = DVT;
        this.DONGIA = DONGIA;
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

    public String getDONGIA() { return DONGIA; }

    public void setDONGIA(String DONGIA) { this.DONGIA = DONGIA; }

    @Override
    public String toString() {
        return "Thuoc{" +
                "MATHUOC='" + MATHUOC + '\'' +
                ", TENTHUOC='" + TENTHUOC + '\'' +
                ", DVT='" + DVT + '\'' +
                ", DONGIA='" + DONGIA + '\'' +
                '}';
    }
}