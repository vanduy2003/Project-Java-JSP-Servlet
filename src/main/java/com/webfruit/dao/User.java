package com.webfruit.dao;

import java.util.Date;

public class User {
    private int id;
    private String ho_dem;
    private String ten;
    private String ho_va_ten;
    private float chi_tieu;
    private String ngay_sinh;
    private String so_dien_thoai;
    private String email;
    private String mat_khau;
    private String dia_chi;
    private String vai_tro;

    public User() {
    }

    public User(int id, String ho_dem, String ho_va_ten, String ten, String so_dien_thoai, String ngay_sinh, float chi_tieu, String mat_khau, String email, String dia_chi, String vai_tro) {
        this.id = id;
        this.ho_dem = ho_dem;
        this.ho_va_ten = ho_va_ten;
        this.ten = ten;
        this.so_dien_thoai = so_dien_thoai;
        this.ngay_sinh = ngay_sinh;
        this.chi_tieu = chi_tieu;
        this.mat_khau = mat_khau;
        this.email = email;
        this.dia_chi = dia_chi;
        this.vai_tro = vai_tro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHo_dem() {
        return ho_dem;
    }

    public void setHo_dem(String ho_dem) {
        this.ho_dem = ho_dem;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHo_va_ten() {
        return ho_va_ten;
    }

    public void setHo_va_ten(String ho_va_ten) {
        this.ho_va_ten = ho_va_ten;
    }

    public float getChi_tieu() {
        return chi_tieu;
    }

    public void setChi_tieu(float chi_tieu) {
        this.chi_tieu = chi_tieu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public String getNgay_sinh() {
        return ngay_sinh;
    }

    public void setNgay_sinh(String ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }

    public String getMat_khau() {
        return mat_khau;
    }

    public void setMat_khau(String mat_khau) {
        this.mat_khau = mat_khau;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public String getVai_tro() {
        return vai_tro;
    }

    public void setVai_tro(String vai_tro) {
        this.vai_tro = vai_tro;
    }
}
