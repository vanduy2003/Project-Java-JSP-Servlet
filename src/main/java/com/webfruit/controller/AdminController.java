package com.webfruit.controller;

import com.sun.mail.imap.protocol.ID;
import com.webfruit.dao.Order;
import com.webfruit.model.HandlePay;
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

@WebServlet(name = "admin", urlPatterns = { "/admin", "/admin/quan-ly-nguoi-dung",
        "/admin/quan-ly-nguoi-dung/add-user", "/admin/quan-ly-nguoi-dung/delete", "/admin/quan-ly-nguoi-dung/update", "/admin/quan-ly-don-hang" })
public class AdminController extends HttpServlet {
    public AdminController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        // check user
//        User user = (User) session.getAttribute("user");
//        if (req.getAttribute("user") == null) {
//            resp.sendRedirect(req.getContextPath() + "/dang-nhap");
//        } else {
//            try {
//                String id = Auth.getInstance().getUserByID(String.valueOf(user.getId())).getVai_tro();
//                if (!id.equalsIgnoreCase("admin")) {
//                    resp.sendRedirect(req.getContextPath() + "/trang-chu");
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }

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
                User user1 = new User();
                user1.setHo_dem(ho_dem);
                user1.setTen(ten);
                user1.setHo_va_ten(ho_va_ten);
                user1.setNgay_sinh(ngay_sinh);
                user1.setSo_dien_thoai(so_dien_thoai);
                user1.setEmail(email);
                user1.setMat_khau(mat_khau);
                user1.setDia_chi(dia_chi);
                user1.setVai_tro(vai_tro);
                user1.setChi_tieu(0);
                UserModel.getInstance().insert(user1);
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

                User user2 = new User();
                user2.setId(Integer.parseInt(idUser));
                user2.setHo_va_ten(ten);
                user2.setChi_tieu(chi_tieu);
                user2.setSo_dien_thoai(so_die_thoai);
                user2.setNgay_sinh(ngay_sinh);
                user2.setEmail(email);
                user2.setMat_khau(mat_khau);
                user2.setDia_chi(dia_chi);
                user2.setVai_tro(vai_tro);
                UserModel.getInstance().update(user2);
                UserModel.getInstance().closeConnection();
                resp.sendRedirect(req.getContextPath() + "/admin/quan-ly-nguoi-dung");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        try {
            int countUser = UserModel.getInstance().CountUser();
            UserModel.getInstance().closeConnection();
            req.setAttribute("countUser", countUser);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String url = req.getRequestURI();
        if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-nguoi-dung")
                || url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-nguoi-dung/add-user")) {
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
            req.removeAttribute("quanLyDonHang");
            req.getRequestDispatcher("/views/admin/Home.jsp").forward(req, resp);
        } else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-nguoi-dung/update")) {
            try {
                String idUser =  req.getParameter("id");
                User user1 = UserModel.getInstance().selectById(Integer.parseInt(idUser));
                req.setAttribute("userUpdate", user1);
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
            req.getRequestDispatcher("/views/admin/Home.jsp").forward(req, resp); // Đừng sử dụng sendRedirect ở đây
        }else if (url.equalsIgnoreCase(req.getContextPath() + "/admin/quan-ly-don-hang")) {

            req.removeAttribute("home");
            req.removeAttribute("quanLySanPham");
            req.removeAttribute("quanLyNguoiDung");
            req.removeAttribute("quanLyDonHang");
            req.setAttribute("quanLyDonHang", "quanLyDonHang");
            try {
                ArrayList<Order> listDataOrder = HandlePay.getInstance().selectAllOrder();
                req.setAttribute("listDataOrder", listDataOrder);
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            req.getRequestDispatcher("/views/admin/Home.jsp").forward(req, resp); // Đừng sử dụng sendRedirect ở đây
        }   else {
            req.setAttribute("home", "home");
            req.removeAttribute("quanLySanPham");
            req.removeAttribute("quanLyNguoiDung");
            req.removeAttribute("quanLyDonHang");
            req.getRequestDispatcher("/views/admin/Home.jsp").forward(req, resp);
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
