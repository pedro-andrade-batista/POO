package loaders;

import controller.CtrlSS;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Barragem;

import java.io.IOException;

public class WindowSS {

        public void start(Barragem b) throws Exception {
            FXMLLoader loader = new FXMLLoader();
            try {
                Pane graph = loader.load(getClass().getResource("../view/FXMLSS.fxml").openStream());

                CtrlSS ctrl = loader.getController();
                b = ctrl.show(b);


                Stage primaryStage = new Stage();
                Scene scene = new Scene(graph, 700, 450);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Sistema de Sismo");
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
