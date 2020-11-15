package Utilities;

import Arena.Arena;
import Enums.UnitType;
import Units.Forces;
import Units.Unit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args){
        Unit soldier = new Forces(UnitType.RifleSoldier) ;
        Arena.Deploy(soldier,new Position(0,0)) ;
        Arena.Deploy(soldier,new Position(1,0)) ;
        Arena.Deploy(soldier,new Position(0,1)) ;
        Arena.Deploy(soldier,new Position(1,1)) ;
        Arena.PrintArena();
       // Arena.BasePosition

    }
}
