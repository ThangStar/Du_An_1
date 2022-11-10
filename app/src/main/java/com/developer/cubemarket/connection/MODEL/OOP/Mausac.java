package com.developer.cubemarket.connection.MODEL.OOP;

public class Mausac {
    int mamausac;
    int masanpham;
    String tenmausac;

    public Mausac() {
    }

    public Mausac(int mamausac, int masanpham, String tenmausac) {
        this.mamausac = mamausac;
        this.masanpham = masanpham;
        this.tenmausac = tenmausac;
    }

    public int getMamausac() {
        return mamausac;
    }

    public void setMamausac(int mamausac) {
        this.mamausac = mamausac;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public String getTenmausac() {
        return tenmausac;
    }

    public void setTenmausac(String tenmausac) {
        this.tenmausac = tenmausac;
    }
}
