package GUI;

import Strategies.Strategies;
import gameManager.DoDGameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import player.Player;

import java.io.IOException;


public class SelectStrategy {
    static int PlayerId =0 ;

    public void initialize(){
        this.getPlayerId();
    }
    @FXML
    private AnchorPane rootAnchor;

    @FXML
    private Label id;

    @FXML
    private ToggleGroup Strategy;

    @FXML
    void NextScene(ActionEvent event) throws IOException {
        RadioButton radioButton = (RadioButton) Strategy.getSelectedToggle() ;
        System.out.println(radioButton.getText());
        DoDGameManager.Players.get(SelectStrategy.PlayerId).SetStrategy(Strategies.valueOf(radioButton.getText()));

        if (SelectStrategy.PlayerId== DoDGameManager.Players.size()-1)
                 GUIManager.ChangeScene(rootAnchor,Scene.SetUnits);
        else
        {
            SelectStrategy.PlayerId++ ;
            GUIManager.ChangeScene(rootAnchor,Scene.Strategy);
        }

    }
    public void getPlayerId ()
    {
        id.setText(DoDGameManager.Players.get(SelectStrategy.PlayerId).GetType().toString() + "  id : "+SelectStrategy.PlayerId) ;
    }

}
