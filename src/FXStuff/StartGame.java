package FXStuff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class StartGame {

    @FXML
    private AnchorPane rootAnchor;

    @FXML
    private Button play;

    @FXML
    public void startGame(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("PlayersCounter.fxml"));
        rootAnchor.getChildren().setAll(pane) ;
    }
    /*        */
}
