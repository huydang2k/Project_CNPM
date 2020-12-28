package app.controller.home.hokhau;

import app.model.HoKhau;
import app.model.NhanKhau;
import app.model.ThanhVienCuaHo;
import app.model.form.FormThongTinThanhVienCuaHo;
import app.repository.HoKhauRepo;
import app.repository.NhanKhauRepo;
import app.repository.ThanhVienCuaHoRepo;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HoKhauController implements Initializable {

    ThanhVienCuaHoRepo thanhVienCuaHoRepo;
    NhanKhauRepo nhanKhauRepo;
    HoKhauRepo hoKhauRepo;

    @FXML
    TableView<HoKhau> tableHoKhau;

    @FXML
    TableColumn<HoKhau, Integer> maHoKhauColumn;

    @FXML
    TableColumn<HoKhau, Integer> maChuHoColumn;

    @FXML
    TableColumn<HoKhau, String> maKhuVucColumn;

    @FXML
    TableColumn<HoKhau, String> diaChiColumn;

    @FXML
    TableColumn<HoKhau, String> numberColumn_HK;

    @FXML
    TableView<FormThongTinThanhVienCuaHo> tableThanhVienCuaHo;

    @FXML
    TableColumn<FormThongTinThanhVienCuaHo, Integer> maNhanKhauColumn;

    @FXML
    TableColumn<FormThongTinThanhVienCuaHo, String> hoTenColumn;

    @FXML
    TableColumn<FormThongTinThanhVienCuaHo, Date> ngaySinhColumn;

    @FXML
    TableColumn<FormThongTinThanhVienCuaHo, String> gioiTinhColumn;

    @FXML
    TableColumn<FormThongTinThanhVienCuaHo, String> diaChiHienNayColumn;

    @FXML
    TableColumn<FormThongTinThanhVienCuaHo, String> quanHeVoiChuHoColumn;

    @FXML
    TableColumn<FormThongTinThanhVienCuaHo, String> numberColumn_TVCH;

    ObservableList<HoKhau> hoKhauObservableList;

    ObservableList<FormThongTinThanhVienCuaHo> thanhVienCuaHoObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        thanhVienCuaHoRepo = new ThanhVienCuaHoRepo();
        nhanKhauRepo = new NhanKhauRepo();
        hoKhauRepo = new HoKhauRepo();
        initTableHoKhau();
        initTableNhanKhau();
        loadDataHoKhau();
    }

    private void initTableHoKhau(){
        initColumnsHoKhau();
        tableHoKhau.setRowFactory( RowFactory -> {
            TableRow<HoKhau> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    HoKhau rowData = row.getItem();
                    System.out.println(rowData);
                    loadDataNhanKhau(rowData.getID());
                }
            });
            return row ;
        });
    }

    private void initColumnsHoKhau(){
        numberColumn_HK.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HoKhau, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<HoKhau, String> param) {
                return new ReadOnlyObjectWrapper<>(tableHoKhau.getItems().indexOf(param.getValue()) + 1 + " ");
            }
        });
        numberColumn_HK.setSortable(false);
        maHoKhauColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        maChuHoColumn.setCellValueFactory(new PropertyValueFactory<>("idChuHo"));
        maKhuVucColumn.setCellValueFactory(new PropertyValueFactory<>("maKhuVuc"));
        diaChiColumn.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
    }

    private void loadDataHoKhau(){
        try {
            hoKhauObservableList = FXCollections.observableArrayList(hoKhauRepo.findAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tableHoKhau.setItems(hoKhauObservableList);
    }

    private void initTableNhanKhau(){
        initColumnsNhanKhau();
    }

    private void initColumnsNhanKhau(){
        numberColumn_TVCH.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FormThongTinThanhVienCuaHo, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FormThongTinThanhVienCuaHo, String> param) {
                return new ReadOnlyObjectWrapper<>(tableThanhVienCuaHo.getItems().indexOf(param.getValue()) + 1 + " ");
            }
        });
        numberColumn_TVCH.setSortable(false);
        maNhanKhauColumn.setCellValueFactory(new PropertyValueFactory<>("idNhanKhau"));
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        diaChiHienNayColumn.setCellValueFactory(new PropertyValueFactory<>("diaChiHienNay"));
        quanHeVoiChuHoColumn.setCellValueFactory(new PropertyValueFactory<>("quanHeVoiChuHo"));
    }

    private void loadDataNhanKhau(int idHoKhau){
        try {
            thanhVienCuaHoObservableList = FXCollections.observableArrayList();
            ArrayList<ThanhVienCuaHo> thanhVienCuaHoArrayList = thanhVienCuaHoRepo.findThanhVienByIdHoKhau(idHoKhau);
            for(int i = 0; i< thanhVienCuaHoArrayList.size(); i++){
                ThanhVienCuaHo thanhVienCuaHo = thanhVienCuaHoArrayList.get(i);
                NhanKhau nhanKhau = nhanKhauRepo.findById(thanhVienCuaHo.getIdNhanKhau());
                FormThongTinThanhVienCuaHo formThongTinThanhVienCuaHo = new FormThongTinThanhVienCuaHo(thanhVienCuaHo.getIdHoKhau(), thanhVienCuaHo.getIdNhanKhau(), nhanKhau.getHoTen(), nhanKhau.getNamSinh(), nhanKhau.getGioiTinh(), nhanKhau.getDiaChiHienNay(), thanhVienCuaHo.getQuanHeVoiChuHo());
                thanhVienCuaHoObservableList.add(formThongTinThanhVienCuaHo);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tableThanhVienCuaHo.setItems(thanhVienCuaHoObservableList);
    }
}
