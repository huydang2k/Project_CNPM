package app.model;

public class DSPhatThuong {
    private Integer maDS;
    private String suKien;
    private String ngayTao;
    private Integer trangThai;
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

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Double getTongChiPhi() {
        return tongChiPhi;
    }

    public void setTongChiPhi(Double tongChiPhi) {
        this.tongChiPhi = tongChiPhi;
    }

    public DSPhatThuong(Integer maDS, String suKien, String ngayTao, Integer trangThai, Double tongChiPhi) {
        this.maDS = maDS;
        this.suKien = suKien;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.tongChiPhi = tongChiPhi;
    }

    public DSPhatThuong() {
    }
}
