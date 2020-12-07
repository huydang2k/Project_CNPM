package app.model;

import java.sql.Date;

public class HoKhau {
    private Integer ID;
    private String maHoKhau;
    private Integer idChuHo;
    private String maKhuVuc;
    private String diaChi;
    private Date ngayLap;
    private Date ngayChuyenDi;
    private String lyDoChuyen;
    private Integer nguoiThucHien;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public HoKhau() {
    }

    public HoKhau(Integer ID, String maHoKhau, Integer idChuHo, String maKhuVuc, String diaChi, Date ngayLap, Date ngayChuyenDi, String lyDoChuyen, Integer nguoiThucHien) {
        this.ID = ID;
        this.maHoKhau = maHoKhau;
        this.idChuHo = idChuHo;
        this.maKhuVuc = maKhuVuc;
        this.diaChi = diaChi;
        this.ngayLap = ngayLap;
        this.ngayChuyenDi = ngayChuyenDi;
        this.lyDoChuyen = lyDoChuyen;
        this.nguoiThucHien = nguoiThucHien;
    }

    public String toString(){
        return "HoKhau{ID = " + ID +
                ", maHoKhau = \'"+ maHoKhau +"\'" +
                ", idChuHo = " + idChuHo +
                ", maKhuVuc = \'"+ maKhuVuc +"\'" +
                ", diaChi = \'"+ diaChi +"\'" +
                ", ngayLap = \'"+ ngayLap +"\'" +
                ", ngayChuyenDi = \'"+ ngayChuyenDi +"\'" +
                ", lyDoChuyen = \'"+ lyDoChuyen +"\'" +
                ", nguoiThucHien = " + nguoiThucHien +
                "}";
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getMaHoKhau() {
        return maHoKhau;
    }

    public void setMaHoKhau(String maHoKhau) {
        this.maHoKhau = maHoKhau;
    }

    public Integer getIdChuHo() {
        return idChuHo;
    }

    public void setIdChuHo(Integer idChuHo) {
        this.idChuHo = idChuHo;
    }

    public String getMaKhuVuc() {
        return maKhuVuc;
    }

    public void setMaKhuVuc(String maKhuVuc) {
        this.maKhuVuc = maKhuVuc;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public Date getNgayChuyenDi() {
        return ngayChuyenDi;
    }

    public void setNgayChuyenDi(Date ngayChuyenDi) {
        this.ngayChuyenDi = ngayChuyenDi;
    }

    public String getLyDoChuyen() {
        return lyDoChuyen;
    }

    public void setLyDoChuyen(String lyDoChuyen) {
        this.lyDoChuyen = lyDoChuyen;
    }

    public Integer getNguoiThucHien() {
        return nguoiThucHien;
    }

    public void setNguoiThucHien(Integer nguoiThucHien) {
        this.nguoiThucHien = nguoiThucHien;
    }
}
