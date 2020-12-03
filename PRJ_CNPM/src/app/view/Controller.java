package app.view;

import app.controller.UserController;
import app.model.form.LoginForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    UserController userController;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    public void loginClick(ActionEvent e){
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        LoginForm loginForm = new LoginForm(username, password);
        try{
            boolean loginSuccess = userController.login(loginForm);
            if(loginSuccess){
                //switch scene
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("Đăng nhập thành công");
                alert.show();
            }else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Đăng nhập thất bại");
                alert.show();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userController = new UserController();
    }
}
