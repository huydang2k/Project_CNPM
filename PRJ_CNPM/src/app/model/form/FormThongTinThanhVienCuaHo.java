package app.model.form;

import java.sql.Date;

public class FormThongTinThanhVienCuaHo {
    private int idHoKhau;
    private int idNhanKhau;
    private String hoTen;
    private Date ngaySinh;
    private String gioiTinh;
    private String diaChiHienNay;
    private String quanHeVoiChuHo;

    public FormThongTinThanhVienCuaHo(int idHoKhau, int idNhanKhau, String hoTen, Date ngaySinh, String gioiTinh, String diaChiHienNay, String quanHeVoiChuHo) {
        this.idHoKhau = idHoKhau;
        this.idNhanKhau = idNhanKhau;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChiHienNay = diaChiHienNay;
        this.quanHeVoiChuHo = quanHeVoiChuHo;
    }

    public FormThongTinThanhVienCuaHo() {
    }

    public int getIdHoKhau() {
        return idHoKhau;
    }

    public void setIdHoKhau(int idHoKhau) {
        this.idHoKhau = idHoKhau;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChiHienNay() {
        return diaChiHienNay;
    }

    public void setDiaChiHienNay(String diaChiHienNay) {
        this.diaChiHienNay = diaChiHienNay;
    }

    public String getQuanHeVoiChuHo() {
        return quanHeVoiChuHo;
    }

    public void setQuanHeVoiChuHo(String quanHeVoiChuHo) {
        this.quanHeVoiChuHo = quanHeVoiChuHo;
    }
}
