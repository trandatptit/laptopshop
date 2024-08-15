/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dal.NguoiDungDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import model.NguoiDung;
import model.VaiTro;

/**
 *
 * @author PC
 */
@WebServlet(name = "LoginControll", urlPatterns = {"/login"})
public class LoginControll extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginControll</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginControll at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String hashedPassword = hashPassword(password);

        NguoiDungDAO nguoiDungDao = new NguoiDungDAO();
        NguoiDung nguoiDung = nguoiDungDao.login(email, hashedPassword);

        if (nguoiDung != null) {

            // luu thong tin nguoi dung vao session
            HttpSession session = request.getSession(true);
            // Lưu thông tin người dùng vào phiên
            session.setAttribute("acc", nguoiDung);
            System.out.println(session + " thoi gian tao " + session.getCreationTime());

            // kiem tra xem nguoi dung có an vao remember ko neu co thi luu thông tin dang nhap vao cookie
            String rememberPass = request.getParameter("remember");
            if (rememberPass != null && rememberPass.equals("true")) {
                Cookie c_user = new Cookie("email", nguoiDung.getEmail());
                Cookie c_pass = new Cookie("password", password);
                c_user.setMaxAge(3600 * 24 * 10);
                c_pass.setMaxAge(3600 * 24 * 10);
                c_user.setPath("login");
                c_pass.setPath("login");
                response.addCookie(c_pass);
                response.addCookie(c_user);
            }

            boolean isAdmin = false;
            for (VaiTro vt : nguoiDung.getListRole()) {
                if (vt.getId() == 2) {
                    isAdmin = true;
                    break;
                }
            }

            // phan role nguoi dung
            if (isAdmin) {                
                session.setAttribute("role", "admin");
                response.sendRedirect("admin");
            } else {                
                session.setAttribute("role", "khach_hang");
                response.sendRedirect("home");
            }

        } else {
            request.setAttribute("failedLogin", "Email or Password is wrong");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
