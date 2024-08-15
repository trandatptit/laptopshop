/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.DanhMuc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class DanhMucDAO extends DBContext {
    // lay tat ca ca ban ghi trong bang Danh_Muc
    public ArrayList<DanhMuc> getAllCategory(){
        String sql = "select * from [Danh_Muc]";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            ArrayList<DanhMuc> list = new ArrayList<>();
            while(rs.next()){
                DanhMuc dm = new DanhMuc(rs.getInt("cid"), rs.getString("ten_danh_muc"));
                list.add(dm);
            }
            return list;
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public void insertCategory(String tenDanhMuc){
        String sql = "insert into [Danh_Muc](ten_danh_muc) values(?)";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, tenDanhMuc);
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public void deleteCategory(int cid){
        String sql = "delete from [Danh_Muc] where cid = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public DanhMuc getCategoryByCid(int cid){
        String sql = "select * from [Danh_Muc] where cid = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();            
            if(rs.next()){
                return new DanhMuc(rs.getInt("cid"), rs.getString("ten_danh_muc"));                
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public void updateCategory(int cid, String tenDanhMuc){
        String sql = "update [Danh_Muc] set [ten_danh_muc] = ? where cid = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, tenDanhMuc);
            st.setInt(2, cid);
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public ArrayList<DanhMuc> search(String txt) {
        try {
            String sql = """
                         select * from [Danh_Muc]
                         where ([ten_danh_muc] like ?)""";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + txt + "%"); // Đặt giá trị cho tham số 1            
            ArrayList<DanhMuc> list = new ArrayList<>();
            ResultSet rs = st.executeQuery();           
            while (rs.next()) {
                DanhMuc dm = new DanhMuc(rs.getInt("cid"), rs.getString("ten_danh_muc"));
                list.add(dm);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
