package com.developer.cubemarket.connection.MODEL.OOP;

public class User {
    private int id;
    private String ten;
    private String password;
    private int chucvu;

    private String phone;
    private String gmail;


    public User() {
    }

    public User(int id, String ten, String password, int chucvu, String phone, String gmail) {
        this.id = id;
        this.ten = ten;
        this.password = password;
        this.chucvu = chucvu;
        this.phone = phone;
        this.gmail = gmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getChucvu() {
        return chucvu;
    }

    public void setChucvu(int chucvu) {
        this.chucvu = chucvu;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}
