package app.repository;

import app.model.ThanhVienCuaHo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ThanhVienCuaHoRepo  extends BaseRepo<ThanhVienCuaHo> {
    @Override
    public ThanhVienCuaHo getObject(ResultSet rs) throws SQLException {
        ThanhVienCuaHo thanhVienCuaHo = new ThanhVienCuaHo();
        thanhVienCuaHo.setIdHoKhau(rs.getInt("idNhanKhau"));
        thanhVienCuaHo.setIdHoKhau(rs.getInt("idHoKhau"));
        thanhVienCuaHo.setQuanHeVoiChuHo(rs.getString("quanHeVoiChuHo"));
        return thanhVienCuaHo;
    }
}
