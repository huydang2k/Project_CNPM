package app.repository;

import app.common.MyConnection;
import app.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepo<T> {
    public PreparedStatement prepare(String sql) throws SQLException, ClassNotFoundException{
        try {
            System.out.println(">> "+sql);
            //connection.prepareStatment trả về đối tượng PrepareStatment dùng để thực hiện query String sql
            return MyConnection.connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //ResultSet.TYPE_SCROLL_SENSITIVE: cho phép con trỏ resultSet chạy từ bản ghi đầu đến cuối.
            //ResultSet.CONCUR_UPDATABLE: tạo ra một đuối tượng resultSet có thể được cập nhập.
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public abstract T getObject(ResultSet rs) throws SQLException;

    public List<T> getList(ResultSet rs) throws SQLException{
        List<T> data = new ArrayList<>();
        while(rs.next()){
            data.add(getObject(rs));
        }
        return data;
    }
}
