package app.repository;

import app.model.DSPhatQua;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "SELECT * FROM ds_phat_qua";
        PreparedStatement preparedStatement = prepare(sql);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }
    public DSPhatQua danhSachPhatQuaTheoMa(int maDanhSach){
        return null;
    }
    public void themMoiDanhSachPhatQua(DSPhatQua dsPhatQua){
    }
    public void xoaDanhSachPhatQua(int maDanhSach){

    }


}
