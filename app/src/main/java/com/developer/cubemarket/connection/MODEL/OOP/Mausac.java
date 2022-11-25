package com.developer.cubemarket.connection.MODEL.OOP;

public class Mausac {
    int mamausac;
    String tenmausac;

    public Mausac() {
    }

    public Mausac(int mamausac, String tenmausac) {
        this.mamausac = mamausac;
        this.tenmausac = tenmausac;
    }

    public int getMamausac() {
        return mamausac;
    }

    public void setMamausac(int mamausac) {
        this.mamausac = mamausac;
    }

    public String getTenmausac() {
        return tenmausac;
    }

    public void setTenmausac(String tenmausac) {
        this.tenmausac = tenmausac;
    }

    @Override
    public String toString() {
        return tenmausac;
    }
}
