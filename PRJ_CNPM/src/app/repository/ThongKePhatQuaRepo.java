package app.repository;

import app.model.form.FormThongKe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ThongKePhatQuaRepo extends BaseRepo<FormThongKe>{

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
            String sql = "SELECT nk2.hoTen as hoTenChuHo,hk.ID as hoGiaDinh ,count(dnq.maDS) as soPhanQua,sum(dnq.mucQua) as soTien FROM ((((duoc_nhan_qua as dnq  join nhan_khau as nk on dnq.idNhanKhau = nk.ID)\n" +
                    "  join thanh_vien_cua_ho as tvch on nk.ID = tvch.idNhanKhau)\n" +
                    " join ho_khau as hk on tvch.idHoKhau = hk.ID)  join nhan_khau as nk2 on hk.idChuHo = nk2.ID) where dnq.maDS = ? group by nk2.hoTen,hk.ID";
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

}
