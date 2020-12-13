package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class About {

    @FXML
    private AnchorPane rootAnchor;

    @FXML
    void MainMenu(ActionEvent event) throws IOException {
        GUIManager.ChangeScene(rootAnchor,WindowType.StartGame);

    }
}
