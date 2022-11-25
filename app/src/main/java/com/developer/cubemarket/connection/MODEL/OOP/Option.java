package com.developer.cubemarket.connection.MODEL.OOP;

public class Option {
    private  int option_id;
    private  int id_product;
    private  String name_product;
    private  String color_name;
    private  String size_name;
    private  int price;
    private  int number;

    public Option(int option_id, int id_product, String name_product, String color_name, String size_name, int price, int number) {
        this.option_id = option_id;
        this.id_product = id_product;
        this.name_product = name_product;
        this.color_name = color_name;
        this.size_name = size_name;
        this.price = price;
        this.number = number;
    }

    public int getOption_id() {
        return option_id;
    }

    public void setOption_id(int option_id) {
        this.option_id = option_id;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }

    public String getSize_name() {
        return size_name;
    }

    public void setSize_name(String size_name) {
        this.size_name = size_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return  name_product+"/"+color_name+"/"+size_name;

    }
}
