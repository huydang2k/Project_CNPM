package app.service;



import app.model.DSPhatQua;
import app.model.DSPhatThuong;
import app.repository.DSPhatThuongRepo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class DSPhatThuongService  {
    private DSPhatThuong lastInsertedDS;
    private DSPhatThuongRepo dsPhatThuongRepo;

    public DSPhatThuongService() {
        this.dsPhatThuongRepo = new DSPhatThuongRepo();
    }

    public DSPhatThuongService(DSPhatThuongRepo dsPhatThuongRepo) {

        this.dsPhatThuongRepo = dsPhatThuongRepo;
    }
    public Date getHanNopMc(int maDS)throws SQLException{
        return dsPhatThuongRepo.getHanNopMc(maDS);
    }
    public ArrayList<DSPhatThuong> getAll() throws SQLException {
        return dsPhatThuongRepo.findAll();
    }
    public ArrayList<DSPhatThuong> getDSHoanThanh() throws SQLException {
        return dsPhatThuongRepo.findDSHoanThanh();
    }
    public void insertDSPhatThuong(String suKien) throws SQLException{
        Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        System.out.println(date);
        lastInsertedDS = new DSPhatThuong(null,suKien,date,0,0.0);
        dsPhatThuongRepo.insert(lastInsertedDS);
    }
    public void update(DSPhatThuong dsPhatThuong) throws SQLException{
        dsPhatThuongRepo.update(dsPhatThuong);
    }
    public void deleteByMaDS(int id) throws SQLException{
        dsPhatThuongRepo.deleteById(id);
    }
    public DSPhatThuong getLastInserted(){
        return lastInsertedDS;
    }
}
