package FXStuff;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import team.AttackerTeam;

public class NumOfPlayers implements EventHandler {
    Label label1 =new Label("NumOFAttackers");
    Spinner Attackers=new Spinner(1,5,1);

    Label label2 =new Label("NumOFDefenders");
    Spinner Defenders=new Spinner(1,5,1);

    Pane root =new Pane();
    int AttackTeamPlayers;
    int DefendTeamPlayers;
    Button NextButton=new Button("Next");
    Stage prevStage;


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


    @Override
    public void handle(Event event) {
        if(event.getSource()==NextButton)
        {
            AttackTeamPlayers= (int) Attackers.getValue();
            DefendTeamPlayers= (int) Defenders.getValue();
            /////////////
            prevStage.hide();



            for(int i=0;i<AttackTeamPlayers;i++)
            {

                

            Stage MyNewStage;


            AttackersBuyList attackersBuyList=new AttackersBuyList();
            MyNewStage=attackersBuyList.BuildBuyList(i+1);

            MyNewStage.show();

            }




        }
    }
}
