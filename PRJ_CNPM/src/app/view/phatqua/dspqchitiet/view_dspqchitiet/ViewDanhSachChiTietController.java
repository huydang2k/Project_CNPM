package app.view.phatqua.dspqchitiet.view_dspqchitiet;

import app.model.DSPhatQua;
import app.model.DuocNhanQua;
import app.model.NhanKhau;
import app.model.ThanhVienCuaHo;
import app.model.form.FormDSPQChiTiet;
import app.repository.DuocNhanQuaRepo;
import app.repository.NhanKhauRepo;
import app.repository.ThanhVienCuaHoRepo;
import app.service.DSPQChiTietService;
import app.view.CommonController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewDanhSachChiTietController implements Initializable {

    private DSPhatQua dsPhatQua;

    CommonController commonController;

    DSPQChiTietService dspqChiTietService;

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
    TableColumn<FormDSPQChiTiet, Void> buttonsColumn;

    ObservableList<FormDSPQChiTiet> dspqChiTietObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
        dspqChiTietService = new DSPQChiTietService();
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
        numberColumn.setSortable(false);
        Callback<TableColumn<FormDSPQChiTiet, Void>, TableCell<FormDSPQChiTiet, Void>> cellFactory = new Callback<TableColumn<FormDSPQChiTiet, Void>, TableCell<FormDSPQChiTiet, Void>>() {
            @Override
            public TableCell<FormDSPQChiTiet, Void> call(final TableColumn<FormDSPQChiTiet, Void> param) {
                final TableCell<FormDSPQChiTiet, Void> cell = new TableCell<FormDSPQChiTiet, Void>() {
                    private final Button deleteButton = new Button("Xóa");

                    {
                        deleteButton.setOnAction((ActionEvent event) -> {
                            FormDSPQChiTiet data = getTableView().getItems().get(getIndex());
                            dspqChiTietObservableList.remove(data);
                            table.setItems(dspqChiTietObservableList);
                        });
                        deleteButton.setMaxSize(200,200);
                    }


                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteButton);
                        }
                    }
                };
                return cell;
            }
        };
        buttonsColumn.setCellFactory(cellFactory);
        buttonsColumn.setSortable(false);
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
