package app.model;

import app.model.form.FormThongTinHocSinh;
import app.repository.NhanKhauRepo;

import java.sql.SQLException;

public class HocSinh {
    private Integer maHS;
    private Integer idNhanKhau;
    private String hocVan;

    public HocSinh(Integer maHS, Integer idNhanKhau, String hocVan) {
        this.maHS = maHS;
        this.idNhanKhau = idNhanKhau;
        this.hocVan = hocVan;
    }

    public HocSinh() {
    }

    public String toString(){
        return "HocSinh{maHS = " + maHS +
                ", idNhanKhau = " + idNhanKhau +
                ", hocVan = \'"+hocVan+"\' " +
                "}";
    }

    public FormThongTinHocSinh toFormThongTinHocSinh() throws SQLException {
        NhanKhauRepo nhanKhauRepo = new NhanKhauRepo();
        NhanKhau nhanKhau = nhanKhauRepo.findById(idNhanKhau);
        FormThongTinHocSinh formThongTinHocSinh = new FormThongTinHocSinh(maHS, idNhanKhau, nhanKhau.getHoTen(), nhanKhau.getNamSinh(), nhanKhau.getGioiTinh(), nhanKhau.getDiaChiHienNay(), nhanKhau.getTrinhDoHocVan());
        return formThongTinHocSinh;
    }

    public Integer getMaHS() {
        return maHS;
    }

    public void setMaHS(Integer maHS) {
        this.maHS = maHS;
    }

    public Integer getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(Integer idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public String getHocVan() {
        return hocVan;
    }

    public void setHocVan(String hocVan) {
        this.hocVan = hocVan;
    }
}
