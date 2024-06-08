package com.webfruit.dao;

public class OrderProduct {
    private int ID_san_pham;
    private int ID_dat_hang;
    private float so_luong;

    public int getID_san_pham() {
        return ID_san_pham;
    }

    public int getID_dat_hang() {
        return ID_dat_hang;
    }

    public float getSo_luong() {
        return so_luong;
    }

    public void setID_dat_hang(int ID_dat_hang) {
        this.ID_dat_hang = ID_dat_hang;
    }

    public void setID_san_pham(int ID_san_pham) {
        this.ID_san_pham = ID_san_pham;
    }

    public void setSo_luong(float so_luong) {
        this.so_luong = so_luong;
    }
}
