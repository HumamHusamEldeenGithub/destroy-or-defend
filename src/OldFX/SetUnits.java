package OldFX;

import GUI.ErrorMessage;
import Utilitiy.Position;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import unit.Unit;
import Arena.*;

import java.util.ArrayList;
import java.util.List;

public class SetUnits implements EventHandler{

    //DataMembers

    int numOfCells;//get from DORD manager
    ImageView imageView = new ImageView("\\Images\\Black.jpg");
    Pane root =new Pane();
    Label playerUnitsLabel=new Label("---------------------------Your Units----------------------------");
    //ArrayList<Button>playerUnits=new ArrayList<Button>();//the units of the current player as a buttons
    int m;
    Button[]playerUnits;
    boolean isSet[];
    ScrollPane scrollPane=new ScrollPane();
    HBox playerUnitsHbox=new HBox();
    int ID;
    Label numOfPlayer=new Label("");
    Button arena [][];
    GridPane arenaPane=new GridPane();
    Stage prevStage;
    Button DoneButton=new Button("Done");
    int unitIndex=-1;
    List<Unit> Units=new ArrayList<>();

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
                        if (unitIndex != -1) {
                            arena[finalI][finalJ].setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(0), Insets.EMPTY)));
                            System.out.println(new Position(finalI,finalJ));
                            if(Grid.GetGrid().CheckCell(new Position(finalI,finalJ),Units.get(unitIndex)))
                            {Units.get(unitIndex).SetPosition(new Position(finalI,finalJ));
                            unitIndex = -1;}
                            else
                            {
                                ErrorMessage errorMessage=new ErrorMessage();
                               // errorMessage.PrintError("You cant place here ");

                            }
                        }
                    }
                });

            }
        }
    }

    Stage BuildSetUnits(int id)
    {
        ID=id;
        numOfCells=Grid.CellNum;
        System.out.println(numOfCells);
        arena=new Button[numOfCells][numOfCells];


        scrollPane.setContent(arenaPane);
        scrollPane.setPrefSize(800,600);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setFitToHeight(true);
        scrollPane.pannableProperty().set(true);
        ///////////

        m=NumOfPlayers.Players.get(id-1).GetUnits().size();
        playerUnits=new Button[m];
        isSet=new boolean[m];

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
        DoneButton.setLayoutY(760);
        DoneButton.setLayoutY(760);
        DoneButton.setOnAction(this::handle);
        //////

        //////
        String playerType=NumOfPlayers.Players.get(id-1).GetType().toString();
        String playerId=String.valueOf(id);
        String temp=playerType+playerId;
        numOfPlayer.setText(temp);
        numOfPlayer.setLayoutY(700);
        numOfPlayer.setLayoutX(700);
        //////
        root.getChildren().addAll(imageView,arenaPane,playerUnitsLabel,playerUnitsHbox,numOfPlayer,scrollPane,DoneButton);
        ////
        printArena();
        printBench();
        ////////
        Scene scene=new Scene(root,800,800);
        Stage stage=new Stage();
        stage.setScene(scene);
        prevStage=stage;
        return  stage;
    }

    public void printBench() {
        Units=NumOfPlayers.Players.get(ID-1).GetUnits();
        for (int i = 0; i < m; i++) {
            String s=new String();
            s=Units.get(i).GetType().toString();

            playerUnits[i] = new Button();
            playerUnits[i].setText(s);
            playerUnitsHbox.getChildren().add(playerUnits[i]);
            int finalI = i;
            playerUnits[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(unitIndex==-1)
                    {
                        unitIndex=finalI;
                        playerUnits[finalI].setVisible(false);
                    }
                }
            });
        }
    }

    @Override
    public void handle(Event event) {
        if(event.getSource()==DoneButton)
        {   boolean ok=true;
            for(int i=0;i<m;i++)
            {
                if(isSet[i]==true)
                    ok=false;
            }
            if(ok==true)
            prevStage.close();
            else
            {
                ErrorMessage errorMessage=new ErrorMessage();
               // errorMessage.PrintError("You have to set all of your units ");
            }
        }
    }
}