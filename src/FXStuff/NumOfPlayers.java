package FXStuff;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import player.Player;
import player.PlayerType;

import java.util.ArrayList;

public class NumOfPlayers implements EventHandler {
    Label label1 =new Label("NumOFAttackers");
    Spinner Attackers=new Spinner(1,5,1);

    Label label2 =new Label("NumOFDefenders");
    Spinner Defenders=new Spinner(1,5,1);

    Pane root =new Pane();
   static   int AttackTeamPlayers;
     static int DefendTeamPlayers;
    Button NextButton=new Button("Next");
    Stage prevStage;

    ArrayList<Player>Players=new ArrayList<Player>();
     Stage Build()
    {

        ImageView imageView=new ImageView("\\Images\\r6s.jpg");
        label1.setFont(new Font("Arial",20));
        label2.setFont(new Font("Arial",20));

        VBox vBox1=new VBox();
        vBox1.getChildren().addAll(label1,Attackers);
        vBox1.setLayoutX(30);
        vBox1.setLayoutY(100);

        VBox vBox2=new VBox();
        vBox2.getChildren().addAll(label2,Defenders);

        vBox2.setLayoutX(600);
        vBox2.setLayoutY(100);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);
        NextButton.setLayoutX(390);
        NextButton.setLayoutY(500);
        NextButton.setOnAction(this::handle);

        root.getChildren().addAll(imageView,vBox1,vBox2,NextButton);
        Scene scene =new Scene(root,800,600);
        Stage stage=new Stage();
        stage.setScene(scene);
        prevStage=stage;

        return stage;



    }


    public  ArrayList<Player> GetPLayers()
    {
        return Players;

    }


    @Override
    public void handle(Event event) {
        if(event.getSource()==NextButton)
        {
            AttackTeamPlayers= (int) Attackers.getValue();
            DefendTeamPlayers= (int) Defenders.getValue();

            for(int i=1;i<=AttackTeamPlayers;i++)
            {
                //Players.add(new Player(PlayerType.Attacker,i));
            }
            for(int i=0;i<=DefendTeamPlayers;i++)
            {
                //Players.add( new Player(PlayerType.Defender,i+AttackTeamPlayers));

            }


            /////////////
            prevStage.hide();

            ShopScreen shopScreen =new ShopScreen();
            Stage stage=new Stage();
            stage= shopScreen.Build();
            stage.show();







        }
    }
}
