package app.model.form;

public class FormDSPTChiTiet {
    private int idDS;
    private int idHocSinh;
    private int idNhanKhau;
    private String hoTen;
    private int namSinh;
    private int idHoKhau;
    private String thanhTich;
    private boolean minhChung;
    private String xepLoai;
    private double mucThuong;
    private boolean duocXacNhan;

    public FormDSPTChiTiet(int idDS, int idHocSinh, int idNhanKhau, String hoTen, int namSinh, int idHoKhau, String thanhTich, boolean minhChung, String xepLoai, double mucThuong, boolean duocXacNhan) {
        this.idDS = idDS;
        this.idHocSinh = idHocSinh;
        this.idNhanKhau = idNhanKhau;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.idHoKhau = idHoKhau;
        this.thanhTich = thanhTich;
        this.minhChung = minhChung;
        this.xepLoai = xepLoai;
        this.mucThuong = mucThuong;
        this.duocXacNhan = duocXacNhan;
    }

    @Override
    public String toString() {
        return "FormDSPTChiTiet{" +
                "idDS=" + idDS +
                ", idHocSinh=" + idHocSinh +
                ", idNhanKhau=" + idNhanKhau +
                ", hoTen='" + hoTen + '\'' +
                ", namSinh=" + namSinh +
                ", idHoKhau=" + idHoKhau +
                ", thanhTich='" + thanhTich + '\'' +
                ", minhChung=" + minhChung +
                ", xepLoai='" + xepLoai + '\'' +
                ", mucThuong=" + mucThuong +
                ", duocXacNhan=" + duocXacNhan +
                '}';
    }

    public int getIdDS() {
        return idDS;
    }

    public void setIdDS(int idDS) {
        this.idDS = idDS;
    }

    public int getIdHocSinh() {
        return idHocSinh;
    }

    public void setIdHocSinh(int idHocSinh) {
        this.idHocSinh = idHocSinh;
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

    public String getThanhTich() {
        return thanhTich;
    }

    public void setThanhTich(String thanhTich) {
        this.thanhTich = thanhTich;
    }

    public boolean isMinhChung() {
        return minhChung;
    }

    public void setMinhChung(boolean minhChung) {
        this.minhChung = minhChung;
    }

    public String getXepLoai() {
        return xepLoai;
    }

    public void setXepLoai(String xepLoai) {
        this.xepLoai = xepLoai;
    }

    public double getMucThuong() {
        return mucThuong;
    }

    public void setMucThuong(double mucThuong) {
        this.mucThuong = mucThuong;
    }

    public boolean isDuocXacNhan() {
        return duocXacNhan;
    }

    public void setDuocXacNhan(boolean duocXacNhan) {
        this.duocXacNhan = duocXacNhan;
    }

    public FormDSPTChiTiet() {

    }
}
