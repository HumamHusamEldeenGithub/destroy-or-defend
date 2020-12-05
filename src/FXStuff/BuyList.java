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
import java.util.Iterator;
import java.util.Set;


public class BuyList implements EventHandler {

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

    Stage BuildBuyList( int id)
    {
        //ImageView imageView=new ImageView("\\Images\\Black.jpg");
        AllUnits=UnitFactory.GetUnitsInfo();
        Coins=NumOfPlayers.Players.get(id-1).GetCoins();

        for(UnitType unitType:AllUnits.keySet())
        {
            MenuItem menuItem=new MenuItem(unitType.toString());
            menu.getItems().add(menuItem);
            menuItem.setOnAction(this::handle);

        }

        String playerType=NumOfPlayers.Players.get(id-1).GetType().toString();
       String playerId=String.valueOf(id);
        String temp=playerType+":"+playerId;

        numOfPlayer.setText(temp);

        numOfPlayer.setLayoutX(200);
        numOfPlayer.setLayoutY(10);

        //imageView.setFitHeight(300);
        //imageView.setFitWidth(300);

        Coinslabel=new Label("Coins:"+Coins);

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

        for(MenuItem item : menu.getItems()){
            if(event.getSource() == item){
                unitType.setText(item.getText());
            }
        }

        if(event.getSource()==BuyButton)
        {
            if(unitType.getText()=="")
            {
                ErrorMessage errorMessage=new ErrorMessage();
                errorMessage.PrintError("You need to choose a unit first");
            }
            else
            {
                if(getPrice(unitType.getText())>Coins)
                {
                    ErrorMessage errorMessage=new ErrorMessage();
                    errorMessage.PrintError("You dont have enough money");
                }
                else
                {
                    Coins-=getPrice(unitType.getText());
                    Coinslabel.setText("Coins="+Coins);
                    NumOfPlayers.Players.get(ID-1).BuyUnit(UnitType.valueOf(unitType.getText()));
                }
            }

        }
        if(event.getSource()==InfoButton)
        {
            if(unitType.getText()=="")
            {
                ErrorMessage errorMessage=new ErrorMessage();
                errorMessage.PrintError("You need to choose a unit first");
            }
            else
            {
                UnitsInfo unitsInfo=new UnitsInfo();
                Stage MyNewStage=new Stage();
                MyNewStage=unitsInfo.Build(unitType.getText(),AllUnits);
                MyNewStage.show();
            }
            //make an object of units Info
        }
        if(event.getSource()==nextButton)
        {
           prevStage.close();
        }
    }
}
