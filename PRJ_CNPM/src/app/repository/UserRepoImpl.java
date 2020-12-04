package app.repository;

import app.common.MyConnection;
import app.model.User;
import app.model.form.LoginForm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepoImpl implements UserRepo{

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

    public User getObject(ResultSet rs) throws SQLException{
        User user = new User();
        user.setId(rs.getInt("ID"));
        user.setUsername(rs.getString("userName"));
        user.setPassword(rs.getString("password"));
        return user;
    }

    @Override
    public User login(LoginForm loginForm) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM users WHERE userName = ? AND password = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setString(1, loginForm.getUsername( ));
        preparedStatement.setString(2, loginForm.getPassword());
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            rs.first();            return getObject(rs);
        }else{
            return null;
        }
    }
}
