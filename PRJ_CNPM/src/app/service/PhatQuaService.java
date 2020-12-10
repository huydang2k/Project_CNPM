package app.service;

import app.model.DSPhatQua;
import app.repository.DSPhatQuaRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhatQuaService {

    private DSPhatQuaRepo dsPhatQuaRepo;

    public PhatQuaService() {
        this.dsPhatQuaRepo = new DSPhatQuaRepo();
    }

    public PhatQuaService(DSPhatQuaRepo dsPhatQuaRepo) {
        this.dsPhatQuaRepo = dsPhatQuaRepo;
    }

    public ArrayList<DSPhatQua> getAll() throws SQLException {
        return dsPhatQuaRepo.findAll();
    }
}
