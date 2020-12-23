package app.controller.phatthuong.themmoi;

import app.controller.CommonController;
import app.model.DSPhatThuong;
import app.model.NhanKhau;
import app.model.form.FormDSPTChiTiet;
import app.model.form.FormDSPTChiTiet;
import app.model.form.FormDSPTChiTiet;
import app.model.form.FormDSPTChiTiet;
import app.service.DSPTChiTietService;
import app.view.button.DeleteButton;
import app.view.button.EditButton;
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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;

public class ThemMoiPTController implements Initializable {

    DSPTChiTietService dsptChiTietService;

    double tongChi;

    @FXML
    SplitPane R;

    CommonController commonController;

    @FXML
    TextField tenDSPTTextField;

    @FXML
    Label thoiGianTaoDanhSachLabel;

    Date thoiGianTaoDanhSach;

    @FXML
    Label thoiHanNopMinhChungLabel;

    Date thoiHanNopMinhChung;

    @FXML
    TableView<FormDSPTChiTiet> table;

    @FXML
    TableColumn<FormDSPTChiTiet, String> hoTenColumn;

    @FXML
    TableColumn<FormDSPTChiTiet, Integer> namSinhColumn;

    @FXML
    TableColumn<FormDSPTChiTiet, Integer> hoGDColumn;
    
    @FXML
    TableColumn<FormDSPTChiTiet, String> thanhTichColumn;

    @FXML
    TableColumn<FormDSPTChiTiet, String> minhChungColumn;

    @FXML
    TableColumn<FormDSPTChiTiet, String> xepLoaiColumn;

    @FXML
    TableColumn<FormDSPTChiTiet, Double> mucThuongColumn;

    @FXML
    TableColumn<FormDSPTChiTiet, String> ghiChuColumn;

    @FXML
    TableColumn<FormDSPTChiTiet, String> numberColumn;

    @FXML
    TableColumn<FormDSPTChiTiet, Void> buttonsColumn;

    ObservableList<FormDSPTChiTiet> dsptChiTietObservableList;

    @FXML
    Button saveButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dsptChiTietService = new DSPTChiTietService();
        commonController = new CommonController();
        commonController.csslize(R);

