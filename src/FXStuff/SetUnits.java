package FXStuff;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import unit.Unit;
import unit.UnitType;

import java.util.ArrayList;
import java.util.List;

public class SetUnits {

    //DataMembers

    int numOfCells;//get from DORD manager
    ImageView imageView = new ImageView("\\Images\\Black.jpg");
    Pane root =new Pane();
    Label playerUnitsLabel=new Label("---------------------------Your Units----------------------------");
    //ArrayList<Button>playerUnits=new ArrayList<Button>();//the units of the current player as a buttons
    int m;
    Button[]playerUnits;

    HBox playerUnitsHbox=new HBox();
    int ID;
    Label numOfPlayer=new Label("");

    Button arena [][]=new Button[numOfCells][numOfCells];
    GridPane arenaPane=new GridPane();

    int Index=-1;
    void printArena() {
        for (int i = 0; i < numOfCells; i++) {
            for (int j = 0; j < numOfCells; j++) {

                arena[i][j] = new Button();
                //arena_btn[i][j].setPrefSize(60, 60);
                arenaPane.add(arena[i][j], j, i);
                int finalI = i;
                int finalJ = j;

                arena[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (Index != -1) {
                            arena[finalI][finalJ].setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(0), Insets.EMPTY)));
                            Index = -1;
                        }
                    }
                });


            }
        }
    }

    Stage BuildSetUnits(int id)
    {
        m=NumOfPlayers.Players.get(id).GetUnits().size();
        playerUnits=new Button[m];

        ////
        imageView.setFitHeight(800);
        imageView.setFitWidth(800);
        //////
        arenaPane.setLayoutY(30);
        //////
        playerUnitsLabel.setLayoutY(600);
        //////
        playerUnitsHbox.setLayoutY(650);
        //////
        root.getChildren().addAll(imageView,arenaPane,playerUnitsLabel,playerUnitsHbox);
        //////
        printArena();
        printBench();
        //////
        String playerType=NumOfPlayers.Players.get(id).GetType().toString();
        String playerId=String.valueOf(id);
        String temp=playerType+playerId;
        numOfPlayer.setText(temp);
        numOfPlayer.setLayoutY(700);
        numOfPlayer.setLayoutX(700);
        //////
        Scene scene=new Scene(root,800,800);
        Stage stage=new Stage();
        stage.setScene(scene);
        return  stage;



    }
    void fillplayerUnits()
    {

    }



    public void printBench() {
        if (playerUnits != null) {
            for (int i = 0; i <m; i++) {
                playerUnits[i].setVisible(false);
            }
        }
        playerUnits = new Button[m];
        for (int i = 0; i < m; i++) {
            playerUnits[i] = new Button();
            playerUnitsHbox.getChildren().add(playerUnits[i]);
            int finalI = i;
            playerUnits[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Index = finalI;
                    playerUnits[Index].setVisible(false);

                }
            });
        }

    }


}
