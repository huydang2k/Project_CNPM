package app.controller.phatthuong.dsptchitiet.edit_dsptchitiet;

import app.controller.CommonController;
import app.model.DSPhatThuong;
import app.model.NhanKhau;
import app.model.form.FormDSPTChiTiet;
import app.service.DSPTChiTietService;
import app.service.DSPhatThuongService;
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

public class EditDanhSachPTChiTietController implements Initializable {
    CommonController commonController;
    DSPTChiTietService dsptChiTietService;
    DSPhatThuongService dsPhatThuongService;
    private DSPhatThuong dsPhatThuong;
    ObservableList<FormDSPTChiTiet> formDSPTChiTietObservableList;

    private boolean editMode;

    private boolean daPhatHet;

    double tongChi;

    private String dip;
    @FXML
    SplitPane R;
    @FXML
    TextField dipTextField;

    @FXML
    ComboBox<String> trangThaiComboBox;

    @FXML
    ObservableList<String> trangThaiComboBoxList;

    @FXML
    Label nameDSPT;

    @FXML
    Label ngayTaoDSPT;

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
    TableColumn<FormDSPTChiTiet,Integer> namSinhColumn;
    @FXML
    TableColumn<FormDSPTChiTiet,Integer> hoGDColumn;
    @FXML
    TableColumn<FormDSPTChiTiet,String> thanhTichColumn;
    @FXML
    TableColumn<FormDSPTChiTiet,String> minhChungColumn;
    @FXML
    TableColumn<FormDSPTChiTiet,String> xepLoaiColumn;
    @FXML
    TableColumn<FormDSPTChiTiet,Double> mucThuongColumn;
    @FXML
    TableColumn<FormDSPTChiTiet,String> ghiChuColumn;
    @FXML
    Button editOrSaveButton;
    @FXML
    TableColumn<FormDSPTChiTiet, Void> buttonsColumn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
        commonController.csslize(R);
        dsptChiTietService = new DSPTChiTietService();
        trangThaiComboBoxList = FXCollections.observableArrayList("Đợi nộp minh chứng","Đang phát", "Đã hoàn thành");
        trangThaiComboBox.setItems(trangThaiComboBoxList);
        editMode = false;
        daPhatHet = false;
        dsPhatThuongService = new DSPhatThuongService();
    }

    public void initData(DSPhatThuong rowData){
        this.dsPhatThuong = rowData;
        tongChi = dsPhatThuong.getTongChiPhi();
        dip = dsPhatThuong.getSuKien();
        nameDSPT.setText(dip);
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
        trangThaiComboBox.setValue(trangThai);
        trangThaiDSPT.setText("Trạng thái: "+trangThai);
        ngayTaoDSPT.setText("Ngày tạo: "+dsPhatThuong.getNgayTao().toString());
        tongChiDSPT.setText("Tổng chi: "+tongChi);
        dipTextField.setText(dip);
        dipDSPT.setText("Dịp: "+dip);
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
        minhChungColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FormDSPTChiTiet, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FormDSPTChiTiet, String> param) {
                String content = "";
                FormDSPTChiTiet formDSPTChiTiet = param.getValue();
                boolean daNop = formDSPTChiTiet.isMinhChung();
                if(daNop){
                    content = "Đã nộp";
                }else{
                    content = "Chưa nộp";
                }
                return new ReadOnlyObjectWrapper<>(content);
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

        Callback<TableColumn<FormDSPTChiTiet, Void>, TableCell<FormDSPTChiTiet, Void>> cellFactory = new Callback<TableColumn<FormDSPTChiTiet, Void>, TableCell<FormDSPTChiTiet, Void>>() {
            @Override
            public TableCell<FormDSPTChiTiet, Void> call(final TableColumn<FormDSPTChiTiet, Void> param) {
                final TableCell<FormDSPTChiTiet, Void> cell = new TableCell<FormDSPTChiTiet, Void>() {
                    private final Button deleteButton = new Button("Xóa");
                    private final Button editButton = new Button("Chỉnh sửa");

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
                                try {
                                    dsptChiTietService.deleteDuocNhanThuong(data);
                                    formDSPTChiTietObservableList.remove(data);
                                    table.setItems(formDSPTChiTietObservableList);
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
                            FormDSPTChiTiet data = getTableView().getItems().get(getIndex());
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

                            TextField thanhTichTextFiled = new TextField();
                            thanhTichTextFiled.setPromptText("Thành tích");
                            thanhTichTextFiled.setText(data.getThanhTich());


                            ComboBox ghiChuComboBox = new ComboBox();
                            ObservableList<String> ghiChuComboBoxList = FXCollections.observableArrayList("Đã trao", "Chưa trao");
                            ghiChuComboBox.setItems(ghiChuComboBoxList);
                            if(data.isDuocXacNhan()){
                                ghiChuComboBox.setValue("Đã trao");
                            }else{
                                ghiChuComboBox.setValue("Chưa trao");
                            }

                            ComboBox minhChungCmbBox = new ComboBox();
                            ObservableList<String> minhCHungCmbList = FXCollections.observableArrayList("Đã nộp", "Chưa nộp");
                            minhChungCmbBox.setItems(minhCHungCmbList);
                            if(data.isMinhChung()){
                                minhChungCmbBox.setValue("Đã nộp");
                            }else{
                                minhChungCmbBox.setValue("Chưa nộp");
                            }

                            ComboBox xepLoaiCmbBox = new ComboBox();
                            ObservableList<String> xepLoaiCmbList = FXCollections.observableArrayList("A", "B","C");
                            xepLoaiCmbBox.setItems(xepLoaiCmbList);
                            xepLoaiCmbBox.setValue(data.getXepLoai());

                            TextField mucThuongTextField = new TextField();
                            mucThuongTextField.setPromptText("Mức thưởng");
                            mucThuongTextField.setText(String.valueOf(data.getMucThuong()));
                            mucThuongTextField.textProperty().addListener((observable, oldValue, newValue) -> {
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
                                    mucThuongTextField.setText("");
                                }
                            });

                            gridPane.add(new Label("Thành  tích "),0,0);
                            gridPane.add(thanhTichTextFiled, 1,0);
                            gridPane.add(new Label("Minh chứng "),0,1);
                            gridPane.add(minhChungCmbBox, 1,1);
                            gridPane.add(new Label("Xếp loại "), 0, 2);
                            gridPane.add(xepLoaiCmbBox, 1,2);
                            gridPane.add(new Label("Mức thưởng "), 0, 3);
                            gridPane.add(mucThuongTextField, 1,3);
                            gridPane.add(new Label("Ghi chú "), 0, 4);
                            gridPane.add(ghiChuComboBox, 1,4);

                            editDialog.getDialogPane().setContent(gridPane);
                            Optional<ButtonType> result = editDialog.showAndWait();
                            if(result.get() == xacNhanButtonType){
                                data.setThanhTich(thanhTichTextFiled.getText());
                                data.setXepLoai(xepLoaiCmbBox.getValue().toString());
                                if(mucThuongTextField.getText().isEmpty()){
                                    data.setMucThuong(0);
                                }else{
                                    data.setMucThuong(Double.parseDouble(mucThuongTextField.getText()));
                                }
                                if(ghiChuComboBox.getValue().toString().equalsIgnoreCase("Đã trao")){
                                    data.setDuocXacNhan(true);
                                }else{
                                    data.setDuocXacNhan(false);
                                }
                                if(minhChungCmbBox.getValue().toString().equalsIgnoreCase("Đã nộp")){
                                    data.setMinhChung(true);
                                }else{
                                    data.setMinhChung(false);
                                }
                                try {

                                    dsptChiTietService.updateDuocNhanThuong(data);
                                    formDSPTChiTietObservableList.set(getIndex(),data);
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
        thanhTichColumn.setCellValueFactory(new PropertyValueFactory<>("thanhTich"));

        xepLoaiColumn.setCellValueFactory(new PropertyValueFactory<>("xepLoai"));
        mucThuongColumn.setCellValueFactory(new PropertyValueFactory<>("mucThuong"));
    }

    private void loadData(){
        if(dsPhatThuong.getTrangThai() == 1) {
            try {
            formDSPTChiTietObservableList = FXCollections.observableArrayList(dsptChiTietService.getFormPTChiTietDangPhat(dsPhatThuong.getMaDS()));
            } catch (SQLException throwables) {
            throwables.printStackTrace();
             }
        table.setItems(formDSPTChiTietObservableList);}
        else {
            try {
                formDSPTChiTietObservableList = FXCollections.observableArrayList(dsptChiTietService.getFormDSPTChiTietByMaDS(dsPhatThuong.getMaDS()));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            table.setItems(formDSPTChiTietObservableList);}

    }



    private void tinhTongChi(){
        tongChi = 0;
        for(int i = 0; i < formDSPTChiTietObservableList.size(); i++){
            tongChi += formDSPTChiTietObservableList.get(i).getMucThuong();
        }
    }

    private void updateTongChi(){
        tinhTongChi();
        tongChiDSPT.setText("Tổng chi: "+String.valueOf(tongChi));
        updateDSPT();
    }

    private void updateDSPT(){
        dsPhatThuong.setTongChiPhi(tongChi);
        dsPhatThuong.setSuKien(dip);
        int trangThai;
        if(trangThaiComboBox.getValue().equalsIgnoreCase("Đã hoàn thành")){
            trangThai = 2;
        }else if (trangThaiComboBox.getValue().equalsIgnoreCase("Đang phát")){
            trangThai = 1;
        } else {
            trangThai = 0;
        }
        dsPhatThuong.setTrangThai(trangThai);
        try {
            dsPhatThuongService.update(dsPhatThuong);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void checkDaPhatHet(){
        boolean result = true;
        for(int i = 0; i < formDSPTChiTietObservableList.size(); i ++){
            if(!formDSPTChiTietObservableList.get(i).isDuocXacNhan()){
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
        }else {
            if (trangThaiComboBox.getValue().equalsIgnoreCase("Đã hoàn thành")) {
                if (!daPhatHet) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText("Đã có lỗi xảy ra");
                    alert.setContentText("Bạn chưa phát hết quà, không thể đổi trạng thái thành đã hoàn thành");
                    alert.show();
                    return;
                } //return
            } else if (trangThaiComboBox.getValue().equalsIgnoreCase("Đang phát")) {
                try {
                    formDSPTChiTietObservableList = FXCollections.observableArrayList(dsptChiTietService.getFormPTChiTietDangPhat(dsPhatThuong.getMaDS()));
                    table.setItems(formDSPTChiTietObservableList);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else{
                try {
                    formDSPTChiTietObservableList = FXCollections.observableArrayList(dsptChiTietService.getFormDSPTChiTietByMaDS(dsPhatThuong.getMaDS()));
                    table.setItems(formDSPTChiTietObservableList);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            editOrSaveButton.setText("Chỉnh sửa");
            dip = dipTextField.getText();
            nameDSPT.setText(dip);
            dipDSPT.setText("Dịp: "+dip);
            dipTextField.setVisible(false);

            trangThaiComboBox.setVisible(false);
            editMode = false;
            updateDSPT();
            trangThaiDSPT.setText("Trạng thái: "+ trangThaiComboBox.getValue());
        }
    }

    private boolean checkExist(int idNhanKhau){
        for(int i = 0; i < formDSPTChiTietObservableList.size(); i++){
            if(idNhanKhau == formDSPTChiTietObservableList.get(i).getIdNhanKhau()){
                return true;
            }
        }
        return false;
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

        ComboBox minhChungCmbBox = new ComboBox();
        ObservableList<String> minhChungCmbList = FXCollections.observableArrayList("Đã nộp", "Chưa nộp");
        minhChungCmbBox.setItems(minhChungCmbList);
        minhChungCmbBox.setValue("Chưa nộp");

        ComboBox xepLoaiCmbBox = new ComboBox();
        ObservableList<String> xepLoaiCmbList = FXCollections.observableArrayList("A", "B","C");
        xepLoaiCmbBox.setItems(xepLoaiCmbList);
        xepLoaiCmbBox.setValue("A");

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
        gridPane.add(new Label("Minh chứng "),0,2);
        gridPane.add(minhChungCmbBox, 1,2);
        gridPane.add(new Label("Xếp loại "),0,3);
        gridPane.add(xepLoaiCmbBox, 1,3);
        gridPane.add(new Label("Mức thưởng "),0,4);
        gridPane.add(mucThuongTextField, 1,4);


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
                boolean minhChung = minhChungCmbBox.getValue().toString().equalsIgnoreCase("Đã nộp");
                String xepLoai = xepLoaiCmbBox.getValue().toString();
                double mucThuong = 0;
                if(!mucThuongTextField.getText().isEmpty()){
                    mucThuong = Double.parseDouble(mucThuongTextField.getText());
                }
                try{
                    int maDS = dsPhatThuong.getMaDS();
                    FormDSPTChiTiet formDSPTChiTiet = dsptChiTietService.addDuocNhanThuong(maDS,idHocSinh,thanhTich,minhChung,xepLoai,mucThuong);
                    formDSPTChiTietObservableList.add(formDSPTChiTiet);
                    updateTongChi();
                }catch (SQLException ex){
                    ex.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText("Đã có lỗi xảy ra");
                    alert.setContentText("Có lỗi khi thêm mới");
                    alert.show();
                }
            }
        }
    }

    public void toThemMoiDsPT(){
        commonController.toThemMoiDsPhatThuong();
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
