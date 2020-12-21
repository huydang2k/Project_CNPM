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
    public ArrayList<FormDSPTChiTiet> getFormPTChiTietDangPhat(int maDS) throws SQLException {
        ArrayList<FormDSPTChiTiet> formDSPTChiTietArrayList = new ArrayList<>();
        ArrayList<DuocNhanThuong> duocNhanQuaArrayList = duocNhanThuongRepo.findByMaDSDangPhat(maDS);
        for (int i = 0; i < duocNhanQuaArrayList.size(); i++) {
            DuocNhanThuong duocNhanThuong = duocNhanQuaArrayList.get(i);
            System.out.println(duocNhanThuong);
            System.out.println("find hoc sinh co - "+duocNhanThuong.getMaHS());
            HocSinh hocSinh = hocSinhRepo.findById(duocNhanThuong.getMaHS());
            System.out.println(hocSinh);
            System.out.println("find hoc sinh co ma nhan khau la "+hocSinh.getIdNhanKhau());
            NhanKhau nhanKhau = nhanKhauRepo.findById(hocSinh.getIdNhanKhau());
            System.out.println(nhanKhau);
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
    public ArrayList<FormDSPTChiTiet> getFormDSPTChiTietByMaDS(int maDS) throws SQLException {
        ArrayList<FormDSPTChiTiet> formDSPTChiTietArrayList = new ArrayList<>();
        ArrayList<DuocNhanThuong> duocNhanQuaArrayList = duocNhanThuongRepo.findByMaDS(maDS);
        for (int i = 0; i < duocNhanQuaArrayList.size(); i++) {
            DuocNhanThuong duocNhanThuong = duocNhanQuaArrayList.get(i);
            System.out.println(duocNhanThuong);
            System.out.println("find hoc sinh co - "+duocNhanThuong.getMaHS());
            HocSinh hocSinh = hocSinhRepo.findById(duocNhanThuong.getMaHS());
            System.out.println(hocSinh);
            System.out.println("find hoc sinh co ma nhan khau la "+hocSinh.getIdNhanKhau());
            NhanKhau nhanKhau = nhanKhauRepo.findById(hocSinh.getIdNhanKhau());
            System.out.println(nhanKhau);
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

    public FormDSPTChiTiet addDuocNhanThuong(int maDS, int idHS, String thanhTich,
                                             boolean minhChung, String xepLoai,
                                             double mucThuong) throws SQLException {
        DuocNhanThuong duocNhanThuong = new DuocNhanThuong();
        duocNhanThuong.setMaDS(maDS);
        duocNhanThuong.setMaHS(idHS);
        duocNhanThuong.setThanhTich(thanhTich);
        duocNhanThuong.setMinhChung(minhChung);
        duocNhanThuong.setXepLoai(xepLoai);
        duocNhanThuong.setMucThuong(mucThuong);
        duocNhanThuong.setDuocXacNhan(false);
        duocNhanThuongRepo.insert(duocNhanThuong);

        NhanKhau nhanKhau = getNhanKhauByMaHS(duocNhanThuong.getMaHS());
        ThanhVienCuaHo thanhVienCuaHo = thanhVienCuaHoRepo.findByIdNhanKhau(nhanKhau.getID());
        FormDSPTChiTiet formDSPTChiTiet = new FormDSPTChiTiet(maDS,idHS,nhanKhau.getID(),
                nhanKhau.getHoTen(),Integer.parseInt(nhanKhau.getNamSinh().toString().substring(0, 4)),
                thanhVienCuaHo.getIdHoKhau(),duocNhanThuong.getThanhTich(),duocNhanThuong.getMinhChung(),
                duocNhanThuong.getXepLoai(),duocNhanThuong.getMucThuong(),duocNhanThuong.getDuocXacNhan());
        return formDSPTChiTiet;
    }

    public void deleteDuocNhanThuong(FormDSPTChiTiet formDSPTChiTiet) throws SQLException {
        int maDS = formDSPTChiTiet.getIdDS();
        int maHS = formDSPTChiTiet.getIdHocSinh();
        duocNhanThuongRepo.deleteBymaDSandIdHS(maDS, maHS);
    }

    public void updateDuocNhanThuong(FormDSPTChiTiet formDSPTChiTiet) throws SQLException {
        DuocNhanThuong duocNhanThuong = new DuocNhanThuong();

        duocNhanThuong.setMaDS(formDSPTChiTiet.getIdDS());
        duocNhanThuong.setMaHS(formDSPTChiTiet.getIdHocSinh());
        duocNhanThuong.setThanhTich(formDSPTChiTiet.getThanhTich());
        duocNhanThuong.setMinhChung(formDSPTChiTiet.isMinhChung());
        duocNhanThuong.setXepLoai(formDSPTChiTiet.getXepLoai());
        duocNhanThuong.setMucThuong(formDSPTChiTiet.getMucThuong());
        duocNhanThuong.setDuocXacNhan(formDSPTChiTiet.isDuocXacNhan());

        duocNhanThuongRepo.update(duocNhanThuong);
    }

    public NhanKhau getNhanKhau(int idNhanKhau)throws SQLException{
        return nhanKhauRepo.findById(idNhanKhau);
    }
    public NhanKhau getNhanKhauByMaHS(int idHS)throws SQLException{
        return new NhanKhauRepo().findById((new HocSinhRepo().findById(idHS).getIdNhanKhau()));

    }
}
