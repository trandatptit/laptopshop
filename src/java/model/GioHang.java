/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trant
 */
public class GioHang {
    private List<Item> items;

    public GioHang() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }
    public int getSoluongById(int id) {
        return getItemById(id).getSoluong();
    }
    private Item getItemById(int id){
        for(Item i : items){
            if(i.getSanPham().getId() == id){
                return i;
            }
        }
        return null;
    }
    public void addItem (Item t) {
        if(getItemById(t.getSanPham().getId()) != null){
            Item m = getItemById(t.getSanPham().getId());
            m.setSoluong(m.getSoluong()+t.getSoluong());
        }
        else {
            items.add(t);
        }
        
    }
    public void removeItem(int id) {
        if(getItemById(id) != null){
            items.remove(getItemById(id));
        }
    }
    public int getTongSoluong(){
        int cnt = 0;
        for(Item x : items){
            cnt += getSoluongById(x.getSanPham().getId());
        }
        return cnt;
    }
    public double getTongtien(){
        double  t = 0;
        for(Item i : items){
            t += (i.getSoluong()*i.getDongia());
        }
        return t;
    }
    private SanPham getSanPhamById(int id, List<SanPham> list){
        for(SanPham i : list){
            if(i.getId()==id){
                return i;
            }
        }
        return null;
    }
    public GioHang(String txt, List<SanPham> list){
        items = new ArrayList<>();
        try {
            if(txt != null && txt.length()!=0){
                String[] s = txt.split("/");
                for(String i : s){
                    String[] n = i.split(":");
                    int id = Integer.parseInt(n[0]);
                    int soluong = Integer.parseInt(n[1]);
                    SanPham p = getSanPhamById(id, list);
                    Item t = new Item(p, soluong, p.getGiaBan());
                    addItem(t);
                }
            }
        } catch (NumberFormatException e) {
            
        }
        
    }
}
