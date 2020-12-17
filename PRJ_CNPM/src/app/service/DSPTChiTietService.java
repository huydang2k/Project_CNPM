package app.service;



import app.model.DuocNhanThuong;
import app.model.HocSinh;
import app.model.NhanKhau;
import app.model.ThanhVienCuaHo;
import app.model.form.FormDSPTChiTiet;
import app.repository.DuocNhanThuongRepo;
import app.repository.HocSinhRepo;
import app.repository.NhanKhauRepo;
import app.repository.ThanhVienCuaHoRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class DSPTChiTietService {
    DuocNhanThuongRepo duocNhanThuongRepo;

    NhanKhauRepo nhanKhauRepo;

    ThanhVienCuaHoRepo thanhVienCuaHoRepo;
    HocSinhRepo hocSinhRepo;

    public DSPTChiTietService() {
        this.duocNhanThuongRepo = new DuocNhanThuongRepo();
        this.nhanKhauRepo = new NhanKhauRepo();
        this.thanhVienCuaHoRepo = new ThanhVienCuaHoRepo();
        this.hocSinhRepo = new HocSinhRepo();
    }

    public ArrayList<FormDSPTChiTiet> getFormDSPTChiTietByMaDS(int maDS) throws SQLException {
        ArrayList<FormDSPTChiTiet> formDSPTChiTietArrayList = new ArrayList<>();
        ArrayList<DuocNhanThuong> duocNhanQuaArrayList = duocNhanThuongRepo.findByMaDS(maDS);
        for (int i = 0; i < duocNhanQuaArrayList.size(); i++) {
            DuocNhanThuong duocNhanThuong = duocNhanQuaArrayList.get(i);
            HocSinh hocSinh = hocSinhRepo.findById(duocNhanThuong.getMaHS());
            NhanKhau nhanKhau = nhanKhauRepo.findById(hocSinh.getIdNhanKhau());
            ThanhVienCuaHo thanhVienCuaHo = thanhVienCuaHoRepo.findByIdNhanKhau(nhanKhau.getID());
            FormDSPTChiTiet formDSPTChiTiet = new FormDSPTChiTiet();
            formDSPTChiTiet.setIdDS(maDS);
            formDSPTChiTiet.setIdHocSinh(hocSinh.getMaHS());
            formDSPTChiTiet.setIdNhanKhau(nhanKhau.getID());
            formDSPTChiTiet.setHoTen(nhanKhau.getHoTen());
            formDSPTChiTiet.setNamSinh(Integer.parseInt(nhanKhau.getNamSinh().toString().substring(0, 4)));
            formDSPTChiTiet.setIdHoKhau(thanhVienCuaHo.getIdHoKhau());
            formDSPTChiTiet.setThanhTich(duocNhanThuong.getThanhTich());
            formDSPTChiTiet.setMinhChung(duocNhanThuong.getMinhChung());
            formDSPTChiTiet.setXepLoai(duocNhanThuong.getXepLoai());
            formDSPTChiTiet.setMucThuong(duocNhanThuong.getMucThuong());
            formDSPTChiTiet.setDuocXacNhan(duocNhanThuong.getDuocXacNhan());
            formDSPTChiTiet.setDuocXacNhan(duocNhanThuong.getDuocXacNhan());
            formDSPTChiTietArrayList.add(formDSPTChiTiet);
        }
        return formDSPTChiTietArrayList;
    }
}
