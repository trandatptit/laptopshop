/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import model.NguoiDung;
import model.VaiTro;

/**
 *
 * @author PC
 */
public class NguoiDungDAO extends DBContext {

    //register
    public void register(String userName, String email, String sdt, String address, String password) {
        try {
            connection.setAutoCommit(false); // Bắt đầu giao dịch
            String sql = "insert into [Nguoi_Dung] (ten, email, sdt, [dia_chi], [mat_khau]) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            st.setString(2, email);
            st.setString(3, sdt);
            st.setString(4, address);
            st.setString(5, password);
            int check = st.executeUpdate();
            if (check == 0) {

            } else {
                String selectIdQuery = "SELECT @@IDENTITY AS id";
                PreparedStatement idStmt = connection.prepareStatement(selectIdQuery);
                ResultSet idResult = idStmt.executeQuery();
                if (idResult.next()) {
                    int userId = idResult.getInt("id");

                    // luu thong tin vao bang vai tro nguoi dung 
                    String sql1 = "insert into [Vai_Tro_Nguoi_Dung] (rid, uid) values(?, ?)";
                    PreparedStatement st1 = connection.prepareStatement(sql1);
                    st1.setInt(1, 1);
                    st1.setInt(2, userId);
                    int check1 = st1.executeUpdate();
                    if (check1 != 0) {
                        connection.commit();
                    }
                }
            }

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback nếu có lỗi
                } catch (SQLException ex) {
                    System.out.println("Lỗi trong quá trình rollback: " + ex.getMessage());
                }
            }
            System.out.println("Loi trong qua trinh them du lieu: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true); // Bật lại chế độ tự động commit
                } catch (SQLException ex) {
                    System.out.println("Lỗi trong quá trình bật lại chế độ tự động commit: " + ex.getMessage());
                }
            }

        }
    }

    public NguoiDung login(String email, String password) {
        try {
            String sql = """
                         select nd.*, vt.* from
                         Nguoi_Dung as nd join Vai_Tro_Nguoi_Dung as vtnd
                         on nd.id = vtnd.uid
                         join Vai_Tro as vt 
                         on vt.id = vtnd.rid
                         where nd.email = ? and nd.mat_khau = ?""";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            ArrayList<VaiTro> listRole = new ArrayList<>();
            int id = 0;
            String ten = null, sdt = null, diaChi = null, avatar = null;
            while (rs.next()) {
                id = rs.getInt(1);
                ten = rs.getString(2);
                sdt = rs.getString(4);
                diaChi = rs.getString(5);
                avatar = rs.getString(7);
                int rid = rs.getInt(8);
                String tenVaiTro = rs.getString(9);
                VaiTro vt = new VaiTro(rid, tenVaiTro);
                listRole.add(vt);
            }
            if (listRole.isEmpty()) {
                return null;
            } else {
                return new NguoiDung(id, ten, email, sdt, diaChi, avatar, listRole);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // lay khi nguoi dung dang nhap roi
    public NguoiDung getUser(String email) {
        try {
            String sql = """
                         select nd.*, vt.* from
                         Nguoi_Dung as nd join Vai_Tro_Nguoi_Dung as vtnd
                         on nd.id = vtnd.uid
                         join Vai_Tro as vt 
                         on vt.id = vtnd.rid
                         where nd.email = ?""";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            ArrayList<VaiTro> listRole = new ArrayList<>();
            int id = 0;
            String ten = null, sdt = null, diaChi = null, avatar = null;
            while (rs.next()) {
                id = rs.getInt(1);
                ten = rs.getString(2);
                sdt = rs.getString(4);
                diaChi = rs.getString(5);
                avatar = rs.getString(7);
                int rid = rs.getInt(8);
                String tenVaiTro = rs.getString(9);
                VaiTro vt = new VaiTro(rid, tenVaiTro);
                listRole.add(vt);
            }
            if (listRole.isEmpty()) {
                return null;
            } else {
                return new NguoiDung(id, ten, email, sdt, diaChi, avatar, listRole);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean isEmailAssociated(String email) {
        String sql = "SELECT COUNT(*) FROM [Nguoi_Dung] WHERE email = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // If count > 0, the email is associated with at least one user.
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false; // If there was an error or the email is not associated with any user.
    }

    public boolean updateInfo(String email, Map<String, Object> updatedFieldsNguoiDung) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement st;
            if (!updatedFieldsNguoiDung.isEmpty()) {
                // Create the SQL update statement for [San_Pham]
                StringBuilder userUpdateSQL = new StringBuilder("UPDATE [Nguoi_Dung] SET ");
                boolean firstField = true;

                for (Map.Entry<String, Object> entry : updatedFieldsNguoiDung.entrySet()) {
                    String fieldName = entry.getKey();
                    if (!firstField) {
                        userUpdateSQL.append(", ");
                    }
                    userUpdateSQL.append(fieldName).append(" = ?");
                    firstField = false;
                }
                userUpdateSQL.append(" WHERE email = ?");

                // Create a batch PreparedStatement for [San_Pham]
                st = connection.prepareStatement(userUpdateSQL.toString());

                int i = 1;
                for (Map.Entry<String, Object> entry : updatedFieldsNguoiDung.entrySet()) {
                    Object fieldValue = entry.getValue();
                    st.setObject(i, fieldValue);
                    i++;
                }
                st.setString(i, email);
                int rowCount = st.executeUpdate();
                return rowCount > 0;
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println("Loi trong qua trinh rollback: " + ex.getMessage());
                }
            }
            System.out.println("Loi trong qua trinh cap nhat du liu: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    System.out.println("Loi trong qua trinh bat lai che đo tu đong commit: " + ex.getMessage());
                }
            }
        }
        return false;
    }

    public boolean updatePassword(String email, String newPassword) { // dung khi nguoi dung quen mat khau
        String sql = "update [Nguoi_Dung] set [mat_khau] = ? where email = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, newPassword);
            stm.setString(2, email);
            int rowCount = stm.executeUpdate();
            connection.close();
            return rowCount > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean changePassword(String currentPass, String newPass) {
        try {
            String sql = "update [Nguoi_Dung] set [mat_khau] = ? where [mat_khau] = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newPass);
            st.setString(2, currentPass);
            int rowCount = st.executeUpdate();
            connection.close();
            return rowCount > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<NguoiDung> getAllUser() {
        try {
            String sql = "select * from Nguoi_Dung";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            ArrayList<NguoiDung> list = new ArrayList<>();
            int id = 0;
            String ten = null, email = null, sdt = null, diaChi = null, avatar = null;            
            while (rs.next()) {
                id = rs.getInt(1);
                ten = rs.getString(2);
                email = rs.getString(3);
                sdt = rs.getString(4);
                diaChi = rs.getString(5);
                avatar = rs.getString(7);
                ArrayList<VaiTro> listRole = new VaiTroDAO().getAllRoleUser(id);
                if (isAdmin(listRole)) {
                    continue;
                }
                NguoiDung nd = new NguoiDung(id, ten, email, sdt, diaChi, avatar, listRole);
                list.add(nd);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<NguoiDung> search(String txt) {
        try {
            String sql = """
                         select * from [Nguoi_Dung]
                         where ([ten] like ?) or (email like ?)""";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + txt + "%"); // Đặt giá trị cho tham số 1
            st.setString(2, "%" + txt + "%"); // Đặt giá trị cho tham số 2
            ArrayList<NguoiDung> list = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            int id = 0;
            String ten = null, email = null, sdt = null, diaChi = null, avatar = null;            
            while (rs.next()) {
                id = rs.getInt(1);
                ten = rs.getString(2);
                email = rs.getString(3);
                sdt = rs.getString(4);
                diaChi = rs.getString(5);
                avatar = rs.getString(7);
                ArrayList<VaiTro> listRole = new VaiTroDAO().getAllRoleUser(id);
                if (isAdmin(listRole)) {
                    continue;
                }
                NguoiDung nd = new NguoiDung(id, ten, email, sdt, diaChi, avatar, listRole);
                list.add(nd);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    private boolean isAdmin(ArrayList<VaiTro> listRole){
        for(VaiTro vt : listRole){
            if(vt.getId() == 2){
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteAccount(int id){
        try {
            connection.setAutoCommit(false); // Bắt đầu giao dịch
            String sql1 = "delete from [Vai_Tro_Nguoi_Dung] where uid = ?";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setInt(1, id);
            st1.executeUpdate();
            String sql2 = "delete from [Nguoi_Dung] where id = ?";
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setInt(1, id);
            st2.executeUpdate();                       
            connection.commit(); // Kết thúc giao dịch thành công
            return true;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback nếu có lỗi
                } catch (SQLException ex) {
                    System.out.println("Lỗi trong quá trình rollback: " + ex.getMessage());
                }
            }
            System.out.println("Loi trong qua trinh them du lieu: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true); // Bật lại chế độ tự động commit
                } catch (SQLException ex) {
                    System.out.println("Lỗi trong quá trình bật lại chế độ tự động commit: " + ex.getMessage());
                }
            }

        }        
        return false;
    }
    //24/11/2023
    public NguoiDung getNguoiDungById(int id){
        try {
            String sql = "select * from Nguoi_Dung where id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            String ten = null, email = null, sdt = null, diaChi = null;
            if(rs.next()){
                ten = rs.getString(2);
                email = rs.getString(3);
                sdt = rs.getString(4);
                diaChi = rs.getString(5);
                return new NguoiDung(id, ten, email, sdt, diaChi);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
