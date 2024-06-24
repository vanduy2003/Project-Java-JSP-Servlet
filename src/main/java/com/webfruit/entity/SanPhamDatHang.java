package com.webfruit.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "san_pham_dat_hang")
public class SanPhamDatHang {
    @EmbeddedId
    private SanPhamDatHangId id;

    @Column(name = "so_luong")
    private Float soLuong;

    public SanPhamDatHangId getId() {
        return id;
    }

    public void setId(SanPhamDatHangId id) {
        this.id = id;
    }

    public Float getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Float soLuong) {
        this.soLuong = soLuong;
    }

}