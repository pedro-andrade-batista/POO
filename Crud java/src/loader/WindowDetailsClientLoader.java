package loader;

import controller.WindowClientController;
import controller.WindowDetailsClientController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Client;

public class WindowDetailsClientLoader {
    public void start(Client client){
        FXMLLoader loader = new FXMLLoader();
        try {
            Pane graph = loader.load(getClass().getResource("../view/FXMLWindowDetailsClient.fxml").openStream());
            WindowDetailsClientController ctrl = loader.getController();
            ctrl.initializeWindow(client);

            Scene scene = new Scene(graph,950,500);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Details Client");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
