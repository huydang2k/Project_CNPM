package app.model;

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
