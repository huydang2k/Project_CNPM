package app.repository;

import app.common.MyConnection;
import app.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepo<T> {
    /**
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    protected PreparedStatement prepare(String sql) throws SQLException{
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

    protected ArrayList<T> getList(ResultSet rs) throws SQLException{
        ArrayList<T> data = new ArrayList<>();
        while(rs.next()){
            data.add(getObject(rs));
        }
        return data;
    }
    protected abstract ArrayList<T> findAll() throws SQLException;
    protected abstract T findById(int maDs) throws SQLException;
    protected abstract  int insert(T t) throws SQLException;
    protected abstract  int update(T t) throws SQLException;

}
