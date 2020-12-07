package app.repository;

import app.model.HoKhau;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HoKhauRepo extends BaseRepo<HoKhau> {
    @Override
    public HoKhau getObject(ResultSet rs) throws SQLException {
        return null;
    }
    public int soLuongHoKhau(){
        return 0;
    }
    public int soLuongThieuNhi(){
        return 0;
    }
}
