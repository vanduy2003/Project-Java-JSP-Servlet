package com.webfruit.entity;

import javax.persistence.*;

@Entity
@Table(name = "loai_san_pham")
public class LoaiSanPham {
    @EmbeddedId
    private LoaiSanPhamId id;

    @MapsId("idSanPham")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_san_pham", nullable = false)
    private SanPham idSanPham;

    public LoaiSanPhamId getId() {
        return id;
    }

    public void setId(LoaiSanPhamId id) {
        this.id = id;
    }

    public SanPham getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(SanPham idSanPham) {
        this.idSanPham = idSanPham;
    }

}