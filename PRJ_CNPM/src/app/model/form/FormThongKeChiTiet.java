package app.model.form;

public class FormThongKeChiTiet {
    private String hoTenChuHo;
    private int hoGiaDinh;
    private int soPhanQua;
    private double soTien;

    @Override
    public String toString() {
        return "FormThongKeChiTiet{" +
                "hoTenChuHo='" + hoTenChuHo + '\'' +
                ", hoGiaDinh=" + hoGiaDinh +
                ", soPhanQua=" + soPhanQua +
                ", soTien=" + soTien +
                '}';
    }

    public int getHoGiaDinh() {
        return hoGiaDinh;
    }

    public void setHoGiaDinh(int hoGiaDinh) {
        this.hoGiaDinh = hoGiaDinh;
    }

    public int getSoPhanQua() {
        return soPhanQua;
    }

    public void setSoPhanQua(int soPhanQua) {
        this.soPhanQua = soPhanQua;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public String getHoTenChuHo() {
        return hoTenChuHo;
    }

    public void setHoTenChuHo(String hoTenChuHo) {
        this.hoTenChuHo = hoTenChuHo;
    }

    public FormThongKeChiTiet() {
    }

    public FormThongKeChiTiet(String hoTenChuHo, int hoGiaDinh, int soPhanQua, double soTien) {
        this.hoTenChuHo = hoTenChuHo;
        this.hoGiaDinh = hoGiaDinh;
        this.soPhanQua = soPhanQua;
        this.soTien = soTien;
    }
}
