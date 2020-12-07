package app.view.phatqua;

import app.model.DSPhatQua;
import app.model.DSPhatThuong;
import app.service.DSPhatQuaService;
import app.view.CommonController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PhatQuaController implements Initializable {

    DSPhatQuaService dsPhatQuaService;
    CommonController commonController;

    private ArrayList<DSPhatQua> dsPhatQuaArrayList = null;
    private DSPhatQua dsPhatQuaDangChon = null;

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
        dsPhatQuaService = new DSPhatQuaService();
        commonController = new CommonController();
        dsPhatQuaArrayList = dsPhatQuaService.tatCaDanhSachPhatQua();
    }
}
