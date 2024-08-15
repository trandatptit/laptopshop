/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dal.NguoiDungDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import model.NguoiDung;

/**
 *
 * @author PC
 */
@WebServlet(name = "ProfileControll", urlPatterns = {"/user/profile"})
@MultipartConfig
public class ProfileControll extends HttpServlet {

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
            out.println("<title>Servlet ProfileControll</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileControll at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "change_info" ->
                displayInfo(request, response);
            case "change_pass" ->
                displayChangePass(request, response);           
            default ->
                displayInfo(request, response);
        }        
    }
    
    private void displayInfo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("changeInfo", "changeInfo");
        session.removeAttribute("changePass");        
        request.getRequestDispatcher("../profile.jsp").forward(request, response);
    }
    
     private void displayChangePass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("changePass", "changePass");
        session.removeAttribute("changeInfo");        
        request.getRequestDispatcher("../profile.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "change_info" ->
                changeInfo(request, response);
            case "change_pass" ->
                changePassword(request, response);           
            default ->
                changeInfo(request, response);
        }   
        
        
    }
    
    private void changeInfo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        Map<String, Object> updatedFieldsNguoiDung = new HashMap<>();
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("acc");

        // lay thong tin nguoi dung khi cap nhat lai thong tin
        String username = request.getParameter("username");
        if (!username.equals(nguoiDung.getTen())) {
            updatedFieldsNguoiDung.put("ten", username);
        }
        String email = request.getParameter("email");

        String phone = request.getParameter("phone");
        if (!phone.equals(nguoiDung.getSdt())) {
            updatedFieldsNguoiDung.put("sdt", phone);
        }
        String address = request.getParameter("address");
        if (!address.equals(nguoiDung.getDiaChi())) {
            updatedFieldsNguoiDung.put("dia_chi", address);
        }

        Collection<Part> parts = request.getParts();
        ArrayList<String> list = new ArrayList<>();
        for (Part part : parts) {
            if (part.getSubmittedFileName() != null) {
                String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                list.add(fileName);
            }
        }
        if (!list.isEmpty()) {
            String avatar = list.get(0);
            if (avatar != null && !avatar.equals("") && !avatar.equals(nguoiDung.getAvatar())) {

                updatedFieldsNguoiDung.put("avatar", avatar);

            }
        }

        NguoiDungDAO nguoiDungDao = new NguoiDungDAO();
        if (nguoiDungDao.updateInfo(email, updatedFieldsNguoiDung)) {
            session.setAttribute("message", "Cập nhật thông tin thành công");
            NguoiDung user = nguoiDungDao.getUser(email);
            session.removeAttribute("acc");
            session.setAttribute("acc", user);

        } else {
            session.setAttribute("error", "Vui lòng cập nhật thông tin sau");
        }
        response.sendRedirect("/shop/user/profile");
    }
    
    private void changePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
                
        
        String currentPass = request.getParameter("currentPass");
        String hashedCurrentPass = hashPassword(currentPass);
        
        String newPass = request.getParameter("newPass");               
        String hashedNewPass = hashPassword(newPass);
        
        NguoiDungDAO nguoiDungDao = new NguoiDungDAO();
        if(nguoiDungDao.changePassword(hashedCurrentPass, hashedNewPass)){
            session.setAttribute("message", "Đổi mật khẩu thành công");
        }else{
            session.setAttribute("error", "Nhap sai mat khau");
        }
        response.sendRedirect("/shop/user/profile?action=change_pass");
        
        
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
