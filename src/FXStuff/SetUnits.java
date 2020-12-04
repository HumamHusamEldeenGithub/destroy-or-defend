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

public class SetUnits {

    //DataMembers
    Button zoomIn=new Button("ZoomIn");
    Button zoomOut=new Button("ZoomOut");
    Button Up=new Button("UP");
    Button Down=new Button("Down");
    Button Left=new Button("Left");
    Button Right=new Button("Right");
    int n=20; //GetFromNumof cells in the grid class
    ImageView imageView = new ImageView("\\Images\\Black.jpg");
    Pane root =new Pane();
    Label playerUnitsLabel=new Label("---------------------------Your Units----------------------------");

    int m =5;//num of units of the current player
    Button playerUnits[];//the units of the current player as a buttons
    HBox playerUnitsHbox=new HBox();






    Button arena [][]=new Button[n][n];
    GridPane arenaPane=new GridPane();

    int Index=-1;
    void printArena() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

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

    Stage BuildSetUnits()
    {   imageView.setFitHeight(800);
    imageView.setFitWidth(800);


        HBox hBox=new HBox();
        hBox.getChildren().addAll(zoomIn,zoomOut,Up,Down,Left,Right);
        hBox.setLayoutX(0);
        hBox.setLayoutX(0);
        arenaPane.setLayoutY(30);
        playerUnitsLabel.setLayoutY(600);
        playerUnitsHbox.setLayoutY(650);

        root.getChildren().addAll(imageView,hBox,arenaPane,playerUnitsLabel,playerUnitsHbox);
        printArena();
        printBench();
        Scene scene=new Scene(root,800,800);
        Stage stage=new Stage();
        stage.setScene(scene);
        return  stage;



    }



    public void printBench() {
        if (playerUnits != null) {
            for (int i = 0; i < playerUnits.length; i++) {
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
                    System.out.println(finalI);
                }
            });
        }

    }


}
