package FXStuff;

import Arena.Arena;
import Enums.UnitType;
import Game.Game;
import Tactics.RandomlyTactic;
import Units.Forces;
import Units.Unit;
import Utilities.Position;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {

        Unit solder = new Forces(UnitType.RifleSoldier,new RandomlyTactic());
        solder.SetPos(0,0);
        Unit solder2 = new Forces(UnitType.RifleSoldier,new RandomlyTactic());
        solder2.SetPos(8,8);
        //Arena.Deploy(solder , new Position(0,0));
        Arena.BasePosition = new Position(4,4);
        Game game = new Game();
        List<Game.Team> teams = game.GetTeamList();
        teams.get(0).AddPlayer();
        teams.get(0).GetPlayerList().get(0).AddUnit(solder2,new Position(8,8));
        teams.get(0).GetPlayerList().get(0).AddUnit(solder,new Position(0,0));

        teams.get(0).GetPlayerList().get(0).Start();

        //Arena.PrintArena();
        //launch(args);
    }
}
