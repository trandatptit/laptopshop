/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dal.SanPhamDAO;
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
import java.util.Map;
import model.Pair;
import model.SanPham;

/**
 *
 * @author PC
 */
@WebServlet(name = "FilterControl", urlPatterns = {"/filter"})
public class FilterControl extends HttpServlet {

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
            out.println("<title>Servlet FilterControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilterControl at " + request.getContextPath() + "</h1>");
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
        Map<String, Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        String ram = request.getParameter("ram");
        if(ram != null && ram.length() > 0){
            map.put("ram", ram);    
            session.setAttribute("ram", ram);
        }
        String cpu = request.getParameter("cpu");
        if(cpu != null && cpu.length() > 0){
            map.put("cpu", cpu);
            session.setAttribute("cpu", cpu);
        }
        String screen = request.getParameter("screen");
        if(screen != null && screen.length() > 0){
            map.put("man_hinh", screen);
            session.setAttribute("screen", screen);
        }
        String priceRange = request.getParameter("price");
        
        if (priceRange != null && priceRange.contains("-")) {
            String[] priceParts = priceRange.split("-");
            double minPrice = Double.parseDouble(priceParts[0]);
            double maxPrice = Double.parseDouble(priceParts[1]);
            map.put("gia_ban", new Pair(minPrice, maxPrice));
            if(minPrice == 0 && maxPrice == 10000000){
                session.setAttribute("price", "Dưới 10 triệu");
            }else if(minPrice == 10000000 && maxPrice == 15000000){
                session.setAttribute("price", "Từ 10 triệu đến 15 triệu");
            }else if(minPrice == 15000000 && maxPrice == 20000000){
                session.setAttribute("price", "Từ 15 triệu đến 20 triệu");
            }else if(minPrice == 20000000 && maxPrice == 25000000){
                session.setAttribute("price", "Từ 20 triệu đến 25 triệu");
            }else{
                session.setAttribute("price", "Hơn 25 triệu");
            }
        }
        
        int cid = Integer.parseInt(request.getParameter("cid"));
        
        SanPhamDAO sanPhamDao = new SanPhamDAO();
        ArrayList<SanPham> listProductFilter = sanPhamDao.filter(cid, map);        
        session.setAttribute("cid", cid);
        session.setAttribute("listProductFilter", listProductFilter);
        response.sendRedirect("category?cid=" + cid);
        
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
