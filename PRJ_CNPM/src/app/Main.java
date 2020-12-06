package app;

import app.view.CommonController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import app.common.MyConnection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            CommonController.primaryStage = primaryStage;
            MyConnection myConnection = new MyConnection();
            myConnection.connectDB();
            Parent root = FXMLLoader.load(getClass().getResource("view/login/login.fxml"));
            primaryStage.setTitle("Quản lý phát thưởng - phát quà");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
