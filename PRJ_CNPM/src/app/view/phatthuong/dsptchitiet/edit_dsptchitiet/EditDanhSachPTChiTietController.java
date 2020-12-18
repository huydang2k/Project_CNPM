package app.view.phatthuong.dsptchitiet.edit_dsptchitiet;

import app.view.CommonController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDanhSachPTChiTietController implements Initializable {
    CommonController commonController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
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
    public void toPhatQua(){
        commonController.toPhatQua();
    }
}
