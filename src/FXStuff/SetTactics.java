package FXStuff;

import Strategies.AttackStrategy;
import Strategies.HighestDamageAttackStrategy;
import Strategies.LowestHealthAttackStrategy;
import Strategies.RandomAttackStrategy;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;


public class SetTactics implements EventHandler {

    AnchorPane root =new AnchorPane();
    VBox vBox=new VBox();
    ToggleGroup toggleGroup=new ToggleGroup();
    Label label =new Label("Choose your Tactics :");
    RadioButton r1=new RadioButton("Random");
    RadioButton r2=new RadioButton("Highest Damage ");
    RadioButton r3=new RadioButton("Lowest Health");
    Button nextButton=new Button("Next");
    Label numOfPlayer=new Label();
    Stage prevStage;
    int ID;

    Stage Build(int id)
    {
        ID=id;
        r1.setToggleGroup(toggleGroup);
        r2.setToggleGroup(toggleGroup);
        r3.setToggleGroup(toggleGroup);

        vBox.getChildren().add(r1);
        vBox.getChildren().add(r2);
        vBox.getChildren().add(r3);

        label.setLayoutX(10);
        label.setLayoutY(0);

        vBox.setLayoutX(10);
        vBox.setLayoutY(30);

        nextButton.setLayoutX(10);
        nextButton.setLayoutY(100);

        String playerType=NumOfPlayers.Players.get(id-1).GetType().toString();
        String playerId=String.valueOf(id);
        String temp=playerType+playerId;

        numOfPlayer.setLayoutX(300);
        numOfPlayer.setLayoutY(10);

        root.getChildren().addAll(numOfPlayer,label,vBox,nextButton);
        nextButton.setOnAction(this::handle);

        Scene scene=new Scene(root,400,300);
        Stage stage=new Stage();
        stage.setScene(scene);
        prevStage=stage;
        return  stage;
    }

    @Override
    public void handle(Event event) {
        if(event.getSource()==nextButton)
        {
            String Tactics=new String();

            if(r1.isSelected())
            {NumOfPlayers.Players.get(ID-1).SetStrategy(RandomAttackStrategy.getObj());
                prevStage.close();}
            else if(r2.isSelected())
            {NumOfPlayers.Players.get(ID-1).SetStrategy(HighestDamageAttackStrategy.getObj());
                prevStage.close();}

            else if(r3.isSelected())
            { NumOfPlayers.Players.get(ID-1).SetStrategy(LowestHealthAttackStrategy.getObj());
                prevStage.close();
            }
            else
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("You have to choose of the Tactics above... ");
                alert.show();
            }
        }
    }
}
