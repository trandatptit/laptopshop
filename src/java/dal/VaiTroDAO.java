/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.VaiTro;

/**
 *
 * @author PC
 */
public class VaiTroDAO extends DBContext {

    public ArrayList<VaiTro> getAllRoleUser(int uid) {
        try {
            String sql = """
                         select vt.* from
                         Nguoi_Dung as nd join Vai_Tro_Nguoi_Dung as vtnd
                         on nd.id = vtnd.uid
                         join Vai_Tro as vt 
                         on vt.id = vtnd.rid
                         where nd.id = ?""";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            ArrayList<VaiTro> list = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                int x = rs.getInt("id");
                String tenVaiTro = rs.getString("ten_vai_tro");
                list.add(new VaiTro(x, tenVaiTro));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
