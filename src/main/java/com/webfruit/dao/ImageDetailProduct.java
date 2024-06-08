package com.webfruit.dao;

public class ImageDetailProduct {
    private int ID_sp;
    private String img;
    public ImageDetailProduct (int ID_sp, String img) {
        this.ID_sp = ID_sp;
        this.img = img;
    }
    public int getID_sp() {
        return ID_sp;
    }
    public String getImg() {
        return img;
    }
    public void setID_sp(int ID_sp) {
        this.ID_sp = ID_sp;
    }
    public void setImg(String img) {
        this.img = img;
    }

}
