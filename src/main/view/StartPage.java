package view;

import javafx.application.Application;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartPage extends Application implements Runnable{
    public static Stage stage;

    private volatile boolean isCancelled = false;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/startPage.fxml"));
        primaryStage.setTitle("Queue Manager Assistance");
        primaryStage.setScene(new Scene(root, 479, 119));
        primaryStage.show();
        this.stage = primaryStage;
    }

    public void closeWindow() {
        isCancelled = true;
        Platform.exit();
//        this.stage.close();
    }

    @Override
    public void run() {

            launch(new String[]{});

    }

    public static void main(String[] args) {
        launch(args);
    }
}
