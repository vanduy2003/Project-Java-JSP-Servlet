package com.webfruit.model;

import java.util.ArrayList;

public interface CommonDao <T> {
    public boolean insert (T T);
    public boolean update (T T);
    public boolean delete (int id);
    public T selectById (String id);
    public ArrayList<T> selectAll();
}
