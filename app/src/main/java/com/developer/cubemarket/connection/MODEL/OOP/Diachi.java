package com.developer.cubemarket.connection.MODEL.OOP;

public class Diachi {
    private int madiachi;
    private int id;
    private String tendiachi;

    public Diachi() {
    }

    public Diachi(int madiachi, int id, String tendiachi) {
        this.madiachi = madiachi;
        this.id = id;
        this.tendiachi = tendiachi;
    }

    public int getMadiachi() {
        return madiachi;
    }

    public void setMadiachi(int madiachi) {
        this.madiachi = madiachi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTendiachi() {
        return tendiachi;
    }

    public void setTendiachi(String tendiachi) {
        this.tendiachi = tendiachi;
    }

    @Override
    public String toString() {
        return tendiachi;
    }
}
