package app.repository;

import app.model.NhanKhau;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NhanKhauRepo extends BaseRepo<NhanKhau>{

    @Override
    public NhanKhau getObject(ResultSet rs) throws SQLException {
        NhanKhau nhanKhau = new NhanKhau();
        nhanKhau.setID(rs.getInt("ID"));
        nhanKhau.setMaNhanKhau(rs.getString("maNhanKhau"));
        nhanKhau.setHoTen(rs.getString("hoTen"));
        nhanKhau.setNamSinh(rs.getDate("namSinh"));
        nhanKhau.setGioiTinh(rs.getString("gioiTinh"));
        nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));
        nhanKhau.setTrinhDoHocVan(rs.getString("trinhDoHocVan"));
        nhanKhau.setTrinhDoChuyenMon(rs.getString("trinhDoChuyenMon"));
        nhanKhau.setNgheNghiep(rs.getString("ngheNghiep"));
        nhanKhau.setNoiLamViec(rs.getString("noiLamViec"));
        return nhanKhau;
    }

    public NhanKhau findById(int id)throws SQLException{
        String sql = "SELECT * FROM nhan_khau WHERE ID = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        rs.first();
        return getObject(rs);
    }
}
