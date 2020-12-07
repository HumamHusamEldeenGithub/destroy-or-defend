package GUI;

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

    static ArrayList<Player> Players=new ArrayList<Player>();

    @FXML
    private AnchorPane rootAnchor;

    @FXML
    private Spinner<?> AttackersNum;

    @FXML
    private Spinner<?> DefendersNum;

    @FXML
    void NextScene(ActionEvent event) {

    }

    public  ArrayList<Player> GetPLayers()
    {
        return Players;
    }
    @FXML
    public void NextScene(javafx.event.ActionEvent actionEvent) throws IOException {

        int AttackTeamPlayers = (int) AttackersNum.getValue();
        int DefendTeamPlayers = (int) DefendersNum.getValue();
        for(int i=1;i<=AttackTeamPlayers;i++)
        {
            Players.add(new Player(PlayerType.Attacker,i,null) );
        }
        for(int i=1;i<=DefendTeamPlayers;i++)
        {
            Players.add( new Player(PlayerType.Defender,i+AttackTeamPlayers,null));
        }


        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXML/ShopMenu.fxml"));
        rootAnchor.getChildren().setAll(pane) ;
    }

}
