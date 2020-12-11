package GUI;


import Strategies.LowestHealthAttackStrategy;
import Utilitiy.Position;
import Utilitiy.TerrainGenerator;
import gameManager.DoDGameManager;
import gameManager.GameState;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import player.Player;
import player.PlayerType;
import unit.Unit;
import unit.UnitType;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GUIManager.GetRootLinks();
        Parent root = FXMLLoader.load(getClass().getResource(GUIManager.Pages.get(WindowType.StartGame)));
        primaryStage.setTitle("Destroy Or Defend");
        primaryStage.setScene(new javafx.scene.Scene(root, 1000  , 800));
//        primaryStage=newArena.Build();
        primaryStage.show();
//        MainMenu mainMenu=new MainMenu();
//        primaryStage=mainMenu.BuildMainMenu();
//        primaryStage.show();
    }
    static DoDGameManager manager;
    public static void main(String[] args) {
        //Arena.PrintArena();

        manager = DoDGameManager.getObj();
        DoDGameManager.Initialize();

        List<Player> players = new ArrayList<Player>();
        Player player1 = new Player(PlayerType.Attacker,1, LowestHealthAttackStrategy.getObj());
        Player player2 = new Player(PlayerType.Defender,2,LowestHealthAttackStrategy.getObj());
        player1.BuyUnit(UnitType.PrismTank);
        player2.BuyUnit(UnitType.MainBase);
        //System.out.println(player1.GetUnits().size());
        Unit unit1 = player1.GetUnits().get(0);
        unit1.SetPosition(new Position(79,650));
        Unit bl = null;
        Unit unit2 = player2.GetUnits().get(0);
        unit2.SetPosition(new Position(70,60));
        players.add(player1);
        players.add(player2);
        DoDGameManager.InitializePlayers(players);
        DoDGameManager.StartGame();

        while (DoDGameManager.GetState()!= GameState.AttackerWon && DoDGameManager.GetState() != GameState.DefenderWon)
            DoDGameManager.UpdateGame();
        System.out.println("wa2el");

        TerrainGenerator terrainGenerator = new TerrainGenerator();
        terrainGenerator.GenerateTerrain();
        //launch(args);

       //Grid.Print();
    }
}
