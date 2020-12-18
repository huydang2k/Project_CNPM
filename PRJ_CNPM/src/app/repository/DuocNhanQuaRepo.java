package app.repository;

import app.model.DuocNhanQua;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DuocNhanQuaRepo extends BaseRepo<DuocNhanQua> {
    @Override
    public DuocNhanQua getObject(ResultSet rs) throws SQLException {
        DuocNhanQua duocNhanQua = new DuocNhanQua();
        duocNhanQua.setMaDS(rs.getInt("maDS"));
        duocNhanQua.setIdNhanKhau(rs.getInt("idNhanKhau"));
        duocNhanQua.setPhanQua(rs.getString("phanQua"));
        duocNhanQua.setMucQua(rs.getDouble("mucQua"));
        duocNhanQua.setDuocXacNhan(rs.getBoolean("duocXacNhan"));
        return duocNhanQua;
    }

    @Override
    protected ArrayList<DuocNhanQua> findAll() throws SQLException {
        return null;
    }

    @Override
    protected DuocNhanQua findById(int maDs) throws SQLException {
        return null;
    }

    public ArrayList<DuocNhanQua> findByMaDS(int maDS) throws SQLException{
        String sql = "SELECT * FROM duoc_nhan_qua WHERE maDS = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1, maDS);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }
    public int insert(DuocNhanQua duocNhanQua) throws SQLException{
        String sql = "insert into duoc_nhan_qua values (?,?,?,?,?)";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1,duocNhanQua.getMaDS());
        preparedStatement.setInt(2,duocNhanQua.getIdNhanKhau());
        preparedStatement.setString(3,duocNhanQua.getPhanQua());
        preparedStatement.setDouble(4,duocNhanQua.getMucQua());
        preparedStatement.setBoolean(5,duocNhanQua.getDuocXacNhan());
        return preparedStatement.executeUpdate();
    }
    public int update(DuocNhanQua duocNhanQua) throws SQLException{
        String sql = "update duoc_nhan_qua set phanQua = ?, mucQua = ?, duocXacNhan = ? " +
                "where maDs = ? and idNhanKhau = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setString(1, duocNhanQua.getPhanQua());
        preparedStatement.setDouble(2, duocNhanQua.getMucQua());
        preparedStatement.setBoolean(3, duocNhanQua.getDuocXacNhan());
        preparedStatement.setInt(4,duocNhanQua.getMaDS());
        preparedStatement.setInt(5,duocNhanQua.getIdNhanKhau());
        return preparedStatement.executeUpdate();
    }


    public int deleteByMaDSAndIdNhanKhau(int maDs,int idNhanKhau) throws SQLException{
        String sql = "delete from duoc_nhan_qua where maDs = ? and idNhanKhau = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1, maDs);
        preparedStatement.setInt(2, idNhanKhau);
        return preparedStatement.executeUpdate();
    }
}
