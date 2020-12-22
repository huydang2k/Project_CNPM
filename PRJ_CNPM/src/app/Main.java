package app;

import app.common.MyConnection;
import app.common.PrintPDF;
import app.controller.CommonController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            CommonController.primaryStage = primaryStage;
            MyConnection myConnection = new MyConnection();
            myConnection.connectDB();
            Parent root = FXMLLoader.load(getClass().getResource("view/login/login.fxml"));
            primaryStage.setTitle("Quản lý phát thưởng - phát quà");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            PrintPDF printPDF = new PrintPDF();
//        //type = true: danh sách phát quà
//        //type = false: danh sách phát thưởng
            printPDF.printDS(2, true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
