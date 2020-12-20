package app.repository;

import app.model.DuocNhanQua;
import app.model.DuocNhanThuong;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DuocNhanThuongRepo extends BaseRepo<DuocNhanThuong> {
    @Override
    public DuocNhanThuong getObject(ResultSet rs) throws SQLException {
        DuocNhanThuong duocNhanThuong = new DuocNhanThuong();
        duocNhanThuong.setMaDS(rs.getInt("maDS"));
        duocNhanThuong.setMaHS(rs.getInt("maHS"));
        duocNhanThuong.setThanhTich(rs.getString("thanhTich"));
        duocNhanThuong.setMinhChung(rs.getBoolean("minhChung"));
        duocNhanThuong.setXepLoai(rs.getString("xepLoai"));
        duocNhanThuong.setMucThuong(rs.getDouble("mucThuong"));
        duocNhanThuong.setDuocXacNhan(rs.getBoolean("duocXacNhan"));
        return duocNhanThuong;
    }

    @Override
    protected ArrayList<DuocNhanThuong> findAll() throws SQLException {
        return null;
    }

    @Override
    protected DuocNhanThuong findById(int maDs) throws SQLException {
        return null;
    }

    public ArrayList<DuocNhanThuong> findByMaDS(int maDS) throws SQLException{
        String sql = "SELECT * FROM duoc_nhan_thuong WHERE maDS = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1, maDS);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }
    public ArrayList<DuocNhanThuong> findByMaDSDangPhat(int maDS) throws SQLException{
        String sql = "SELECT * FROM duoc_nhan_thuong WHERE maDS = ? and minhChung = 1";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1, maDS);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }
    public int insert(DuocNhanThuong duocNhanThuong) throws SQLException{
        String sql = "insert into duoc_nhan_thuong values (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1,duocNhanThuong.getMaDS());
        preparedStatement.setInt(2,duocNhanThuong.getMaHS());
        preparedStatement.setString(3,duocNhanThuong.getThanhTich());
        preparedStatement.setBoolean(4,duocNhanThuong.getMinhChung());
        preparedStatement.setString(5,duocNhanThuong.getXepLoai());
        preparedStatement.setDouble(6,duocNhanThuong.getMucThuong());
        preparedStatement.setBoolean(7,duocNhanThuong.getDuocXacNhan());
        return preparedStatement.executeUpdate();
    }
    public int update(DuocNhanThuong duocNhanThuong) throws SQLException{
        String sql = "update duoc_nhan_thuong set " +
                "thanhTich = ?, " +
                "minhChung = ?," +
                "xepLoai = ?," +
                "mucThuong = ?,"+
                "duocXacNhan = ? " +
                "where maDS = ? and maHS = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setString(1,duocNhanThuong.getThanhTich());
        preparedStatement.setBoolean(2,duocNhanThuong.getMinhChung());
        preparedStatement.setString(3,duocNhanThuong.getXepLoai());
        preparedStatement.setDouble(4,duocNhanThuong.getMucThuong());
        preparedStatement.setBoolean(5,duocNhanThuong.getDuocXacNhan());
        preparedStatement.setInt(6,duocNhanThuong.getMaDS());
        preparedStatement.setInt(7,duocNhanThuong.getMaHS());
        return preparedStatement.executeUpdate();
    }


    public int deleteBymaDSandIdHS(int maDs,int maHS) throws SQLException{
        String sql = "delete from duoc_nhan_thuong where maDs = ? and maHS = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1, maDs);
        preparedStatement.setInt(2, maHS);
        return preparedStatement.executeUpdate();
    }
}
