/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
@WebServlet(name = "ValidateOtpControl", urlPatterns = {"/validateOtp"})
public class ValidateOtpControl extends HttpServlet {

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
            out.println("<title>Servlet ValidateOtpControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ValidateOtpControl at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        String message = (String) session.getAttribute("message");
        session.removeAttribute("message");
        request.setAttribute("message", message);
        request.getRequestDispatcher("enterOtp.jsp").forward(request, response);
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
        try {
            int value = Integer.parseInt(request.getParameter("otp"));
            HttpSession session = request.getSession();
            int otp = (int) session.getAttribute("otp");

            long currentTime = System.currentTimeMillis();
            long otpTime = (Long) session.getAttribute("otpTime");
            if (currentTime - otpTime > 60000) { // Kiểm tra xem đã trôi qua 1 phút chưa (1 phút = 60000 ms)
                session.removeAttribute("otp"); // Xóa OTP khỏi session
                // Yêu cầu người dùng nhập lại OTP hoặc gửi lại OTP mới
                request.setAttribute("message", "Otp hết hiệu lực vui lòng nhập lại");
                request.getRequestDispatcher("enterOtp.jsp").forward(request, response);
            } else {
                // Thực hiện kiểm tra OTP và xử lý nếu đúng
                if (value == otp) {
                    //25/11/2023
                    session.setAttribute("otpVerified", true);
                    response.sendRedirect("newPassword");
                } else {
                    request.setAttribute("message", "wrong otp");
                    request.getRequestDispatcher("enterOtp.jsp").forward(request, response);
                }
            }

        } catch (NumberFormatException e) {
            request.setAttribute("message", "wrong otp");
            request.getRequestDispatcher("enterOtp.jsp").forward(request, response);
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
