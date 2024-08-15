/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dal.DonHangDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import model.ChiTietDonHang;
import model.DonHang;
import model.NguoiDung;

/**
 *
 * @author trant
 */
@WebServlet(name = "OrderUserControl", urlPatterns = {"/orderuser"})
public class OrderUserControl extends HttpServlet {

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
            out.println("<title>Servlet OrderUserControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderUserControl at " + request.getContextPath() + "</h1>");
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
            case "dangxuly" ->
                goDonHangDangXuLy(request, response);
            case "danggiaohang" ->
                goDonHangDangGiaoHang(request, response);
            case "hoanthanh" ->
                goDonHangHoanThanh(request, response);
            case "dahuy" ->
                goDonHangDaHuy(request, response);
            default ->
                goDonHangDangXuLy(request, response);
        }
    }

    private void goDonHangDangXuLy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("dangxuly", "dangxuly");
        session.removeAttribute("danggiaohang");
        session.removeAttribute("hoanthanh");
        session.removeAttribute("dahuy");

        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("acc");
        int uid = nguoiDung.getId();
        DonHangDao d = new DonHangDao();
        ArrayList<DonHang> list = d.getAllOrderUser(uid);
        ArrayList<DonHang> dangxuly = new ArrayList<>();
        HashMap<Integer, ArrayList<ChiTietDonHang>> hashxuly = new HashMap<>();
        for (DonHang i : list) {
            if (i.getMatrangthai() == 1) {
                dangxuly.add(i);
                int oid = i.getId();
                ArrayList<ChiTietDonHang> chitiet = d.getAllOrderDetail(oid);
                hashxuly.put(oid, chitiet);
            }
        }
        request.setAttribute("hashxuly", hashxuly);
        request.setAttribute("dangxuly", dangxuly);
        request.getRequestDispatcher("orderuser.jsp").forward(request, response);
    }

    private void goDonHangDangGiaoHang(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("danggiaohang", "danggiaohang");
        session.removeAttribute("dangxuly");
        session.removeAttribute("hoanthanh");
        session.removeAttribute("dahuy");
        
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("acc");
        int uid = nguoiDung.getId();
        DonHangDao d = new DonHangDao();
        ArrayList<DonHang> list = d.getAllOrderUser(uid);
        ArrayList<DonHang> danggiaohang = new ArrayList<>();
        HashMap<Integer, ArrayList<ChiTietDonHang>> hashgiaohang = new HashMap<>();
        for (DonHang i : list) {
            if (i.getMatrangthai() == 2) {
                danggiaohang.add(i);
                int oid = i.getId();
                ArrayList<ChiTietDonHang> chitiet = d.getAllOrderDetail(oid);
                hashgiaohang.put(oid, chitiet);
            }
        }
        request.setAttribute("hashgiaohang", hashgiaohang);
        request.setAttribute("danggiaohang", danggiaohang);
        request.getRequestDispatcher("orderuser.jsp").forward(request, response);
    }

    private void goDonHangHoanThanh(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("hoanthanh", "hoanthanh");
        session.removeAttribute("dangxuly");
        session.removeAttribute("danggiaohang");
        session.removeAttribute("dahuy");
        
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("acc");
        int uid = nguoiDung.getId();
        DonHangDao d = new DonHangDao();
        ArrayList<DonHang> list = d.getAllOrderUser(uid);
        ArrayList<DonHang> hoanthanh = new ArrayList<>();
        HashMap<Integer, ArrayList<ChiTietDonHang>> hashhoanthanh = new HashMap<>();
        for (DonHang i : list) {
            if (i.getMatrangthai() == 3) {
                hoanthanh.add(i);
                int oid = i.getId();
                ArrayList<ChiTietDonHang> chitiet = d.getAllOrderDetail(oid);
                hashhoanthanh.put(oid, chitiet);
            }
        }
        request.setAttribute("hashhoanthanh", hashhoanthanh);
        request.setAttribute("hoanthanh", hoanthanh);
        request.getRequestDispatcher("orderuser.jsp").forward(request, response);
    }

    private void goDonHangDaHuy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("dahuy", "dahuy");
        session.removeAttribute("dangxuly");
        session.removeAttribute("danggiaohang");
        session.removeAttribute("hoanthanh");
        
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("acc");
        int uid = nguoiDung.getId();
        DonHangDao d = new DonHangDao();
        ArrayList<DonHang> list = d.getAllOrderUser(uid);
        ArrayList<DonHang> dahuy = new ArrayList<>();
        HashMap<Integer, ArrayList<ChiTietDonHang>> hashdahuy = new HashMap<>();
        for (DonHang i : list) {
            if (i.getMatrangthai() == 4) {
                dahuy.add(i);
                int oid = i.getId();
                ArrayList<ChiTietDonHang> chitiet = d.getAllOrderDetail(oid);
                hashdahuy.put(oid, chitiet);
            }
        }
        request.setAttribute("hashdahuy", hashdahuy);
        request.setAttribute("dahuy", dahuy);
        request.getRequestDispatcher("orderuser.jsp").forward(request, response);
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
                goDonHangDangXuLy(request, response);
        }
    }
    
    private void updateStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("chitiet");
        DonHangDao d = new DonHangDao();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            String trangthai_raw=request.getParameter("trangthai");
            session.setAttribute("trangthai", trangthai_raw);
            int trangthai = Integer.parseInt(trangthai_raw);
            d.updateOrderInDatabase(id, trangthai);
            response.sendRedirect("orderuser");
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
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
