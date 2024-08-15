/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class NguoiDung {

    private int id;
    private String ten, email, sdt, diaChi, avatar;
    private ArrayList<VaiTro> listRole;

    public NguoiDung() {
    }
    //24-11-2023
    public NguoiDung(int id, String ten, String email, String sdt, String diaChi) {
        this.id = id;
        this.ten = ten;
        this.email = email;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public NguoiDung(int id, String ten, String email, String sdt, String diaChi, String avatar, ArrayList<VaiTro> listRole) {
        this.id = id;
        this.ten = ten;
        this.email = email;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.avatar = avatar;
        this.listRole = listRole;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public ArrayList<VaiTro> getListRole() {
        return listRole;
    }

    public void setListRole(ArrayList<VaiTro> listRole) {
        this.listRole = listRole;
    }

}
