/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.time.LocalDate;
import model.GioHang;
import model.NguoiDung;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.text.ParseException;
import java.util.Date;
import model.ChiTietDonHang;
import model.DonHang;
import model.Item;
import model.SanPham;
import model.TrangThai;

/**
 *
 * @author trant
 */
public class DonHangDao extends DBContext {

    /**
     *
     * @param c
     * @param cart
     */
    public void addOrder(NguoiDung c, GioHang cart, String tennguoinhan, String diachi, String sdt, String email, String ghichu, String hinhthucTT) throws java.text.ParseException {
        LocalDate curDate = LocalDate.now();
        String ngaydat = curDate.toString();
        try {
            //add vao bang DonHang
            String sql = "insert into Don_Hang values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, c.getId());
            st.setString(2, tennguoinhan);
            st.setString(3, ngaydat);
            st.setString(4, hinhthucTT);
            st.setInt(5, cart.getTongSoluong());
            st.setDouble(6, cart.getTongtien());
            double thanhtien = cart.getTongtien();
            st.setDouble(7, thanhtien);
            st.setString(8, diachi);
            st.setString(9, sdt);
            st.setInt(10, (int) 1);
            st.setString(11, ghichu);
            st.executeUpdate();

            //lay ra id cua don hang vua add
            String sql1 = "select top 1 id from [Don_Hang] order by id desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();

            // add vao bang chi tiet don hang
            if (rs.next()) {
                int oid = rs.getInt("id");
                for (Item i : cart.getItems()) {
                    String sql2 = "insert into [dbo].[Chi_Tiet_Don_Hang] values(?, ?, ?, ?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, oid);
                    st2.setInt(2, i.getSanPham().getId());
                    st2.setInt(3, i.getSoluong());
                    st2.setDouble(4, i.getDongia());
                    st2.executeUpdate();
                }
            }
            
            // cap nhat lai so luong san pham
            String sql3 = "update [San_Pham] set so_luong=so_luong-? where id=?";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            for (Item i : cart.getItems()) {
                st3.setInt(1, i.getSoluong());
                st3.setInt(2, i.getSanPham().getId());
                st3.executeUpdate();
            }

        } catch (SQLException e) {

        }
    }

    public ArrayList<DonHang> getAllOrder() {
        try {
            String sql = "select * from [Don_Hang]";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            ArrayList<DonHang> list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int uid = rs.getInt("uid");
                String nguoiNhanHang = rs.getString("nguoi_nhan_hang");
                String ngayDat = rs.getString("ngay_dat");
                String hinhThucTT = rs.getString("hinh_thuc_thanh_toan");
                int tongSoLuong = rs.getInt("tong_so_luong");
                double tongTien = rs.getDouble("tong_tien");
                double thanhTien = rs.getDouble("thanh_tien");
                String diaChiGiaoHang = rs.getString("dia_chi_giao_hang");
                String sdt = rs.getString("sdt");
                int maTrangThai = rs.getInt("ma_trang_thai");
                String ghiChu = rs.getString("ghi_chu");
                NguoiDungDAO nguoiDungDAO = new NguoiDungDAO();
                NguoiDung nguoiDung = nguoiDungDAO.getNguoiDungById(uid);
                TrangThaiDao trangThaiDao = new TrangThaiDao();
                TrangThai trangThai = trangThaiDao.getTrangThaiById(maTrangThai);
                DonHang donHang = new DonHang(nguoiDung, trangThai, id, uid, nguoiNhanHang, ngayDat, hinhThucTT, tongSoLuong, tongTien, thanhTien, diaChiGiaoHang, sdt, maTrangThai, ghiChu);
                list.add(donHang);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public DonHang getDonHangById(int id){
        try {
            String sql = "select * from [Don_Hang] where id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                int uid = rs.getInt("uid");
                String nguoiNhanHang = rs.getString("nguoi_nhan_hang");
                String ngayDat = rs.getString("ngay_dat");
                String hinhthucTT = rs.getString("hinh_thuc_thanh_toan");
                int tongSL = rs.getInt("tong_so_luong");
                double tongTien = rs.getDouble("tong_tien");
                double thanhTien = rs.getDouble("thanh_tien");
                String diaChiGiaoHang = rs.getString("dia_chi_giao_hang");
                String sdt = rs.getString("sdt");
                int maTrangThai = rs.getInt("ma_trang_thai");
                String ghiChu = rs.getString("ghi_chu");
                return new DonHang(id,uid,nguoiNhanHang, ngayDat,  hinhthucTT, tongSL, tongTien, thanhTien, diaChiGiaoHang, sdt, maTrangThai, ghiChu);
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    // cập nhật trạng thái cảu đơn hàng
    public void updateOrderInDatabase(int id, int matrangthai){
        try {
//            connection.setAutoCommit(true);
            String sql = "update Don_Hang set ma_trang_thai=? where id=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, matrangthai);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //hien thi bang chi tiet don hang theo ma don hang
    public ArrayList<ChiTietDonHang> getAllOrderDetail(int oid){
        try {
            String sql = "select * from Chi_Tiet_Don_Hang  where oid = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oid);
            ResultSet rs = st.executeQuery();
            ArrayList<ChiTietDonHang> listOrderDetail = new ArrayList<>();
            while(rs.next()){
                int pid = rs.getInt(2);
                int soluong =rs.getInt(3);
                double dongia = rs.getDouble(4);
                DonHangDao donHangDao = new DonHangDao();
                SanPhamDAO sanPhamDAO = new SanPhamDAO();
                DonHang dh = donHangDao.getDonHangById(oid);
                SanPham sp = sanPhamDAO.getProductById(pid);
                ChiTietDonHang a = new ChiTietDonHang(dh, sp, oid, pid, soluong, dongia);
                listOrderDetail.add(a);
            }
            return listOrderDetail;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
//    tim kiem don hang
    
//    public void updateNumberProduct(GioHang cart){
//        try {
//            // cap nhat lai so luong san pham
//            String sql3 = "update [San_Pham] set so_luong=so_luong-? where id=?";
//            PreparedStatement st3 = connection.prepareStatement(sql3);
//            for (Item i : cart.getItems()) {
//                st3.setInt(1, i.getSoluong());
//                st3.setInt(2, i.getSanPham().getId());
//                st3.executeUpdate();
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
}
