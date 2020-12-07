package app.service;

import app.model.User;
import app.model.form.LoginForm;
import app.repository.UserRepo;

import java.sql.SQLException;

public class UserService {
    UserRepo userRepo;


    public UserService() {
        userRepo = new UserRepo();
    }

    public boolean verifyUser(String username, String password) throws SQLException, ClassNotFoundException{
        User user = userRepo.getUser(username,password);
        if (user == null){
            return false;
        }else{
            return true;
        }
    }
}
