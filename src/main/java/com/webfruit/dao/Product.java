package com.webfruit.dao;

public class Product {
    private String ten_san_pham;
    private Double gia_san_pham;
    private String mo_ta_san_pham;
    private String ma_giam_gia;
    private String so_luong_san_pham;
    private String hinh_anh_san_pham;

    public Product(String mo_ta_san_pham, Double gia_san_pham, String ten_san_pham, String ma_giam_gia, String so_luong_san_pham, String hinh_anh_san_pham) {
        this.mo_ta_san_pham = mo_ta_san_pham;
        this.gia_san_pham = gia_san_pham;
        this.ten_san_pham = ten_san_pham;
        this.ma_giam_gia = ma_giam_gia;
        this.so_luong_san_pham = so_luong_san_pham;
        this.hinh_anh_san_pham = hinh_anh_san_pham;
    }

    public String getTen_san_pham() {
        return ten_san_pham;
    }

    public Double getGia_san_pham() {
        return gia_san_pham;
    }

    public String getMo_ta_san_pham() {
        return mo_ta_san_pham;
    }

    public String getMa_giam_gia() {
        return ma_giam_gia;
    }

    public String getSo_luong_san_pham() {
        return so_luong_san_pham;
    }

    public String getHinh_anh_san_pham() {
        return hinh_anh_san_pham;
    }

    public void setTen_san_pham(String ten_san_pham) {
        this.ten_san_pham = ten_san_pham;
    }

    public void setGia_san_pham(Double gia_san_pham) {
        this.gia_san_pham = gia_san_pham;
    }

    public void setMo_ta_san_pham(String mo_ta_san_pham) {
        this.mo_ta_san_pham = mo_ta_san_pham;
    }

    public void setSo_luong_san_pham(String so_luong_san_pham) {
        this.so_luong_san_pham = so_luong_san_pham;
    }

    public void setMa_giam_gia(String ma_giam_gia) {
        this.ma_giam_gia = ma_giam_gia;
    }

    public void setHinh_anh_san_pham(String hinh_anh_san_pham) {
        this.hinh_anh_san_pham = hinh_anh_san_pham;
    }
}
