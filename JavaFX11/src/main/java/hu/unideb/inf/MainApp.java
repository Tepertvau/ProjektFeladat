package hu.unideb.inf;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import hu.unideb.inf.model.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.h2.tools.Server;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/FXMLStudentsScene.fxml"));
        Scene scene = new Scene(loader.load());
        Image image = new Image("C:\\Users\\user\\Desktop\\SFMprojekt\\ProjektFeladat\\JavaFX11\\src\\main\\resources\\fxml\\logo.png");
        stage.getIcons().add(image);
        stage.setTitle("Véradós Móka");

        stage.setScene(scene);
        stage.setMinWidth(1000);
        stage.setMinHeight(800);

        stage.show();
    }


    public static void main(String[] args) {
        System.out.println("Hello World!");

        try {

            startDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        launch(args);
        stopDatabase();
    }

    private static Server s = new Server();



    private static void startDatabase() throws SQLException {
        s.runTool("-tcp", "-web", "-ifNotExists");
    }

    private static void stopDatabase()  {
        s.shutdown();
    }
    
}
