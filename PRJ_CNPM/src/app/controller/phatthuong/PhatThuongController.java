package app.controller.phatthuong;

import app.model.DSPhatThuong;
import app.service.DSPhatThuongService;
import app.controller.CommonController;
import app.controller.phatthuong.dsptchitiet.edit_dsptchitiet.EditDanhSachPTChiTietController;
import app.controller.phatthuong.dsptchitiet.view_dsptchitiet.ViewDanhSachPTChiTietController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class PhatThuongController implements Initializable {
    DSPhatThuongService dsPhatThuongService;
    CommonController commonController;
    ObservableList<DSPhatThuong> dsPhatThuongObservableList;

    @FXML
    TableView<DSPhatThuong> table;
    @FXML
    Button themMoiBtn;
    @FXML
    TableColumn<DSPhatThuong, String> dsPhatThuongColumn;

    @FXML
    TableColumn<DSPhatThuong, String> numberColumn;

    @FXML
    TableColumn<DSPhatThuong, Void> buttonsColumn;

    private void initTable(){
        initColumns();
        table.setRowFactory( RowFactory -> {
            TableRow<DSPhatThuong> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    DSPhatThuong rowData = row.getItem();
                    System.out.println(rowData);
                    Stage stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
                    FXMLLoader loader = new FXMLLoader();
                    if(rowData.getTrangThai() == 2){
                        loader.setLocation(getClass().getResource("..\\..\\view\\phatthuong\\dsptchitiet\\view_dsptchitiet\\View_DanhSachChiTiet.fxml"));
                        try {
                            Parent root = loader.load();
                            ViewDanhSachPTChiTietController controller = loader.getController();
                            controller.initData(rowData);
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }else{
                        loader.setLocation(getClass().getResource("..\\..\\view\\phatthuong\\dsptchitiet\\edit_dsptchitiet\\Edit_DanhSachChiTiet.fxml"));
                        try {
                            Parent root = loader.load();
                            EditDanhSachPTChiTietController controller = loader.getController();
                            controller.initData(rowData);
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            });
            return row ;
        });
    }

    private void initColumns(){
        numberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DSPhatThuong, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DSPhatThuong, String> param) {
                return new ReadOnlyObjectWrapper<>(table.getItems().indexOf(param.getValue()) + 1 + " ");
            }
        });
        dsPhatThuongColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DSPhatThuong, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DSPhatThuong, String> param) {
                DSPhatThuong dsPhatThuong = param.getValue();
                String trangThai = "";
                switch (dsPhatThuong.getTrangThai()){
                    case -1:
                        trangThai = "Đã xóa";
                        break;
                    case 0:
                        trangThai = "Đợi nộp minh chứng";
                        break;
                    case 1:
                        trangThai = "Đang phát";
                        break;
                    case 2:
                        trangThai = "Đã hoàn thành";
                        break;
                }
                String content = dsPhatThuong.getSuKien() + "\n" + "Trạng thái: " + trangThai + "\n" + "Ngày tạo: " + dsPhatThuong.getNgayTao();
                return new ReadOnlyObjectWrapper<>(content);
            }
        });

        Callback<TableColumn<DSPhatThuong, Void>, TableCell<DSPhatThuong, Void>> cellFactory = new Callback<TableColumn<DSPhatThuong, Void>, TableCell<DSPhatThuong, Void>>() {
            @Override
            public TableCell<DSPhatThuong, Void> call(final TableColumn<DSPhatThuong, Void> param) {
                final TableCell<DSPhatThuong, Void> cell = new TableCell<DSPhatThuong, Void>() {
                    private final Button deleteButton = new Button("Xóa");

                    {
                        deleteButton.setOnAction((ActionEvent event) -> {
                            DSPhatThuong data = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Xóa danh sách");
                            alert.setHeaderText("Xác nhận xóa");
                            alert.setContentText("Bạn có chắc xóa danh sách "+data.getSuKien()+" không?");
                            ButtonType xacNhanButtonType = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
                            ButtonType thoatButtonType = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
                            alert.getButtonTypes().clear();
                            alert.getButtonTypes().addAll(xacNhanButtonType, thoatButtonType);
                            Optional<ButtonType> result = alert.showAndWait();
                            if(result.get() == xacNhanButtonType) {
                                try {
                                    dsPhatThuongService.deleteByMaDS(data.getMaDS());
                                    loadData();
                                    table.setItems(dsPhatThuongObservableList);
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                            }
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
        numberColumn.setSortable(false);
        dsPhatThuongColumn.setSortable(false);
    }

    private void loadData(){
        try{
            dsPhatThuongObservableList = FXCollections.observableArrayList(dsPhatThuongService.getAll());
            table.setItems(dsPhatThuongObservableList);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    public void toHome(){
        commonController.toHome();
    }
    public void toPhatQua(){
        commonController.toPhatQua();
    }
    public void toThongKe(){
        commonController.toThongKe();
    }
    public void toThemMoiDsPhatThuong(){
        commonController.toThemMoiDsPhatThuong();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
        dsPhatThuongService = new DSPhatThuongService();
        initTable();
        loadData();
    }

}
