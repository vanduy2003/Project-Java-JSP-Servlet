package com.webfruit.controller;

import com.webfruit.dao.Product;
import com.webfruit.model.Auth;
import com.webfruit.model.HandleCRUDProduct;
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

@WebServlet(name = "cart", urlPatterns = {"/cart", "/add-to-cart", "/remove-from-cart", "/minus-from-cart", "/checkout"})
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
                    String IDUser = (String) session_auth.getAttribute("IDUser");
                    if (IDUser == null) {
                        req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
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
        super.doPost(req, resp);
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
