package com.webfruit.controller;

import com.webfruit.dao.Product;
import com.webfruit.dao.User;
import com.webfruit.model.Auth;
import com.webfruit.model.HandleCRUDProduct;
import com.webfruit.model.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "product", urlPatterns = { "/admin/quan-ly-san-pham", "/admin/quan-ly-san-pham/add-product", "/admin/quan-ly-san-pham/delete", "/admin/quan-ly-san-pham/add-type-product", "/admin/quan-ly-san-pham/update-product"})
public class ProductController extends HttpServlet {
    public ProductController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            try {
                User user = (User) session.getAttribute("user");
                if (user.getVai_tro().equals("user")) {
                    resp.sendRedirect(req.getContextPath() + "/dang-nhap");
                }
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }else {
            resp.sendRedirect(req.getContextPath() + "/dang-nhap");
        }
        try {
            int countUser = UserModel.getInstance().CountUser();
            UserModel.getInstance().closeConnection();
            req.setAttribute("countUser", countUser);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String url = req.getRequestURI();
        if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-san-pham")) {
            req.setAttribute("quanLySanPham", "quanLySanPham");
            try {
                req.removeAttribute("quanLyNguoiDung");
                ArrayList<Product> listProduct = HandleCRUDProduct.getInstance().selectAllProducts();
                ArrayList<Product> listTypeProduct = HandleCRUDProduct.getInstance().selectAllLoaiSanPham();
                req.setAttribute("listProduct", listProduct);
                req.setAttribute("listTypeProduct", listTypeProduct);
                HandleCRUDProduct.getInstance().closeConnection();
            }catch (Exception ex) {
                System.out.println("Đã xảy ra lỗi khi lấy dữ liệu sản phẩm");
            }
            req.removeAttribute("home");
            req.getRequestDispatcher("/views/admin/Home.jsp").forward(req, resp);
        }else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-san-pham/delete")) {
            String ID = req.getParameter("idproduct");
            if (ID != null) {
                try {

                    boolean result = HandleCRUDProduct.getInstance().deleteProduct(Integer.parseInt(ID));
                    resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-san-pham");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else {
                resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-san-pham");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            try {
                User user = (User) session.getAttribute("user");
                if (user.getVai_tro().equals("user")) {
                    resp.sendRedirect(req.getContextPath() + "/dang-nhap");
                }
                session.setAttribute("fullname", user.getHo_va_ten());
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }else {
            resp.sendRedirect(req.getContextPath() + "/dang-nhap");
        }
        String url = req.getRequestURI();
        if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-san-pham/add-product")) {
            String ten_san_pham = req.getParameter("add_product_ten_san_pham");
            String gia_san_pham = req.getParameter("add_product_gia_san_pham");
            String mo_ta_san_pham = req.getParameter("update_product_mo_ta");
            String ma_giam_gia = req.getParameter("add_product_ma_giam_gia");
            String so_luong_san_pham = req.getParameter("add_product_so_luong_san_pham");
            String hinh_anh_san_pham = req.getParameter("add_product_hinh_anh_san_pham");
            String ID_loai_san_pham = req.getParameter("id-type-product");
            // validate data
            if (ten_san_pham == null || ten_san_pham.equals("") || gia_san_pham == null || gia_san_pham.equals("") || mo_ta_san_pham == null || mo_ta_san_pham.equals("") || ma_giam_gia == null || ma_giam_gia.equals("") || so_luong_san_pham == null || so_luong_san_pham.equals("") || hinh_anh_san_pham == null || hinh_anh_san_pham.equals("") || ID_loai_san_pham == null || ID_loai_san_pham.equals("")) {
                resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-san-pham");

            }
            Product product = new Product();
            product.setTen_san_pham(ten_san_pham);
            product.setGia_san_pham(Double.parseDouble(gia_san_pham));
            product.setMo_ta_san_pham(mo_ta_san_pham);
            product.setMa_giam_gia(ma_giam_gia);
            product.setSo_luong_san_pham(so_luong_san_pham);
            product.setHinh_anh_san_pham(hinh_anh_san_pham);
            product.setID_loai_san_pham(Integer.parseInt(ID_loai_san_pham));
            try {


                boolean result = HandleCRUDProduct.getInstance().createProduct(product);
                if (result) {
                    resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-san-pham");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-san-pham");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-san-pham/add-type-product")) {
            String ten_loai_san_pham = req.getParameter("add_ten_loai_san_pham");
            if (ten_loai_san_pham == null || ten_loai_san_pham.equals("")) {
                resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-san-pham");
            }
            try {
                boolean result = HandleCRUDProduct.getInstance().createTypeProduct(ten_loai_san_pham);
                if (result) {
                    resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-san-pham");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-san-pham");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-san-pham/update-product")) {
            String ID = req.getParameter("id_update_user");
            String ten_san_pham = req.getParameter("update_product_name");
            String gia_san_pham = req.getParameter("update_product_gia_san_pham");
            String mo_ta_san_pham = req.getParameter("update_product_mo_ta");
            String ma_giam_gia = req.getParameter("update_product_ma_giam_gia");
            String so_luong_san_pham = req.getParameter("update_product_so_luong_san_pham");
            String hinh_anh_san_pham = req.getParameter("update_product_duong_dan_anh");
            String ID_loai_san_pham = req.getParameter("id-type-product-update");
            if (ID == null || ID.equals("") || ten_san_pham == null || ten_san_pham.equals("") || gia_san_pham == null || gia_san_pham.equals("") || mo_ta_san_pham == null || mo_ta_san_pham.equals("") || ma_giam_gia == null || ma_giam_gia.equals("") || so_luong_san_pham == null || so_luong_san_pham.equals("") || hinh_anh_san_pham == null || hinh_anh_san_pham.equals("") || ID_loai_san_pham == null || ID_loai_san_pham.equals("")) {
                resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-san-pham");
            }
            Product product = new Product();
            product.setID(Integer.parseInt(ID));
            product.setTen_san_pham(ten_san_pham);
            product.setGia_san_pham(Double.parseDouble(gia_san_pham));
            product.setMo_ta_san_pham(mo_ta_san_pham);
            product.setMa_giam_gia(ma_giam_gia);
            product.setSo_luong_san_pham(so_luong_san_pham);
            product.setHinh_anh_san_pham(hinh_anh_san_pham);
            product.setID_loai_san_pham(Integer.parseInt(ID_loai_san_pham));
            try {
                boolean result = HandleCRUDProduct.getInstance().updateProduct(product);

                if (result) {
                    resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-san-pham");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-san-pham");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
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
}
