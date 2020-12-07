package app.repository;

import app.model.NhanKhau;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NhanKhauRepo extends BaseRepo<NhanKhau>{

    @Override
    public NhanKhau getObject(ResultSet rs) throws SQLException {
        NhanKhau nhanKhau = new NhanKhau();
        nhanKhau.setID(rs.getInt("ID"));
        nhanKhau.setMaNhanKhau(rs.getString("maNhanKhau"));
        nhanKhau.setHoTen(rs.getString("hoTen"));
        nhanKhau.setNamSing(rs.getDate("namSinh"));
        nhanKhau.setGioiTinh(rs.getString("gioiTinh"));
        nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));
        nhanKhau.setTrinhDoHocVan(rs.getString("trinhDoHocVan"));
        nhanKhau.setTrinhDoChuyenMon(rs.getString("trinhDoChuyenMon"));
        nhanKhau.setNgheNghiep(rs.getString("ngheNghiep"));
        nhanKhau.setNoiLamViec(rs.getString("noiLamViec"));
        return nhanKhau;
    }
}
