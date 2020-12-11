package OldFX;

import gameManager.DoDGameManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class ShopScreen implements EventHandler {

    //DataMembers

    Button nextButton=new Button("Next");
    ImageView imageView=new ImageView("\\Images\\Attackers.jpg");
    Label mainLabel=new Label("STORE");
    Pane root =new Pane();
    Stage prevStage;

    Stage Build()
    {

        imageView.setFitWidth(400);
        imageView.setFitHeight(400);

        mainLabel.setLayoutX(200);
        mainLabel.setLayoutY(10);
        mainLabel.setFont(new Font("Aerial",20));

        nextButton.setLayoutX(200);
        nextButton.setLayoutY(350);


        root.getChildren().addAll(imageView,nextButton,mainLabel);
        nextButton.setOnAction(this::handle);

        Scene scene=new Scene(root,400,400);
        Stage stage=new Stage();
        stage.setScene(scene);
        prevStage=stage;

        return stage;

    }



    @Override
    public void handle(Event event) {
        if(event.getSource()==nextButton)
        {
            prevStage.close();
            for(int i=1;i<=NumOfPlayers.AttackTeamPlayers;i++)
            {
                SetTactics setTactics=new SetTactics();
                Stage MyNewStage;
                MyNewStage=setTactics.Build(i);
                MyNewStage.showAndWait();
            }

            for(int i=1;i<=NumOfPlayers.AttackTeamPlayers;i++)
            {

                Stage MyNewStage;
                BuyList buyList =new BuyList();
                MyNewStage= buyList.BuildBuyList(i);
                MyNewStage.showAndWait();

            }

            for(int i=1;i<=NumOfPlayers.DefendTeamPlayers;i++)
            {
                SetTactics setTactics=new SetTactics();
                Stage MyNewStage;
                MyNewStage=setTactics.Build(i+NumOfPlayers.AttackTeamPlayers);
                MyNewStage.showAndWait();
            }

            for(int i=1;i<=NumOfPlayers.DefendTeamPlayers;i++)
            {

                Stage MyNewStage;
                BuyList buyList =new BuyList();
                MyNewStage= buyList.BuildBuyList(i+NumOfPlayers.AttackTeamPlayers);
                MyNewStage.showAndWait();
            }

            SetUnitsScreen setUnitsScreen=new SetUnitsScreen();
            Stage MyNewStage=new Stage();
            MyNewStage=setUnitsScreen.Build();
            MyNewStage.showAndWait();



            for(int i=1;i<=NumOfPlayers.AttackTeamPlayers;i++)
            {
                SetUnits setUnits=new SetUnits();
                 MyNewStage=new Stage();
                MyNewStage=setUnits.BuildSetUnits(i);
                MyNewStage.showAndWait();
            }

            for(int i=1;i<=NumOfPlayers.DefendTeamPlayers;i++)
            {
                SetUnits setUnits=new SetUnits();
                 MyNewStage=new Stage();
                MyNewStage=setUnits.BuildSetUnits(i+NumOfPlayers.AttackTeamPlayers);
                MyNewStage.showAndWait();
            }
            //DoDGameManager.InitializePlayers();
            Arena arena=new Arena();
            MyNewStage=new Stage();
            MyNewStage=arena.Build();
            GameRunner runner = GameRunner.GetObj();
            GameRunner.StartGame(MyNewStage );
            //MyNewStage.showAndWait();


        }

    }
}
