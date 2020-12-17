package app.repository;

import app.model.HoKhau;

import java.io.FileNotFoundException;
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
    protected ArrayList<HoKhau> findAll() throws SQLException {
        return null;
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

    @Override
    protected int deleteById(int maDS) throws SQLException {
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
