package app.service;

import app.model.DuocNhanQua;
import app.model.DuocNhanThuong;
import app.model.form.FormDSPQChiTiet;
import app.model.form.FormDSPTChiTiet;
import app.repository.DuocNhanQuaRepo;
import app.repository.DuocNhanThuongRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class DuocNhanThuongService {
    DuocNhanThuongRepo duocNhanThuongRepo;

    public DuocNhanThuongService() {
        duocNhanThuongRepo = new DuocNhanThuongRepo();
    }

    public DuocNhanThuongService(DuocNhanThuongRepo duocNhanThuongRepo) {
        this.duocNhanThuongRepo = duocNhanThuongRepo;
    }
    public void insertDuocNhanThuongList(int maDS, ArrayList<FormDSPTChiTiet> formDSPTChiTiets) throws SQLException {
        for(FormDSPTChiTiet i : formDSPTChiTiets){
            DuocNhanThuong duocNhanThuong = new DuocNhanThuong(maDS,i.getIdHocSinh(),
                    i.getThanhTich()
                    ,i.isMinhChung(),i.getXepLoai(),
                    i.getMucThuong(),i.isDuocXacNhan());
            duocNhanThuongRepo.insert(duocNhanThuong);
        }
    }
    public void insertDuocNhanThuong(int maDS, FormDSPTChiTiet formDSPTChiTiet) throws SQLException {

        DuocNhanThuong duocNhanThuong = new DuocNhanThuong(maDS,formDSPTChiTiet.getIdHocSinh(),
                formDSPTChiTiet.getThanhTich()
                ,formDSPTChiTiet.isMinhChung(),formDSPTChiTiet.getXepLoai(),
                formDSPTChiTiet.getMucThuong(),
                formDSPTChiTiet.isDuocXacNhan());
        duocNhanThuongRepo.insert(duocNhanThuong);

    }

}