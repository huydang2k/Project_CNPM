package app.view.phatthuong;

import app.model.DSPhatQua;
import app.model.DSPhatThuong;
import app.service.DSPhatThuongService;
import app.view.CommonController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class PhatThuongController implements Initializable {
    DSPhatThuongService dsPhatThuongService;
    CommonController commonController;

    private ArrayList<DSPhatThuong> dsPhatThuongArrayList = null;
    private DSPhatThuong dsPhatThuongDangChon = null;
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
        dsPhatThuongService = new DSPhatThuongService();
    }
}
