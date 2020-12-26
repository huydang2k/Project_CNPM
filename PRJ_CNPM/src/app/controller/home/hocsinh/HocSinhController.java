package app.controller.home.hocsinh;

import app.model.HocSinh;
import app.model.form.FormThongTinHocSinh;
import app.repository.HocSinhRepo;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HocSinhController implements Initializable {

    HocSinhRepo hocSinhRepo;

    @FXML
    TableView<FormThongTinHocSinh> table;

    @FXML
    TableColumn<FormThongTinHocSinh, Integer> maHocSinhColumn;

    @FXML
    TableColumn<FormThongTinHocSinh, Integer> maNhanKhauColumn;

    @FXML
    TableColumn<FormThongTinHocSinh, String> hoTenColumn;

    @FXML
    TableColumn<FormThongTinHocSinh, Date> ngaySinhColumn;

    @FXML
    TableColumn<FormThongTinHocSinh, String> gioiTinhColumn;

    @FXML
    TableColumn<FormThongTinHocSinh, String> diaChiHienNayColumn;

    @FXML
    TableColumn<FormThongTinHocSinh, String> trinhDoHocVanColumn;
    
    @FXML
    TableColumn<FormThongTinHocSinh, String> numberColumn;

    ObservableList<FormThongTinHocSinh> formThongTinHocSinhObservableList;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hocSinhRepo = new HocSinhRepo();
        initTable();
        loadData();
    }
    
    private void initTable(){
        initColumns();
    }
    
    private void initColumns(){
        numberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FormThongTinHocSinh, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FormThongTinHocSinh, String> param) {
                return new ReadOnlyObjectWrapper<>(table.getItems().indexOf(param.getValue()) + 1 + " ");
            }
        });
        numberColumn.setSortable(false);
        maHocSinhColumn.setCellValueFactory(new PropertyValueFactory<>("maHS"));
        maNhanKhauColumn.setCellValueFactory(new PropertyValueFactory<>("maNhanKhau"));
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        diaChiHienNayColumn.setCellValueFactory(new PropertyValueFactory<>("diaChiHienNay"));
        trinhDoHocVanColumn.setCellValueFactory(new PropertyValueFactory<>("trinhDoHocVan"));
    }

    private void loadData(){
        formThongTinHocSinhObservableList = FXCollections.observableArrayList(new ArrayList<>());
        try {
            ArrayList<HocSinh> hocSinhArrayList = hocSinhRepo.findAll();
            for(int i = 0; i< hocSinhArrayList.size(); i++){
                formThongTinHocSinhObservableList.add(hocSinhArrayList.get(i).toFormThongTinHocSinh());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        table.setItems(formThongTinHocSinhObservableList);
    }
}
