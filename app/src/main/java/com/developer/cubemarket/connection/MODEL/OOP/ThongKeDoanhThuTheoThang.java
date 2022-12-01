package com.developer.cubemarket.connection.MODEL.OOP;

public class ThongKeDoanhThuTheoThang {
    private  String thangmua;
    private  int tongtien;

    public ThongKeDoanhThuTheoThang() {
    }

    public ThongKeDoanhThuTheoThang(String thangmua, int tongtien) {
        this.thangmua = thangmua;
        this.tongtien = tongtien;
    }

    public String getThangmua() {
        return thangmua;
    }

    public void setThangmua(String thangmua) {
        this.thangmua = thangmua;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }
}
