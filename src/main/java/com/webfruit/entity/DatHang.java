package com.webfruit.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "dat_hang")
public class DatHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_nguoi_dung")
    private NguoiDung idNguoiDung;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_san_pham")
    private SanPham idSanPham;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Lob
    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "ma_giam_gia", length = 50)
    private String maGiamGia;

    @Column(name = "trang_thai_dat_hang")
    private String trangThaiDatHang;

    @Column(name = "ngay_them", nullable = false)
    private Instant ngayThem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NguoiDung getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(NguoiDung idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public SanPham getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(SanPham idSanPham) {
        this.idSanPham = idSanPham;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public String getTrangThaiDatHang() {
        return trangThaiDatHang;
    }

    public void setTrangThaiDatHang(String trangThaiDatHang) {
        this.trangThaiDatHang = trangThaiDatHang;
    }

    public Instant getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(Instant ngayThem) {
        this.ngayThem = ngayThem;
    }

}