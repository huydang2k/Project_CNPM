package app.controller.phatqua.themmoi;

import app.controller.CommonController;
import app.model.DSPhatQua;
import app.model.NhanKhau;
import app.model.form.FormDSPQChiTiet;
import app.service.DSPQChiTietService;
import app.service.DSPhatQuaService;
import app.view.button.DeleteButton;
import app.view.button.EditButton;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.awt.*;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ThemMoiPQController implements Initializable {

    CommonController commonController;

    DSPQChiTietService dspqChiTietService;

    DSPhatQuaService dsPhatQuaService;

    double tongChi;
    @FXML
    SplitPane R;
    @FXML
    TextField tenDSPQTextField;

    @FXML
    Label thoiGianTaoDanhSachLabel;

    Date thoiGianTaoDanhSach;

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

    ObservableList<FormDSPQChiTiet> dspqChiTietObservableList;

    @FXML
    Button saveButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
        commonController.csslize(R);
        dsPhatQuaService = new DSPhatQuaService();
        dspqChiTietService = new DSPQChiTietService();

        thoiGianTaoDanhSach = Date.valueOf(LocalDate.now());
        thoiGianTaoDanhSachLabel.setText(thoiGianTaoDanhSachLabel.getText()+" " +thoiGianTaoDanhSach);
        saveButton.setDisable(true);
        tenDSPQTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            saveButton.setDisable(newValue.trim().isEmpty());
        });
        initTable();

    }

    private void initTable(){
        initColumns();
        loadData();
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
        Callback<TableColumn<FormDSPQChiTiet, Void>, TableCell<FormDSPQChiTiet, Void>> buttonsColumnCellFactory = new Callback<TableColumn<FormDSPQChiTiet, Void>, TableCell<FormDSPQChiTiet, Void>>() {
            @Override
            public TableCell<FormDSPQChiTiet, Void> call(final TableColumn<FormDSPQChiTiet, Void> param) {
                final TableCell<FormDSPQChiTiet, Void> cell = new TableCell<FormDSPQChiTiet, Void>() {
                    private final DeleteButton deleteButton = new DeleteButton();
                    private final EditButton editButton = new EditButton();

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
                                dspqChiTietObservableList.remove(data);
                            }
                        });
//                        deleteButton.setMaxSize(200,100);
//                        deleteButton.setPrefSize(200,20);
                    }

                    {
                        editButton.setOnAction((ActionEvent event) -> {
                            FormDSPQChiTiet data = getTableView().getItems().get(getIndex());
                            javafx.scene.control.Dialog<ButtonType> editDialog = new Dialog<>();
                            editDialog.setTitle("Sửa bản ghi");
                            editDialog.setHeaderText("Sửa thông tin của "+data.getHoTen());

                            ButtonType xacNhanButtonType = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
                            ButtonType thoatButtonType = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
                            editDialog.getDialogPane().getButtonTypes().addAll(xacNhanButtonType, thoatButtonType);

                            GridPane gridPane = new GridPane();
                            gridPane.setHgap(10);
                            gridPane.setVgap(10);
                            gridPane.setPadding(new Insets(20,150,10,10));

                            javafx.scene.control.TextField phanQuaTextField = new javafx.scene.control.TextField();
                            phanQuaTextField.setPromptText("Phần quà");
                            phanQuaTextField.setText(data.getPhanQua());

                            javafx.scene.control.TextField mucQuaTextField = new javafx.scene.control.TextField();
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

                            gridPane.add(new javafx.scene.control.Label("Phần quà "),0,0);
                            gridPane.add(phanQuaTextField, 1,0);
                            gridPane.add(new javafx.scene.control.Label("Mức quà "),0,1);
                            gridPane.add(mucQuaTextField, 1,1);
//                            gridPane.add(new Label("Ghi chú "), 0, 2);
//                            gridPane.add(ghiChuComboBox, 1,2);

                            editDialog.getDialogPane().setContent(gridPane);
                            Optional<ButtonType> result = editDialog.showAndWait();
                            if(result.get() == xacNhanButtonType){
                                data.setPhanQua(phanQuaTextField.getText());
                                if(mucQuaTextField.getText().isEmpty()){
                                    data.setMucQua(0);
                                }else{
                                    data.setMucQua(Double.parseDouble(mucQuaTextField.getText()));
                                }
//                                if(ghiChuComboBox.getValue().toString().equalsIgnoreCase("Đã trao")){
//                                    data.setDuocXacNhan(true);
//                                }else{
//                                    data.setDuocXacNhan(false);
//                                }
                                dspqChiTietObservableList.set(getIndex(),data);

                            }
                        });
//                        editButton.setMaxSize(200,100);
//                        editButton.setPrefSize(200,20);
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

        buttonsColumn.setCellFactory(buttonsColumnCellFactory);
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
            dspqChiTietObservableList = FXCollections.observableArrayList(dspqChiTietService.getAllThieuNhi());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        table.setItems(dspqChiTietObservableList);
    }

    public void themMoi(ActionEvent e){
        Dialog<ButtonType> themMoiDiaglog = new Dialog<>();
        themMoiDiaglog.setTitle("Thêm mới");
        themMoiDiaglog.setHeaderText("Thêm bản ghi mới");

        ButtonType xacNhanButtonType = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
        ButtonType thoatButtonType = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
        themMoiDiaglog.getDialogPane().getButtonTypes().addAll(xacNhanButtonType, thoatButtonType);

        TextField idNhanKhauTextField = new TextField();
        idNhanKhauTextField.setPromptText("Mã nhân khẩu");
        idNhanKhauTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            try{
                if(!newValue.isEmpty()){
                    Integer.parseInt(newValue);
                }
            }catch (NumberFormatException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Mã nhân khẩu không hợp lệ");
                alert.setTitle("LỖI");
                alert.setHeaderText("Đã có lỗi xảy ra!!!");
                alert.show();
                idNhanKhauTextField.setText("");
            }
        }));

        TextField phanQuaTextField = new TextField();
        phanQuaTextField.setPromptText("Phần quà");

        TextField mucQuaTextField = new TextField();
        mucQuaTextField.setPromptText("Mức quà");
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

        Button detailNhanKhauButton = new Button("Chi tiết");
        detailNhanKhauButton.setOnAction((ActionEvent event)->{
            try{
                int idNhanKhau = Integer.parseInt(idNhanKhauTextField.getText());
                boolean exist = checkExist(idNhanKhau);
                NhanKhau nhanKhau = dspqChiTietService.getNhanKhau(idNhanKhau);
                String content = "Mã nhân khẩu: "+idNhanKhau+"\n"+
                        "Họ tên: "+nhanKhau.getHoTen()+"\n"+
                        "Năm sinh: "+nhanKhau.getNamSinh()+"\n"+
                        "Giới tính: "+nhanKhau.getGioiTinh()+"\n";
                if(exist){
                    content += "Nhân khẩu này đã tồn tại trong danh sách!!!\n";
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông tin");
                alert.setHeaderText("Thông tin nhân khẩu");
                alert.setContentText(content);
                alert.show();
            }catch (SQLException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Mã nhân khẩu không hợp lệ");
                alert.setTitle("LỖI");
                alert.setHeaderText("Đã có lỗi xảy ra!!!");
                alert.show();
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20,150,10,10));

        gridPane.add(new Label("Mã nhân khẩu "),0,0);
        gridPane.add(idNhanKhauTextField, 1,0);
        gridPane.add(detailNhanKhauButton, 2,0);
        gridPane.add(new Label("Phần quà "),0,1);
        gridPane.add(phanQuaTextField,1,1);
        gridPane.add(new Label("Mức quà "),0,2);
        gridPane.add(mucQuaTextField, 1,2);

        themMoiDiaglog.getDialogPane().setContent(gridPane);
        Optional<ButtonType> result = themMoiDiaglog.showAndWait();
        if(result.get() == xacNhanButtonType){
            int idNhanKhau = Integer.parseInt(idNhanKhauTextField.getText());
            if(checkExist(idNhanKhau)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText("Đã có lỗi xảy ra");
                alert.setContentText("Nhân khẩu này đã tồn tại trong danh sách, không thể thêm mới");
                alert.show();
            }else{
                String phanQua = phanQuaTextField.getText();
                double mucQua = 0;
                if(!mucQuaTextField.getText().isEmpty()){
                    mucQua = Double.parseDouble(mucQuaTextField.getText());
                }
                FormDSPQChiTiet formDSPQChiTiet = null;
                try {
                    formDSPQChiTiet = dspqChiTietService.addDuocNhanQuaForThemMoi(idNhanKhau, phanQua, mucQua);
                    dspqChiTietObservableList.add(formDSPQChiTiet);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText("Đã có lỗi xảy ra");
                    alert.setContentText("Có lỗi khi thêm mới");
                    alert.show();
                }
            }
        }
    }

    private void tinhTongChi(){
        tongChi = 0;
        for(int i = 0; i < dspqChiTietObservableList.size(); i++){
            tongChi += dspqChiTietObservableList.get(i).getMucQua();
        }
    }

    public void save(){
        DSPhatQua dsPhatQua = new DSPhatQua();
        dsPhatQua.setSuKien(tenDSPQTextField.getText());
        dsPhatQua.setTrangThai(1);
        tinhTongChi();
        dsPhatQua.setTongChiPhi(tongChi);
        dsPhatQua.setNgayTao(thoiGianTaoDanhSach);
        int soLuongPhatQua = dspqChiTietObservableList.size();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thêm mới");
        alert.setHeaderText("Xác nhận thêm mới danh sách sau");
        String content = "Tên danh sách: "+dsPhatQua.getSuKien()+"\n"+
                "Ngày tạo: "+dsPhatQua.getNgayTao()+"\n"+
                "Tổng chi: "+dsPhatQua.getTongChiPhi()+"\n"+
                "Số người được nhận quà: "+soLuongPhatQua+"\n";
        alert.setContentText(content);
        ButtonType xacNhanButtonType = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
        ButtonType thoatButtonType = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(xacNhanButtonType, thoatButtonType);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == xacNhanButtonType){
            try {
                dspqChiTietService.addDSPQ(dsPhatQua, dspqChiTietObservableList);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Thông báo");
                alert1.setHeaderText("Thêm mới danh sách thành công");
                alert1.setContentText(content);
                alert1.show();
                toPhatQua();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Lỗi");
                alert1.setHeaderText("Đã có lỗi xảy ra");
                alert1.setContentText("Có lỗi khi thêm mới danh sách");
                alert1.show();
            }
        }
    }

    private boolean checkExist(int idNhanKhau){
        for(int i = 0; i < dspqChiTietObservableList.size(); i++){
            if(idNhanKhau == dspqChiTietObservableList.get(i).getIdNhanKhau()){
                return true;
            }
        }
        return false;
    }


    public void toPhatQua(){
        commonController.toPhatQua();
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
