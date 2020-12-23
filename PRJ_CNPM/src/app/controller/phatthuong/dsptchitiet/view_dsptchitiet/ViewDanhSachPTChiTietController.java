package app.controller.phatthuong.dsptchitiet.view_dsptchitiet;

import app.controller.CommonController;
import app.model.DSPhatThuong;
import app.model.form.FormDSPTChiTiet;
import app.service.DSPTChiTietService;
import app.service.DSPhatThuongService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewDanhSachPTChiTietController implements Initializable {
    CommonController commonController;
    DSPTChiTietService dsptChiTietService;
    private DSPhatThuong dsPhatThuong;
    ObservableList<FormDSPTChiTiet> formDSPTChiTietObservableList;
    @FXML
    SplitPane R;
    @FXML
    Label nameDSPT;

    @FXML
    Label namDSPT;

    @FXML
    Label dipDSPT;

    @FXML
    Label hanNop;

    @FXML
    Label trangThaiDSPT;

    @FXML
    Label tongChiDSPT;
    @FXML
    TableView<FormDSPTChiTiet> table;
    @FXML
    TableColumn<FormDSPTChiTiet,String> numberColumn;
    @FXML
    TableColumn<FormDSPTChiTiet,String> hoTenColumn;
    @FXML
    TableColumn<FormDSPTChiTiet,Integer> namColumn;
    @FXML
    TableColumn<FormDSPTChiTiet,Integer> hoGDColumn;
    @FXML
    TableColumn<FormDSPTChiTiet,String> thanhTichColumn;

    @FXML
    TableColumn<FormDSPTChiTiet,String> xepLoaiColumn;
    @FXML
    TableColumn<FormDSPTChiTiet,String> mucThuongColumn;
    @FXML
    TableColumn<FormDSPTChiTiet,String> ghiChuColumn;

    public void initData(DSPhatThuong rowData){
        this.dsPhatThuong = rowData;
        nameDSPT.setText(dsPhatThuong.getSuKien());
        String trangThai = "";
        switch (dsPhatThuong.getTrangThai()){
            case -1:
                trangThai = "Đã xóa";
                break;
            case 1:
                trangThai = "Đang phát";
                break;
            case 2:
                trangThai = "Đã hoàn thành";
                break;
            case 0:
                trangThai = "Đợi nộp minh chứng";
                break;
        }
        trangThaiDSPT.setText(trangThaiDSPT.getText() +" "+trangThai);
        namDSPT.setText(namDSPT.getText()+" "+dsPhatThuong.getNgayTao().toString().substring(0,4));
        tongChiDSPT.setText(tongChiDSPT.getText()+" "+String.valueOf(dsPhatThuong.getTongChiPhi()));
        dipDSPT.setText(dipDSPT.getText()+" "+dsPhatThuong.getSuKien());
        try{hanNop.setText("Hạn nộp minh chứng: "+new DSPhatThuongService().getHanNopMc(dsPhatThuong.getMaDS()).toString());}
        catch (SQLException ex){
            hanNop.setText("Hạn nộp minh chứng: NULL");
        }
        initTable();
        loadData();
    }

    private void initTable(){
        initColumns();
    }

    private void initColumns(){
        numberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FormDSPTChiTiet, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FormDSPTChiTiet, String> param) {
                return new ReadOnlyObjectWrapper<>(table.getItems().indexOf(param.getValue()) + 1 + " ");
            }
        });
        ghiChuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FormDSPTChiTiet, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FormDSPTChiTiet, String> param) {
                String content = "";
                FormDSPTChiTiet formDSPTChiTiet = param.getValue();
                boolean duocXacNhan = formDSPTChiTiet.isDuocXacNhan();
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
        namColumn.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
        hoGDColumn.setCellValueFactory(new PropertyValueFactory<>("idHoKhau"));
        thanhTichColumn.setCellValueFactory(new PropertyValueFactory<>("thanhTich"));
        xepLoaiColumn.setCellValueFactory(new PropertyValueFactory<>("xepLoai"));
        mucThuongColumn.setCellValueFactory(new PropertyValueFactory<>("mucThuong"));
    }

    private void loadData(){
        try {
            formDSPTChiTietObservableList = FXCollections.observableArrayList(dsptChiTietService.getFormDSPTChiTietByMaDS(dsPhatThuong.getMaDS()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        table.setItems(formDSPTChiTietObservableList);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
        commonController.csslize(R);
        dsptChiTietService = new DSPTChiTietService();
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
