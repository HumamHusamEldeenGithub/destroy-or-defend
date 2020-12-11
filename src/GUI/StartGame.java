package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StartGame {

    @FXML
    private AnchorPane rootAnchor;

    @FXML
    private Button play;

    @FXML
    private Button about;

    @FXML
    private Button exit;

    @FXML
    public void startGame(javafx.event.ActionEvent actionEvent) throws IOException {
        GUIManager.ChangeScene(this.rootAnchor , WindowType.PlayerCounter );
    }
    public void AboutPage(javafx.event.ActionEvent actionEvent) throws IOException {
        GUIManager.ChangeScene(this.rootAnchor , WindowType.About );
    }
    public void Exit (javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}
