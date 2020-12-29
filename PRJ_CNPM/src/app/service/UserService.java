package app.service;

import app.model.User;
import app.repository.UserRepo;

import java.sql.SQLException;

public class UserService {
    UserRepo userRepo;


    public UserService() {
        userRepo = new UserRepo();
    }

    public boolean getUser(String username, String password) throws SQLException, ClassNotFoundException{
        User user = userRepo.findUserByUserNameAndPassWord(username,password);
        if (user == null){
            return false;
        }else{
            return true;
        }
    }
}
