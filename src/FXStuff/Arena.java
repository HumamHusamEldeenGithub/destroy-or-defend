package FXStuff;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Arena {
    //DATAMEMBERS
    GridPane arenaPane =new GridPane();
    Button arenaButtons[][]=new Button[10][10];
    Pane pane=new Pane();
    ScrollPane scrollPane=new ScrollPane();

    void PrintArena()
    {
        for(int i=0;i<100;i++)
        {
            for(int j=0;j<100;j++)
            {   int finalI=i;
                int finalJ=j;
                arenaButtons[i][j]=new Button();
                arenaButtons[i][j].setPrefSize(20,20);
                arenaPane.add(arenaButtons[i][j],j,i);

                arenaButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        System.out.print(finalI);
                        System.out.print(finalJ);
                    }
                });
            }
        }
    }
    Stage Build()
    {
        scrollPane.setContent(arenaPane);
        scrollPane.setPrefSize(800,600);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setFitToHeight(true);
        scrollPane.pannableProperty().set(true);

        pane.getChildren().addAll(arenaPane,scrollPane);
        PrintArena();
        Scene scene=new Scene(pane,800,600);
        Stage stage=new Stage();

        stage.setScene(scene);
        return stage;
    }
}
