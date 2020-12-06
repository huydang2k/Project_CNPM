package app.service;

import app.model.User;
import app.model.form.LoginForm;
import app.repository.UserRepo;
import app.repository.UserRepoImpl;

import java.sql.SQLException;

public class UserService {
    UserRepo userRepo;


    public UserService() {
        userRepo = new UserRepoImpl();
    }

    public boolean login(LoginForm loginForm) throws SQLException, ClassNotFoundException{
        User user = userRepo.login(loginForm);
        if (user == null){
            return false;
        }else{
            return true;
        }
    }
}
