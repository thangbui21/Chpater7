/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

/**
 *
 * @author Thắng Bùi
 */
public class KhachHang {

    private String maKH;
    private String name;
    private String loaiPhong;

    public KhachHang() {
    }

    public KhachHang(String maKH, String name, String loaiPhong) {
        this.maKH = maKH;
        this.name = name;
        this.loaiPhong = loaiPhong;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "maKH=" + maKH + ", name=" + name + ", loaiPhong=" + loaiPhong + '}';
    }

    public String showInfo() {
        return "Ma khach hang: " + maKH + "\n"
                + "Ten KH: " + name + "\n"
                + "Loai phong: " + loaiPhong + "\n"
                + "------------------------------\n";
    }

}
