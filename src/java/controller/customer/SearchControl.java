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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import model.SanPham;

/**
 *
 * @author PC
 */
@WebServlet(name = "SearchControl", urlPatterns = {"/search"})
public class SearchControl extends HttpServlet {

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
            out.println("<title>Servlet SearchControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchControl at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        String text = request.getParameter("text").toLowerCase();
        SanPhamDAO sanPhamDao = new SanPhamDAO();
        ArrayList<SanPham> listSearchProduct = sanPhamDao.search(text);
        PrintWriter out = response.getWriter();
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        int cnt = 0;
        for (SanPham sp : listSearchProduct) {
            cnt++;
            out.println("<li class=\"list-group-item product_suggest\">");
            out.println("    <div class=\"row\">");
            out.println("        <div class=\"col-md-3\">");
            out.println("            <a href=\"productdetail?id="+sp.getId()+"\">");
            out.println("                <div class=\"item-img\" style=\"width: 100%;\">");
            out.println("                    <img src=\"./image/" + sp.getAnhDaiDien() + "\" alt=\"\" style=\"width: 100%;\">");
            out.println("                </div>");
            out.println("            </a>");
            out.println("        </div>");
            out.println("        <div class=\"col-md-9\">");
            out.println("            <a href=\"productdetail?id="+sp.getId()+"\">");
            out.println("                <div class=\"item-info\">");
            out.println("                    <h3 class=\"mb-0\" style=\"font-size: 20px;\">" + sp.getTenSanPham() + "</h3>");
            out.println("                    <span style=\"color: red;\">" + currencyFormatter.format(sp.getGiaBan()) + "</span>");
            out.println("                </div>");
            out.println("            </a>");
            out.println("        </div>");
            out.println("    </div>");
            out.println("</li>");
            if(cnt == 5){
                break;
            }
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
