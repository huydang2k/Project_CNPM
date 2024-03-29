package app.model.form;



public class FormDSPQChiTiet {
    private int idDS;
    private int idNhanKhau;
    private String hoTen;
    private int namSinh;
    private int idHoKhau;
    private String phanQua;
    private double mucQua;
    private boolean duocXacNhan;

    public FormDSPQChiTiet(int idDS, int idNhanKhau, String hoTen, int namSinh, int idHoKhau, String phanQua, double mucQua, boolean duocXacNhan) {
        this.idDS = idDS;
        this.idNhanKhau = idNhanKhau;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.idHoKhau = idHoKhau;
        this.phanQua = phanQua;
        this.mucQua = mucQua;
        this.duocXacNhan = duocXacNhan;
    }

    public FormDSPQChiTiet() {
    }

    public int getIdDS() {
        return idDS;
    }

    public void setIdDS(int idDS) {
        this.idDS = idDS;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public int getIdHoKhau() {
        return idHoKhau;
    }

    public void setIdHoKhau(int idHoKhau) {
        this.idHoKhau = idHoKhau;
    }

    public String getPhanQua() {
        return phanQua;
    }

    public void setPhanQua(String phanQua) {
        this.phanQua = phanQua;
    }

    public double getMucQua() {
        return mucQua;
    }

    public void setMucQua(double mucQua) {
        this.mucQua = mucQua;
    }

    public boolean isDuocXacNhan() {
        return duocXacNhan;
    }

    public void setDuocXacNhan(boolean duocXacNhan) {
        this.duocXacNhan = duocXacNhan;
    }
}
