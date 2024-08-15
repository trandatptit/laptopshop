/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.customer;

import dal.SanPhamDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.GioHang;
import model.SanPham;

/**
 *
 * @author trant
 */
public class BuyControl extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet BuyControl</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuyControl at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        SanPhamDAO d = new SanPhamDAO();
        ArrayList<SanPham> list = d.getAllProduct();
        Cookie[] arr = request.getCookies();
        String txt = "";
        if(arr != null){
            for(Cookie o : arr){
                if(o.getName().equals("cart")){
                    txt += o.getValue();
                    o.setMaxAge(0);
                    response.addCookie(o);
                }
            }
        }
        GioHang cart = new GioHang(txt, list);
        String num = request.getParameter("num");
        String id = request.getParameter("id");
        try {
            SanPham p = d.getProductById(Integer.parseInt(id));
            HttpSession sessionBuy = request.getSession();
            int numstore = p.getSoLuong();
            if(txt.isEmpty()){
                txt = id + ":" + num;
            }
            else {
                 if(txt.contains(id)){
                    if((Integer.parseInt(num) + cart.getSoluongById(Integer.parseInt(id))) > numstore){
                        sessionBuy.setAttribute("messageBuy", "Thêm không thành công (hãy kiểm tra giỏ hàng của bạn)");
                    }
                    else{
                        txt += "/" + id + ":" + num;
                    }
                }
                else{
                    txt += "/" + id + ":" + num;
                }
            }
            Cookie c = new Cookie("cart",txt);
            c.setMaxAge(2*24*60*60);
            response.addCookie(c);
            response.sendRedirect("productdetail?id="+Integer.parseInt(id));
            
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
