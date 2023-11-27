/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class ThongSo {
    private int id;
    private String cpu, ram, ssd, card, congKetNoi, manHinh, heDieuHanh;
    private int thoiDiemRaMat;
    private SanPham sanPham;

    public ThongSo() {
    }

    public ThongSo(int id, String cpu, String ram, String ssd, String card, String congKetNoi, String manHinh, String heDieuHanh, int thoiDiemRaMat, SanPham sanPham) {
        this.id = id;
        this.cpu = cpu;
        this.ram = ram;
        this.ssd = ssd;
        this.card = card;
        this.congKetNoi = congKetNoi;
        this.manHinh = manHinh;
        this.heDieuHanh = heDieuHanh;
        this.thoiDiemRaMat = thoiDiemRaMat;
        this.sanPham = sanPham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getSsd() {
        return ssd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCongKetNoi() {
        return congKetNoi;
    }

    public void setCongKetNoi(String congKetNoi) {
        this.congKetNoi = congKetNoi;
    }

    public String getManHinh() {
        return manHinh;
    }

    public void setManHinh(String manHinh) {
        this.manHinh = manHinh;
    }

    public String getHeDieuHanh() {
        return heDieuHanh;
    }

    public void setHeDieuHanh(String heDieuHanh) {
        this.heDieuHanh = heDieuHanh;
    }

    public int getThoiDiemRaMat() {
        return thoiDiemRaMat;
    }

    public void setThoiDiemRaMat(int thoiDiemRaMat) {
        this.thoiDiemRaMat = thoiDiemRaMat;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }
    
    
}
