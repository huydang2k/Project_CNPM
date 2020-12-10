package app.service;

import app.model.DSPhatQua;
import app.repository.DSPhatQuaRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DSPhatQuaService{

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
}
