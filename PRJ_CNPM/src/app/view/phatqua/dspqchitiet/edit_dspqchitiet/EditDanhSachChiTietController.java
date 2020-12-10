package app.view.phatqua.dspqchitiet.edit_dspqchitiet;

import app.view.CommonController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDanhSachChiTietController implements Initializable {

    boolean editMode;

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
}
