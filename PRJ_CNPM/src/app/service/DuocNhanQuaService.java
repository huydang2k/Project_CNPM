package app.service;

import app.model.DuocNhanQua;
import app.model.form.FormDSPQChiTiet;
import app.repository.DuocNhanQuaRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class DuocNhanQuaService {
    DuocNhanQuaRepo duocNhanQuaRepo;

    public DuocNhanQuaService() {
        duocNhanQuaRepo = new DuocNhanQuaRepo();
    }

    public DuocNhanQuaService(DuocNhanQuaRepo duocNhanQuaRepo) {
        this.duocNhanQuaRepo = duocNhanQuaRepo;
    }
    public void insertDuocNhanQuaList(int maDS, ArrayList<FormDSPQChiTiet> formDSPQChiTiets) throws SQLException {
        for(FormDSPQChiTiet i : formDSPQChiTiets){
            DuocNhanQua duocNhanQua = new DuocNhanQua(maDS,i.getIdNhanKhau(),
                    i.getPhanQua()
            ,i.getMucQua(),i.isDuocXacNhan());
            duocNhanQuaRepo.insert(duocNhanQua);
        }
    }
    public void insertDuocNhanQua(int maDS, FormDSPQChiTiet formDSPQChiTiet) throws SQLException {

            DuocNhanQua duocNhanQua = new DuocNhanQua(maDS,formDSPQChiTiet.getIdNhanKhau(),
                    formDSPQChiTiet.getPhanQua()
                    ,formDSPQChiTiet.getMucQua(),formDSPQChiTiet.isDuocXacNhan());
            duocNhanQuaRepo.insert(duocNhanQua);

    }

}