        thoiGianTaoDanhSach = Date.valueOf(LocalDate.now());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 15);
        thoiHanNopMinhChung = new Date(cal.getTimeInMillis());
        thoiGianTaoDanhSachLabel.setText(thoiGianTaoDanhSachLabel.getText()+" " +thoiGianTaoDanhSach);
        thoiHanNopMinhChungLabel.setText(thoiHanNopMinhChungLabel.getText()+" "+thoiHanNopMinhChung);
        saveButton.setDisable(true);
        tenDSPTTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            saveButton.setDisable(newValue.trim().isEmpty());
        });

        initTable();

    }

    private void initTable(){
        initColumns();
        loadData();
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
                FormDSPTChiTiet FormDSPTChiTiet = param.getValue();
                boolean duocXacNhan = FormDSPTChiTiet.isDuocXacNhan();
                if(duocXacNhan){
                    content = "Đã trao";
                }else{
                    content = "Chưa trao";
                }
                return new ReadOnlyObjectWrapper<>(content);
            }
        });
        Callback<TableColumn<FormDSPTChiTiet, Void>, TableCell<FormDSPTChiTiet, Void>> buttonsColumnCellFactory = new Callback<TableColumn<FormDSPTChiTiet, Void>, TableCell<FormDSPTChiTiet, Void>>() {
            @Override
            public TableCell<FormDSPTChiTiet, Void> call(final TableColumn<FormDSPTChiTiet, Void> param) {
                final TableCell<FormDSPTChiTiet, Void> cell = new TableCell<FormDSPTChiTiet, Void>() {
                    private final DeleteButton deleteButton = new DeleteButton();
                    private final EditButton editButton = new EditButton();

                    {
                        deleteButton.setOnAction((ActionEvent event) -> {
                            FormDSPTChiTiet data = getTableView().getItems().get(getIndex());
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
                                dsptChiTietObservableList.remove(data);
                            }
                        });
//                        deleteButton.setMaxSize(200,100);
//                        deleteButton.setPrefSize(200,20);
                    }

                    {
                        editButton.setOnAction((ActionEvent event) -> {
                            FormDSPTChiTiet data = getTableView().getItems().get(getIndex());
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

                            TextField thanhTichTextField = new TextField();
                            thanhTichTextField.setPromptText("Thành tích ");
                            thanhTichTextField.setText(data.getThanhTich());


                            ComboBox xepLoaiComboBox = new ComboBox();
                            ObservableList<String> xepLoaiObservableList = FXCollections.observableArrayList("A","B","C");
                            xepLoaiComboBox.setItems(xepLoaiObservableList);
                            if(data.getXepLoai() == null){
                                xepLoaiComboBox.setValue("Chọn xếp loại cho học sinh");
                            }else if(data.getXepLoai().trim().equalsIgnoreCase("a")){
                                xepLoaiComboBox.setValue("A");
                            }else if(data.getXepLoai().trim().equalsIgnoreCase("b")){
                                xepLoaiComboBox.setValue("B");
                            }else if(data.getXepLoai().trim().equalsIgnoreCase("c")){
                                xepLoaiComboBox.setValue("C");
                            }

                            TextField mucThuongTextField = new TextField();
                            mucThuongTextField.setPromptText("Mức  thưởng");
                            mucThuongTextField.setText(String.valueOf(data.getMucThuong()));
                            mucThuongTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                                try{
                                    if(!newValue.isEmpty()){
                                        Double.parseDouble(newValue);
                                    }
                                }catch (NumberFormatException ex){
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setContentText("Mức thưởng phải là số");
                                    alert.setTitle("LỖI");
                                    alert.setHeaderText("Đã có lỗi xảy ra!!!");
                                    alert.show();
                                    mucThuongTextField.setText("");
                                }
                            });


                            gridPane.add(new Label("Thành tích "), 0,0 );
                            gridPane.add(thanhTichTextField, 1, 0);
                            gridPane.add(new Label("Xếp loại "), 0, 1);
                            gridPane.add(xepLoaiComboBox, 1, 1);
                            gridPane.add(new Label("Mức thưởng "), 0, 2);
                            gridPane.add(mucThuongTextField, 1,2);
                            editDialog.getDialogPane().setContent(gridPane);
                            Optional<ButtonType> result = editDialog.showAndWait();
                            if(result.get() == xacNhanButtonType){
                                data.setThanhTich(thanhTichTextField.getText());
                                String xepLoai = xepLoaiComboBox.getValue().toString();
                                if(xepLoai.length() > 2){
                                    data.setXepLoai(null);
                                }else{
                                    data.setXepLoai(xepLoai);
                                }
                                if(mucThuongTextField.getText().isEmpty()){
                                    data.setMucThuong(0);
                                }else{
                                    data.setMucThuong(Double.parseDouble(mucThuongTextField.getText()));
                                }
                                dsptChiTietObservableList.set(getIndex(),data);
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
        thanhTichColumn.setCellValueFactory(new PropertyValueFactory<>("thanhTich"));
        minhChungColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FormDSPTChiTiet, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FormDSPTChiTiet, String> param) {
                String content = "";
                FormDSPTChiTiet formDSPTChiTiet = param.getValue();
                boolean minhChung = formDSPTChiTiet.isMinhChung();
                if(minhChung){
                    content = "Đã nộp";
                }else{
                    content = "Chưa nộp";
                }
                return new ReadOnlyObjectWrapper<>(content);
            }
        });
        xepLoaiColumn.setCellValueFactory(new PropertyValueFactory<>("xepLoai"));
        mucThuongColumn.setCellValueFactory(new PropertyValueFactory<>("mucThuong"));
    }

    private void loadData(){
        try {
            dsptChiTietObservableList = FXCollections.observableArrayList(dsptChiTietService.getAllHocSinh());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        table.setItems(dsptChiTietObservableList);
    }

    public void themMoi(ActionEvent e){
        Dialog<ButtonType> themMoiDiaglog = new Dialog<>();
        themMoiDiaglog.setTitle("Thêm mới");
        themMoiDiaglog.setHeaderText("Thêm bản ghi mới");

        ButtonType xacNhanButtonType = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
        ButtonType thoatButtonType = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
        themMoiDiaglog.getDialogPane().getButtonTypes().addAll(xacNhanButtonType, thoatButtonType);

        TextField idHS = new TextField();
        idHS.setPromptText("Mã học sinh");
        idHS.textProperty().addListener(((observable, oldValue, newValue) -> {
            try{
                if(!newValue.isEmpty()){
                    Integer.parseInt(newValue);
                }
            }catch (NumberFormatException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Mã học sinh không hợp lệ");
                alert.setTitle("LỖI");
                alert.setHeaderText("Đã có lỗi xảy ra!!!");
                alert.show();
                idHS.setText("");
            }
        }));
        TextField thanhTichTextField = new TextField();
        thanhTichTextField.setPromptText("Thành tích");


        ComboBox xepLoaiComboBox = new ComboBox();
        ObservableList<String> xepLoaiObservableList = FXCollections.observableArrayList("A","B","C");
        xepLoaiComboBox.setItems(xepLoaiObservableList);
        xepLoaiComboBox.setValue("Chọn xếp loại cho học sinh");


        TextField mucThuongTextField = new TextField();
        mucThuongTextField.setPromptText("Mức thưởng");
        mucThuongTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                if(!newValue.isEmpty()){
                    Double.parseDouble(newValue);
                }
            }catch (NumberFormatException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Mức thưởng phải là số");
                alert.setTitle("LỖI");
                alert.setHeaderText("Đã có lỗi xảy ra!!!");
                alert.show();
                mucThuongTextField.setText("");
            }
        });



        Button detailNhanKhauButton = new Button("Chi tiết");
        detailNhanKhauButton.setOnAction((ActionEvent event)->{
            try{
                int idHocSinh = Integer.parseInt(idHS.getText());
                boolean exist = checkExist(idHocSinh);
                NhanKhau nhanKhau = dsptChiTietService.getNhanKhauByMaHS(idHocSinh);
                String content = "Mã nhân khẩu: "+nhanKhau.getID()+"\n"+
                        "Họ tên: "+nhanKhau.getHoTen()+"\n"+
                        "Năm sinh: "+nhanKhau.getNamSinh()+"\n"+
                        "Giới tính: "+nhanKhau.getGioiTinh()+"\n";
                if(exist){
                    content += "Học sinh này đã tồn tại trong danh sách!!!\n";
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông tin");
                alert.setHeaderText("Thông tin nhân khẩu");
                alert.setContentText(content);
                alert.show();
            }catch (SQLException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Mã học sinh không hợp lệ");
                alert.setTitle("LỖI");
                alert.setHeaderText("Đã có lỗi xảy ra!!!");
                alert.show();
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20,150,10,10));

        gridPane.add(new Label("Mã học sinh "),0,0);
        gridPane.add(idHS, 1,0);
        gridPane.add(detailNhanKhauButton, 2,0);
        gridPane.add(new Label("Thành tích "),0,1);
        gridPane.add(thanhTichTextField,1,1);
        gridPane.add(new Label("Xếp loại "),0,2);
        gridPane.add(xepLoaiComboBox, 1,2);
        gridPane.add(new Label("Mức thưởng "),0,3);
        gridPane.add(mucThuongTextField, 1,3);


        themMoiDiaglog.getDialogPane().setContent(gridPane);
        Optional<ButtonType> result = themMoiDiaglog.showAndWait();
        if(result.get() == xacNhanButtonType){
            int idHocSinh = Integer.parseInt(idHS.getText());
            if(checkExist(idHocSinh)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText("Đã có lỗi xảy ra");
                alert.setContentText("Học sinh này đã tồn tại trong danh sách, không thể thêm mới");
                alert.show();
            }else{
                String thanhTich = thanhTichTextField.getText();
                String xepLoai = xepLoaiComboBox.getValue().toString();
                if(xepLoai.length() > 2){
                    xepLoai = null;
                }
                double mucThuong = 0;
                if(!mucThuongTextField.getText().isEmpty()){
                    mucThuong = Double.parseDouble(mucThuongTextField.getText());
                }
                try {
                    FormDSPTChiTiet formDSPTChiTiet = dsptChiTietService.addDuocNhanThuongForThemMoi(idHocSinh, thanhTich, xepLoai, mucThuong);
                    dsptChiTietObservableList.add(formDSPTChiTiet);
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

    private boolean checkExist(int idNhanKhau){
        for(int i = 0; i < dsptChiTietObservableList.size(); i++){
            if(idNhanKhau == dsptChiTietObservableList.get(i).getIdHocSinh()){
                return true;
            }
        }
        return false;
    }

    public void tinhTongChi(){
        tongChi = 0;
        for(int i = 0; i < dsptChiTietObservableList.size(); i++){
            tongChi += dsptChiTietObservableList.get(i).getMucThuong();
        }
    }

    public void save(){
        DSPhatThuong dsPhatThuong = new DSPhatThuong();
        dsPhatThuong.setSuKien(tenDSPTTextField.getText());
        dsPhatThuong.setTrangThai(0);
        dsPhatThuong.setNgayTao(thoiGianTaoDanhSach);
        tinhTongChi();
        dsPhatThuong.setTongChiPhi(tongChi);
        int soLuongPhatThuong = dsptChiTietObservableList.size();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thêm mới");
        alert.setHeaderText("Xác nhận thêm mới danh sách sau");
        String content = "Tên danh sách: "+dsPhatThuong.getSuKien()+"\n"+
                "Ngày tạo: "+dsPhatThuong.getNgayTao()+"\n"+
                "Tổng chi: "+dsPhatThuong.getTongChiPhi()+"\n"+
                "Số học sinh được nhận thưởng: "+soLuongPhatThuong+"\n";
        alert.setContentText(content);
        ButtonType xacNhanButtonType = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
        ButtonType thoatButtonType = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(xacNhanButtonType, thoatButtonType);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == xacNhanButtonType){
            try {
                dsptChiTietService.addDSPT(dsPhatThuong, dsptChiTietObservableList);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Thông báo");
                alert1.setHeaderText("Thêm mới danh sách thành công");
                alert1.setContentText(content);
                alert1.show();
                toPhatThuong();
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
