/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dal.HinhAnhSanPhamDAO;
import dal.SanPhamDAO;
import dal.ThongSoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.GioHang;
import model.HinhAnhSanPham;
import model.Item;
import model.SanPham;
import model.ThongSo;

/**
 *
 * @author trant
 */
@WebServlet(name = "ProductDetailControl", urlPatterns = {"/productdetail"})
public class ProductDetailControl extends HttpServlet {

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
            out.println("<title>Servlet ProductDetailControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductDetailControl at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        try {
            SanPhamDAO d = new SanPhamDAO();
            ArrayList<SanPham> list = d.getAllProduct();
            Cookie[] arr = request.getCookies();
            String txt = "";
            if (arr != null) {
                for (Cookie o : arr) {
                    if (o.getName().equals("cart")) {
                        txt += o.getValue();
                    }
                }
            }
            GioHang cart = new GioHang(txt, list);
            List<Item> listItem = cart.getItems();
            int n;
            if (listItem != null) {
                n = listItem.size();
            } else {
                n = 0;
            }
            int id = Integer.parseInt(id_raw);
            SanPhamDAO p = new SanPhamDAO();
            SanPham sanPham = p.getProductById(id);
            HinhAnhSanPhamDAO i = new HinhAnhSanPhamDAO();
            ArrayList<HinhAnhSanPham> hinhAnhSanPham = i.getAllImageProductByPid(id);
            
            ThongSoDAO thongSoDAO = new ThongSoDAO();
            ThongSo thongSo = thongSoDAO.getSecificationByPid(id);
            
            request.setAttribute("size", n);
            request.setAttribute("thongso", thongSo);
            request.setAttribute("sanpham", sanPham);
            request.setAttribute("hinhanhsp", hinhAnhSanPham);
            request.getRequestDispatcher("productdetail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
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
