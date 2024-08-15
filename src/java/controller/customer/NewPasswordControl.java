/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dal.NguoiDungDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author PC
 */
@WebServlet(name = "NewPasswordControl", urlPatterns = {"/newPassword"})
public class NewPasswordControl extends HttpServlet {

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
            out.println("<title>Servlet NewPasswordControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewPasswordControl at " + request.getContextPath() + "</h1>");
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
        
        //25/11/2023
        HttpSession session = request.getSession();
        Boolean otpVerified = (Boolean) session.getAttribute("otpVerified");
        session.removeAttribute("otpVerified");
        if (otpVerified != null && otpVerified) {
            request.getRequestDispatcher("newPassword.jsp").forward(request, response);
        }else{
            response.sendRedirect("home");
        }
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
        try {
            HttpSession session = request.getSession();
            String newPassword = request.getParameter("password");
            String confPassword = request.getParameter("confPassword");
            RequestDispatcher dispatcher = null;
            String message;
            NguoiDungDAO nguoiDungDao = new NguoiDungDAO();

            String myHash = hashPassword(newPassword);

            String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";

            if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {

                if (newPassword.matches(passwordPattern)) {
                    String email = (String) session.getAttribute("email");
                    boolean passwordUpdated = nguoiDungDao.updatePassword(email, myHash);
                    if (passwordUpdated) {
                        session.setAttribute("status", "Reset password successfully");
                        response.sendRedirect("login");
                    } else {
                        request.setAttribute("status", "Password reset failed");
                        dispatcher = request.getRequestDispatcher("newPassword.jsp");
                    }
                } else {
                    message = "Password must contain at least eight characters, at least one letter, one number and one special character.";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("newPassword.jsp").forward(request, response);
                }
            }
            if (newPassword != null && confPassword != null && !newPassword.equals(confPassword)) {
                message = "New password and confirmation password do not match!";
                request.setAttribute("message", message);
                request.getRequestDispatcher("newPassword.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println(e);
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
