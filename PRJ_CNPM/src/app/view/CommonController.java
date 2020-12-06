package app.view;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
/*This class is used to switch scene*/
public class CommonController implements Initializable {
    public static Stage primaryStage;
    public Scene makeScene(String fxmlpath){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(fxmlpath));
            return new Scene(root);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public void switchScene(Scene scene){
        try{
            primaryStage.setScene(scene);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void toHome(){
        primaryStage.setScene(makeScene("home/HomeUI.fxml"));
    }
    public void toPhatQua(){
        primaryStage.setScene(makeScene("phatqua/PQUI.fxml"));
    }
    public void toPhatThuong(){
        primaryStage.setScene(makeScene("phatthuong/PTUI.fxml"));
    }
    public void toThongKe(){
       // primaryStage.setScene(makeScene("thongke/TKUI.fxml"));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
