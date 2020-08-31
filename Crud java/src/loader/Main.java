package loader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        try {
            Pane root = loader.load(getClass().getResource("../view/FXMLWindowMain.fxml"));
            primaryStage.setTitle("Clients");
            primaryStage.setScene(new Scene(root, 800, 500));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
