package app.service;

import app.model.form.FormThongKe;
import app.repository.ThongKePhatThuongRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class ThongKePhatThuongService {
    private ThongKePhatThuongRepo thongKePhatThuongRepo;

    public ThongKePhatThuongService() {
        thongKePhatThuongRepo = new ThongKePhatThuongRepo();
    }

    public ThongKePhatThuongService(ThongKePhatThuongRepo thongKePhatThuongRepo) {
        this.thongKePhatThuongRepo = thongKePhatThuongRepo;
    }
    public ArrayList<FormThongKe> findByMaDS(int maDs) throws SQLException{
        return thongKePhatThuongRepo.findByMaDs(maDs);
    }
}
