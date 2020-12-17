package app.service;

import app.model.form.FormThongKePhatQua;
import app.repository.ThongKePhatQuaRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class ThongKePhatQuaService {
    ThongKePhatQuaRepo thongKePhatQuaRepo;

    public ThongKePhatQuaService(ThongKePhatQuaRepo thongKePhatQuaRepo) {
        this.thongKePhatQuaRepo = thongKePhatQuaRepo;
    }

    public ThongKePhatQuaService() {
        thongKePhatQuaRepo = new ThongKePhatQuaRepo();
    }

    public ArrayList<FormThongKePhatQua> findByMaDs(int maDS) throws SQLException{
        return thongKePhatQuaRepo.findByMaDs(maDS);
    }
}
