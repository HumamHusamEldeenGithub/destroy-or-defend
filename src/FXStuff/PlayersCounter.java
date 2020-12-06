package FXStuff;

import com.sun.glass.events.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class PlayersCounter {

    @FXML
    private AnchorPane rootAnchor;

    @FXML
    private Spinner<?> AttackersNum;

    @FXML
    private Spinner<?> DefendersNum;

    @FXML
    void NextScene(ActionEvent event) {

    }

    @FXML
    public void NextScene(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ShopMenu.fxml"));
        rootAnchor.getChildren().setAll(pane) ;
    }

}
