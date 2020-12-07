package app.repository;


import app.model.DSPhatThuong;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DSPhatThuongRepo extends BaseRepo<DSPhatThuong> {
    @Override
    public DSPhatThuong getObject(ResultSet rs) throws SQLException {
        return null;
    }
    public ArrayList<DSPhatThuong> tatCaDanhSachPhatThuong(){
        return null;
    }
    public DSPhatThuong danhSachPhatThuongTheoMa(int maDanhSach){
        return null;
    }
    public void themMoiDanhSachPhatThuong(DSPhatThuong dsPhatThuong){
    }
    public void xoaDanhSachPhatThuong(int maDanhSach){

    }
}
