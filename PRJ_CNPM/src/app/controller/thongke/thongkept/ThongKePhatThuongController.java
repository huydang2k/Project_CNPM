package app.controller.thongke.thongkept;

import app.model.DSPhatThuong;
import app.service.DSPhatThuongService;
import app.controller.CommonController;
import app.controller.thongke.thongkept.tkptchitiet.TKPTChiTietController;
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
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ThongKePhatThuongController implements Initializable {
    CommonController commonController;
    DSPhatThuongService dsPhatThuongService ;
    ObservableList<DSPhatThuong> dsPhatThuongObservableList;
    @FXML
    TableView<DSPhatThuong> table;
    @FXML
    TableColumn<DSPhatThuong, String> dsPhatThuong;

    @FXML
    TableColumn<DSPhatThuong, String> numberColumn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
        dsPhatThuongService = new DSPhatThuongService();
        initTable();
        loadData();
    }

    private void initTable(){
        initColumns();
        table.setRowFactory( RowFactory -> {
            TableRow<DSPhatThuong> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ){
                    DSPhatThuong rowData = row.getItem();
                    Stage stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("..\\..\\..\\view\\thongke\\thongkept\\tkptchitiet\\TKPTChiTiet.fxml"));
                    try {
                        Parent root = loader.load();
                        TKPTChiTietController controller = loader.getController();
                        controller.initData(rowData);
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
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
        dsPhatThuong.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DSPhatThuong, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DSPhatThuong, String> param) {
                DSPhatThuong dsPhatThuong = param.getValue();
                String content = "Thống kê hoạt động phát thưởng " + dsPhatThuong.getSuKien();
                return new ReadOnlyObjectWrapper<>(content);
            }
        });

        numberColumn.setSortable(false);
        dsPhatThuong.setSortable(false);
    }

    private void loadData(){
        try{
            dsPhatThuongObservableList = FXCollections.observableArrayList(dsPhatThuongService.getDSHoanThanh());
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
    public void toPhatThuong(){
        commonController.toPhatThuong();
    }
    public void toThongKe(){
        commonController.toThongKe();
    }
}