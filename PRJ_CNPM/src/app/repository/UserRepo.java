package app.repository;

import app.model.User;
import app.model.form.LoginForm;

import java.sql.SQLException;

public interface UserRepo {
    public User login(LoginForm loginForm) throws SQLException, ClassNotFoundException;
}
