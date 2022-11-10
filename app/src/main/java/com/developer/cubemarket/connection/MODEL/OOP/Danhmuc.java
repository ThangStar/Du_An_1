package com.developer.cubemarket.connection.MODEL.OOP;

public class Danhmuc {
    private int madanhmuc;
    private String tendanhmuc;
    private String khuvuc;

    public Danhmuc() {
    }

    public Danhmuc(int madanhmuc, String tendanhmuc, String khuvuc) {
        this.madanhmuc = madanhmuc;
        this.tendanhmuc = tendanhmuc;
        this.khuvuc = khuvuc;
    }

    public int getMadanhmuc() {
        return madanhmuc;
    }

    public void setMadanhmuc(int madanhmuc) {
        this.madanhmuc = madanhmuc;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

    public String getKhuvuc() {
        return khuvuc;
    }

    public void setKhuvuc(String khuvuc) {
        this.khuvuc = khuvuc;
    }
}
