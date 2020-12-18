package app.view.phatqua.dspqchitiet.edit_dspqchitiet;

import app.model.DSPhatQua;
import app.model.form.FormDSPQChiTiet;
import app.service.DSPQChiTietService;
import app.service.DSPhatQuaService;
import app.view.CommonController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditDanhSachChiTietController implements Initializable {

    private DSPhatQua dsPhatQua;

    DSPQChiTietService dspqChiTietService;

    DSPhatQuaService dsPhatQuaService;

    CommonController commonController;

    private boolean editMode;

    private boolean daPhatHet;

    double tongChi;

    String dip;

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

    ObservableList<FormDSPQChiTiet> dspqChiTietObservableList;

    @FXML
    Button editOrSaveButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editMode = false;
        daPhatHet = false;
        commonController = new CommonController();
        dspqChiTietService = new DSPQChiTietService();
        dsPhatQuaService = new DSPhatQuaService();
        trangThaiComboBoxList = FXCollections.observableArrayList("Đang phát", "Đã hoàn thành");
        trangThaiComboBox.setItems(trangThaiComboBoxList);
    }

    public void initData(DSPhatQua rowData){
        this.dsPhatQua = rowData;
        tongChi = dsPhatQua.getTongChiPhi();
        dip = dsPhatQua.getSuKien();
        nameDSPQ.setText(dip);
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
        trangThaiComboBox.setValue(trangThai);
        trangThaiDSPQ.setText("Trạng thái: "+trangThai);
        ngayTaoDSPQ.setText("Ngày tạo: "+dsPhatQua.getNgayTao().toString());
        tongChiDSPQ.setText("Tổng chi: "+tongChi);
        dipTextField.setText(dip);
        dipDSPQ.setText("Dịp: "+dip);
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
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Xóa bản ghi");
                            alert.setHeaderText("Xác nhận xóa");
                            alert.setContentText("Bạn có chắc xóa "+data.getHoTen()+" khỏi danh sách không?");
                            ButtonType xacNhanButtonType = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
                            ButtonType thoatButtonType = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
                            alert.getButtonTypes().clear();
                            alert.getButtonTypes().addAll(xacNhanButtonType, thoatButtonType);
                            Optional<ButtonType> result = alert.showAndWait();
                            if(result.get() == xacNhanButtonType) {
                                try {
                                    dspqChiTietService.deleteDuocNhanQua(data);
                                    dspqChiTietObservableList.remove(data);
                                    table.setItems(dspqChiTietObservableList);
                                    updateTongChi();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                    alert1.setTitle("Lỗi");
                                    alert1.setHeaderText("Có lỗi xảy ra khi xóa bản ghi");
                                    alert1.setContentText("Không thể xóa bản ghi");
                                    alert1.show();
                                }
                            }
                        });
                        deleteButton.setMaxSize(200,100);
                        deleteButton.setPrefSize(200,20);
                    }

                    {
                        editButton.setOnAction((ActionEvent event) -> {
                            FormDSPQChiTiet data = getTableView().getItems().get(getIndex());
                            Dialog<ButtonType> editDialog = new Dialog<>();
                            editDialog.setTitle("Sửa bản ghi");
                            editDialog.setHeaderText("Sửa thông tin của "+data.getHoTen());

                            ButtonType xacNhanButtonType = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
                            ButtonType thoatButtonType = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
                            editDialog.getDialogPane().getButtonTypes().addAll(xacNhanButtonType, thoatButtonType);

                            GridPane gridPane = new GridPane();
                            gridPane.setHgap(10);
                            gridPane.setVgap(10);
                            gridPane.setPadding(new Insets(20,150,10,10));

                            TextField phanQuaTextField = new TextField();
                            phanQuaTextField.setPromptText("Phần quà");
                            phanQuaTextField.setText(data.getPhanQua());

                            TextField mucQuaTextField = new TextField();
                            mucQuaTextField.setPromptText("Mức quà");
                            mucQuaTextField.setText(String.valueOf(data.getMucQua()));
                            mucQuaTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                                try{
                                    if(!newValue.isEmpty()){
                                        Double.parseDouble(newValue);
                                    }
                                }catch (NumberFormatException ex){
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setContentText("Mức quà phải là số");
                                    alert.setTitle("LỖI");
                                    alert.setHeaderText("Đã có lỗi xảy ra!!!");
                                    alert.show();
                                    mucQuaTextField.setText("");
                                }
                            });

                            ComboBox ghiChuComboBox = new ComboBox();
                            ObservableList<String> ghiChuComboBoxList = FXCollections.observableArrayList("Đã trao", "Chưa trao");
                            ghiChuComboBox.setItems(ghiChuComboBoxList);
                            if(data.isDuocXacNhan()){
                                ghiChuComboBox.setValue("Đã trao");
                            }else{
                                ghiChuComboBox.setValue("Chưa trao");
                            }

                            gridPane.add(new Label("Phần quà "),0,0);
                            gridPane.add(phanQuaTextField, 1,0);
                            gridPane.add(new Label("Mức quà "),0,1);
                            gridPane.add(mucQuaTextField, 1,1);
                            gridPane.add(new Label("Ghi chú "), 0, 2);
                            gridPane.add(ghiChuComboBox, 1,2);

                            editDialog.getDialogPane().setContent(gridPane);
                            Optional<ButtonType> result = editDialog.showAndWait();
                            if(result.get() == xacNhanButtonType){
                                data.setPhanQua(phanQuaTextField.getText());
                                if(mucQuaTextField.getText().isEmpty()){
                                    data.setMucQua(0);
                                }else{
                                    data.setMucQua(Double.parseDouble(mucQuaTextField.getText()));
                                }
                                if(ghiChuComboBox.getValue().toString().equalsIgnoreCase("Đã trao")){
                                    data.setDuocXacNhan(true);
                                }else{
                                    data.setDuocXacNhan(false);
                                }
                                try {
                                    dspqChiTietService.updateDuocNhanQua(data);
                                    dspqChiTietObservableList.set(getIndex(),data);
                                    updateTongChi();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                    alert1.setTitle("Lỗi");
                                    alert1.setHeaderText("Có lỗi xảy ra khi sửa bản ghi");
                                    alert1.setContentText("Không thể sửa bản ghi");
                                    alert1.show();
                                }

                            }
                        });
                        editButton.setMaxSize(200,100);
                        editButton.setPrefSize(200,20);
                    }

                    HBox buttonsBox = new HBox(editButton, deleteButton);
                    {
                        buttonsBox.setMaxSize(1000,100);
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
        table.setItems(dspqChiTietObservableList);
    }

    private void tinhTongChi(){
        tongChi = 0;
        for(int i = 0; i < dspqChiTietObservableList.size(); i++){
            tongChi += dspqChiTietObservableList.get(i).getMucQua();
        }
    }

    private void updateTongChi(){
        tinhTongChi();
        tongChiDSPQ.setText("Tổng chi: "+String.valueOf(tongChi));
        updateDSPQ();
    }

    private void updateDSPQ(){
        dsPhatQua.setTongChiPhi(tongChi);
        dsPhatQua.setSuKien(dip);
        int trangThai;
        if(trangThaiComboBox.getValue().equalsIgnoreCase("Đã hoàn thành")){
            trangThai = 2;
        }else{
            trangThai = 1;
        }
        dsPhatQua.setTrangThai(trangThai);
        try {
            dsPhatQuaService.update(dsPhatQua);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
        checkDaPhatHet();
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
                    dip = dipTextField.getText();
                    nameDSPQ.setText(dip);
                    dipDSPQ.setText("Dịp: "+dip);
                    dipTextField.setVisible(false);
                    trangThaiComboBox.setVisible(false);
                    editMode = false;
                    updateDSPQ();
                }
            }else{
                editOrSaveButton.setText("Chỉnh sửa");
                dip = dipTextField.getText();
                nameDSPQ.setText(dip);
                dipDSPQ.setText("Dịp: "+dip);
                dipTextField.setVisible(false);
                trangThaiComboBox.setVisible(false);
                editMode = false;
                updateDSPQ();
            }
        }
    }

    public void themMoi(ActionEvent e){
        Dialog<ButtonType> themMoiDiaglog = new Dialog<>();
        themMoiDiaglog.setTitle("Thêm mới");
        themMoiDiaglog.setHeaderText("Thêm bản ghi mới");

        ButtonType xacNhanButtonType = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
        ButtonType thoatButtonType = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
        themMoiDiaglog.getDialogPane().getButtonTypes().addAll(xacNhanButtonType, thoatButtonType);
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
