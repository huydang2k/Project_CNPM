package app.view.phatqua;

import app.view.CommonController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class PhatQuaController implements Initializable {
    CommonController commonController;
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
    }
}
