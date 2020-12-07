package app.repository;

import app.model.HoKhau;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public int soLuongThieuNhi() throws SQLException,ClassNotFoundException{
        String sql = "select count(nk.ID) as c from nhan_khau as nk where round(DATEDIFF(CURRENT_DATE,nk.namsinh )/365) < 18";
        PreparedStatement preparedStatement = prepare(sql);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            return Integer.parseInt(rs.getString("c"));
        }else{
            return 0;
        }
    }
}
