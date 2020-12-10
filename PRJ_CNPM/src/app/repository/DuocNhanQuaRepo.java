package app.repository;

import app.model.DuocNhanQua;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<DuocNhanQua> findByMaDS(int maDS) throws SQLException{
        String sql = "SELECT * FROM duoc_nhan_qua WHERE maDS = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1, maDS);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }
}
