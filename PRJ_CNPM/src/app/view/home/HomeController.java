package app.view.home;

import app.service.HoKhauService;
import app.view.CommonController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    HoKhauService hoKhauService;
    CommonController commonController;
    @FXML
    private Label dotQuaSapToiLabel;
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
        hoKhauService = new HoKhauService();
        commonController = new CommonController();
        try{
            soLuongHoKhauLabel.setText(soLuongHoKhauLabel.getText()
                    +" "+
                    hoKhauService.soLuongHoKhau());
            System.out.println(hoKhauService.soLuongThieuNhi());
            soLuongThieuNhiLabel.setText(soLuongThieuNhiLabel.getText()
                    +" "+
                    hoKhauService.soLuongThieuNhi());
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
