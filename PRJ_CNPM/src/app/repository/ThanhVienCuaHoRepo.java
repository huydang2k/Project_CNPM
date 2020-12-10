package app.repository;

import app.model.ThanhVienCuaHo;

import java.sql.PreparedStatement;
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

    public ThanhVienCuaHo findByIdNhanKhau(int idNhanKhau) throws SQLException{
        String sql = "SELECT * FROM thanh_vien_cua_ho WHERE idNhanKhau = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1, idNhanKhau);
        ResultSet rs = preparedStatement.executeQuery();
        rs.first();
        return getObject(rs);
    }
}
