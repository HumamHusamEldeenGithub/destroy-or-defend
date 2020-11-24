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
        

        //Arena.PrintArena();
        //launch(args);
    }
}
