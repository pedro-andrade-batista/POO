package loaders;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WindowPeixeVivo extends Application {

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader =  new FXMLLoader();
        Pane graph = loader.load(getClass().getResource("../view/FXMLPeixeVivo.fxml"));

        Scene scene = new Scene(graph, 700, 450);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tela principal");
        primaryStage.show();
    }
}
