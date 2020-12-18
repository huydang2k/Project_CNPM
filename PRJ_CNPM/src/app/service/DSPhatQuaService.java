package app.service;

import app.model.DSPhatQua;
import app.model.DSPhatThuong;
import app.repository.DSPhatQuaRepo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DSPhatQuaService{

    private DSPhatQua lastInsertedDS;
    private DSPhatQuaRepo dsPhatQuaRepo;

    public DSPhatQuaService() {
        this.dsPhatQuaRepo = new DSPhatQuaRepo();
    }

    public DSPhatQuaService(DSPhatQuaRepo dsPhatQuaRepo) {
        this.dsPhatQuaRepo = dsPhatQuaRepo;
    }

    public ArrayList<DSPhatQua> getAll() throws SQLException {
        return dsPhatQuaRepo.findAll();
    }
    public void insertDSPhatQUa(String suKien) throws SQLException{
        Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        System.out.println(date);
        lastInsertedDS = new DSPhatQua(null,suKien,date,1,0.0);
        dsPhatQuaRepo.insert(lastInsertedDS);
    }
    public void update(DSPhatQua dsPhatQua) throws SQLException{
        dsPhatQuaRepo.update(dsPhatQua);
    }
    public void deleteByI(int id) throws SQLException{
        dsPhatQuaRepo.deleteById(id);
    }
    public DSPhatQua getLastInserted(){
        return lastInsertedDS;
    }
}
