package com.developer.cubemarket.connection.MODEL.OOP;

public class ThongKeDoanhThuTheoNgay {
    private  String ngaymua;
    private  int tongtien;

    public ThongKeDoanhThuTheoNgay() {
    }

    public ThongKeDoanhThuTheoNgay(String ngaymua, int tongtien) {
        this.ngaymua = ngaymua;
        this.tongtien = tongtien;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }
}
