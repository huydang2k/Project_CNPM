package app.controller.phatthuong.themmoi;

import app.controller.CommonController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ThemMoiPTController implements Initializable {
    @FXML
    SplitPane R;
    CommonController commonController;
    public void toPhatQua(){
        commonController.toPhatQua();
    }
    public void toHome(){
        commonController.toHome();
    }
    public void toPhatThuong(){
        commonController.toPhatThuong();
    }
    public void toThongKe(){
        commonController.toThongKe();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
        commonController.csslize(R);
    }
}
