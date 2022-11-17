package com.developer.cubemarket.object.search;

public class recently {
    private  int Image;
    private String name;
    private String price;

    public recently(int image, String name, String price) {
        Image = image;
        this.name = name;
        this.price = price;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}