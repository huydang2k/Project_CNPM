package app.controller.phatqua.dspqchitiet.view_dspqchitiet;

import app.controller.CommonController;
import app.model.DSPhatQua;
import app.model.form.FormDSPQChiTiet;
import app.service.DSPQChiTietService;
import app.service.PrintPDFService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewDanhSachChiTietController implements Initializable {

    private DSPhatQua dsPhatQua;

    CommonController commonController;

    DSPQChiTietService dspqChiTietService;
    @FXML
    SplitPane R;
    @FXML
    Label nameDSPQ;

    @FXML
    Label namDSPQ;

    @FXML
    Label dipDSPQ;

    @FXML
    Label trangThaiDSPQ;

    @FXML
    Label tongChiDSPQ;

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
    TableColumn<FormDSPQChiTiet, String> numberColumn;

    @FXML
    TableColumn<FormDSPQChiTiet, String> ghiChuColumn;

    ObservableList<FormDSPQChiTiet> dspqChiTietObservableList;
    PrintPDFService printPDFService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
        commonController.csslize(R);
        dspqChiTietService = new DSPQChiTietService();
        printPDFService = new PrintPDFService();
    }
    public void printpdf(){
        try{
            String filePath = commonController.chooseDirectory();
            if(filePath != null) {
                printPDFService.printDS(dsPhatQua.getMaDS(), true, filePath);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("In danh sách");
                alert.setHeaderText("In danh sách hoạt động phát quà " + dsPhatQua.getSuKien());
                alert.setContentText("In thành công");
                alert.show();
            }
        }
        catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Đã có lỗi xảy ra");
            alert.setContentText("Không thể in danh sách. Vui lòng thử lại sau");
            alert.show();
        }
    }
    public void initData(DSPhatQua rowData){
        this.dsPhatQua = rowData;
        nameDSPQ.setText(dsPhatQua.getSuKien());
        String trangThai = "";
        switch (dsPhatQua.getTrangThai()){
            case -1:
                trangThai = "Đã xóa";
                break;
            case 1:
                trangThai = "Đang phát";
                break;
            case 2:
                trangThai = "Đã hoàn thành";
                break;
        }
        trangThaiDSPQ.setText(trangThaiDSPQ.getText() +" "+trangThai);
        namDSPQ.setText(namDSPQ.getText()+" "+dsPhatQua.getNgayTao().toString().substring(0,4));
        tongChiDSPQ.setText(tongChiDSPQ.getText()+" "+String.valueOf(dsPhatQua.getTongChiPhi()));
        dipDSPQ.setText(dipDSPQ.getText()+" "+dsPhatQua.getSuKien());
        initTable();
        loadData();
    }

    private void initTable(){
        initColumns();
    }

    private void initColumns(){
        numberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FormDSPQChiTiet, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FormDSPQChiTiet, String> param) {
                return new ReadOnlyObjectWrapper<>(table.getItems().indexOf(param.getValue()) + 1 + " ");
            }
        });
        ghiChuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FormDSPQChiTiet, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FormDSPQChiTiet, String> param) {
                String content = "";
                FormDSPQChiTiet formDSPQChiTiet = param.getValue();
                boolean duocXacNhan = formDSPQChiTiet.isDuocXacNhan();
                if(duocXacNhan){
                    content = "Đã trao";
                }else{
                    content = "Chưa trao";
                }
                return new ReadOnlyObjectWrapper<>(content);
            }
        });
        numberColumn.setSortable(false);
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        namSinhColumn.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
        hoGDColumn.setCellValueFactory(new PropertyValueFactory<>("idHoKhau"));
        phanQuaColumn.setCellValueFactory(new PropertyValueFactory<>("phanQua"));
        mucQuaColumn.setCellValueFactory(new PropertyValueFactory<>("mucQua"));
    }

    private void loadData(){
        try {
            dspqChiTietObservableList = FXCollections.observableArrayList(dspqChiTietService.getFormDSPQChiTietByMaDS(dsPhatQua.getMaDS()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        table.setItems(dspqChiTietObservableList);
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
