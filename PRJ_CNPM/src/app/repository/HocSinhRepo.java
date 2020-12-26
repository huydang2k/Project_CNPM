package app.repository;

import app.model.HocSinh;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HocSinhRepo extends BaseRepo<HocSinh> {
    @Override
    public HocSinh getObject(ResultSet rs) throws SQLException {
        HocSinh hocSinh = new HocSinh();
        hocSinh.setMaHS(rs.getInt("maHS"));
        hocSinh.setIdNhanKhau(rs.getInt("idNhanKhau"));
        hocSinh.setHocVan(rs.getString("hocVan"));
        return hocSinh;
    }


    @Override
    public HocSinh findById(int maHS) throws SQLException {
        String sql = "select * from hoc_sinh where maHS = ? ";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1,maHS);
        ResultSet rs = preparedStatement.executeQuery();
        rs.first();
        return getObject(rs);
    }

    @Override
    protected int insert(HocSinh hocSinh) throws SQLException {
        return 0;
    }

    @Override
    protected int update(HocSinh hocSinh) throws SQLException {
        return 0;
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
    @Override
    public ArrayList<HocSinh> findAll() throws SQLException{
        String sql = "select * from hoc_sinh";
        PreparedStatement preparedStatement = prepare(sql);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }
}
