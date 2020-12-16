package app.repository;

import app.common.MyConnection;
import app.model.User;
import app.model.form.LoginForm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo extends BaseRepo<User>{

    public User getObject(ResultSet rs) throws SQLException{
        User user = new User();
        user.setId(rs.getInt("ID"));
        user.setUsername(rs.getString("userName"));
        user.setPassword(rs.getString("password"));
        return user;
    }


    public User findUserByUserNameAndPassWord(String username, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM users WHERE userName = ? AND password = ?";
        PreparedStatement preparedStatement = prepare(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            rs.first();            return getObject(rs);
        }else{
            return null;
        }
    }
}
