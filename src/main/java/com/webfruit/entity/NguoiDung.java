package com.webfruit.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "nguoi_dung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ho_dem")
    private String hoDem;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ho_va_ten")
    private String hoVaTen;

    @Column(name = "chi_tieu", precision = 10, scale = 2)
    private BigDecimal chiTieu;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Column(name = "so_dien_thoai", length = 20)
    private String soDienThoai;

    @Column(name = "email")
    private String email;

    @Column(name = "mat_khau")
    private String matKhau;

    @Lob
    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "ngay_them", nullable = false)
    private Instant ngayThem;

    @Column(name = "ngay_cap_nhat", nullable = false)
    private Instant ngayCapNhat;

    @Column(name = "vai_tro")
    private String vaiTro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoDem() {
        return hoDem;
    }

    public void setHoDem(String hoDem) {
        this.hoDem = hoDem;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public BigDecimal getChiTieu() {
        return chiTieu;
    }

    public void setChiTieu(BigDecimal chiTieu) {
        this.chiTieu = chiTieu;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Instant getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(Instant ngayThem) {
        this.ngayThem = ngayThem;
    }

    public Instant getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Instant ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

}