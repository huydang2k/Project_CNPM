package app.service;

import app.model.DuocNhanQua;
import app.model.NhanKhau;
import app.model.ThanhVienCuaHo;
import app.model.form.FormDSPQChiTiet;
import app.repository.DuocNhanQuaRepo;
import app.repository.NhanKhauRepo;
import app.repository.ThanhVienCuaHoRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class DSPQChiTietService {
    DuocNhanQuaRepo duocNhanQuaRepo;

    NhanKhauRepo nhanKhauRepo;

    ThanhVienCuaHoRepo thanhVienCuaHoRepo;

    public DSPQChiTietService() {
        this.duocNhanQuaRepo = new DuocNhanQuaRepo();
        this.nhanKhauRepo = new NhanKhauRepo();
        this.thanhVienCuaHoRepo = new ThanhVienCuaHoRepo();
    }

    public ArrayList<FormDSPQChiTiet> getFormDSPQChiTietByMaDS(int maDS) throws SQLException {
        ArrayList<FormDSPQChiTiet> formDSPQChiTietArrayList = new ArrayList<>();
        ArrayList<DuocNhanQua> duocNhanQuaArrayList = duocNhanQuaRepo.findByMaDS(maDS);
        for (int i = 0; i < duocNhanQuaArrayList.size(); i++) {
            DuocNhanQua duocNhanQua = duocNhanQuaArrayList.get(i);
            NhanKhau nhanKhau = nhanKhauRepo.findById(duocNhanQua.getIdNhanKhau());
            ThanhVienCuaHo thanhVienCuaHo = thanhVienCuaHoRepo.findByIdNhanKhau(duocNhanQua.getIdNhanKhau());
            FormDSPQChiTiet formDSPQChiTiet = new FormDSPQChiTiet();
            formDSPQChiTiet.setIdDS(maDS);
            formDSPQChiTiet.setPhanQua(duocNhanQua.getPhanQua());
            formDSPQChiTiet.setMucQua(duocNhanQua.getMucQua());
            formDSPQChiTiet.setIdNhanKhau(duocNhanQua.getIdNhanKhau());
            formDSPQChiTiet.setIdHoKhau(thanhVienCuaHo.getIdHoKhau());
            formDSPQChiTiet.setHoTen(nhanKhau.getHoTen());
            formDSPQChiTiet.setNamSinh(Integer.parseInt(nhanKhau.getNamSinh().toString().substring(0, 4)));
            formDSPQChiTiet.setDuocXacNhan(duocNhanQua.getDuocXacNhan());
            formDSPQChiTietArrayList.add(formDSPQChiTiet);
        }
        return formDSPQChiTietArrayList;
    }

    public void deleteDuocNhanQua(FormDSPQChiTiet formDSPQChiTiet) throws SQLException {
        int maDS = formDSPQChiTiet.getIdDS();
        int idNhanKhau = formDSPQChiTiet.getIdNhanKhau();
        duocNhanQuaRepo.deleteByMaDSAndIdNhanKhau(maDS, idNhanKhau);
    }

    public void updateDuocNhanQua(FormDSPQChiTiet formDSPQChiTiet) throws SQLException {
        DuocNhanQua duocNhanQua = new DuocNhanQua();
        duocNhanQua.setIdNhanKhau(formDSPQChiTiet.getIdNhanKhau());
        duocNhanQua.setMaDS(formDSPQChiTiet.getIdDS());
        duocNhanQua.setPhanQua(formDSPQChiTiet.getPhanQua());
        duocNhanQua.setMucQua(formDSPQChiTiet.getMucQua());
        duocNhanQua.setDuocXacNhan(formDSPQChiTiet.isDuocXacNhan());
        duocNhanQuaRepo.update(duocNhanQua);
    }
}
