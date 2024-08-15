/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DanhMucDAO;
import dal.HinhAnhSanPhamDAO;
import dal.SanPhamDAO;
import dal.ThongSoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import model.DanhMuc;
import model.HinhAnhSanPham;
import model.SanPham;
import model.ThongSo;

/**
 *
 * @author PC
 */
@WebServlet(name = "SanPhamControl", urlPatterns = {"/admin/san-pham"})
@MultipartConfig
public class SanPhamControl extends HttpServlet {

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
            out.println("<title>Servlet SanPhamControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SanPhamControl at " + request.getContextPath() + "</h1>");
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
                createProduct(request, response);
            case "edit" ->
                editProduct(request, response);
            case "delete" ->
                deleteProduct(request, response);
            default ->
                goProductList(request, response);
        }
    }

    private void goProductList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("create");
        session.removeAttribute("edit");    
                

        SanPhamDAO sanPhamDao = new SanPhamDAO();
        ArrayList<SanPham> listProduct;
        
        // lay danh sach tim kiem
        ArrayList<SanPham> listSearchProduct = (ArrayList<SanPham>) session.getAttribute("listSearchProduct");
        String text = "";
        text = (String) session.getAttribute("text");
        session.removeAttribute("text");
        session.removeAttribute("listSearchProduct");

        if (listSearchProduct != null && !listSearchProduct.isEmpty()) {
            listProduct = listSearchProduct;
        } else if (listSearchProduct != null && text != null) {
            listProduct = null;
        } else {
            listProduct = sanPhamDao.getAllProduct();
        }

        int itemsPerPage = 10;
        int currentPage = 1;
        int totalItems = 0;
        if (listProduct != null) {
            totalItems = listProduct.size();
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
            listItemsPerPage.add(listProduct.get(i));
        }       

        request.setAttribute("managerProduct", "managerProduct"); // hiện thị active 
        request.setAttribute("listItemsPerPage", listItemsPerPage);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("quanLySanPham.jsp").forward(request, response);
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("create", "create");
        session.removeAttribute("edit");
        DanhMucDAO danhMucDao = new DanhMucDAO();
        ArrayList<DanhMuc> listDanhMuc = danhMucDao.getAllCategory();
        request.setAttribute("listDanhMuc", listDanhMuc);
        request.getRequestDispatcher("quanLySanPham.jsp").forward(request, response);
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("edit", "edit");
        session.removeAttribute("create");

        DanhMucDAO danhMucDao = new DanhMucDAO();
        ArrayList<DanhMuc> listDanhMuc = danhMucDao.getAllCategory();

        int id = Integer.parseInt(request.getParameter("id"));
        SanPhamDAO sanPhamDao = new SanPhamDAO();
        SanPham sanPham = sanPhamDao.getProductById(id);

        ThongSoDAO thongSoDao = new ThongSoDAO();
        ThongSo thongSo = thongSoDao.getSecificationByPid(id);

        ArrayList<HinhAnhSanPham> listImageProduct = new HinhAnhSanPhamDAO().getAllImageProductByPid(id);

        request.setAttribute("listDanhMuc", listDanhMuc);
        request.setAttribute("sanPham", sanPham);
        request.setAttribute("thongSo", thongSo);
        request.setAttribute("listImageProduct", listImageProduct);

        request.getRequestDispatcher("quanLySanPham.jsp").forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        SanPhamDAO sanPhamDao = new SanPhamDAO();
        sanPhamDao.deleleProduct(id);
        response.sendRedirect("san-pham");
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
                saveNewProduct(request, response);
            case "saveEdit" ->
                saveEditProduct(request, response);
            case "search" -> 
                searchSanPham(request, response);
            default ->
                goProductList(request, response);
        }
    }
    
    private void searchSanPham(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String text = request.getParameter("text").toLowerCase();
        SanPhamDAO sanPhamDao = new SanPhamDAO();
        ArrayList<SanPham> listSearchProduct = sanPhamDao.search(text);
        HttpSession session = request.getSession();
        session.setAttribute("text", text);
        session.setAttribute("listSearchProduct", listSearchProduct);

        response.sendRedirect("san-pham");
    }

    private void saveNewProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("create");

        // lay cac gia tri tu form gui qua
        String tenSanPham = request.getParameter("tenSanPham").trim();
        String moTaNgan = request.getParameter("moTaNgan").trim();
        String moTaChiTiet = request.getParameter("moTaChiTiet").trim();
        double giaBan = Double.parseDouble(request.getParameter("giaBan"));
        int soLuong = Integer.parseInt(request.getParameter("soLuong"));
        int category = Integer.parseInt(request.getParameter("category"));
        String cpu = request.getParameter("cpu").trim();
        String ram = request.getParameter("ram").trim();
        String ssd = request.getParameter("ssd").trim();
        String card = request.getParameter("card").trim();
        String congKetNoi = request.getParameter("congKetNoi").trim();
        String manHinh = request.getParameter("manHinh").trim();
        String heDieuHanh = request.getParameter("heDieuHanh").trim();
        int thoiDiemRaMat = Integer.parseInt(request.getParameter("thoiDiemRaMat"));
        Collection<Part> parts = request.getParts();
        ArrayList<String> list = new ArrayList<>();
        for (Part part : parts) {
            if (part.getSubmittedFileName() != null) {
                String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                list.add(fileName);
            }
        }
        String anhDaiDien = list.get(0);
        list.remove(0);

        // goi tu dal de them vao database
        SanPhamDAO sanPhamDao = new SanPhamDAO();
        sanPhamDao.insert(tenSanPham, moTaNgan, moTaChiTiet, giaBan, soLuong, anhDaiDien, category, cpu, ram, ssd, card, congKetNoi, manHinh, heDieuHanh, thoiDiemRaMat, list);

        response.sendRedirect("san-pham");
    }

    private void saveEditProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("create");

        Map<String, Object> updatedFieldsSanPham = new HashMap<>();
        Map<String, Object> updatedFieldsThongSo = new HashMap<>();

        int id = Integer.parseInt(request.getParameter("id"));
        SanPhamDAO sanPhamDao = new SanPhamDAO();
        SanPham sanPham = sanPhamDao.getProductById(id);

        ThongSoDAO thongSoDao = new ThongSoDAO();
        ThongSo thongSo = thongSoDao.getSecificationByPid(id);

        // lay cac gia tri tu form gui qua
        String tenSanPham = request.getParameter("tenSanPham").trim();
        if (!tenSanPham.equals(sanPham.getTenSanPham())) {
            updatedFieldsSanPham.put("ten_san_pham", tenSanPham);
        }
        String moTaNgan = request.getParameter("moTaNgan").trim();
        if (!moTaNgan.equals(sanPham.getMoTaNgan())) {
            updatedFieldsSanPham.put("mo_ta_ngan", moTaNgan);
        }

        String moTaChiTiet = request.getParameter("moTaChiTiet").trim();
        if (!moTaChiTiet.equals(sanPham.getMoTaChiTiet())) {
            updatedFieldsSanPham.put("mo_ta_chi_tiet", moTaChiTiet);
        }
        double giaBan = Double.parseDouble(request.getParameter("giaBan"));
        if (giaBan != sanPham.getGiaBan()) {
            updatedFieldsSanPham.put("gia_ban", giaBan);
        }
        int soLuong = Integer.parseInt(request.getParameter("soLuong"));
        if (soLuong != sanPham.getSoLuong()) {
            updatedFieldsSanPham.put("so_luong", soLuong);
        }
        int category = Integer.parseInt(request.getParameter("category"));
        if (category != sanPham.getDanhMuc().getCid()) {
            updatedFieldsSanPham.put("cid", category);
        }
        String cpu = request.getParameter("cpu").trim();
        if (!cpu.equals(thongSo.getCpu())) {
            updatedFieldsThongSo.put("cpu", cpu);
        }
        String ram = request.getParameter("ram").trim();
        if (!ram.equals(thongSo.getRam())) {
            updatedFieldsThongSo.put("ram", ram);
        }
        String ssd = request.getParameter("ssd").trim();
        if (!ssd.equals(thongSo.getSsd())) {
            updatedFieldsThongSo.put("ssd", ssd);
        }
        String card = request.getParameter("card").trim();
        if (!card.equals(thongSo.getCard())) {
            updatedFieldsThongSo.put("card", card);
        }
        String congKetNoi = request.getParameter("congKetNoi").trim();
        if (!congKetNoi.equals(thongSo.getCongKetNoi())) {
            updatedFieldsThongSo.put("cong_ket_noi", congKetNoi);
        }
        String manHinh = request.getParameter("manHinh").trim();
        if (!manHinh.equals(thongSo.getManHinh())) {
            updatedFieldsThongSo.put("man_hinh", manHinh);
        }
        String heDieuHanh = request.getParameter("heDieuHanh").trim();
        if (!heDieuHanh.equals(thongSo.getHeDieuHanh())) {
            updatedFieldsThongSo.put("he_dieu_hanh", heDieuHanh);
        }
        int thoiDiemRaMat = Integer.parseInt(request.getParameter("thoiDiemRaMat"));
        if (thoiDiemRaMat != thongSo.getThoiDiemRaMat()) {
            updatedFieldsThongSo.put("thoi_diem_ra_mat", thoiDiemRaMat);
        }
        Collection<Part> parts = request.getParts();
        ArrayList<String> list = new ArrayList<>();
        for (Part part : parts) {
            if (part.getSubmittedFileName() != null) {
                String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                list.add(fileName);
            }
        }
        String anhDaiDien = list.get(0);
        if (anhDaiDien != null && !anhDaiDien.equals("")) {
            updatedFieldsSanPham.put("anh_dai_dien", anhDaiDien);
        }
        list.remove(0);
        for (int i = 0; i < list.size();) {
            if (list.get(i).equals("")) {
                list.remove(i);
            } else {
                i++;
            }
        }

        // goi tu dal de them vao database
        sanPhamDao.updateProductInDatabase(id, updatedFieldsSanPham, updatedFieldsThongSo, list);

        response.sendRedirect("san-pham");
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
