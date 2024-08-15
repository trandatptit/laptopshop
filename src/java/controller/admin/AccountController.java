/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.NguoiDungDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.NguoiDung;

/**
 *
 * @author PC
 */
@WebServlet(name = "AccountController", urlPatterns = {"/admin/tai-khoan"})
public class AccountController extends HttpServlet {

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
            out.println("<title>Servlet AccountController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccountController at " + request.getContextPath() + "</h1>");
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
            case "delete" ->
                deleteAccount(request, response);
            default ->
                goAccountList(request, response);
        }

    }

    private void goAccountList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NguoiDungDAO nguoiDungDao = new NguoiDungDAO();
        ArrayList<NguoiDung> listUser = null;
        HttpSession session = request.getSession();
        ArrayList<NguoiDung> listSearchUser = (ArrayList<NguoiDung>) session.getAttribute("listSearchUser");
        String text = "";
        text = (String) session.getAttribute("text");
        session.removeAttribute("text");
        session.removeAttribute("listSearchUser");

        if (listSearchUser != null && !listSearchUser.isEmpty()) {
            listUser = listSearchUser;
        } else if (listSearchUser != null && text != null) {
            listUser = null;
        } else {
            listUser = nguoiDungDao.getAllUser();
        }

        int itemsPerPage = 6;
        int currentPage = 1;
        int totalItems = 0;
        if (listUser != null) {
            totalItems = listUser.size();
        }
        int totalPages = totalItems / itemsPerPage;
        if (totalItems % itemsPerPage != 0) {
            totalPages++;
        }
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        int startItem = (currentPage - 1) * itemsPerPage;
        ArrayList<NguoiDung> listItemsPerPage = new ArrayList<>();
        for (int i = startItem; i < Math.min(startItem + itemsPerPage, totalItems); i++) {
            listItemsPerPage.add(listUser.get(i));
        }

        request.setAttribute("text", text);
        request.setAttribute("managerAccount", "managerAccount");
        request.setAttribute("listItemsPerPage", listItemsPerPage);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
//        request.setAttribute("listUser", listUser);
        request.getRequestDispatcher("quanLyTaiKhoan.jsp").forward(request, response);
    }

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        NguoiDungDAO nguoiDungDao = new NguoiDungDAO();
        if (nguoiDungDao.deleteAccount(id)) {
            session.setAttribute("status", "Xoa thanh cong");
        } else {
            session.setAttribute("error", "Da xay ra loi vui long thu lai sau");
        }
        response.sendRedirect("tai-khoan");
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
            case "search" ->
                searchTaiKhoan(request, response);

        }
    }

    private void searchTaiKhoan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String text = request.getParameter("text");
        NguoiDungDAO nguoiDungDao = new NguoiDungDAO();
        ArrayList<NguoiDung> listSearchUser = nguoiDungDao.search(text);
        HttpSession session = request.getSession();
        session.setAttribute("text", text);
        session.setAttribute("listSearchUser", listSearchUser);

        response.sendRedirect("tai-khoan");
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
