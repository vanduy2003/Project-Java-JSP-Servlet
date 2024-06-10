package com.webfruit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.webfruit.model.Auth;

@WebServlet(name = "forgot-password", urlPatterns = {"/forgot-password"})
public class ForgotPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/web/forgot-pass.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        if (email != null && !email.isEmpty()) {
            try {
                if (Auth.getInstance().emailExists(email)) {
                    String tempPassword = generateTempPassword();
                    boolean updated = Auth.getInstance().updatePassword(email, tempPassword);

                    if (updated) {
                        try {
                            sendEmail(email, tempPassword);
                            req.setAttribute("message", "A temporary password has been sent to your email.");
                        } catch (MessagingException e) {
                            e.printStackTrace();
                            req.setAttribute("error", "Failed to send email.");
                        }
                    } else {
                        req.setAttribute("error", "Failed to update password.");
                    }
                } else {
                    req.setAttribute("error", "Email does not exist.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            req.setAttribute("error", "Email is required.");
        }

        req.getRequestDispatcher("/views/web/forgot-pass.jsp").forward(req, resp);
    }


    private String generateTempPassword() {
        // Implement logic to generate a temporary password
        return "123"; // Example temporary password
    }

    private void sendEmail(String to, String tempPassword) throws MessagingException {
        String from = "duy9541@gmail.com"; // Thay thế bằng email của bạn
        String password = "heas srjn bmgk mydi"; // Thay thế bằng mật khẩu ứng dụng của bạn

        // Kiểm tra giá trị email và mật khẩu
        System.out.println("From email: " + from);
        System.out.println("To email: " + to);
        System.out.println("Email password: " + (password != null ? "exists" : "null"));

        if (from == null || to == null || password == null) {
            throw new MessagingException("Email configuration is not set properly");
        }

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587"); // Sử dụng cổng 587 với STARTTLS

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Temporary Password");
            message.setText("Your temporary password is: " + tempPassword);

            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new MessagingException("Failed to send email: " + e.getMessage());
        }
    }


}
