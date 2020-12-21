package app.controller.thongke.thongkepq;

import app.model.DSPhatQua;
import app.service.DSPhatQuaService;
import app.controller.CommonController;
import app.controller.thongke.thongkepq.tkpqchitiet.TKPQChiTietController;
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
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ThongKePhatQuaController implements Initializable {
    CommonController commonController;
    DSPhatQuaService dsPhatQuaService;
    ObservableList<DSPhatQua> dsPhatQuaObservableList;
    @FXML
    TableView<DSPhatQua> table;
    @FXML
    TableColumn<DSPhatQua, String> dsPhatQuaColumn;

    @FXML
    TableColumn<DSPhatQua, String> numberColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
        dsPhatQuaService = new DSPhatQuaService();
        initTable();
        loadData();
    }

    private void initTable(){
        initColumns();
        table.setRowFactory( RowFactory -> {
            TableRow<DSPhatQua> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ){
                    DSPhatQua rowData = row.getItem();
                    Stage stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("..\\..\\..\\view\\thongke\\thongkepq\\tkpqchitiet\\TKPQChiTiet.fxml"));
                    try {
                        Parent root = loader.load();
                        TKPQChiTietController controller = loader.getController();
                        System.out.println("init");
                        System.out.println(rowData);
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
                String content = "Thống kê hoạt động phát quà " + dsPhatQua.getSuKien();
                return new ReadOnlyObjectWrapper<>(content);
            }
        });

        numberColumn.setSortable(false);
        dsPhatQuaColumn.setSortable(false);
    }

    private void loadData(){
        try{
            dsPhatQuaObservableList = FXCollections.observableArrayList(dsPhatQuaService.getDSHoanThanh());
            table.setItems(dsPhatQuaObservableList);
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
