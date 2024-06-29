package com.webfruit.dao;

public class Product {
    private int ID;
    private String ten_san_pham;
    private double gia_san_pham;
    private String mo_ta_san_pham;
    private String ma_giam_gia;
    private String so_luong_san_pham;
    private String hinh_anh_san_pham;
    private String ngay_them;
    private String ngay_cap_nhat;
    private String ten_loai_san_pham;
    private int ID_loai_san_pham;

    public Product() {
    }

    public Product(int ID, String ten_san_pham, String mo_ta_san_pham, Double gia_san_pham, String ma_giam_gia, String so_luong_san_pham, String hinh_anh_san_pham, String ngay_them, String ngay_cap_nhat, String ten_loai_san_pham, int ID_loai_san_pham) {
        this.ID = ID;
        this.ten_san_pham = ten_san_pham;
        this.mo_ta_san_pham = mo_ta_san_pham;
        this.gia_san_pham = gia_san_pham;
        this.ma_giam_gia = ma_giam_gia;
        this.so_luong_san_pham = so_luong_san_pham;
        this.hinh_anh_san_pham = hinh_anh_san_pham;
        this.ngay_them = ngay_them;
        this.ngay_cap_nhat = ngay_cap_nhat;
        this.ten_loai_san_pham = ten_loai_san_pham;
        this.ID_loai_san_pham = ID_loai_san_pham;
    }

    public int getID_loai_san_pham() {
        return ID_loai_san_pham;
    }

    public void setID_loai_san_pham(int ID_loai_san_pham) {
        this.ID_loai_san_pham = ID_loai_san_pham;
    }

    public String getNgay_them() {
        return ngay_them;
    }

    public void setNgay_them(String ngay_them) {
        this.ngay_them = ngay_them;
    }

    public String getNgay_cap_nhat() {
        return ngay_cap_nhat;
    }

    public void setNgay_cap_nhat(String ngay_cap_nhat) {
        this.ngay_cap_nhat = ngay_cap_nhat;
    }

    public String getTen_loai_san_pham() {
        return ten_loai_san_pham;
    }

    public void setTen_loai_san_pham(String ten_loai_san_pham) {
        this.ten_loai_san_pham = ten_loai_san_pham;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public void setGia_san_pham(double gia_san_pham) {
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
