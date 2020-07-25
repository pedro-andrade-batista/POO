package loaders;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WindowSS {

        public void start() throws Exception {
            FXMLLoader loader = new FXMLLoader();
            Pane graph = loader.load(getClass().getResource("../view/FXMLSS.fxml"));

            Stage primaryStage = new Stage();
            Scene scene = new Scene(graph, 300, 500);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Classes vs Students");
            primaryStage.show();
        }
}
