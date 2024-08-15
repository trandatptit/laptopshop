/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;
import java.text.DecimalFormat;

/**
 *
 * @author PC
 */
public class SanPham {
    private int id;
    private String tenSanPham, moTaNgan, moTaChiTiet;
    private double giaBan;
    private String anhDaiDien;
    private Date ngayDang;
    private Date ngaySua;
    private int soLuong;
    private DanhMuc danhMuc;

    public SanPham() {
    }

    public SanPham(int id, String tenSanPham, String moTaNgan, String moTaChiTiet, double giaBan, String anhDaiDien, Date ngayDang, Date ngaySua, int soLuong, DanhMuc danhMuc) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.moTaNgan = moTaNgan;
        this.moTaChiTiet = moTaChiTiet;
        this.giaBan = giaBan;
        this.anhDaiDien = anhDaiDien;
        this.ngayDang = ngayDang;
        this.ngaySua = ngaySua;
        this.soLuong = soLuong;
        this.danhMuc = danhMuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMoTaNgan() {
        return moTaNgan;
    }

    public void setMoTaNgan(String moTaNgan) {
        this.moTaNgan = moTaNgan;
    }

    public String getMoTaChiTiet() {
        return moTaChiTiet;
    }

    public void setMoTaChiTiet(String moTaChiTiet) {
        this.moTaChiTiet = moTaChiTiet;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public Date getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }
    
    //26/11/2023
    public String getFormattedGiaBan() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        return decimalFormat.format(giaBan);
    }
    
}
