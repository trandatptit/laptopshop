/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.TrangThai;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author trant
 */
public class TrangThaiDao extends DBContext{
    public TrangThai getTrangThaiById(int id){
        try {
            String sql = "select * from Trang_Thai where id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                String tenTrangThai = rs.getString(2);
                return new TrangThai(id, tenTrangThai);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public ArrayList<TrangThai> getAllTrangThai(){
        try {
            String sql = "select * from Trang_Thai";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            ArrayList<TrangThai> listStatus = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt(1);
                String tenTT = rs.getString(2);
                TrangThai t = new TrangThai(id, tenTT);
                listStatus.add(t);
            }
            return listStatus;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
