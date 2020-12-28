package app.repository;

import app.model.HoKhau;
import app.model.ThanhVienCuaHo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HoKhauRepo extends BaseRepo<HoKhau> {
    @Override
    public HoKhau getObject(ResultSet rs) throws SQLException {
        HoKhau hoKhau = new HoKhau();
        hoKhau.setID(rs.getInt("ID"));
        hoKhau.setMaHoKhau(rs.getString("maHoKhau"));
        hoKhau.setIdChuHo(rs.getInt("idChuHo"));
        hoKhau.setMaKhuVuc(rs.getString("maKhuVuc"));
        hoKhau.setDiaChi(rs.getString("diaChi"));
        hoKhau.setNgayLap(rs.getDate("ngayLap"));
        hoKhau.setNgayChuyenDi(rs.getDate("ngayChuyenDi"));
        hoKhau.setLyDoChuyen(rs.getString("lyDoChuyen"));
        hoKhau.setNguoiThucHien(rs.getInt("nguoiThucHien"));
        return hoKhau;
    }

    @Override
    public ArrayList<HoKhau> findAll() throws SQLException {
        String sql = "SELECT * FROM ho_khau";
        PreparedStatement preparedStatement = prepare(sql);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }

    @Override
    protected HoKhau findById(int maDs) throws SQLException {
        return null;
    }

    @Override
    protected int insert(HoKhau hoKhau) throws SQLException {
        return 0;
    }

    @Override
    protected int update(HoKhau hoKhau) throws SQLException {
        return 0;
    }

    public int soLuongHoKhau() throws SQLException, ClassNotFoundException {
        String sql = "SELECT count(ID) FROM ho_khau";
        PreparedStatement preparedStatement = prepare(sql);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            return Integer.parseInt(rs.getString("count(ID)"));
        }else{
            return 0;
        }
    }

}
