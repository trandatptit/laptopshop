/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author trant
 */
public class ChiTietDonHang {
    private DonHang donHang;
    private SanPham sanPham;
    private int oid, pid, soluong;
    private double dongia;

    public ChiTietDonHang() {
    }

    public ChiTietDonHang(DonHang donHang, SanPham sanPham, int oid, int pid, int soluong, double dongia) {
        this.donHang = donHang;
        this.sanPham = sanPham;
        this.oid = oid;
        this.pid = pid;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public DonHang getDonHang() {
        return donHang;
    }

    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }
    
}