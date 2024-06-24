package com.webfruit.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "san_pham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ten_san_pham")
    private String tenSanPham;

    @Column(name = "gia_san_pham", precision = 10, scale = 2)
    private BigDecimal giaSanPham;

    @Lob
    @Column(name = "mo_ta_san_pham")
    private String moTaSanPham;

    @Column(name = "ma_giam_gia", length = 50)
    private String maGiamGia;

    @Column(name = "so_luong_san_pham", length = 11)
    private String soLuongSanPham;

    @Column(name = "hinh_anh_san_pham")
    private String hinhAnhSanPham;

    @Column(name = "ngay_them", nullable = false)
    private Instant ngayThem;

    @Column(name = "ngay_cap_nhat", nullable = false)
    private Instant ngayCapNhat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public BigDecimal getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(BigDecimal giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getMoTaSanPham() {
        return moTaSanPham;
    }

    public void setMoTaSanPham(String moTaSanPham) {
        this.moTaSanPham = moTaSanPham;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public String getSoLuongSanPham() {
        return soLuongSanPham;
    }

    public void setSoLuongSanPham(String soLuongSanPham) {
        this.soLuongSanPham = soLuongSanPham;
    }

    public String getHinhAnhSanPham() {
        return hinhAnhSanPham;
    }

    public void setHinhAnhSanPham(String hinhAnhSanPham) {
        this.hinhAnhSanPham = hinhAnhSanPham;
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

}