package app.repository;

import app.model.DSPhatQua;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DSPhatQuaRepo extends BaseRepo<DSPhatQua> {
    @Override
    public DSPhatQua getObject(ResultSet rs) throws SQLException {
        DSPhatQua dsPhatQua = new DSPhatQua();
        dsPhatQua.setMaDS(rs.getInt("maDS"));
        dsPhatQua.setSuKien(rs.getString("suKien"));
        dsPhatQua.setNgayTao(rs.getDate("ngayTao"));
        dsPhatQua.setTrangThai(rs.getInt("trangThai"));
        dsPhatQua.setTongChiPhi(rs.getDouble("tongChiPhi"));
        return dsPhatQua;
    }
    public ArrayList<DSPhatQua> findAll() throws SQLException{
        String sql = "SELECT * FROM ds_phat_qua where trangThai != -1";
        PreparedStatement preparedStatement = prepare(sql);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }
    public ArrayList<DSPhatQua> findDSHoanThanh() throws SQLException{
        String sql = "SELECT * FROM ds_phat_qua where trangThai = 2";
        PreparedStatement preparedStatement = prepare(sql);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }
    public DSPhatQua findById(int maDS) throws SQLException{
        String sql = "SELECT * FROM ds_phat_qua WHERE maDS = ? and trangThai != -1";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1, maDS);
        ResultSet rs = preparedStatement.executeQuery();
        rs.first();
        return getObject(rs);
    }
    public int insert(DSPhatQua dsPhatQua) throws SQLException{
        String sql = "insert into ds_phat_qua values (null,?,?,1,?)";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setString(1,dsPhatQua.getSuKien());
        preparedStatement.setDate(2,dsPhatQua.getNgayTao());
        preparedStatement.setDouble(3, dsPhatQua.getTongChiPhi());
        preparedStatement.executeUpdate();
        sql = "SELECT LAST_INSERT_ID() as id";
        preparedStatement = prepare(sql);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            return rs.getInt("id");
        }else{
            return 0;
        }

    }
    public int update(DSPhatQua dsPhatQua)throws SQLException{
        String sql = "update ds_phat_qua set suKien = ?, ngayTao = ?, trangThai = ?," +
                "tongChiPhi = ? " +
                "where maDs = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setString(1, dsPhatQua.getSuKien());
        preparedStatement.setDate(2, dsPhatQua.getNgayTao());
        preparedStatement.setInt(3,dsPhatQua.getTrangThai());
        preparedStatement.setDouble(4,dsPhatQua.getTongChiPhi());
        preparedStatement.setInt(5, dsPhatQua.getMaDS());
        return preparedStatement.executeUpdate();
    }
    public int deleteById(int maDS)throws SQLException{
        String sql = "update ds_phat_qua set trangThai = -1 where " +
                "maDS = ? ";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1,maDS);
        return preparedStatement.executeUpdate();
    }
}
