package loader;

import controller.WindowClientController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Client;


public class WindowAddClientLoader {

    public void start(TableView<Client> tableView){
        FXMLLoader loader = new FXMLLoader();
        try {
            Pane graph = loader.load(getClass().getResource("../view/FXMLWindowAddClient.fxml").openStream());

            WindowClientController ctrl = loader.getController();
            ctrl.initializeWindow(tableView);

            Scene scene = new Scene(graph,600,570);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Add Client");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
