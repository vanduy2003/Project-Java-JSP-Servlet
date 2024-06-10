package com.webfruit.dao;

import java.util.Date;

public class User {
    private String ho_dem;
    private String ten;
    private String ho_va_ten;
    private float chi_tieu;
    private Date ngay_sinh;
    private String so_dien_thoai;
    private String email;
    private String mat_khau;
    private String dia_chi;

    public User() {
    }

    public User(String ho_dem, String ho_va_ten, String ten, String so_dien_thoai, Date ngay_sinh, float chi_tieu, String mat_khau, String email, String dia_chi) {
        this.ho_dem = ho_dem;
        this.ho_va_ten = ho_va_ten;
        this.ten = ten;
        this.so_dien_thoai = so_dien_thoai;
        this.ngay_sinh = ngay_sinh;
        this.chi_tieu = chi_tieu;
        this.mat_khau = mat_khau;
        this.email = email;
        this.dia_chi = dia_chi;
    }

    public String getHo_dem() {
        return ho_dem;
    }

    public String getTen() {
        return ten;
    }

    public String getHo_va_ten() {
        return ho_va_ten;
    }

    public float getChi_tieu() {
        return chi_tieu;
    }

    public Date getNgay_sinh() {
        return ngay_sinh;
    }

    public String getEmail() {
        return email;
    }

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public String getMat_khau() {
        return mat_khau;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setHo_dem(String ho_dem) {
        this.ho_dem = ho_dem;
    }

    public void setHo_va_ten(String ho_va_ten) {
        this.ho_va_ten = ho_va_ten;
    }

    public void setNgay_sinh(Date ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }

    public void setChi_tieu(float chi_tieu) {
        this.chi_tieu = chi_tieu;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMat_khau(String mat_khau) {
        this.mat_khau = mat_khau;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }
}
