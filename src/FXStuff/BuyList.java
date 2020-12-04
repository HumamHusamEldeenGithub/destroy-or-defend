package FXStuff;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import player.Player;
import unit.*;

import java.util.HashMap;


public class BuyList implements EventHandler {

//////////////////
    Pane pane =new Pane();
    Menu menu =new Menu("UNITS");
    Button BuyButton=new Button("Buy");
    MenuItem tempItem=new MenuItem();
    Label unitType=new Label("");
    Button InfoButton=new Button("Info");
    Button nextButton=new Button("Next");
    int Coins;//GetFromPLayer
    Label Coinslabel=new Label();
    Label numOfPlayer= new Label();
    Stage prevStage;
    HashMap<UnitType,String[]> AllUnits=new HashMap<>();
    int ID;
    ///////////////////////

    Stage BuildBuyList( int id)
    {
        //ImageView imageView=new ImageView("\\Images\\Black.jpg");

        AllUnits=UnitFactory.GetUnitsInfo();
        Coins=NumOfPlayers.Players.get(id).GetCoins();
        /*for(UnitType unitType:AllUnits.keySet())
        {
            MenuItem menuItem=new MenuItem(unitType.toString());
            menu.getItems().add(menuItem);
            menuItem.setOnAction(this::handle);


        }*/
        String playerType=NumOfPlayers.Players.get(id).GetType().toString();
        String playerId=String.valueOf(id);
        String temp=playerType+playerId;



        numOfPlayer.setText(temp);


        numOfPlayer.setLayoutX(200);
        numOfPlayer.setLayoutY(10);

        //imageView.setFitHeight(300);
        //imageView.setFitWidth(300);

        Coinslabel=new Label("Points:"+Coins);



        Coinslabel.setLayoutX(150);
        Coinslabel.setLayoutY(200);


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
        pane.getChildren().addAll(unitType,menuBar, BuyButton,Coinslabel,InfoButton,nextButton,numOfPlayer);

        Scene scene=new Scene(pane,300,300);
        Stage stage=new Stage();
        stage.setScene(scene);
        prevStage=stage;
        ID=id;

        return stage;

    }


    int getPrice(String unitType)
    {
        UnitType unitType1=UnitType.valueOf(unitType);
        String[] price=AllUnits.get(unitType1);
        return Integer.parseInt(price[8]);
    }







    @Override
    public void handle(Event event) {

    if(event.getSource()==menu.getItems().get(0))
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
        }


        if(event.getSource()==BuyButton)
        {
            if(unitType.getText()=="")
            {
                ErrorMessage errorMessage=new ErrorMessage();
                errorMessage.PrintError();
            }
            else
            {
                if(getPrice(unitType.getText())>Coins)
                {
                    CoinsMessage coinsMessage=new CoinsMessage();
                    coinsMessage.print();
                }
                else
                {
                    Coins-=getPrice(unitType.getText());
                    NumOfPlayers.Players.get(ID).BuyUnit(UnitType.valueOf(unitType.getText()));



                }
            }







            Coins--;
            Coinslabel.setText("Coins="+Coins);
        }
        if(event.getSource()==InfoButton)
        {
            if(unitType.getText()=="")
            {
                ErrorMessage errorMessage=new ErrorMessage();
                errorMessage.PrintError();
            }
            else
            {

            }
            //make an object of units Info

        }

        if(event.getSource()==nextButton)
        {

           prevStage.close();

        }






    }
}
