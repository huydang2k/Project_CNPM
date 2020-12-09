package app.view.phatqua;

import app.model.DSPhatQua;
import app.model.DSPhatThuong;
import app.service.DSPhatQuaService;
import app.view.CommonController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PhatQuaController implements Initializable {

    DSPhatQuaService dsPhatQuaService;
    CommonController commonController;

    private List<DSPhatQua> dsPhatQuaArrayList = null;
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
    public void initialize(URL location, ResourceBundle resources){
        dsPhatQuaService = new DSPhatQuaService();
        commonController = new CommonController();
        try{
            dsPhatQuaArrayList = dsPhatQuaService.tatCaDanhSachPhatQua();
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }
}
