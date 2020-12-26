package app.controller.home.thieunhi;

import app.model.NhanKhau;
import app.model.NhanKhau;
import app.repository.NhanKhauRepo;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ThieuNhiController implements Initializable {

    NhanKhauRepo nhanKhauRepo;

    @FXML
    TableView<NhanKhau> table;

    @FXML
    TableColumn<NhanKhau, Integer> maNhanKhauColumn;

    @FXML
    TableColumn<NhanKhau, String> hoTenColumn;

    @FXML
    TableColumn<NhanKhau, Date> ngaySinhColumn;

    @FXML
    TableColumn<NhanKhau, String> gioiTinhColumn;

    @FXML
    TableColumn<NhanKhau, String> diaChiHienNayColumn;

    @FXML
    TableColumn<NhanKhau, String> trinhDoHocVanColumn;

    @FXML
    TableColumn<NhanKhau, String> numberColumn;

    @FXML
    ObservableList<NhanKhau> nhanKhauObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nhanKhauRepo = new NhanKhauRepo();
        initTable();
        loadData();
    }
    
    private void initTable(){
        initColumns();
    }
    
    private void initColumns(){
        numberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<NhanKhau, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<NhanKhau, String> param) {
                return new ReadOnlyObjectWrapper<>(table.getItems().indexOf(param.getValue()) + 1 + " ");
            }
        });
        numberColumn.setSortable(false);
        maNhanKhauColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        diaChiHienNayColumn.setCellValueFactory(new PropertyValueFactory<>("diaChiHienNay"));
        trinhDoHocVanColumn.setCellValueFactory(new PropertyValueFactory<>("trinhDoHocVan"));
    }

    private void loadData(){
        try {
            nhanKhauObservableList = FXCollections.observableArrayList(nhanKhauRepo.findAllChildren());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        table.setItems(nhanKhauObservableList);
    }
}
