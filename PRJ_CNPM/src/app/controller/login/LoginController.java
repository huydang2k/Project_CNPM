package app.controller.login;

import app.controller.CommonController;
import app.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    CommonController commonController;
    UserService userService;

    @FXML
    private TextField usernameTextField;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginBtn;
    public void loginClick(ActionEvent e){
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        try{
            boolean loginSuccess = userService.getUser(username,password);
            if(loginSuccess){
                //switch scene
                Screen screen = Screen.getPrimary();
                Rectangle2D bounds = screen.getVisualBounds();
                commonController.primaryStage.setWidth(bounds.getWidth());
                commonController.primaryStage.setHeight(bounds.getHeight());
                commonController.primaryStage.setMaximized(true);
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
    public void loginEnter(KeyEvent e){
        if(e.getCode()== KeyCode.ENTER){
            loginBtn.fire();
            e.consume();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userService = new UserService();
        commonController = new CommonController();

    }
}
