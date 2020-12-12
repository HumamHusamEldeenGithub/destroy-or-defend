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
    static int AttackerNumber =0 ;
    static int DefenderNumber =0 ;

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

         PlayersCounter.AttackerNumber = (int) AttackersNum.getValue();
         PlayersCounter.DefenderNumber = (int) DefendersNum.getValue();
        SetPlayers();
        GUIManager.ChangeScene(rootAnchor, WindowType.Strategy);
    }
    void SetPlayers( )
    {
        for (int i =0 ; i<AttackerNumber+DefenderNumber ; i++)
        {
            if (i<DefenderNumber)
            {
                Player player = new Player(PlayerType.Defender,i,null) ;
                GUIManager.Players.add(player) ;
            }
            else
            {
                Player player = new Player(PlayerType.Attacker , i-DefenderNumber ,null ) ;
                GUIManager.Players.add(player) ;
            }
        }
    }

}
