package com.webfruit.controller;

import com.sun.mail.imap.protocol.ID;
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

import com.webfruit.dao.User;
import com.webfruit.model.UserModel;
import com.webfruit.model.Auth;

@WebServlet(name = "admin", urlPatterns = { "/admin", "/admin/quan-ly-san-pham", "/admin/quan-ly-nguoi-dung",
        "/admin/quan-ly-nguoi-dung/add-user", "/admin/quan-ly-nguoi-dung/delete", "/admin/quan-ly-nguoi-dung/update" })
public class AdminController extends HttpServlet {
    public AdminController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String IDUser = (String) session.getAttribute("IDUser");
        if (IDUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang-nhap");
            return;
        }
        String url = req.getRequestURI();
        if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-nguoi-dung/add-user")) {
            String ho_dem = req.getParameter("ho-dem");
            String ten = req.getParameter("ten");
            String ho_va_ten = ho_dem + " " + ten;
            String ngay_sinh = req.getParameter("ngay-sinh");
            String so_dien_thoai = req.getParameter("so-dien-thoai");
            String email = req.getParameter("email");
            String mat_khau = req.getParameter("mat-khau");
            String dia_chi = req.getParameter("dia-chi");
            String vai_tro = req.getParameter("vai-tro");
            // validate
            if (ho_dem == null || ho_dem.isEmpty() || ten == null || ten.isEmpty() || ngay_sinh == null
                    || ngay_sinh.isEmpty() || so_dien_thoai == null || so_dien_thoai.isEmpty() || email == null
                    || email.isEmpty() || mat_khau == null || mat_khau.isEmpty() || dia_chi == null || dia_chi.isEmpty()
                    || vai_tro == null || vai_tro.isEmpty()) {
                req.setAttribute("error", "Vui lòng nhập đầy đủ thông tin");
                req.getRequestDispatcher("/views/admin/Home.jsp").forward(req, resp);
                return;
            }
            try {
                User user = new User();
                user.setHo_dem(ho_dem);
                user.setTen(ten);
                user.setHo_va_ten(ho_va_ten);
                user.setNgay_sinh(ngay_sinh);
                user.setSo_dien_thoai(so_dien_thoai);
                user.setEmail(email);
                user.setMat_khau(mat_khau);
                user.setDia_chi(dia_chi);
                user.setVai_tro(vai_tro);
                user.setChi_tieu(0);
                UserModel.getInstance().insert(user);
                UserModel.getInstance().closeConnection();
                req.setAttribute("success", "Thêm người dùng thành công");
                resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-nguoi-dung");
            } catch (Exception ex) {
                req.setAttribute("error", "Thêm người dùng thất bại");
                ex.printStackTrace();

            }

        } else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-nguoi-dung/delete")) {
            try {
                String id = req.getParameter("id");
                UserModel.getInstance().delete(Integer.parseInt(id));
                UserModel.getInstance().closeConnection();
                resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-nguoi-dung");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-nguoi-dung/update")) {
            try {
                String idUser = req.getParameter("id_update_user");
                String ten = req.getParameter("ho_va_ten_update_user");
                float chi_tieu = Float.parseFloat(req.getParameter("chi_tieu_update_user"));
                String so_die_thoai = req.getParameter("so_dien_thoai_update_user");
                String ngay_sinh = req.getParameter("ngay_sinh_update_user");
                String email = req.getParameter("email_update_user");
                String mat_khau = req.getParameter("mat_khau_update_user");
                String dia_chi = req.getParameter("dia_chi_update_user");
                String vai_tro = req.getParameter("vai_tro_update_user");
                // validate lại
                if (ten == null || ten.isEmpty() || so_die_thoai == null || so_die_thoai.isEmpty() || ngay_sinh == null
                        || ngay_sinh.isEmpty() || email == null || email.isEmpty() || mat_khau == null
                        || mat_khau.isEmpty() || dia_chi == null || dia_chi.isEmpty() || vai_tro == null
                        || vai_tro.isEmpty()) {
                    req.setAttribute("error", "Vui lòng nhập đầy đủ thông tin");
                    req.getRequestDispatcher("/views/admin/Home.jsp").forward(req, resp);
                    return;
                }

                User user = new User();
                user.setId(Integer.parseInt(idUser));
                user.setHo_va_ten(ten);
                user.setChi_tieu(chi_tieu);
                user.setSo_dien_thoai(so_die_thoai);
                user.setNgay_sinh(ngay_sinh);
                user.setEmail(email);
                user.setMat_khau(mat_khau);
                user.setDia_chi(dia_chi);
                user.setVai_tro(vai_tro);
                UserModel.getInstance().update(user);
                UserModel.getInstance().closeConnection();
                resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-nguoi-dung");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String IDUser = (String) session.getAttribute("IDUser");
        if (IDUser == null) {
            resp.sendRedirect(req.getContextPath() + "/dang-nhap");
        } else {
            try {
                String id = Auth.getInstance().getUserByID(IDUser).getVai_tro();
                if (!id.equalsIgnoreCase("admin")) {
                    resp.sendRedirect(req.getContextPath() + "/trang-chu");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        try {
            int countUser = UserModel.getInstance().CountUser();
            UserModel.getInstance().closeConnection();
            req.setAttribute("countUser", countUser);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // check url
        String url = req.getRequestURI();
        if (url.equalsIgnoreCase(req.getContextPath() + "/admin")) {
            req.setAttribute("home", "home");
            req.removeAttribute("quanLySanPham");
            req.removeAttribute("quanLyNguoiDung");
            req.getRequestDispatcher("/views/admin/Home.jsp").forward(req, resp);
        } else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-san-pham")) {
            req.setAttribute("quanLySanPham", "quanLySanPham");
            req.removeAttribute("home");
            req.getRequestDispatcher("/views/admin/Home.jsp").forward(req, resp);
        } else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-nguoi-dung")
                || url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-nguoi-dung/add-user")
        ) {
            try {
                ArrayList<User> listUser = UserModel.getInstance().selectAll();
                UserModel.getInstance().closeConnection();
                req.setAttribute("listUser", listUser);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            req.setAttribute("quanLyNguoiDung", "quanLyNguoiDung");
            req.removeAttribute("home");
            req.removeAttribute("quanLySanPham");
            req.getRequestDispatcher("/views/admin/Home.jsp").forward(req, resp);
        }else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-nguoi-dung/update")) {
            try {
                String idUser =  req.getParameter("id");
                User user = UserModel.getInstance().selectById(Integer.parseInt(idUser));
                req.setAttribute("userUpdate", user);
                req.setAttribute("clickUpdate", "true");
                ArrayList<User> listUser = UserModel.getInstance().selectAll();
                UserModel.getInstance().closeConnection();
                req.setAttribute("listUser", listUser);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            req.setAttribute("quanLyNguoiDung", "quanLyNguoiDung");
            req.removeAttribute("home");
            req.removeAttribute("quanLySanPham");
            resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-nguoi-dung");
        }

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
