package app.controller.thongke.thongkepq.tkpqchitiet;

import app.model.DSPhatQua;

import app.model.form.FormThongKe;
import app.service.ThongKePhatQuaService;
import app.controller.CommonController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TKPQChiTietController implements Initializable {
    CommonController commonController;
    private DSPhatQua dsPhatQua;
    ObservableList<FormThongKe> formThongKeObservableList;
    ThongKePhatQuaService thongKePhatQuaService;
    @FXML
    Label nameLabel;
    @FXML
    TableView<FormThongKe> table;

    @FXML
    TableColumn<FormThongKe, String> sttColumn;

    @FXML
    TableColumn<FormThongKe, String> hoTenColumn;

    @FXML
    TableColumn<FormThongKe, String> hoGDColumn;

    @FXML
    TableColumn<FormThongKe, String> soPhanQuaColumn;
    @FXML
    TableColumn<FormThongKe, String> soTienColum;

    public void initData(DSPhatQua rowData){
        this.dsPhatQua = rowData;
        nameLabel.setText("Thống kê hoạt động phát quà\n"+dsPhatQua.getSuKien());
        initTable();
        loadData();
    }

    private void initTable(){
        initColumns();
        table.setRowFactory( RowFactory -> {
            TableRow<FormThongKe> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                System.out.println(row);
                System.out.println(row.getItem().getHoGiaDinh());
            });
            return  row;
    });

    }

    private void initColumns(){
        sttColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FormThongKe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FormThongKe, String> param) {
                return new ReadOnlyObjectWrapper<>(table.getItems().indexOf(param.getValue()) + 1 + " ");
            }
        });
        hoGDColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FormThongKe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FormThongKe, String> param) {
                String content = "";
                FormThongKe formThongKe = param.getValue();
                content = String.valueOf(formThongKe.getHoGiaDinh());
                return new ReadOnlyObjectWrapper<>(content);
            }
        });
        soPhanQuaColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FormThongKe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FormThongKe, String> param) {
                String content = "";
                FormThongKe formThongKe = param.getValue();
                content = String.valueOf(formThongKe.getSoPhanQua());
                return new ReadOnlyObjectWrapper<>(content);
            }
        });
        soTienColum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FormThongKe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FormThongKe, String> param) {
                String content = "";
                FormThongKe formThongKe = param.getValue();
                content = String.valueOf(formThongKe.getSoTien());
                return new ReadOnlyObjectWrapper<>(content);
            }
        });
        sttColumn.setSortable(false);
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoTenChuHo"));
    }

    private void loadData(){
        try {
            formThongKeObservableList = FXCollections.observableArrayList(thongKePhatQuaService.findByMaDs(dsPhatQua.getMaDS()));
            System.out.println(formThongKeObservableList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        table.setItems(formThongKeObservableList);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
        thongKePhatQuaService = new ThongKePhatQuaService();
    }
}
