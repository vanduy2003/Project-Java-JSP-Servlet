package com.webfruit.controller;

import com.webfruit.dao.Product;
import com.webfruit.model.HandleCRUDProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//Import libraries from model and dao
import com.webfruit.model.Auth;
import com.webfruit.dao.User;


import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "trang-chu", urlPatterns = {"/trang-chu", "/"})
public class HomeController extends HttpServlet {
    public HomeController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
       if (session.getAttribute("user") != null) {
           try {
               User user = (User) session.getAttribute("user");
               session.setAttribute("fullname", user.getHo_va_ten());
           }catch (Exception ex) {
                ex.printStackTrace();
           }
       }

        try {

            ArrayList<Product> selectAllTypeProduct = HandleCRUDProduct.getInstance().selectAllLoaiSanPham();
            ArrayList<Product> allVegetables = HandleCRUDProduct.getInstance().selectAllProductsByNameEquasVegetables();
            req.setAttribute("typesProducts", selectAllTypeProduct);

            req.setAttribute("vegetables", allVegetables);
            ArrayList<Product> allProductsSelect = HandleCRUDProduct.getInstance().selectAllProducts();
            req.setAttribute("allProducts", allProductsSelect );
            String idType = req.getParameter("idtype");
            if (idType != null) {
                ArrayList<Product> products = HandleCRUDProduct.getInstance().selectAllProductsByTypeProduct(Integer.parseInt(idType));

                req.setAttribute("products", products);
            }else {
                req.setAttribute("products", allProductsSelect);
            }


        }catch (Exception ex) {
            ex.printStackTrace();
        }

        // check cart session sau do dem so luong san pham trong gio hang
        ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");
        if (cart != null) {
            int count = 0;
            for (Product p : cart) {
                count += Integer.parseInt(p.getSo_luong_san_pham());
            }
            // luu vao session de dung chung
            session.setAttribute("quantityProduct", count);
        }else {
            session.setAttribute("quantityProduct", 0);
        }

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doTrace(req, resp);
    }
}
