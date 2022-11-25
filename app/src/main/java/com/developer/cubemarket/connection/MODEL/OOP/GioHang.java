package com.developer.cubemarket.connection.MODEL.OOP;

public class GioHang {
    private int id_optotion;
    private int magiohang;
    private int id;
    private int masanpham;
    private int gia;
    private int soluong;
    private String tenmau;
    private String tenkichthuoc;
    private String tensanpham;
    private  String img;



    public GioHang() {
    }

    public GioHang(int id_optotion, int magiohang, int id, int masanpham, int gia, int soluong, String tenmau, String tenkichthuoc, String tensanpham, String img) {
        this.id_optotion = id_optotion;
        this.magiohang = magiohang;
        this.id = id;
        this.masanpham = masanpham;
        this.gia = gia;
        this.soluong = soluong;
        this.tenmau = tenmau;
        this.tenkichthuoc = tenkichthuoc;
        this.tensanpham = tensanpham;
        this.img = img;
    }

    public int getId_optotion() {
        return id_optotion;
    }

    public void setId_optotion(int id_optotion) {
        this.id_optotion = id_optotion;
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

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getTenmau() {
        return tenmau;
    }

    public void setTenmau(String tenmau) {
        this.tenmau = tenmau;
    }

    public String getTenkichthuoc() {
        return tenkichthuoc;
    }

    public void setTenkichthuoc(String tenkichthuoc) {
        this.tenkichthuoc = tenkichthuoc;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
