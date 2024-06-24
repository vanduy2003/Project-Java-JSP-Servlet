package com.webfruit.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "gio_hang")
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_gio_hang", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_san_pham")
    private SanPham idSanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_nguoi_dung")
    private NguoiDung idNguoiDung;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "ngay_them", nullable = false)
    private Instant ngayThem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SanPham getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(SanPham idSanPham) {
        this.idSanPham = idSanPham;
    }

    public NguoiDung getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(NguoiDung idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Instant getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(Instant ngayThem) {
        this.ngayThem = ngayThem;
    }

}