package GUI;

import gameManager.DoDGameManager;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;
import player.Player;
import player.PlayerType;

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

        int AttackTeamPlayers = (int) AttackersNum.getValue();
        int DefendTeamPlayers = (int) DefendersNum.getValue();
        SetPlayers(AttackTeamPlayers,DefendTeamPlayers);
        GUIManager.ChangeScene(rootAnchor, WindowType.Strategy);
    }
    void SetPlayers(int AttackerNumber , int DefenderNumber )
    {
        for (int i =0 ; i<AttackerNumber+DefenderNumber ; i++)
        {
            if (i<AttackerNumber)
            {
                Player player = new Player(PlayerType.Attacker,i,null) ;
                GUIManager.Players.add(player) ;
            }
            else
            {
                Player player = new Player(PlayerType.Defender , i-AttackerNumber ,null ) ;
                GUIManager.Players.add(player) ;
            }
        }
    }

}
