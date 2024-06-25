package com.webfruit.entity;

import javax.persistence.*;

@Entity
@Table(name = "anh_chi_tiet_san_pham")
public class AnhChiTietSanPham {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_sp")
    private SanPham idSp;

    @Column(name = "img")
    private String img;

    public SanPham getIdSp() {
        return idSp;
    }

    public void setIdSp(SanPham idSp) {
        this.idSp = idSp;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}