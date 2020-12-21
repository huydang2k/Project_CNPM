package app.controller.thongke;



import app.controller.CommonController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ThongKeController implements Initializable {

    CommonController commonController;
    public void toHome(){
        commonController.toHome();
    }
    public void toPhatQua(){
        commonController.toPhatQua();
    }
    public void toPhatThuong(){
        commonController.toPhatThuong();
    }
    public void toThongKePQ(){
        commonController.toThongKePQ();
    }



    public void toThongKePT(){
        commonController.toThongKePT();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
    }
}
