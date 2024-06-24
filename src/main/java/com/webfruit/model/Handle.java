package com.webfruit.model;
import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.webfruit.entity.NguoiDung;
//import com.webfruit.model.HibernateUtil;

import java.util.List;

public class Handle {
    public List<NguoiDung> getAllNguoiDung() {
        Cache HibernateUtil = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<NguoiDung> query = session.createQuery("from NguoiDung", NguoiDung.class);
            return query.list();
        }
    }
}
