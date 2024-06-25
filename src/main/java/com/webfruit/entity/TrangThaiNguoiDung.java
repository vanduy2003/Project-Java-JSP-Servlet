package com.webfruit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trang_thai_nguoi_dung")
public class TrangThaiNguoiDung {
    @Id
    @Column(name = "ID_nguoi_dung", nullable = false)
    private Integer id;

    @Column(name = "trang_thai")
    private String trangThai;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

}