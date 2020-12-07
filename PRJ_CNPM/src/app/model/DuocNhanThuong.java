package app.model;

public class DuocNhanThuong {
    private Integer maDS;
    private Integer maHS;
    private String thanhTich;
    private String xeoLoai;
    private Double mucThuong;
    private Boolean duocXacNhan;

    public DuocNhanThuong() {
    }

    public Integer getMaDS() {
        return maDS;
    }

    public void setMaDS(Integer maDS) {
        this.maDS = maDS;
    }

    public Integer getMaHS() {
        return maHS;
    }

    public void setMaHS(Integer maHS) {
        this.maHS = maHS;
    }

    public String getThanhTich() {
        return thanhTich;
    }

    public void setThanhTich(String thanhTich) {
        this.thanhTich = thanhTich;
    }

    public String getXeoLoai() {
        return xeoLoai;
    }

    public void setXeoLoai(String xeoLoai) {
        this.xeoLoai = xeoLoai;
    }

    public Double getMucThuong() {
        return mucThuong;
    }

    public void setMucThuong(Double mucThuong) {
        this.mucThuong = mucThuong;
    }

    public Boolean getDuocXacNhan() {
        return duocXacNhan;
    }

    public void setDuocXacNhan(Boolean duocXacNhan) {
        this.duocXacNhan = duocXacNhan;
    }

    public DuocNhanThuong(Integer maDS, Integer maHS, String thanhTich, String xeoLoai, Double mucThuong, Boolean duocXacNhan) {
        this.maDS = maDS;
        this.maHS = maHS;
        this.thanhTich = thanhTich;
        this.xeoLoai = xeoLoai;
        this.mucThuong = mucThuong;
        this.duocXacNhan = duocXacNhan;
    }
}
