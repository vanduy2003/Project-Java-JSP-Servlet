package com.webfruit.dao;

public class OrderDetail {
    private int id_user;
    private int id_product;
    private int quantity;
    private String ghi_chu;
    private int id_order;

    public OrderDetail() {
    }

    public OrderDetail(int id_user, int id_product, int quantity, String ghi_chu, int id_order) {
        this.id_user = id_user;
        this.id_product = id_product;
        this.quantity = quantity;
        this.ghi_chu = ghi_chu;
        this.id_order = id_order;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }
}
