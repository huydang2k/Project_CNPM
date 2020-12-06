package app.view.home;

import app.view.CommonController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    CommonController commonController;
    public void toPhatQua(){
        commonController.toPhatQua();
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
    }
}
