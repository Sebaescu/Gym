package ec.edu.espol.deber2javafx;

import ec.edu.espol.model.GymException;
import ec.edu.espol.model.Rutina;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {     
        scene = new Scene(loadFXML("aplicacion"));
        stage.setScene(scene);
        stage.setTitle("Rutinas");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
        

    }

}