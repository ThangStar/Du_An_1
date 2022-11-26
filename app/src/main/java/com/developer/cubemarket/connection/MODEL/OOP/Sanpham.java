package com.developer.cubemarket.connection.MODEL.OOP;

public class Sanpham {
    private  int masanpham;
    private Danhmuc danhmuc;
    private  String tensanpham;
    private  String img;
    private  String nhasanxuat;
    private  int soluong;
    private  int giaban;
    private  String chitiet;
    private  int id;

    public Sanpham() {
    }

    public Sanpham(int masanpham, Danhmuc danhmuc, String tensanpham, String img, String nhasanxuat, int soluong, int giaban, String chitiet, int id) {
        this.masanpham = masanpham;
        this.danhmuc = danhmuc;
        this.tensanpham = tensanpham;
        this.img = img;
        this.nhasanxuat = nhasanxuat;
        this.soluong = soluong;
        this.giaban = giaban;
        this.chitiet = chitiet;
        this.id = id;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public Danhmuc getDanhmuc() {
        return danhmuc;
    }

    public void setDanhmuc(Danhmuc danhmuc) {
        this.danhmuc = danhmuc;
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

    public String getNhasanxuat() {
        return nhasanxuat;
    }

    public void setNhasanxuat(String nhasanxuat) {
        this.nhasanxuat = nhasanxuat;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGiaban() {
        return giaban;
    }

    public void setGiaban(int giaban) {
        this.giaban = giaban;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}