package loader;

import controller.WindowClientController;
import controller.WindowServicesController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Client;

public class WindowServiceLoader {

    public void start(Client client){
        FXMLLoader loader = new FXMLLoader();
        try {
            Pane graph = loader.load(getClass().getResource("../view/FXMLAddServices.fxml").openStream());
            WindowServicesController ctrl = loader.getController();
            ctrl.initializeTable(client);

            Scene scene = new Scene(graph,1000,700);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Add Services");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
