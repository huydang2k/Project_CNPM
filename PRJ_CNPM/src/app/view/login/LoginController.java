package app.view.login;

import app.service.UserService;
import app.model.form.LoginForm;
import app.view.CommonController;
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

public class LoginController implements Initializable {
    CommonController commonController;
    UserService userService;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    public void loginClick(ActionEvent e){
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        LoginForm loginForm = new LoginForm(username, password);
        try{
            boolean loginSuccess = userService.login(loginForm);
            if(loginSuccess){
                //switch scene
                commonController.toHome();
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
        userService = new UserService();
        commonController = new CommonController();
    }
}
