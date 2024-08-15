/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.SanPham;
import model.ThongSo;

/**
 *
 * @author PC
 */
public class ThongSoDAO extends DBContext {

    public ThongSo getSecificationByPid(int pid) {
        try {
            String sql = "select * from [Thong_So] where pid = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pid);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("id");
                       String cpu = rs.getString("cpu");
                       String ram = rs.getString("ram");
                       String ssd = rs.getString("ssd");
                       String card = rs.getString("card");
                       String congKetNoi = rs.getString("cong_ket_noi");
                       String manHinh = rs.getString("man_hinh");
                       String heDieuHanh = rs.getString("he_dieu_hanh");
                       int thoiDiemRaMat = rs.getInt("thoi_diem_ra_mat");
                       SanPham sanPham = new SanPhamDAO().getProductById(pid);
                                      
                return new ThongSo(id, cpu, ram, ssd, card, congKetNoi, manHinh, heDieuHanh, thoiDiemRaMat, sanPham);                
            }
            
        } catch (SQLException e) {
            System.out.println("");
        }
        return null;
    }
}
