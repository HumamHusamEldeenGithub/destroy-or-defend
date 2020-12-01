package FXStuff;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jdk.jshell.spi.ExecutionControl;



public class AttackersBuyList implements EventHandler {


    Pane pane =new Pane();
    Menu menu =new Menu("UNITS");
    Button BuyButton=new Button("Buy");
    MenuItem tempItem=new MenuItem();
    Label unitType=new Label();
    Button InfoButton=new Button("Info");
    Button nextButton=new Button("Next");


    int Points;//GetFromPLayer
    Label Pointslabel=new Label("Points:"+Points);
    Label numOfPlayer= new Label();
    Stage prevStage;





    Stage BuildBuyList(int num)
    {
        //ImageView imageView=new ImageView("\\Images\\Black.jpg");
        MenuItem m1=new MenuItem("RifleMan");
        MenuItem m2=new MenuItem("Tank");
        MenuItem m3=new MenuItem("Sniper");

        menu.getItems().add(m1);
        menu.getItems().add(m2);
        menu.getItems().add(m3);


        m1.setOnAction(this::handle);
        m2.setOnAction(this::handle);
        m3.setOnAction(this::handle);

        numOfPlayer.setText("Attacker:"+num);

        numOfPlayer.setLayoutX(200);
        numOfPlayer.setLayoutY(10);

        //imageView.setFitHeight(300);
        //imageView.setFitWidth(300);



        Pointslabel.setLayoutX(150);
        Pointslabel.setLayoutY(200);


        InfoButton.setLayoutX(240);
        InfoButton.setLayoutY(30);

        BuyButton.setLayoutX(240);
        BuyButton.setLayoutY(60);


        nextButton.setLayoutX(150);
        nextButton.setLayoutY(240);

        unitType.setLayoutX(100);
        unitType.setLayoutY(0);











        BuyButton.setOnAction(this::handle);
        nextButton.setOnAction(this::handle);
        InfoButton.setOnAction(this::handle);
        MenuBar menuBar=new MenuBar();
        menuBar.getMenus().add(menu);
        pane.getChildren().addAll(unitType,menuBar, BuyButton,Pointslabel,InfoButton,nextButton,numOfPlayer);


        Scene scene=new Scene(pane,300,300);
        Stage stage=new Stage();
        stage.setScene(scene);
        prevStage=stage;


        return stage;

    }






    @Override
    public void handle(Event event) {

    if(event.getSource()==menu.getItems().get(0))
    {
        unitType.setText("RifleMan");

    }

        if(event.getSource()==menu.getItems().get(1))
        {
            unitType.setText("Tank");

        }
        if(event.getSource()==menu.getItems().get(2))
        {
            unitType.setText("Sniper");

        }

        if(event.getSource()==BuyButton)
        {
            if(unitType.getText()=="Tank")
            {
                System.out.print(1);
            }
        }
        if(event.getSource()==InfoButton)
        {

        }

        if(event.getSource()==nextButton)
        {

            Stage MyNewStage=new Stage();
            SetTactics setTactics=new SetTactics();
            MyNewStage=setTactics.Build();
            prevStage.hide();
            MyNewStage.show();
        }






    }
}
