package com.webfruit.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class LoaiSanPhamId implements java.io.Serializable {
    private static final long serialVersionUID = -8800352713850766168L;
    @Column(name = "ID_san_pham", nullable = false)
    private Integer idSanPham;

    @Column(name = "loai_san_pham", nullable = false)
    private String loaiSanPham;

    public Integer getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(Integer idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LoaiSanPhamId entity = (LoaiSanPhamId) o;
        return Objects.equals(this.loaiSanPham, entity.loaiSanPham) &&
                Objects.equals(this.idSanPham, entity.idSanPham);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loaiSanPham, idSanPham);
    }

}