/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import model.DanhMuc;
import model.SanPham;

/**
 *
 * @author PC
 */
public class SanPhamDAO extends DBContext {

    // lay toan bo san pham tu trong table San_Pham
    public ArrayList<SanPham> getAllProduct() {
        try {
            String sql = "select * from [San_Pham]";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            ArrayList<SanPham> list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String tenSanPham = rs.getString("ten_san_pham");
                String moTaNgan = rs.getString("mo_ta_ngan");
                String moTaChiTiet = rs.getString("mo_ta_chi_tiet");
                double giaBan = rs.getDouble("gia_ban");
                String anhDaiDien = rs.getString("anh_dai_dien");
                Date ngayDang = rs.getDate("ngay_dang");
                Date ngaySua = rs.getDate("ngay_sua");
                int soLuong = rs.getInt("so_luong");
                int cid = rs.getInt("cid");
                DanhMucDAO danhMucDao = new DanhMucDAO();
                DanhMuc danhMuc = danhMucDao.getCategoryByCid(cid);
                SanPham sanPham = new SanPham(id, tenSanPham, moTaNgan, moTaChiTiet, giaBan, anhDaiDien, ngayDang, ngaySua, soLuong, danhMuc);
                list.add(sanPham);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public ArrayList<SanPham> search(String txt) {
        try {
            String sql = "select * from [San_Pham] where LOWER([ten_san_pham]) like ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + txt + "%");
            ResultSet rs = st.executeQuery();
            ArrayList<SanPham> list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String tenSanPham = rs.getString("ten_san_pham");
                String moTaNgan = rs.getString("mo_ta_ngan");
                String moTaChiTiet = rs.getString("mo_ta_chi_tiet");
                double giaBan = rs.getDouble("gia_ban");
                String anhDaiDien = rs.getString("anh_dai_dien");
                Date ngayDang = rs.getDate("ngay_dang");
                Date ngaySua = rs.getDate("ngay_sua");
                int soLuong = rs.getInt("so_luong");
                int cid = rs.getInt("cid");
                DanhMucDAO danhMucDao = new DanhMucDAO();
                DanhMuc danhMuc = danhMucDao.getCategoryByCid(cid);
                SanPham sanPham = new SanPham(id, tenSanPham, moTaNgan, moTaChiTiet, giaBan, anhDaiDien, ngayDang, ngaySua, soLuong, danhMuc);
                list.add(sanPham);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public SanPham getProductById(int id) {
        try {
            String sql = "select * from [San_Pham] where id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String tenSanPham = rs.getString("ten_san_pham");
                String moTaNgan = rs.getString("mo_ta_ngan");
                String moTaChiTiet = rs.getString("mo_ta_chi_tiet");
                double giaBan = rs.getDouble("gia_ban");
                String anhDaiDien = rs.getString("anh_dai_dien");
                Date ngayDang = rs.getDate("ngay_dang");
                Date ngaySua = rs.getDate("ngay_sua");
                int soLuong = rs.getInt("so_luong");
                int cid = rs.getInt("cid");
                DanhMucDAO danhMucDao = new DanhMucDAO();
                DanhMuc danhMuc = danhMucDao.getCategoryByCid(cid);
                return new SanPham(id, tenSanPham, moTaNgan, moTaChiTiet, giaBan, anhDaiDien, ngayDang, ngaySua, soLuong, danhMuc);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleleProduct(int id) {
        try {
            connection.setAutoCommit(false); // Bắt đầu giao dịch
            String sql = "delete from [San_Pham] where id = ?";
            String sql1 = "delete from [Thong_So] where pid = ?";
            String sql2 = "delete from [Hinh_Anh_San_Pham] where pid = ?";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setInt(1, id);
            st1.executeUpdate();
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setInt(1, id);
            st2.executeUpdate();
            PreparedStatement st3 = connection.prepareStatement(sql);
            st3.setInt(1, id);
            st3.executeUpdate();
            connection.commit(); // Kết thúc giao dịch thành công
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

    // them thong tin vao san pham
    public void insert(String tenSanPham, String moTaNgan, String moTaChiTiet, double giaBan, int soLuong, String anhDaiDien, int cid, String cpu, String ram, String ssd, String card, String congKetNoi, String manHinh, String heDieuHanh, int thoiDiemRaMat, ArrayList<String> anhSanPham) {
        try {
            connection.setAutoCommit(false); // Tat tu dong commit
            String sql = "insert into [San_Pham] ([ten_san_pham], [mo_ta_ngan], [mo_ta_chi_tiet], [gia_ban], [so_luong], [anh_dai_dien], [cid]) "
                    + "values(?,?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, tenSanPham);
            st.setString(2, moTaNgan);
            st.setString(3, moTaChiTiet);
            st.setDouble(4, giaBan);
            st.setInt(5, soLuong);
            st.setString(6, anhDaiDien);
            st.setInt(7, cid);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                // Xử lý lỗi ở đây (ví dụ: in ra thông báo lỗi)
            } else {
                String selectIdQuery = "SELECT @@IDENTITY AS id";
                PreparedStatement idStmt = connection.prepareStatement(selectIdQuery);
                ResultSet idResult = idStmt.executeQuery();
                if (idResult.next()) {
                    int productId = idResult.getInt("id");

                    // Lưu thông tin kỹ thuật vào bảng thông số kỹ thuật
                    String insertTechnicalInfoQuery = "INSERT INTO [Thong_So] (cpu, ram, ssd, card, [cong_ket_noi], [man_hinh], [he_dieu_hanh], [thoi_diem_ra_mat], pid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try {
                        PreparedStatement technicalInfoStmt = connection.prepareStatement(insertTechnicalInfoQuery);
                        technicalInfoStmt.setString(1, cpu);
                        technicalInfoStmt.setString(2, ram);
                        technicalInfoStmt.setString(3, ssd);
                        technicalInfoStmt.setString(4, card);
                        technicalInfoStmt.setString(5, congKetNoi);
                        technicalInfoStmt.setString(6, manHinh);
                        technicalInfoStmt.setString(7, heDieuHanh);
                        technicalInfoStmt.setInt(8, thoiDiemRaMat);
                        technicalInfoStmt.setInt(9, productId);
                        int technicalInfoRows = technicalInfoStmt.executeUpdate();
                        boolean check = true;
                        for (String s : anhSanPham) {
                            String insertImageQuery = "insert into [Hinh_Anh_San_Pham] (pid, url) values(?,?)";
                            PreparedStatement st1 = connection.prepareStatement(insertImageQuery);
                            st1.setInt(1, productId);
                            st1.setString(2, s);
                            int imageRows = st1.executeUpdate();
                            if (imageRows == 0) {
                                check = false;
                                break;
                            }
                        }
                        if (technicalInfoRows != 0 && check) {
                            connection.commit();
                        }
                    } catch (SQLException e) {
                        System.out.println(e);
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

    public void updateProductInDatabase(int productId, Map<String, Object> updatedFieldsSanPham, Map<String, Object> updatedFieldsThongSo, ArrayList<String> anhSanPham) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement st = null;
            if (!updatedFieldsSanPham.isEmpty()) {
                // Create the SQL update statement for [San_Pham]
                StringBuilder productUpdateSQL = new StringBuilder("UPDATE [San_Pham] SET ");
                boolean firstField = true;

                for (Map.Entry<String, Object> entry : updatedFieldsSanPham.entrySet()) {
                    String fieldName = entry.getKey();
                    if (!firstField) {
                        productUpdateSQL.append(", ");
                    }
                    productUpdateSQL.append(fieldName).append(" = ?");
                    firstField = false;
                }
                productUpdateSQL.append(" WHERE id = ?");

                // Create a batch PreparedStatement for [San_Pham]
                st = connection.prepareStatement(productUpdateSQL.toString());

                int i = 1;
                for (Map.Entry<String, Object> entry : updatedFieldsSanPham.entrySet()) {
                    Object fieldValue = entry.getValue();
                    st.setObject(i, fieldValue);
                    i++;
                }
                st.setInt(i, productId);
                st.addBatch();
            }

            PreparedStatement st1 = null;
            if (!updatedFieldsThongSo.isEmpty()) {
                // Create the SQL update statement for [Thong_So]
                StringBuilder technicalInfoUpdateSQL = new StringBuilder("UPDATE [Thong_So] SET ");
                boolean firstField1 = true;

                for (Map.Entry<String, Object> entry : updatedFieldsThongSo.entrySet()) {
                    String fieldName = entry.getKey();
                    if (!firstField1) {
                        technicalInfoUpdateSQL.append(", ");
                    }
                    technicalInfoUpdateSQL.append(fieldName).append(" = ?");
                    firstField1 = false;
                }
                technicalInfoUpdateSQL.append(" WHERE pid = ?");

                // Create a batch PreparedStatement for [Thong_So]
                st1 = connection.prepareStatement(technicalInfoUpdateSQL.toString());

                int j = 1;
                for (Map.Entry<String, Object> entry : updatedFieldsThongSo.entrySet()) {
                    Object fieldValue = entry.getValue();
                    st1.setObject(j, fieldValue);
                    j++;
                }
                st1.setInt(j, productId);
                st1.addBatch();
            }

            if (!anhSanPham.isEmpty()) {
                // Delete old images from [Hinh_Anh_San_Pham]
                String deleteImageSQL = "DELETE FROM [Hinh_Anh_San_Pham] WHERE pid = ?";
                PreparedStatement deleteImageStatement = connection.prepareStatement(deleteImageSQL);
                deleteImageStatement.setInt(1, productId);
                deleteImageStatement.executeUpdate();

                // Insert new images into [Hinh_Anh_San_Pham]
                String insertImageQuery = "INSERT INTO [Hinh_Anh_San_Pham] (pid, url) VALUES (?, ?)";
                PreparedStatement insertImageStatement = connection.prepareStatement(insertImageQuery);

                for (String url : anhSanPham) {
                    insertImageStatement.setInt(1, productId);
                    insertImageStatement.setString(2, url);
                    insertImageStatement.addBatch();
                }
                insertImageStatement.executeBatch();
            }

            // Execute all batches
            if (st != null) {
                st.executeBatch();
            }
            if (st1 != null) {
                st1.executeBatch();
            }

            connection.commit();
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
    }


}
