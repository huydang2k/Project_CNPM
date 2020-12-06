package app.view.thongke;



import app.view.CommonController;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
    }
}
