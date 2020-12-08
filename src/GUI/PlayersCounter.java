package GUI;

import gameManager.DoDGameManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;
import player.Player;
import player.PlayerType;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

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

        int AttackTeamPlayers = (int) AttackersNum.getValue();
        int DefendTeamPlayers = (int) DefendersNum.getValue();
        DoDGameManager.SetPlayers(AttackTeamPlayers,DefendTeamPlayers);
        GUIManager.ChangeScene(rootAnchor,Scene.ShopMenu);
    }

}
