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
import unit.Unit;
import unit.*;

import java.util.HashMap;


public class BuyList implements EventHandler {


    Pane pane =new Pane();
    Menu menu =new Menu("UNITS");
    Button BuyButton=new Button("Buy");
    MenuItem tempItem=new MenuItem();
    Label unitType=new Label("");
    Button InfoButton=new Button("Info");
    Button nextButton=new Button("Next");

    int Points;//GetFromPLayer
    Label Pointslabel=new Label("Points:"+Points);
    Label numOfPlayer= new Label();
    Stage prevStage;
    HashMap<UnitType,String[]> AllUnits=new HashMap<>();

    Stage BuildBuyList(int num,String TypeofTeam)
    {
        //ImageView imageView=new ImageView("\\Images\\Black.jpg");
        AllUnits=UnitFactory.GetUnitsInfo();
        /*for(UnitType unitType:AllUnits.keySet())
        {
            MenuItem menuItem=new MenuItem(unitType.toString());
            menu.getItems().add(menuItem);
            menuItem.setOnAction(this::handle);


        }*/
        if(TypeofTeam=="Attacker")
        numOfPlayer.setText("Attacker:"+num);
        else
            numOfPlayer.setText("Defender:"+num);


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

   /* if(event.getSource()==menu.getItems().get(0))
    {
        unitType.setText("TeslaTank");

    }

        if(event.getSource()==menu.getItems().get(1))
        {
            unitType.setText("Sniper");

        }
        if(event.getSource()==menu.getItems().get(2))
        {
            unitType.setText("MirageTank");

        }
        if(event.getSource()==menu.getItems().get(3))
        {
            unitType.setText("Infantry");

        }


        if(event.getSource()==menu.getItems().get(4))
        {
            unitType.setText("GrizzlyTank");
        }
        if(event.getSource()==menu.getItems().get(5))
        {
            unitType.setText("NavySeal");
        }
        if(event.getSource()==menu.getItems().get(6))
        {
            unitType.setText("TankDestroyer");
        }
        if(event.getSource()==menu.getItems().get(7))
        {
            unitType.setText("PrismTank");
        }
        if(event.getSource()==menu.getItems().get(8))
        {
            unitType.setText("Pillbox");
        }
        if(event.getSource()==menu.getItems().get(9))
        {
            unitType.setText("PrismTower");
        }
        if(event.getSource()==menu.getItems().get(10))
        {
            unitType.setText("GrandCannon");
        }
        if(event.getSource()==menu.getItems().get(11))
        {
            unitType.setText("MainBase");
        }
        if(event.getSource()==menu.getItems().get(12))
        {
        unitType.setText("BlackEagle");
        }
        if(event.getSource()==menu.getItems().get(13))
        {
        unitType.setText("PatriotMissileSystem")    ;
        }*/


        if(event.getSource()==BuyButton)
        {

        }
        if(event.getSource()==InfoButton)
        {

        }

        if(event.getSource()==nextButton)
        {

           prevStage.close();

        }






    }
}
