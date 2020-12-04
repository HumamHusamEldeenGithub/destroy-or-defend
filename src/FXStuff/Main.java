package FXStuff;


import Strategies.HighestDamageAttackStrategy;
import Strategies.LowestHealthAttackStrategy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Arena.*;
import player.Player;
import player.PlayerType;
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
        Grid grid = Grid.GetGrid();
        grid.Initialize(100,200);
        UnitFactory factory = UnitFactory.GetObj();
        UnitFactory.LoadData();
        List<Player> players = new ArrayList<Player>();
        Player player1 = new Player(PlayerType.Attacker,1, HighestDamageAttackStrategy.getObj());
        Player player2 = new Player(PlayerType.Defender,2,LowestHealthAttackStrategy.getObj());
        player1.BuyUnit(UnitType.BlackEagle);
        launch(args);
    }
}
