package com.developer.cubemarket.connection.MODEL.OOP;

public class ThongKeDoanhThuTheoNam {
    private  String nammua;
    private  int tongtien;

    public ThongKeDoanhThuTheoNam() {
    }

    public ThongKeDoanhThuTheoNam(String nammua, int tongtien) {
        this.nammua = nammua;
        this.tongtien = tongtien;
    }

    public String getNammua() {
        return nammua;
    }

    public void setNammua(String nammua) {
        this.nammua = nammua;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }
}
