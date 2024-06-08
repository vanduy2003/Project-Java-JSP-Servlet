package com.webfruit.dao;

public class Order {
    private int ID_nguoi_dung;
    private int ID_san_pham;
    private String so_luong;
    private String ghi_chu;
    private String ma_giam_gia;
    private String trang_thai_dat_hang;

    public Order(int ID_nguoi_dung, int ID_san_pham, String so_luong, String ghi_chu, String ma_giam_gia, String trang_thai_dat_hang) {
        this.ID_nguoi_dung = ID_nguoi_dung;
        this.ID_san_pham = ID_san_pham;
        this.so_luong = so_luong;
        this.ghi_chu = ghi_chu;
        this.ma_giam_gia = ma_giam_gia;
        this.trang_thai_dat_hang = trang_thai_dat_hang;
    }

    public String getSo_luong() {
        return so_luong;
    }

    public int getID_nguoi_dung() {
        return ID_nguoi_dung;
    }

    public int getID_san_pham() {
        return ID_san_pham;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public String getMa_giam_gia() {
        return ma_giam_gia;
    }

    public String getTrang_thai_dat_hang() {
        return trang_thai_dat_hang;
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

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public void setMa_giam_gia(String ma_giam_gia) {
        this.ma_giam_gia = ma_giam_gia;
    }

    public void setTrang_thai_dat_hang(String trang_thai_dat_hang) {
        this.trang_thai_dat_hang = trang_thai_dat_hang;
    }
}
