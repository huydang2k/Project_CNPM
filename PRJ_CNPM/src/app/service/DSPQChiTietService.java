package app.service;

import app.model.DSPhatQua;
import app.model.DuocNhanQua;
import app.model.NhanKhau;
import app.model.ThanhVienCuaHo;
import app.model.form.FormDSPQChiTiet;
import app.repository.DSPhatQuaRepo;
import app.repository.DuocNhanQuaRepo;
import app.repository.NhanKhauRepo;
import app.repository.ThanhVienCuaHoRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DSPQChiTietService {
    DuocNhanQuaRepo duocNhanQuaRepo;

    NhanKhauRepo nhanKhauRepo;

    ThanhVienCuaHoRepo thanhVienCuaHoRepo;

    DSPhatQuaRepo dsPhatQuaRepo;

    public DSPQChiTietService() {
        this.duocNhanQuaRepo = new DuocNhanQuaRepo();
        this.nhanKhauRepo = new NhanKhauRepo();
        this.thanhVienCuaHoRepo = new ThanhVienCuaHoRepo();
        this.dsPhatQuaRepo = new DSPhatQuaRepo();
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

    public FormDSPQChiTiet addDuocNhanQua(int maDS, int idNhanKhau, String phanQua, double mucQua) throws SQLException {
        DuocNhanQua duocNhanQua = new DuocNhanQua();
        duocNhanQua.setMaDS(maDS);
        duocNhanQua.setIdNhanKhau(idNhanKhau);
        duocNhanQua.setPhanQua(phanQua);
        duocNhanQua.setMucQua(mucQua);
        duocNhanQua.setDuocXacNhan(false);
        duocNhanQuaRepo.insert(duocNhanQua);
        FormDSPQChiTiet formDSPQChiTiet = new FormDSPQChiTiet();
        NhanKhau nhanKhau = nhanKhauRepo.findById(duocNhanQua.getIdNhanKhau());
        ThanhVienCuaHo thanhVienCuaHo = thanhVienCuaHoRepo.findByIdNhanKhau(duocNhanQua.getIdNhanKhau());

        formDSPQChiTiet.setIdDS(maDS);
        formDSPQChiTiet.setPhanQua(duocNhanQua.getPhanQua());
        formDSPQChiTiet.setMucQua(duocNhanQua.getMucQua());
        formDSPQChiTiet.setIdNhanKhau(duocNhanQua.getIdNhanKhau());
        formDSPQChiTiet.setIdHoKhau(thanhVienCuaHo.getIdHoKhau());
        formDSPQChiTiet.setHoTen(nhanKhau.getHoTen());
        formDSPQChiTiet.setNamSinh(Integer.parseInt(nhanKhau.getNamSinh().toString().substring(0, 4)));
        formDSPQChiTiet.setDuocXacNhan(duocNhanQua.getDuocXacNhan());

        return formDSPQChiTiet;
    }

    public FormDSPQChiTiet addDuocNhanQuaForThemMoi(int idNhanKhau, String phanQua, double mucQua) throws SQLException {
        FormDSPQChiTiet formDSPQChiTiet = new FormDSPQChiTiet();
        NhanKhau thieuNhi = nhanKhauRepo.findById(idNhanKhau);
        ThanhVienCuaHo thanhVienCuaHo = thanhVienCuaHoRepo.findByIdNhanKhau(idNhanKhau);
        formDSPQChiTiet.setPhanQua(phanQua);
        formDSPQChiTiet.setMucQua(mucQua);
        formDSPQChiTiet.setIdNhanKhau(idNhanKhau);
        formDSPQChiTiet.setIdHoKhau(thanhVienCuaHo.getIdHoKhau());
        formDSPQChiTiet.setHoTen(thieuNhi.getHoTen());
        formDSPQChiTiet.setNamSinh(Integer.parseInt(thieuNhi.getNamSinh().toString().substring(0, 4)));
        formDSPQChiTiet.setDuocXacNhan(false);
        return formDSPQChiTiet;
    }

    public void addDSPQ(DSPhatQua dsPhatQua, List<FormDSPQChiTiet> formDSPQChiTietList) throws SQLException {
        int maDS = dsPhatQuaRepo.insert(dsPhatQua);
        for(int i = 0; i < formDSPQChiTietList.size(); i ++){
            FormDSPQChiTiet formDSPQChiTiet = formDSPQChiTietList.get(i);
            DuocNhanQua duocNhanQua = new DuocNhanQua();
            duocNhanQua.setMaDS(maDS);
            duocNhanQua.setDuocXacNhan(false);
            duocNhanQua.setMucQua(formDSPQChiTiet.getMucQua());
            duocNhanQua.setPhanQua(formDSPQChiTiet.getPhanQua());
            duocNhanQua.setIdNhanKhau(formDSPQChiTiet.getIdNhanKhau());
            duocNhanQuaRepo.insert(duocNhanQua);
        }
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

    public NhanKhau getNhanKhau(int idNhanKhau)throws SQLException{
        return nhanKhauRepo.findById(idNhanKhau);
    }
    public ArrayList<FormDSPQChiTiet> getAllThieuNhi() throws SQLException {
        ArrayList<FormDSPQChiTiet> formDSPQChiTietArrayList = new ArrayList<>();
        ArrayList<NhanKhau> thieuNhiArrayList = nhanKhauRepo.findAllChildren();
        for(int i = 0; i < thieuNhiArrayList.size(); i++){
            NhanKhau thieuNhi = thieuNhiArrayList.get(i);
            FormDSPQChiTiet formDSPQChiTiet = new FormDSPQChiTiet();
            ThanhVienCuaHo thanhVienCuaHo = thanhVienCuaHoRepo.findByIdNhanKhau(thieuNhi.getID());
            formDSPQChiTiet.setIdNhanKhau(thieuNhi.getID());
            formDSPQChiTiet.setIdHoKhau(thanhVienCuaHo.getIdHoKhau());
            formDSPQChiTiet.setHoTen(thieuNhi.getHoTen());
            formDSPQChiTiet.setNamSinh(Integer.parseInt(thieuNhi.getNamSinh().toString().substring(0, 4)));
            formDSPQChiTiet.setDuocXacNhan(false);
            formDSPQChiTietArrayList.add(formDSPQChiTiet);
        }
        return formDSPQChiTietArrayList;
    }
}
