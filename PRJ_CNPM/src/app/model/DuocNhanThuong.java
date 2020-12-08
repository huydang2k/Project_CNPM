package app.model;

public class DuocNhanThuong {
    private Integer maDS;
    private Integer maHS;
    private String thanhTich;
    private Boolean minhChung;
    private String xepLoai;
    private Double mucThuong;
    private Boolean duocXacNhan;

    public DuocNhanThuong() {
    }

    public Boolean getMinhChung() {
        return minhChung;
    }

    public void setMinhChung(Boolean minhChung) {
        this.minhChung = minhChung;
    }

    public DuocNhanThuong(Integer maDS, Integer maHS, String thanhTich, Boolean minhChung, String xepLoai, Double mucThuong, Boolean duocXacNhan) {
        this.maDS = maDS;
        this.maHS = maHS;
        this.thanhTich = thanhTich;
        this.minhChung = minhChung;
        this.xepLoai = xepLoai;
        this.mucThuong = mucThuong;
        this.duocXacNhan = duocXacNhan;
    }

    public String toString(){
        return "DuocNhanThuong{ maDS = " + maDS +
                ", maHS = " + maHS +
                ", thanhTich = \'"+ thanhTich +"\'" +
                ", xepLoai = \'"+ xepLoai +"\'" +
                ", mucThuong = " + mucThuong +
                ", duocXacNhan = " + duocXacNhan +
                "}";
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

    public String getXepLoai() {
        return xepLoai;
    }

    public void setXepLoai(String xepLoai) {
        this.xepLoai = xepLoai;
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
}
