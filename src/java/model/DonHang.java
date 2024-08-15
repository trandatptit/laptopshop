/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author trant
 */
public class DonHang {
    private NguoiDung nguoidung;
    private TrangThai trangthai;
    private int id;
    private int userid;
    private String nguoinhanhang;
    private String ngaydat;
    private String hinhthucthanhtoan;
    private int tongsoluong;
    private double tongtien, thanhtien;
    private String diachigiaohang;
    private String sdt;
    private int matrangthai;
    private String ghichu;
    

    public DonHang() {
    }
    
    public DonHang(NguoiDung nguoidung,TrangThai trangthai,int id, int userid, String nguoinhanhang, String ngaydat, String hinhthucthanhtoan, int tongsoluong, double tongtien, double thanhtien, String diachigiaohang, String sdt, int matrangthai, String ghichu) {
        this.nguoidung = nguoidung;
        this.trangthai = trangthai;
        this.id = id;
        this.userid = userid;
        this.nguoinhanhang = nguoinhanhang;
        this.ngaydat = ngaydat;
        this.hinhthucthanhtoan = hinhthucthanhtoan;
        this.tongsoluong = tongsoluong;
        this.tongtien = tongtien;
        this.thanhtien = thanhtien;
        this.diachigiaohang = diachigiaohang;
        this.sdt = sdt;
        this.matrangthai = matrangthai;
        this.ghichu = ghichu;
    }
    
    public DonHang(int id, int userid, String nguoinhanhang, String ngaydat, String hinhthucthanhtoan, int tongsoluong, double tongtien, double thanhtien, String diachigiaohang, String sdt, int matrangthai, String ghichu) {
        this.id = id;
        this.userid = userid;
        this.nguoinhanhang = nguoinhanhang;
        this.ngaydat = ngaydat;
        this.hinhthucthanhtoan = hinhthucthanhtoan;
        this.tongsoluong = tongsoluong;
        this.tongtien = tongtien;
        this.thanhtien = thanhtien;
        this.diachigiaohang = diachigiaohang;
        this.sdt = sdt;
        this.matrangthai = matrangthai;
        this.ghichu = ghichu;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public NguoiDung getNguoidung() {
        return nguoidung;
    }

    public void setNguoidung(NguoiDung nguoidung) {
        this.nguoidung = nguoidung;
    }

    public TrangThai getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(TrangThai trangthai) {
        this.trangthai = trangthai;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getNguoinhanhang() {
        return nguoinhanhang;
    }

    public void setNguoinhanhang(String nguoinhanhang) {
        this.nguoinhanhang = nguoinhanhang;
    }

    public String getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(String ngaydat) {
        this.ngaydat = ngaydat;
    }

    public String getHinhthucthanhtoan() {
        return hinhthucthanhtoan;
    }

    public void setHinhthucthanhtoan(String hinhthucthanhtoan) {
        this.hinhthucthanhtoan = hinhthucthanhtoan;
    }

    public int getTongsoluong() {
        return tongsoluong;
    }

    public void setTongsoluong(int tongsoluong) {
        this.tongsoluong = tongsoluong;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }

    public String getDiachigiaohang() {
        return diachigiaohang;
    }

    public void setDiachigiaohang(String diachigiaohang) {
        this.diachigiaohang = diachigiaohang;
    }

    public int getMatrangthai() {
        return matrangthai;
    }

    public void setMatrangthai(int matrangthai) {
        this.matrangthai = matrangthai;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
    
}
    