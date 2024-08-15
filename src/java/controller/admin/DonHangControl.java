/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DonHangDao;
import dal.TrangThaiDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.DonHang;
import model.TrangThai;
import java.nio.file.Paths;
import jakarta.servlet.http.Part;
import java.util.Collection;
import model.ChiTietDonHang;

/**
 *
 * @author trant
 */
@WebServlet(name = "DonHangControl", urlPatterns = {"/admin/don-hang"})
public class DonHangControl extends HttpServlet {

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
            out.println("<title>Servlet DonHangControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DonHangControl at " + request.getContextPath() + "</h1>");
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
            case "chitiet" ->
                goOrderDetail(request, response);
            default ->
                goOrderList(request, response);
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
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "updateStatus" -> 
                updateStatus(request, response);
            default ->
                goOrderList(request, response);
        }
    }

    private void goOrderList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("chitiet");
        
        DonHangDao donHangDao = new DonHangDao();
        ArrayList<DonHang> listOrder;
        
        //lay danh sach tim kiem
        ArrayList<DonHang> listSearchOrder = (ArrayList<DonHang>) session.getAttribute("listSearchOrder");
        String text = "";
        text = (String) session.getAttribute("text");
        session.removeAttribute("text");
        session.removeAttribute("listSearchOrder");
        
        if(listSearchOrder != null && listSearchOrder.isEmpty()){
            listOrder = listSearchOrder;
        } else if(listSearchOrder != null && text != null){
            listOrder = null;
        } else {
            listOrder = donHangDao.getAllOrder();
        }
        int itemsPerPage = 10;
        int currentPage = 1;
        int totalItems = 0;
        if(listOrder != null){
            totalItems = listOrder.size();
        }
        int totalPages = totalItems / itemsPerPage;
        if(totalItems % itemsPerPage != 0){
            totalPages++;
        }
        if(request.getParameter("page") != null){
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        int startItem = (currentPage - 1) * itemsPerPage;
        ArrayList<DonHang> listItemsPerPage = new ArrayList<>();
        for(int i = startItem; i<Math.min(startItem+itemsPerPage, totalItems); i++){
            listItemsPerPage.add(listOrder.get(i));
        }
        TrangThaiDao trangthaidao = new TrangThaiDao();
        ArrayList<TrangThai> listStatus = trangthaidao.getAllTrangThai();
        request.setAttribute("managerOrder", "managerOrder");
        request.setAttribute("listStatus", listStatus);
        request.setAttribute("listItemsPerPage", listItemsPerPage);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("quanlyDonHang.jsp").forward(request, response);
    }
    private void updateStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("chitiet");
        DonHangDao d = new DonHangDao();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            String trangthai_raw=request.getParameter("trangthai");
            int trangthai = Integer.parseInt(trangthai_raw);
            d.updateOrderInDatabase(id, trangthai);
            response.sendRedirect("don-hang");
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
//    private void searchDonHang(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        String text = request.getParameter("text").toLowerCase();
//        DonHangDao donHangDao = new DonHangDao();
//        ArrayList<DonHang> listSearchProduct = donHangDao.search(text);
//        HttpSession session = request.getSession();
//        session.setAttribute("text", text);
//        session.setAttribute("listSearchProduct", listSearchProduct);
//
//        response.sendRedirect("san-pham");
//    }

    private void goOrderDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("chitiet", "chitiet");
        DonHangDao donHangDao = new DonHangDao();
        int id = Integer.parseInt(request.getParameter("id"));
        ArrayList<ChiTietDonHang> listODetail = donHangDao.getAllOrderDetail(id);
        request.setAttribute("listODetail", listODetail);
        request.getRequestDispatcher("quanlyDonHang.jsp").forward(request, response);
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
