/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dal.DanhMucDAO;
import dal.SanPhamDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.DanhMuc;
import model.GioHang;
import model.Item;
import model.SanPham;

/**
 *
 * @author PC
 */
@WebServlet(name = "CategoryControl", urlPatterns = {"/category"})
public class CategoryControl extends HttpServlet {

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

//        SanPhamDAO d = new SanPhamDAO();
//        ArrayList<SanPham> list = d.getAllProduct();
//        Cookie[] arr = request.getCookies();
//        String txt = "";
//        if (arr != null) {
//            for (Cookie o : arr) {
//                if (o.getName().equals("cart")) {
//                    txt += o.getValue();
//                }
//            }
//        }
//        GioHang cart = new GioHang(txt, list);
//        List<Item> listItem = cart.getItems();
//        int n;
//        if (listItem != null) {
//            n = listItem.size();
//        } else {
//            n = 0;
//        }
//        request.setAttribute("data", list);
//        request.setAttribute("size", n);
    
        HttpSession session = request.getSession();
        String ram = (String) session.getAttribute("ram");
        session.removeAttribute("ram");
        if(ram != null){
            request.setAttribute("ram", ram);
        }
        String cpu = (String) session.getAttribute("cpu");
        session.removeAttribute("cpu");
        if(cpu != null){
            request.setAttribute("cpu", cpu);
        }
        String screen = (String) session.getAttribute("screen");
        session.removeAttribute("screen");
        if(screen != null){
            request.setAttribute("screen", screen);
        }
        String price = (String) session.getAttribute("price"); 
        session.removeAttribute("price");
        if(price != null){
            request.setAttribute("price", price);
        }
        
        
        String cid_raw = request.getParameter("cid");
        int cid;
        if(cid_raw != null){
            cid = Integer.parseInt(cid_raw);            
        }else{            
            cid = (Integer)session.getAttribute("cid");
            session.removeAttribute("cid");
        }
        DanhMucDAO danhMucDao = new DanhMucDAO();
        DanhMuc danhMuc = danhMucDao.getCategoryByCid(cid);
        String tenDanhMuc = danhMuc.getTenDanhMuc();                        
        
        ArrayList<SanPham> listProductFilter = (ArrayList)session.getAttribute("listProductFilter");
        session.removeAttribute("listProductFilter");
        SanPhamDAO sanPhamDao = new SanPhamDAO();
        ArrayList<SanPham> listProductByCid = new ArrayList<>();
        
        if(listProductFilter != null){
            listProductByCid = listProductFilter;
        }else{
            listProductByCid = sanPhamDao.getAllProductByCid(cid);
        }
        
        int itemsPerPage = 12;
        int currentPage = 1;
        int totalItems = 0;
        if (listProductByCid != null) {
            totalItems = listProductByCid.size();
        }
        int totalPages = totalItems / itemsPerPage;
        if (totalItems % itemsPerPage != 0) {
            totalPages++;
        }
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        int startItem = (currentPage - 1) * itemsPerPage;
        ArrayList<SanPham> listItemsPerPage = new ArrayList<>();
        for (int i = startItem; i < Math.min(startItem + itemsPerPage, totalItems); i++) {
            listItemsPerPage.add(listProductByCid.get(i));
        }
        
        request.setAttribute("listItemsPerPage", listItemsPerPage);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("tenDanhMuc", tenDanhMuc);
        request.setAttribute("cid", cid);
        request.getRequestDispatcher("category.jsp").forward(request, response);

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
        processRequest(request, response);
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
        processRequest(request, response);
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
