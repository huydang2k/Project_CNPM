package app.repository;

import app.common.MyConnection;
import app.model.User;
import app.model.form.LoginForm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepo extends BaseRepo<User>{

    public User getObject(ResultSet rs) throws SQLException{
        User user = new User();
        user.setId(rs.getInt("ID"));
        user.setUsername(rs.getString("userName"));
        user.setPassword(rs.getString("password"));
        return user;
    }

    @Override
    protected ArrayList<User> findAll() throws SQLException {
        return null;
    }

    @Override
    protected User findById(int maDs) throws SQLException {
        return null;
    }

    @Override
    protected int insert(User user) throws SQLException {
        return 0;
    }

    @Override
    protected int update(User user) throws SQLException {
        return 0;
    }

    @Override
    protected int deleteById(int maDS) throws SQLException {
        return 0;
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
