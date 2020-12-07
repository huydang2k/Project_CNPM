package app.repository;

import app.model.HoKhau;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HoKhauRepo extends BaseRepo<HoKhau> {
    @Override
    public HoKhau getObject(ResultSet rs) throws SQLException {
        return null;
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
