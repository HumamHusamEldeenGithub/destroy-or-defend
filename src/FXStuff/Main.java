package FXStuff;


import Strategies.LowestHealthAttackStrategy;
import Utilitiy.Position;
import Utilitiy.StopWatch;
import gameManager.DoDGameManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import player.Player;
import player.PlayerType;
import unit.Unit;
import unit.UnitFactory;
import unit.UnitType;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       /* Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/

        MainMenu mainMenu=new MainMenu();
        primaryStage=mainMenu.BuildMainMenu();
        primaryStage.show();
    }


    public static void main(String[] args) {
        //Arena.PrintArena();
        DoDGameManager.Initialize();
        List<Player> players = new ArrayList<Player>();
        Player player1 = new Player(PlayerType.Attacker,1, LowestHealthAttackStrategy.getObj());
        Player player2 = new Player(PlayerType.Defender,2,LowestHealthAttackStrategy.getObj());
        player1.BuyUnit(UnitType.BlackEagle);
        player2.BuyUnit(UnitType.MainBase);
        //System.out.println(player1.GetUnits().size());
        Unit unit1 = player1.GetUnits().get(0);
        unit1.SetPosition(new Position(0,0));
        Unit bl = null;
        Unit unit2 = player2.GetUnits().get(0);
        unit2.SetPosition(new Position(20,20));
        players.add(player1);
        players.add(player2);
        DoDGameManager.InitializePlayers(players);
        DoDGameManager.StartGame();
        bl = unit1;
        StopWatch watch = new StopWatch();
        watch.Start();
        DoDGameManager.AddStopWatch(watch);
        try{
            Thread.sleep(1000);
        }
        catch (Exception e){

        }
        DoDGameManager.Pause_Unpause();
        DoDGameManager.UpdateGame();
        try{

        }
        catch (Exception e){
            
        }
        //System.out.println(bl.GetPosition());
        launch(args);

    }
}
