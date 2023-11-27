/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DanhMucDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.DanhMuc;

/**
 *
 * @author PC
 */
@WebServlet(name = "DanhMucControl", urlPatterns = {"/admin/danh-muc"})
public class DanhMucControl extends HttpServlet {

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
            out.println("<title>Servlet DanhMucControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DanhMucControl at " + request.getContextPath() + "</h1>");
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
            case "create" ->
                createCategory(request, response);
            case "edit" ->
                editCategory(request, response);
            case "delete" ->
                deleteCategory(request, response);
            default ->
                goCategoryList(request, response);
        }
    }

    private void editCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("edit", "edit");
        session.removeAttribute("create");
        int cid = Integer.parseInt(request.getParameter("cid"));
        DanhMucDAO danhMucDao = new DanhMucDAO();
        DanhMuc danhMuc = danhMucDao.getCategoryByCid(cid);
        request.setAttribute("danhMuc", danhMuc);
        request.getRequestDispatcher("quanLyDanhMuc.jsp").forward(request, response);
    }

    private void createCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("create", "create");
        session.removeAttribute("edit");
        request.getRequestDispatcher("quanLyDanhMuc.jsp").forward(request, response);
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DanhMucDAO danhMucDao = new DanhMucDAO();
        int cid = Integer.parseInt(request.getParameter("cid"));
        danhMucDao.deleteCategory(cid);
        response.sendRedirect("danh-muc");
    }

    private void saveNewCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("create");
        session.removeAttribute("edit");
        String tenDanhMuc = request.getParameter("tenDanhMuc");
        DanhMucDAO danhMucDao = new DanhMucDAO();
        danhMucDao.insertCategory(tenDanhMuc);
        response.sendRedirect("danh-muc");
    }

    private void saveEditCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("create");
        session.removeAttribute("edit");
        String tenDanhMuc = request.getParameter("tenDanhMuc");
        int cid = Integer.parseInt(request.getParameter("cid"));
        DanhMucDAO danhMucDao = new DanhMucDAO();
        danhMucDao.updateCategory(cid, tenDanhMuc);
        response.sendRedirect("danh-muc");
    }

    private void goCategoryList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("create");
        session.removeAttribute("edit");

        DanhMucDAO danhMucDao = new DanhMucDAO();
        ArrayList<DanhMuc> listDanhMuc;

        // lay danh sach tim kiem
        ArrayList<DanhMuc> listSearchCategory = (ArrayList<DanhMuc>) session.getAttribute("listSearchCategory");
        String text = "";
        text = (String) session.getAttribute("text");
        session.removeAttribute("text");
        session.removeAttribute("listSearchCategory");

        if (listSearchCategory != null && !listSearchCategory.isEmpty()) {
            listDanhMuc = listSearchCategory;
        } else if (listSearchCategory != null && text != null) {
            listDanhMuc = null;
        } else {
            listDanhMuc = danhMucDao.getAllCategory();
        }

        int itemsPerPage = 6;
        int currentPage = 1;
        int totalItems = 0;
        if (listDanhMuc != null) {
            totalItems = listDanhMuc.size();
        }
        int totalPages = totalItems / itemsPerPage;
        if (totalItems % itemsPerPage != 0) {
            totalPages++;
        }
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        int startItem = (currentPage - 1) * itemsPerPage;
        ArrayList<DanhMuc> listItemsPerPage = new ArrayList<>();
        for (int i = startItem; i < Math.min(startItem + itemsPerPage, totalItems); i++) {
            listItemsPerPage.add(listDanhMuc.get(i));
        }

//        request.setAttribute("listDanhMuc", listDanhMuc);
        request.setAttribute("text", text);
        request.setAttribute("managerCategory", "managerCategory"); // hiện thị active 
        request.setAttribute("listItemsPerPage", listItemsPerPage);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("quanLyDanhMuc.jsp").forward(request, response);
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
            case "saveCreate" ->
                saveNewCategory(request, response);
            case "saveEdit" ->
                saveEditCategory(request, response);
            case "search" ->
                searchDanhMuc(request, response);
            default ->
                goCategoryList(request, response);
        }
    }

    private void searchDanhMuc(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String text = request.getParameter("text");
        DanhMucDAO danhMucDao = new DanhMucDAO();
        ArrayList<DanhMuc> listSearchCategory = danhMucDao.search(text);
        HttpSession session = request.getSession();
        session.setAttribute("text", text);
        session.setAttribute("listSearchCategory", listSearchCategory);

        response.sendRedirect("danh-muc");
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
