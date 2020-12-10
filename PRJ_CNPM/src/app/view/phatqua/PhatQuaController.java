package app.view.phatqua;

import app.model.DSPhatQua;
import app.service.PhatQuaService;
import app.view.CommonController;
import app.view.phatqua.dspqchitiet.view_dspqchitiet.ViewDanhSachChiTietController;
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
import java.util.ResourceBundle;

public class PhatQuaController implements Initializable {

    PhatQuaService phatQuaService;
    CommonController commonController;

    ObservableList<DSPhatQua> dsPhatQuaObservableList;

    //table
    @FXML
    TableView<DSPhatQua> table;

    @FXML
    TableColumn<DSPhatQua, String> dsPhatQuaColumn;

    @FXML
    TableColumn<DSPhatQua, String> numberColumn;

    @FXML
    TableColumn<DSPhatQua, Void> buttonsColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        phatQuaService = new PhatQuaService();
        commonController = new CommonController();
        initTable();
        loadData();
    }

    private void initTable(){
        initColumns();
        table.setRowFactory( RowFactory -> {
            TableRow<DSPhatQua> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    DSPhatQua rowData = row.getItem();
                    System.out.println(rowData);
                    Stage stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
                    FXMLLoader loader = new FXMLLoader();
                    if(rowData.getTrangThai() == 2){
                        loader.setLocation(getClass().getResource("dspqchitiet\\view_dspqchitiet\\View_DanhSachChiTiet.fxml"));
                        try {
                            Parent root = loader.load();
                            ViewDanhSachChiTietController controller = loader.getController();
                            controller.initData(rowData);
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }else{
                        loader.setLocation(getClass().getResource("dspqchitiet\\edit_dspqchitiet\\Edit_DanhSachChiTiet.fxml"));
                        try {
                            Parent root = loader.load();
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
        numberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DSPhatQua, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DSPhatQua, String> param) {
                return new ReadOnlyObjectWrapper<>(table.getItems().indexOf(param.getValue()) + 1 + " ");
            }
        });
        dsPhatQuaColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DSPhatQua, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DSPhatQua, String> param) {
                DSPhatQua dsPhatQua = param.getValue();
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
                String content = dsPhatQua.getSuKien() + "\n" + "Trạng thái: " + trangThai + "\n" + "Ngày tạo: " + dsPhatQua.getNgayTao();
                return new ReadOnlyObjectWrapper<>(content);
            }
        });

        Callback<TableColumn<DSPhatQua, Void>, TableCell<DSPhatQua, Void>> cellFactory = new Callback<TableColumn<DSPhatQua, Void>, TableCell<DSPhatQua, Void>>() {
            @Override
            public TableCell<DSPhatQua, Void> call(final TableColumn<DSPhatQua, Void> param) {
                final TableCell<DSPhatQua, Void> cell = new TableCell<DSPhatQua, Void>() {
                    private final Button deleteButton = new Button("Xóa");

                    {
                        deleteButton.setOnAction((ActionEvent event) -> {
                            DSPhatQua data = getTableView().getItems().get(getIndex());
                            dsPhatQuaObservableList.remove(data);
                            table.setItems(dsPhatQuaObservableList);
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
        dsPhatQuaColumn.setSortable(false);
    }

    private void loadData(){
        try{
            dsPhatQuaObservableList = FXCollections.observableArrayList(phatQuaService.getAll());
            table.setItems(dsPhatQuaObservableList);
        }catch (SQLException ex){
            ex.printStackTrace();
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
