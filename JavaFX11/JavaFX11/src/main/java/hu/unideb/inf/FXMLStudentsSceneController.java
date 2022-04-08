package hu.unideb.inf;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FXMLStudentsSceneController {
    @FXML
    Label helloLabel;

    @FXML
    void handleButtonPushed(){
        if (helloLabel.getText().equals("Fekete")) helloLabel.setText("Fehér");
        else helloLabel.setText("Fekete");
        System.out.println("Hello World");
    }
}
