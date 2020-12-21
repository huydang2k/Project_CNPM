package app.repository;

import app.model.NhanKhau;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanKhauRepo extends BaseRepo<NhanKhau>{

    @Override
    public NhanKhau getObject(ResultSet rs) throws SQLException {
        NhanKhau nhanKhau = new NhanKhau();
        nhanKhau.setID(rs.getInt("ID"));
        nhanKhau.setMaNhanKhau(rs.getString("maNhanKhau"));
        nhanKhau.setHoTen(rs.getString("hoTen"));
        nhanKhau.setNamSinh(rs.getDate("namSinh"));
        nhanKhau.setGioiTinh(rs.getString("gioiTinh"));
        nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));
        nhanKhau.setTrinhDoHocVan(rs.getString("trinhDoHocVan"));
        nhanKhau.setTrinhDoChuyenMon(rs.getString("trinhDoChuyenMon"));
        nhanKhau.setNgheNghiep(rs.getString("ngheNghiep"));
        nhanKhau.setNoiLamViec(rs.getString("noiLamViec"));
        return nhanKhau;
    }

    @Override
    protected ArrayList<NhanKhau> findAll() throws SQLException {
        return null;
    }

    public NhanKhau findById(int id)throws SQLException{
        String sql = "SELECT * FROM nhan_khau WHERE ID = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        rs.first();
        return getObject(rs);
    }

    @Override
    protected int insert(NhanKhau nhanKhau) throws SQLException {
        return 0;
    }

    @Override
    protected int update(NhanKhau nhanKhau) throws SQLException {
        return 0;
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
    public ArrayList<NhanKhau> findAllChildren() throws SQLException{
        ArrayList<NhanKhau> nhanKhauArrayList = new ArrayList<>();
        String sql = "select * from nhan_khau as nk where" +
                "where round(DATEDIFF(CURRENT_DATE,nk.namsinh )/365) < 18";
        PreparedStatement preparedStatement = prepare(sql);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }
}
