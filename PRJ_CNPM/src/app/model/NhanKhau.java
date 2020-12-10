package app.model;

import java.sql.Date;

public class NhanKhau {
    private Integer ID;
    private String maNhanKhau;
    private String hoTen;
    private Date namSinh;
    private String gioiTinh;
    private String diaChiHienNay;
    private String trinhDoHocVan;
    private String trinhDoChuyenMon;
    private String ngheNghiep;
    private String noiLamViec;

    public NhanKhau() {
    }

    public NhanKhau(Integer ID, String maNhanKhau, String hoTen, Date namSing, String gioiTinh, String diaChiHienNay, String trinhDoHocVan, String trinhDoChuyenMon, String ngheNghiep, String noiLamViec) {
        this.ID = ID;
        this.maNhanKhau = maNhanKhau;
        this.hoTen = hoTen;
        this.namSinh = namSing;
        this.gioiTinh = gioiTinh;
        this.diaChiHienNay = diaChiHienNay;
        this.trinhDoHocVan = trinhDoHocVan;
        this.trinhDoChuyenMon = trinhDoChuyenMon;
        this.ngheNghiep = ngheNghiep;
        this.noiLamViec = noiLamViec;
    }

    public String toString(){
        return "NhanKhau{ ID = " + ID +
                ",  maNhanKhau = \'"+ maNhanKhau +"\'" +
                ", namSinh = \'"+ namSinh +"\'" +
                ", gioiTinh = \'"+ gioiTinh +"\'" +
                ", diaChiHienNay = \'"+ diaChiHienNay +"\'" +
                ", trinhDoHocVan = \'"+ trinhDoHocVan +"\'" +
                ", trinhDoChuyenMon = \'"+ trinhDoChuyenMon +"\'" +
                ", ngheNghiep = \'"+ ngheNghiep +"\'" +
                ", noiLamViec = \'"+ noiLamViec +"\'" +
                "}";
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getMaNhanKhau() {
        return maNhanKhau;
    }

    public void setMaNhanKhau(String maNhanKhau) {
        this.maNhanKhau = maNhanKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(Date namSinh) {
        this.namSinh = namSinh;
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

    public String getTrinhDoChuyenMon() {
        return trinhDoChuyenMon;
    }

    public void setTrinhDoChuyenMon(String trinhDoChuyenMon) {
        this.trinhDoChuyenMon = trinhDoChuyenMon;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getNoiLamViec() {
        return noiLamViec;
    }

    public void setNoiLamViec(String noiLamViec) {
        this.noiLamViec = noiLamViec;
    }
}
