package com.webfruit.controller;

import com.webfruit.dao.Order;
import com.webfruit.dao.OrderDetail;
import com.webfruit.dao.Product;
import com.webfruit.dao.User;
import com.webfruit.model.Auth;
import com.webfruit.model.HandleCRUDProduct;
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
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "cart", urlPatterns = {"/cart", "/add-to-cart", "/remove-from-cart", "/minus-from-cart", "/checkout", "/buy-products"})
public class HandleCartProduct extends HttpServlet {
    public HandleCartProduct() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/cart":
                HttpSession session1 = req.getSession();
                ArrayList<Product> cart1 = (ArrayList<Product>) session1.getAttribute("cart");
                if (cart1 != null) {
                    req.setAttribute("cart", cart1);
                }


                req.getRequestDispatcher("cart.jsp").forward(req, resp);
                break;
            case "/add-to-cart":

                int quantity = 1;
                int id;
                if (req.getParameter("IDProduct") != null) {
                    try {
                        id = Integer.parseInt(req.getParameter("IDProduct"));
                        Product product = HandleCRUDProduct.getInstance().selectProductByID(id);
                        if (product != null) {
                            if (req.getParameter("quantity") != null) {
                                quantity = Integer.parseInt(req.getParameter("quantity"));
                            }
                        }
                        HttpSession session = req.getSession();
                        if (session.getAttribute("cart") == null) {
                            ArrayList<Product> cart = new ArrayList<>();
                            cart.add(product);
                            session.setAttribute("cart", cart);
                            // return ve lai trang chu
                            req.setAttribute("toast", "success");
                        } else {
                            ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");
                            boolean check = false;
                            for (Product p : cart) {
                                if (p.getID() == product.getID()) {
                                    p.setSo_luong_san_pham(String.valueOf(Integer.parseInt(p.getSo_luong_san_pham()) + quantity));
                                    check = true;
                                    break;
                                }
                            }
                            if (!check) {
                                product.setSo_luong_san_pham(String.valueOf(quantity));
                                cart.add(product);
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Đã xay ra lỗi kiểm tra sản pham trong cart");
                        throw new RuntimeException(e);
                    }

                }
                updateCartIcon(req, resp);

                // in thu gio hang ra
                resp.sendRedirect(req.getContextPath() + "/cart");
                break;
            case "/remove-from-cart":
                int idRemove = Integer.parseInt(req.getParameter("IDProduct"));
                HttpSession session = req.getSession();
                ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");
                for (Product p : cart) {
                    if (p.getID() == idRemove) {
                        cart.remove(p);
                        break;
                    }
                }
                updateCartIcon(req, resp);
                session.setAttribute("cart", cart);
                resp.sendRedirect(req.getContextPath() + "/cart");
                break;
            case "/minus-from-cart":
                int idMinus = Integer.parseInt(req.getParameter("IDProduct"));
                HttpSession sessionMinus = req.getSession();
                ArrayList<Product> cartMinus = (ArrayList<Product>) sessionMinus.getAttribute("cart");
                for (Product p : cartMinus) {
                    if (p.getID() == idMinus) {
                        if (Integer.parseInt(p.getSo_luong_san_pham()) > 1) {
                            p.setSo_luong_san_pham(String.valueOf(Integer.parseInt(p.getSo_luong_san_pham()) - 1));
                        } else {
                            cartMinus.remove(p);
                        }
                        break;
                    }
                }
                sessionMinus.setAttribute("cart", cartMinus);
                updateCartIcon(req, resp);

                resp.sendRedirect(req.getContextPath() + "/cart");
                break;
                case "/checkout":

                    HttpSession session_auth = req.getSession();
                    User user = (User) session_auth.getAttribute("user");
                    // neu chua dang nhap thi chuyen ve trang dang nhap
                    if (user  == null ) {
                        resp.sendRedirect(req.getContextPath() + "/dang-nhap");
                        return;
                    }
                    HttpSession sessionCheckout = req.getSession();
                    ArrayList<Product> cartCheckout = (ArrayList<Product>) sessionCheckout.getAttribute("cart");
                    if (cartCheckout != null) {
                        req.setAttribute("cart", cartCheckout);
                    }
                    req.getRequestDispatcher("checkout.jsp").forward(req, resp);
                    break;

            default:
                // code here
                break;
        }
    }

    private  void updateCartIcon (HttpServletRequest req, HttpServletResponse resp) {HttpSession session = req.getSession();
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
        }}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // check login user
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/dang-nhap");
            return;
        }


        String action = req.getServletPath();
        switch (action) {
            case "/buy-products":
                ArrayList<Product> listProduct = (ArrayList<Product>) session.getAttribute("cart");

                User user_data = (User) session.getAttribute("user");
                if (listProduct.size() > 0 && user_data != null) {
                    String dia_chi_nhan_hang = req.getParameter("dia_chi_nhan_hang");
                    String so_dien_thoai = req.getParameter("so_dien_thoai");
                    String mo_ta = req.getParameter("mo_ta");
                    // check dia chi nhan hang, so dien thoai
                    if (dia_chi_nhan_hang == null || so_dien_thoai == null) {
                        req.setAttribute("toast", "error");
                        req.getRequestDispatcher("checkout.jsp").forward(req, resp);
                        return;
                    }
                    // them vao bang don hang
                    try {
                        Order order = new Order();
                        order.setIdUser(user_data.getId());
                        order.setAddress(dia_chi_nhan_hang);
                        order.setDesc(mo_ta);
                        order.setDiscount("0");
                        order.setStatus("Chờ xác nhận");
                        order.setPhone(so_dien_thoai);
                        int newIDOrder = HandlePay.getInstance().insertOrder(order);
                        // lay id don hang vua them
                       if (newIDOrder == -1) {
                           req.setAttribute("toast", "error");
                           req.getRequestDispatcher("checkout.jsp").forward(req, resp);
                           return;
                       }else {
                            req.setAttribute("toast", "success");
                            int id_order = newIDOrder;
                            // them vao bang chi tiet don hang
                            for (Product p : listProduct) {
                                OrderDetail detailOrder = new OrderDetail();
                                detailOrder.setId_user(user_data.getId());
                                detailOrder.setId_product(p.getID());
                                detailOrder.setQuantity(Integer.parseInt(p.getSo_luong_san_pham()));
                                detailOrder.setGhi_chu("");
                                detailOrder.setId_order(id_order);
                                HandlePay.getInstance().insertOrderDetail(detailOrder);

                            }
                            // xoa gio hang
                            session.removeAttribute("cart");
                            req.setAttribute("quantityProduct", 0);
                            req.getRequestDispatcher("index.jsp").forward(req, resp);
                       }

                    } catch (SQLException e) {
                        System.out.println("Đã xay ra lỗi khi thêm đơn hàng");
                        throw new RuntimeException(e);
                    }
                }
                break;
            default:
                // code here
                break;
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
