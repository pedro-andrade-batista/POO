package loaders;

import controller.CtrlBarragem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Barragem;

import java.io.IOException;

public class WindowBarragem {


        public void start(Barragem b) throws Exception {
            FXMLLoader loader =  new FXMLLoader();
            try {
                Pane graph = loader.load(getClass().getResource("../view/FXMLBarragem.fxml").openStream());

                CtrlBarragem crtl = loader.getController();
                crtl.show(b);

                Scene scene = new Scene(graph, 700, 450);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.setTitle("Barragem");
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
