package app.view.home;

import app.service.HoKhauService;
import app.service.HocSinhService;
import app.service.NhanKhauService;
import app.view.CommonController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    HocSinhService hocSinhService;
    HoKhauService hoKhauService;
    NhanKhauService nhanKhauService;
    CommonController commonController;
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
}
