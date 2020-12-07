package app.model;

import java.sql.Date;

public class DSPhatThuong {
    private Integer maDS;
    private String suKien;
    private Date ngayTao;
    private Integer trangThai;
    private Double tongChiPhi;

    public DSPhatThuong() {
    }

    public DSPhatThuong(Integer maDS, String suKien, Date ngayTao, Integer trangThai, Double tongChiPhi) {
        this.maDS = maDS;
        this.suKien = suKien;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.tongChiPhi = tongChiPhi;
    }

    public String toString(){
        return "DSPhatThuong{maDS = " + maDS +
                ", suKien = \'"+ suKien +"\'" +
                ", ngayTao = \'"+ ngayTao +"\'" +
                ", trangThai = " + trangThai +
                ", tongChiPhi = " + tongChiPhi +
                "}";
    }

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

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
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
}
