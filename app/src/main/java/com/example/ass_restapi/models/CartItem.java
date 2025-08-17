package com.example.ass_restapi.models;

import com.google.gson.annotations.SerializedName;

public class CartItem {

    @SerializedName("_id")
    private String id;
    private String name;
    private String image;
    private int price;
    private int quantity;

    public CartItem(String id, String name, String image, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.image = image;
         this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return price * quantity;
    }
}
