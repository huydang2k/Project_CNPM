package app;

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
            MyConnection myConnection = new MyConnection();
            myConnection.connectDB();
            Parent root = FXMLLoader.load(getClass().getResource("view/fxml/login.fxml"));
            primaryStage.setTitle("Hello World");
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
