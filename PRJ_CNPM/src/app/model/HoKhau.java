package app.model;

public class HoKhau {
    private int ID;
    private String maHoKhau;
    private int idChuHo;
    private String maKhuVuc;
    private String diaChi;
    private String ngayLap;
    private String ngayChuyenDi;
    private String lyDoChuyen;
    private String NguoiThucHien;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public HoKhau() {
    }

    public HoKhau(int ID, String maHoKhau, int idChuHo, String maKhuVuc, String diaChi, String ngayLap, String ngayChuyenDi, String lyDoChuyen, String nguoiThucHien) {
        this.ID = ID;
        this.maHoKhau = maHoKhau;
        this.idChuHo = idChuHo;
        this.maKhuVuc = maKhuVuc;
        this.diaChi = diaChi;
        this.ngayLap = ngayLap;
        this.ngayChuyenDi = ngayChuyenDi;
        this.lyDoChuyen = lyDoChuyen;
        NguoiThucHien = nguoiThucHien;
    }

    public int getIdChuHo() {
        return idChuHo;
    }

    public void setIdChuHo(int idChuHo) {
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

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getNgayChuyenDi() {
        return ngayChuyenDi;
    }

    public void setNgayChuyenDi(String ngayChuyenDi) {
        this.ngayChuyenDi = ngayChuyenDi;
    }

    public String getLyDoChuyen() {
        return lyDoChuyen;
    }

    public void setLyDoChuyen(String lyDoChuyen) {
        this.lyDoChuyen = lyDoChuyen;
    }

    public String getNguoiThucHien() {
        return NguoiThucHien;
    }

    public void setNguoiThucHien(String nguoiThucHien) {
        NguoiThucHien = nguoiThucHien;
    }

    public String getMaHoKhau() {
        return maHoKhau;
    }

    public void setMaHoKhau(String maHoKhau) {
        this.maHoKhau = maHoKhau;
    }
}
