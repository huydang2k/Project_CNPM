package app.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
/*This class is used to switch scene*/
public class CommonController implements Initializable {
    public static Stage primaryStage;

    public String chooseDirectory(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File dir = directoryChooser.showDialog(primaryStage);
        if(dir!= null){
            return dir.getAbsolutePath();
        }else{
            return null;
        }
    }
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
    public void csslize(Parent scene){
        scene.getStylesheets().add(getClass().getResource("../view/common_style.css").toExternalForm());
    }
    public void toThemMoiDsPhatQua() {
        switchScene(makeScene("../view/phatqua/themmoi/ThemMoi.fxml"));
    }
    public void toThemMoiDsPhatThuong(){
        switchScene(makeScene("../view/phatthuong/themmoi/ThemMoi.fxml"));
    }
    public void toHome(){
        switchScene(makeScene("../view/home/HomeUI.fxml"));
    }
    public void toPhatQua(){
        switchScene(makeScene("../view/phatqua/PQUI.fxml"));
    }
    public void toPhatThuong(){
        switchScene(makeScene("../view/phatthuong/PTUI.fxml"));
    }
    public void toThongKe(){
       primaryStage.setScene(makeScene("../view/thongke/ThongKe.fxml"));
    }
    public void toThongKePQ(){
        primaryStage.setScene(makeScene("../view/thongke/thongkepq/ThongKePhatQua.fxml"));
    }
    public void toThongKePT(){
        primaryStage.setScene(makeScene("../view/thongke/thongkept/ThongKePhatThuong.fxml"));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
