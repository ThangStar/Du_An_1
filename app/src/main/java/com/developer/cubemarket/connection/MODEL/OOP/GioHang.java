package com.developer.cubemarket.connection.MODEL.OOP;

public class GioHang {
    int magiohang;
    int id;
    Mausac mausac;
    Kichthuoc kichthuoc;
    Sanpham sanpham;
    int soluong;

    public GioHang() {
    }

    public GioHang(int magiohang, int id, Mausac mausac, Kichthuoc kichthuoc, Sanpham sanpham, int soluong) {
        this.magiohang = magiohang;
        this.id = id;
        this.mausac = mausac;
        this.kichthuoc = kichthuoc;
        this.sanpham = sanpham;
        this.soluong = soluong;
    }

    public int getMagiohang() {
        return magiohang;
    }

    public void setMagiohang(int magiohang) {
        this.magiohang = magiohang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mausac getMausac() {
        return mausac;
    }

    public void setMausac(Mausac mausac) {
        this.mausac = mausac;
    }

    public Kichthuoc getKichthuoc() {
        return kichthuoc;
    }

    public void setKichthuoc(Kichthuoc kichthuoc) {
        this.kichthuoc = kichthuoc;
    }

    public Sanpham getSanpham() {
        return sanpham;
    }

    public void setSanpham(Sanpham sanpham) {
        this.sanpham = sanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
