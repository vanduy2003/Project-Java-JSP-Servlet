package com.webfruit.dao;

public class Cart {
    private int ID_san_pham;
    private int ID_nguoi_dung;
    private String so_luong;

    public Cart(int ID_nguoi_dung, int ID_san_pham, String so_luong) {
        this.ID_nguoi_dung = ID_nguoi_dung;
        this.ID_san_pham = ID_san_pham;
        this.so_luong = so_luong;
    }

    public void setID_san_pham(int ID_san_pham) {
        this.ID_san_pham = ID_san_pham;
    }

    public void setID_nguoi_dung(int ID_nguoi_dung) {
        this.ID_nguoi_dung = ID_nguoi_dung;
    }

    public void setSo_luong(String so_luong) {
        this.so_luong = so_luong;
    }

    public int getID_san_pham() {
        return ID_san_pham;
    }

    public int getID_nguoi_dung() {
        return ID_nguoi_dung;
    }

    public String getSo_luong() {
        return so_luong;
    }
}
