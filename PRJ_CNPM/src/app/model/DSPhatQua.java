package app.model;

public class DSPhatQua {
    private Integer maDS;
    private String suKien;
    private String ngayTao;
    private Boolean trangThai;
    private Double tongChiPhi;

    public Integer getMaDS() {
        return maDS;
    }

    public void setMaDS(Integer maDS) {
        this.maDS = maDS;
    }

    public String getSuKien() {
        return suKien;
    }

    public void setSuKien(String suKien) {
        this.suKien = suKien;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Double getTongChiPhi() {
        return tongChiPhi;
    }

    public void setTongChiPhi(Double tongChiPhi) {
        this.tongChiPhi = tongChiPhi;
    }

    public DSPhatQua(Integer maDS, String suKien, String ngayTao, Boolean trangThai, Double tongChiPhi) {
        this.maDS = maDS;
        this.suKien = suKien;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.tongChiPhi = tongChiPhi;
    }

    public DSPhatQua() {
    }
}
