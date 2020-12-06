package FXStuff;


import Utilitiy.Position;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Cell;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import Arena.*;
import unit.AirForceLogic;
import unit.AttackerLogic;
import unit.Unit;

import java.util.ArrayList;
import java.util.List;

public class Arena implements EventHandler {
    //DATAMEMBERS
    GridPane arenaPane =new GridPane();
    static Button arenaButtons[][]=new Button[10][10];
    Pane pane=new Pane();
    Button UpButton=new Button("Up");
    Button DownButton=new Button("Down");
    Button LeftButton=new Button("Left");
    Button RightButton=new Button("Right");
    TextField XField=new TextField("");
    TextField YField=new TextField("");
    Button GoToButton=new Button("GoTo");
    Label XLabel=new Label("X:");
    Label YLabel=new Label("Y:");
    Position left_upper_corrner=new Position(0,0);
    static GridCell cells[][]=null;











    void PrintArena()
    {
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
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
        PrintArena();
        cells=Grid.GetCut(left_upper_corrner);
        ColorCell();

        UpButton.setLayoutX(600);
        UpButton.setLayoutY(10);
        UpButton.setPrefSize(60,40);
        UpButton.setOnAction(this::handle);

        /////
        DownButton.setLayoutY(60);
        DownButton.setLayoutX(600);
        DownButton.setPrefSize(60,40);
        DownButton.setOnAction(this::handle);
        /////
        LeftButton.setLayoutX(550);
        LeftButton.setLayoutY(35);
        LeftButton.setPrefSize(60,40);
        LeftButton.setOnAction(this::handle);
        /////
        RightButton.setLayoutX(650);
        RightButton.setLayoutY(35);
        RightButton.setPrefSize(60,40);
        RightButton.setOnAction(this::handle);
        /////
        XField.setLayoutX(350);
        XField.setLayoutY(10);
        /////
        YField.setLayoutX(350);
        YField.setLayoutY(50);
        /////
        XLabel.setLayoutX(330);
        XLabel.setLayoutY(15);
        ////
        YLabel.setLayoutX(330);
        YLabel.setLayoutY(55);
        ///
        GoToButton.setLayoutX(280);
        GoToButton.setLayoutY(30);
        GoToButton.setOnAction(this::handle);
        //



        pane.getChildren().addAll(arenaPane,UpButton,DownButton,LeftButton,RightButton,XLabel,YField,YLabel,XField,GoToButton);

        Scene scene=new Scene(pane,800,600);
        Stage stage=new Stage();

        stage.setScene(scene);
        return stage;





    }


    @Override
    public void handle(Event event) {
        if(event.getSource()==GoToButton)
        {
            if(XField.getText()==""||YField.getText()=="")
            {
                ErrorMessage errorMessage=new ErrorMessage();
                errorMessage.PrintError("Enter the postion Pleaseeeeeeeeeeeeeeeeee");


            }
            else
            {
                int x,y;
                x=Integer.parseInt(XField.getText());
                y=Integer.parseInt(YField.getText());
                if(x<0)
                    x=0;
                if(x>= Grid.CellNum)
                    x = Grid.CellNum - 10;
                if(y<0)
                    y=0;
                if(x>= Grid.CellNum)
                    y = Grid.CellNum - 10;
                left_upper_corrner.Set_X(x);
                left_upper_corrner.Set_Y(y);
                cells=Grid.GetCut(left_upper_corrner);
                ColorCell();



            }


        }
        if(event.getSource()==UpButton)
        {

            int x,y;
            x=left_upper_corrner.Get_X();
            y=left_upper_corrner.Get_Y()-10;
            if(x<0)
                x=0;
            if(x>= Grid.CellNum)
                x = Grid.CellNum - 10;
            if(y<0)
                y=0;
            if(x>= Grid.CellNum)
                y = Grid.CellNum - 10;
            left_upper_corrner.Set_X(x);
            left_upper_corrner.Set_Y(y);
            cells=Grid.GetCut(left_upper_corrner);
            ColorCell();
        }
        if(event.getSource()==DownButton)
        {
            int x,y;
            x=left_upper_corrner.Get_X();
            y=left_upper_corrner.Get_Y()+10;
            if(x<0)
                x=0;
            if(x>= Grid.CellNum)
                x = Grid.CellNum - 10;
            if(y<0)
                y=0;
            if(x>= Grid.CellNum)
                y = Grid.CellNum - 10;
            left_upper_corrner.Set_X(x);
            left_upper_corrner.Set_Y(y);
            cells=Grid.GetCut(left_upper_corrner);
            ColorCell();
        }
        if(event.getSource()==LeftButton)
        {
            int x,y;
            x=left_upper_corrner.Get_X()-10;
            y=left_upper_corrner.Get_Y();
            if(x<0)
                x=0;
            if(x>= Grid.CellNum)
                x = Grid.CellNum - 10;
            if(y<0)
                y=0;
            if(x>= Grid.CellNum)
                y = Grid.CellNum - 10;
            left_upper_corrner.Set_X(x);
            left_upper_corrner.Set_Y(y);
            cells=Grid.GetCut(left_upper_corrner);
            ColorCell();
        }
        if(event.getSource()==RightButton)
        {
            int x,y;
            x=left_upper_corrner.Get_X()+10;
            y=left_upper_corrner.Get_Y();
            if(x<0)
                x=0;
            if(x>= Grid.CellNum)
                x = Grid.CellNum - 10;
            if(y<0)
                y=0;
            if(x>= Grid.CellNum)
                y = Grid.CellNum - 10;
            left_upper_corrner.Set_X(x);
            left_upper_corrner.Set_Y(y);
            cells=Grid.GetCut(left_upper_corrner);
            ColorCell();
        }
    }

    public static void ColorCell(){
        for(int i=0;i<cells.length;i++)
            for(int j=0;j<cells.length;j++)
            {
                List<Unit>units=new ArrayList<>();
                units=cells[i][j].GetUnits();
                boolean def=false;
                boolean att=false;
                for(Unit unit:units)
                {
                    if(unit.GetLogic() instanceof AirForceLogic || unit.GetLogic() instanceof AttackerLogic)
                        att = true;
                    else
                        def = true;

                }
                if(def&&att)
                {
                    arenaButtons[i][j].setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), Insets.EMPTY)));
                }
                else if(def)
                {
                    arenaButtons[i][j].setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(0), Insets.EMPTY)));
                }
                else if(att)
                {
                    arenaButtons[i][j].setBackground(new Background(new BackgroundFill(Color.BLUE, new CornerRadii(0), Insets.EMPTY)));
                }

            }


    }
}
