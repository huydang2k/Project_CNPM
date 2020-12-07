package app.model;

public class DuocNhanQua {
    private Integer maDS;
    private Integer idNhanKhau;
    private String phanQua;
    private Double mucQua;
    private Boolean duocXacNhan;

    public Integer getMaDS() {
        return maDS;
    }

    public void setMaDS(Integer maDS) {
        this.maDS = maDS;
    }

    public Integer getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(Integer idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public String getPhanQua() {
        return phanQua;
    }

    public void setPhanQua(String phanQua) {
        this.phanQua = phanQua;
    }

    public Double getMucQua() {
        return mucQua;
    }

    public void setMucQua(Double mucQua) {
        this.mucQua = mucQua;
    }

    public Boolean getDuocXacNhan() {
        return duocXacNhan;
    }

    public void setDuocXacNhan(Boolean duocXacNhan) {
        this.duocXacNhan = duocXacNhan;
    }

    public DuocNhanQua(Integer maDS, Integer idNhanKhau, String phanQua, Double mucQua, Boolean duocXacNhan) {
        this.maDS = maDS;
        this.idNhanKhau = idNhanKhau;
        this.phanQua = phanQua;
        this.mucQua = mucQua;
        this.duocXacNhan = duocXacNhan;
    }

    public DuocNhanQua() {
    }
}
