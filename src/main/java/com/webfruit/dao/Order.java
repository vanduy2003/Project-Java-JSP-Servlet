package com.webfruit.dao;

public class Order {
    private int id;
    private int idUser;
    private String desc;
    private String discount;
    private String status;
    private String address;
    private String phone;
    private String date;



    public Order() {
    }
    public Order(int id, int idUser, String desc, String discount, String status, String address, String phone) {
        this.id = id;
        this.idUser = idUser;
        this.desc = desc;
        this.discount = discount;
        this.status = status;
        this.address = address;
        this.phone = phone;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
