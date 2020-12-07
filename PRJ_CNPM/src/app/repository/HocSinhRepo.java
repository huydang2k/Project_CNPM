package app.repository;

import app.model.HocSinh;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HocSinhRepo extends BaseRepo<HocSinh> {
    @Override
    public HocSinh getObject(ResultSet rs) throws SQLException {
        return null;
    }
    public int soLuongHocSinh()throws SQLException,ClassNotFoundException{
        String sql = "select count(maHS) as c from hoc_sinh";
        PreparedStatement preparedStatement = prepare(sql);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            return Integer.parseInt(rs.getString("c"));
        }else{
            return 0;
        }
    }
}
