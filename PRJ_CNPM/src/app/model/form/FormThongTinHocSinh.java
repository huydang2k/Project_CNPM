package app.model.form;

import java.sql.Date;

public class FormThongTinHocSinh {
    private int maHS;
    private int maNhanKhau;
    private String hoTen;
    private Date ngaySinh;
    private String gioiTinh;
    private String diaChiHienNay;
    private String trinhDoHocVan;

    public FormThongTinHocSinh(int maHS, int maNhanKhau, String hoTen, Date ngaySinh, String gioiTinh, String diaChiHienNay, String trinhDoHocVan) {
        this.maHS = maHS;
        this.maNhanKhau = maNhanKhau;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChiHienNay = diaChiHienNay;
        this.trinhDoHocVan = trinhDoHocVan;
    }

    public FormThongTinHocSinh() {
    }

    public int getMaHS() {
        return maHS;
    }

    public void setMaHS(int maHS) {
        this.maHS = maHS;
    }

    public int getMaNhanKhau() {
        return maNhanKhau;
    }

    public void setMaNhanKhau(int maNhanKhau) {
        this.maNhanKhau = maNhanKhau;
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

    public String getTrinhDoHocVan() {
        return trinhDoHocVan;
    }

    public void setTrinhDoHocVan(String trinhDoHocVan) {
        this.trinhDoHocVan = trinhDoHocVan;
    }
}
