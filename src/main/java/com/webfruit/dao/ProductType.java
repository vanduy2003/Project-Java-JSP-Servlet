package com.webfruit.dao;

public class ProductType {
    private int ID_san_pham;
    private String loai_san_pham;

    public String getLoai_san_pham() {
        return loai_san_pham;
    }

    public int getID_san_pham() {
        return ID_san_pham;
    }

    public void setID_san_pham(int ID_san_pham) {
        this.ID_san_pham = ID_san_pham;
    }

    public void setLoai_san_pham(String loai_san_pham) {
        this.loai_san_pham = loai_san_pham;
    }


}
