package app.repository;

import app.model.DuocNhanQua;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DuocNhanQuaRepo extends BaseRepo<DuocNhanQua> {
    @Override
    public DuocNhanQua getObject(ResultSet rs) throws SQLException {
        DuocNhanQua duocNhanQua = new DuocNhanQua();
        duocNhanQua.setMaDS(rs.getInt("maDS"));
        duocNhanQua.setIdNhanKhau(rs.getInt("idNhanKhau"));
        duocNhanQua.setPhanQua(rs.getString("phanQua"));
        duocNhanQua.setMucQua(rs.getDouble("mucQua"));
        duocNhanQua.setDuocXacNhan(rs.getBoolean("duocXacNhan"));
        return duocNhanQua;
    }
}
