package app.repository;

import app.model.form.FormThongKe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ThongKePhatThuongRepo extends BaseRepo<FormThongKe>{
    @Override
    public FormThongKe getObject(ResultSet rs) throws SQLException {
        FormThongKe formThongKe = new FormThongKe();
        formThongKe.setHoTenChuHo(rs.getString("hoTenChuHo"));
        formThongKe.setHoGiaDinh(rs.getInt("hoGiaDinh"));
        formThongKe.setSoPhanQua(rs.getInt("soPhanQua"));
        formThongKe.setSoTien(rs.getDouble("soTien"));
        return formThongKe;
    }
    public ArrayList<FormThongKe> findByMaDs(int maDS) throws SQLException {
        String sql = "SELECT nk2.hoTen as hoTenChuHo,hk.ID as hoGiaDinh ,count(dnt.maDS) as soPhanQua,sum(dnt.mucThuong) as soTien \n" +
                "FROM (((((duoc_nhan_thuong as dnt  \n" +
                "join hoc_sinh as hs on hs.maHS = dnt.maHS)\n" +
                "join nhan_khau as nk on hs.idNhanKhau = nk.ID)\n" +
                "join thanh_vien_cua_ho as tvch on nk.ID = tvch.idNhanKhau)\n" +
                "join ho_khau as hk on tvch.idHoKhau = hk.ID)  join nhan_khau as nk2 on hk.idChuHo = nk2.ID) \n" +
                "where dnt.maDS = ? group by nk2.hoTen,hk.ID\n" +
                "\n";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setInt(1,maDS);
        ResultSet rs = preparedStatement.executeQuery();
        return getList(rs);
    }
    @Override
    protected ArrayList<FormThongKe> findAll() throws SQLException {
        return null;
    }

    @Override
    protected FormThongKe findById(int maDs) throws SQLException {
        return null;
    }

    @Override
    protected int insert(FormThongKe formThongKe) throws SQLException {
        return 0;
    }

    @Override
    protected int update(FormThongKe formThongKe) throws SQLException {
        return 0;
    }

    @Override
    protected int deleteById(int maDS) throws SQLException {
        return 0;
    }
}
