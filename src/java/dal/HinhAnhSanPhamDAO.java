/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.HinhAnhSanPham;

/**
 *
 * @author PC
 */
public class HinhAnhSanPhamDAO extends DBContext {

    public ArrayList<HinhAnhSanPham> getAllImageProductByPid(int pid) {
        try {
            String sql = "select * from [Hinh_Anh_San_Pham] where pid = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pid);
            ResultSet rs = st.executeQuery();
            ArrayList<HinhAnhSanPham> list = new ArrayList<>();
            while (rs.next()) {
                HinhAnhSanPham hasp = new HinhAnhSanPham(rs.getInt("id"),
                        rs.getInt("pid"),
                        rs.getString("url"));
                list.add(hasp);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("");
        }
        return null;
    }
}
