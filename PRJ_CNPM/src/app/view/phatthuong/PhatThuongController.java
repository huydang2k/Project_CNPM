package app.view.phatthuong;

import app.view.CommonController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class PhatThuongController implements Initializable {
    CommonController commonController;
    public void toHome(){
        commonController.toHome();
    }
    public void toPhatQua(){
        commonController.toPhatQua();
    }
    public void toThongKe(){
        commonController.toThongKe();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
    }
}
