package app.view.thongke;



import app.model.DSPhatQua;
import app.view.CommonController;
import app.view.phatqua.dspqchitiet.edit_dspqchitiet.EditDanhSachChiTietController;
import app.view.phatqua.dspqchitiet.view_dspqchitiet.ViewDanhSachChiTietController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
