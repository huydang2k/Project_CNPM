package app.repository;

import app.model.DSPhatQua;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DSPhatQuaRepo extends BaseRepo<DSPhatQua> {
    @Override
    public DSPhatQua getObject(ResultSet rs) throws SQLException {
        return null;
    }
    public ArrayList<DSPhatQua> tatCaDanhSachPhatQua(){
        return null;
    }
    public DSPhatQua danhSachPhatQuaTheoMa(int maDanhSach){
        return null;
    }
    public void themMoiDanhSachPhatQua(DSPhatQua dsPhatQua){
    }
    public void xoaDanhSachPhatQua(int maDanhSach){

    }


}
