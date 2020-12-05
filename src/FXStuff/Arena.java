package FXStuff;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Arena implements EventHandler {
    //DATAMEMBERS
    GridPane arenaPane =new GridPane();
    Button arenaButtons[][]=new Button[10][10];
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

        UpButton.setLayoutX(600);
        UpButton.setLayoutY(10);
        UpButton.setPrefSize(60,40);
        /////
        DownButton.setLayoutY(60);
        DownButton.setLayoutX(600);
        DownButton.setPrefSize(60,40);
        /////
        LeftButton.setLayoutX(550);
        LeftButton.setLayoutY(35);
        LeftButton.setPrefSize(60,40);
        /////
        RightButton.setLayoutX(650);
        RightButton.setLayoutY(35);
        RightButton.setPrefSize(60,40);
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



        pane.getChildren().addAll(arenaPane,UpButton,DownButton,LeftButton,RightButton,XLabel,YField,YLabel,XField,GoToButton);
        PrintArena();
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

            }


        }
        if(event.getSource()==UpButton)
        {

        }
        if(event.getSource()==DownButton)
        {

        }
        if(event.getSource()==LeftButton)
        {

        }
        if(event.getSource()==RightButton)
        {

        }
    }
}
