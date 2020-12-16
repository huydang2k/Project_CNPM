package app.repository;


import app.model.DSPhatQua;
import app.model.DSPhatThuong;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DSPhatThuongRepo extends BaseRepo<DSPhatThuong> {
    @Override
    public DSPhatThuong getObject(ResultSet rs) throws SQLException {
        DSPhatThuong dsPhatThuong = new DSPhatThuong();
        dsPhatThuong.setMaDS(rs.getInt("maDS"));
        dsPhatThuong.setSuKien(rs.getString("suKien"));
        dsPhatThuong.setNgayTao(rs.getDate("ngayTao"));
        dsPhatThuong.setTrangThai(rs.getInt("trangThai"));
        dsPhatThuong.setTongChiPhi(rs.getDouble("tongChiPhi"));
        return dsPhatThuong;
    }
    public ArrayList<DSPhatThuong> findAll()throws SQLException{
        String sql = "SELECT * FROM ds_phat_thuong";
        PreparedStatement preparedStatement = prepare(sql);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);

    }
    public DSPhatThuong findById(int maDS) throws SQLException{
        String sql = "SELECT * FROM ds_phat_thuong WHERE maDS = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1, maDS);
        ResultSet rs = preparedStatement.executeQuery();
        rs.first();
        return getObject(rs);
    }
    public int update(DSPhatThuong dsPhatThuong)throws SQLException{
        String sql = "update ds_phat_thuong set suKien = ?, ngayTao = ?, trangThai = ?" +
                "tongChiPhi = ?" +
                "where maDs = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setString(1, dsPhatThuong.getSuKien());
        preparedStatement.setDate(2, dsPhatThuong.getNgayTao());
        preparedStatement.setInt(3,dsPhatThuong.getTrangThai());
        preparedStatement.setDouble(4,dsPhatThuong.getTongChiPhi());
        preparedStatement.setInt(5, dsPhatThuong.getMaDS());
        return preparedStatement.executeUpdate();
    }
    public int deleteById(int maDS) throws SQLException{
        String sql = "update ds_phat_thuong set trangThai = -1 where" +
                "maDS = ? ";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1,maDS);
        return preparedStatement.executeUpdate();
    }
    public int insert(DSPhatThuong dsPhatThuong) throws SQLException{
        String sql = "insert into ds_phat_thuong values (null,?,?,0,0)";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setString(1,dsPhatThuong.getSuKien());
        preparedStatement.setDate(2,dsPhatThuong.getNgayTao());
        return preparedStatement.executeUpdate();
    }
}
