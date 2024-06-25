package com.webfruit.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class SanPhamDatHangId implements java.io.Serializable {
    private static final long serialVersionUID = -5414464626041265726L;
    @Column(name = "ID_san_pham", nullable = false)
    private Integer idSanPham;

    @Column(name = "ID_dat_hang", nullable = false)
    private Integer idDatHang;

    public Integer getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(Integer idSanPham) {
        this.idSanPham = idSanPham;
    }

    public Integer getIdDatHang() {
        return idDatHang;
    }

    public void setIdDatHang(Integer idDatHang) {
        this.idDatHang = idDatHang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SanPhamDatHangId entity = (SanPhamDatHangId) o;
        return Objects.equals(this.idDatHang, entity.idDatHang) &&
                Objects.equals(this.idSanPham, entity.idSanPham);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDatHang, idSanPham);
    }

}