package app;

import app.common.MyConnection;
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

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
