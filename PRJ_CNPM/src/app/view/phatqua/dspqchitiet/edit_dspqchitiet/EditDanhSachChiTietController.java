package app.view.phatqua.dspqchitiet.edit_dspqchitiet;

import app.model.DSPhatQua;
import app.model.form.FormDSPQChiTiet;
import app.service.DSPQChiTietService;
import app.view.CommonController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditDanhSachChiTietController implements Initializable {

    private DSPhatQua dsPhatQua;

    DSPQChiTietService dspqChiTietService;

    CommonController commonController;

    private boolean editMode;

    private boolean daPhatHet;

    @FXML
    Label nameDSPQ;

    @FXML
    Label ngayTaoDSPQ;

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
    TableColumn<FormDSPQChiTiet, String> ghiChuColumn;

    @FXML
    TableColumn<FormDSPQChiTiet, String> numberColumn;

    @FXML
    TableColumn<FormDSPQChiTiet, Void> buttonsColumn;

    @FXML
    TextField dipTextField;

    @FXML
    ComboBox<String> trangThaiComboBox;

    @FXML
    ObservableList<String> trangThaiComboBoxList;

    String trangThai;

    ObservableList<FormDSPQChiTiet> dspqChiTietObservableList;

    @FXML
    Button editOrSaveButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editMode = false;
        daPhatHet = false;
        commonController = new CommonController();
        dspqChiTietService = new DSPQChiTietService();
        trangThaiComboBoxList = FXCollections.observableArrayList("Đang phát", "Đã hoàn thành");
        trangThaiComboBox.setItems(trangThaiComboBoxList);
    }

    public void initData(DSPhatQua rowData){
        this.dsPhatQua = rowData;
        nameDSPQ.setText(dsPhatQua.getSuKien());
        trangThai = "";
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
        trangThaiComboBox.setValue(trangThai);
        trangThaiDSPQ.setText("Trạng thái: "+trangThai);
        ngayTaoDSPQ.setText("Ngày tạo: "+dsPhatQua.getNgayTao().toString());
        tongChiDSPQ.setText("Tổng chi: "+String.valueOf(dsPhatQua.getTongChiPhi()));
        dipTextField.setText(dsPhatQua.getSuKien());
        dipDSPQ.setText("Dịp: "+dsPhatQua.getSuKien());
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
        Callback<TableColumn<FormDSPQChiTiet, Void>, TableCell<FormDSPQChiTiet, Void>> cellFactory = new Callback<TableColumn<FormDSPQChiTiet, Void>, TableCell<FormDSPQChiTiet, Void>>() {
            @Override
            public TableCell<FormDSPQChiTiet, Void> call(final TableColumn<FormDSPQChiTiet, Void> param) {
                final TableCell<FormDSPQChiTiet, Void> cell = new TableCell<FormDSPQChiTiet, Void>() {
                    private final Button deleteButton = new Button("Xóa");
                    private final Button editButton = new Button("Chỉnh sửa");

                    {
                        deleteButton.setOnAction((ActionEvent event) -> {
                            FormDSPQChiTiet data = getTableView().getItems().get(getIndex());
                            dspqChiTietObservableList.remove(data);
                            table.setItems(dspqChiTietObservableList);
                        });
                        deleteButton.setMaxSize(200,200);
                    }

                    {
                        editButton.setOnAction((ActionEvent event) -> {

                        });
                        editButton.setMaxSize(200,200);
                    }

                    HBox buttonsBox = new HBox(editButton, deleteButton);
                    {
                        buttonsBox.setMaxSize(1000,400);
                    }


                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(buttonsBox);
                        }
                    }
                };
                return cell;
            }
        };

        buttonsColumn.setCellFactory(cellFactory);
        buttonsColumn.setSortable(false);
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
        checkDaPhatHet();
        table.setItems(dspqChiTietObservableList);
    }

    private void checkDaPhatHet(){
        boolean result = true;
        for(int i = 0; i < dspqChiTietObservableList.size(); i ++){
            if(!dspqChiTietObservableList.get(i).isDuocXacNhan()){
                result = false;
                break;
            }
        }
        daPhatHet = result;
    }

    public void editOrSaveClick(ActionEvent e){
        if(editMode == false){
            editOrSaveButton.setText("Lưu");
            editMode = true;
            trangThaiComboBox.setVisible(true);
            dipTextField.setVisible(true);
        }else{
            if(!daPhatHet){
                if(trangThaiComboBox.getValue().equalsIgnoreCase("Đã hoàn thành")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText("Đã có lỗi xảy ra");
                    alert.setContentText("Bạn chưa phát hết quà, không thể đổi trạng thái thành đã hoàn thành");
                    alert.show();
                }else{
                    editOrSaveButton.setText("Chỉnh sửa");
                    nameDSPQ.setText(dipTextField.getText());
                    dipDSPQ.setText("Dịp: "+dipTextField.getText());
                    dipTextField.setVisible(false);
                    trangThaiComboBox.setVisible(false);
                    editMode = false;
                }
            }else{
                editOrSaveButton.setText("Chỉnh sửa");
                nameDSPQ.setText(dipTextField.getText());
                dipDSPQ.setText("Dịp: "+dipTextField.getText());
                dipTextField.setVisible(false);
                trangThaiComboBox.setVisible(false);
                editMode = false;
            }
        }
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
}
