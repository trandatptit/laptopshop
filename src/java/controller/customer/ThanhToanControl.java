/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dal.DonHangDao;
import dal.SanPhamDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.GioHang;
import model.NguoiDung;
import model.SanPham;

/**
 *
 * @author trant
 */
public class ThanhToanControl extends HttpServlet {

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
            out.println("<title>Servlet ThanhToanControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ThanhToanControl at " + request.getContextPath() + "</h1>");
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
//        request.getRequestDispatcher("thanhtoan.jsp").forward(request, response);
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
        response.setCharacterEncoding("UTF-8");
        String tennguoinhan = request.getParameter("kh_ten");
        String diachi = request.getParameter("kh_diachi");
        String sdt = request.getParameter("kh_dienthoai");
        String email = request.getParameter("kh_email");
        String ghichu = request.getParameter("kh_ghichu");
        String hinhthucthanhtoan = "";
        try {
            int tmp = Integer.parseInt(request.getParameter("httt_ma"));
            if (tmp == 1) {
                hinhthucthanhtoan = "Tiền Mặt";
            } else {
                hinhthucthanhtoan = "Chuyển khoản";
            }
        } catch (NumberFormatException e) {
        }
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
        HttpSession session = request.getSession();
        NguoiDung a = (NguoiDung) session.getAttribute("acc");
        if (a == null) {
            response.sendRedirect("login.jsp");
        } else {
            DonHangDao dao = new DonHangDao();
            try {
                dao.addOrder(a, cart, tennguoinhan, diachi, sdt, email, ghichu, hinhthucthanhtoan);
            } catch (ParseException ex) {
                Logger.getLogger(ThanhToanControl.class.getName()).log(Level.SEVERE, null, ex);
            }

            Cookie c = new Cookie("cart", "");
            c.setMaxAge(0);
            response.addCookie(c);
//            response.sendRedirect("home");

            // CHUYỂN LÊN ĐÂY
            final String username = "shoplaptopaz@gmail.com";
            final String password = "nysleiygtxglbmxp";
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");
            Session s = Session.getInstance(prop, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            String tomailUser = email;
            String tomailAdmin = "laptopAZadm@gmail.com";

            // gửi email cho người dùng
            String subject = "Đã đặt hàng thành công";
            String content = "  Xin chào " + a.getTen() + "." +"\n"
                    + "Cảm ơn bạn đã mua hàng của laptopAZ. Hãy kiểm tra trạng thái đơn hàng của bạn trong phần đơn mua.";

            //gửi email cho admin
            String subjectAdmin = "Có 1 đơn hàng mới";
            String contentAdmin = "  Đơn hàng được đặt từ người dùng có tên tài khoản: " + a.getTen() +"." +"\n" 
                    +"Hãy duyệt đơn hàng trong quản lý đơn hàng để xác nhận giao hàng.";

            if (!tomailUser.equalsIgnoreCase("")) {
                try {
                    // Tạo đối tượng MimeMessage riêng cho email của người dùng và admin
                    Message msgUser = new MimeMessage(s);
                    msgUser.setFrom(new InternetAddress(username));
                    msgUser.addRecipient(Message.RecipientType.TO, new InternetAddress(tomailUser));
                    msgUser.setSubject(subject);
                    msgUser.setText(content);

                    // Tạo một đối tượng MimeMessage riêng cho email của admin
                    Message msgAdmin = new MimeMessage(s);
                    msgAdmin.setFrom(new InternetAddress(username));
                    msgAdmin.addRecipient(Message.RecipientType.TO, new InternetAddress(tomailAdmin));
                    msgAdmin.setSubject(subjectAdmin);
                    msgAdmin.setText(contentAdmin);

                    // Gửi cả hai email
                    Transport.send(msgUser);
                    Transport.send(msgAdmin);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                session.setAttribute("datHangThanhCong", "datHangThanhCong");
                response.sendRedirect("home");
            } else {
                request.setAttribute("message", "Vui lòng điền email");
            }
        }

        // gui email cho admin và nguoi mua
        // dang nhap email
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
