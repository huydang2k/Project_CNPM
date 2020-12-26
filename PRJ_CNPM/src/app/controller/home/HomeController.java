package app.controller.home;

import app.Main;
import app.controller.CommonController;
import app.service.HoKhauService;
import app.service.HocSinhService;
import app.service.NhanKhauService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    HocSinhService hocSinhService;
    HoKhauService hoKhauService;
    NhanKhauService nhanKhauService;
    CommonController commonController;
    Stage thieuNhiStage;
    Stage hocSinhStage;
    @FXML
    private Label soLuongHocSinhLabel;
    @FXML
    private Label soLuongHoKhauLabel;
    @FXML
    private Label soLuongThieuNhiLabel;
    public void toPhatQua(){
        commonController.toPhatQua();
    }
    public void toPhatThuong(){
        commonController.toPhatThuong();
    }
    public void toThongKe(){
        commonController.toThongKe();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        thieuNhiStage = new Stage();
        thieuNhiStage.setTitle("Thiếu nhi trong khu vực");
        hocSinhStage = new Stage();
        hocSinhStage.setTitle("Học sinh trong khu vực");
        hocSinhService = new HocSinhService();
        hoKhauService = new HoKhauService();
        nhanKhauService = new NhanKhauService();
        commonController = new CommonController();
        try{
            soLuongHoKhauLabel.setText(soLuongHoKhauLabel.getText()
                    +" "+
                    hoKhauService.soLuongHoKhau());
            soLuongThieuNhiLabel.setText(soLuongThieuNhiLabel.getText()
                    +" "+
                    nhanKhauService.soLuongThieuNhi());
            soLuongHocSinhLabel.setText(soLuongHocSinhLabel.getText()
                    +" "+
                    hocSinhService.soLuongHocSinh());
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void toHoKhau(){}

    public void toThieuNhi(){
        if(!thieuNhiStage.isShowing()){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../../view/home/thieunhi/ThieuNhiUI.fxml"));
                Scene scene = new Scene(root);
                thieuNhiStage.setScene(scene);
                thieuNhiStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void toHocSinh(){
        if(!hocSinhStage.isShowing()){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../../view/home/hocsinh/HocSinhUI.fxml"));
                Scene scene = new Scene(root);
                thieuNhiStage.setScene(scene);
                thieuNhiStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
