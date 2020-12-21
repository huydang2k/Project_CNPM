package app.controller.phatqua.themmoi;

import app.controller.CommonController;
import app.model.form.FormDSPQChiTiet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.*;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ThemMoiPQController implements Initializable {
    CommonController commonController;

    @FXML
    TextField tenDSPQTextField;

    Date thoiGianTaoDanhSach;

    @FXML
    TableView<FormDSPQChiTiet> table;

    @FXML
    TableColumn<FormDSPQChiTiet, String> hoTenColumn;

    @FXML
    TableColumn<FormDSPQChiTiet, Integer> namSinhColumn;

    @FXML
    TableColumn<FormDSPQChiTiet, Integer> hoGDColumn;

    @FXML
    TableColumn<FormDSPQChiTiet, String> phanQuaColumn;

    @FXML
    TableColumn<FormDSPQChiTiet, Double> mucQuaColumn;

    @FXML
    TableColumn<FormDSPQChiTiet, String> ghiChuColumn;

    @FXML
    TableColumn<FormDSPQChiTiet, String> numberColumn;

    @FXML
    TableColumn<FormDSPQChiTiet, Void> buttonsColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
    }
    public void toPhatQua(){
        commonController.toPhatQua();
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
