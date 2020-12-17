package app.service;



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

    public ArrayList<DSPhatThuong> getAll() throws SQLException {
        return dsPhatThuongRepo.findAll();
    }
    public void insertDSPhatThuong(String suKien) throws SQLException{
        Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        System.out.println(date);
        lastInsertedDS = new DSPhatThuong(null,suKien,date,0,0.0);
        dsPhatThuongRepo.insert(lastInsertedDS);
    }
    public DSPhatThuong getLastInserted(){
        return lastInsertedDS;
    }
}
