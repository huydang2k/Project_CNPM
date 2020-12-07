package app.repository;

import app.model.DuocNhanThuong;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DuocNhanThuongRepo extends BaseRepo<DuocNhanThuong> {
    @Override
    public DuocNhanThuong getObject(ResultSet rs) throws SQLException {
        DuocNhanThuong duocNhanThuong = new DuocNhanThuong();
        duocNhanThuong.setMaDS(rs.getInt("maDS"));
        duocNhanThuong.setMaHS(rs.getInt("maHS"));
        duocNhanThuong.setThanhTich(rs.getString("thanhTich"));
        duocNhanThuong.setXepLoai(rs.getString("xepLoai"));
        duocNhanThuong.setMucThuong(rs.getDouble("mucThuong"));
        duocNhanThuong.setDuocXacNhan(rs.getBoolean("duocXacNhan"));
        return duocNhanThuong;
    }
}
