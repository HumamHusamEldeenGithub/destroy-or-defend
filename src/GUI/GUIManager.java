package GUI;

import Utilitiy.Position;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import player.Player;
import Arena.* ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GUIManager extends Application {
    static HashMap<Scene,String> Pages = new HashMap<>();
    static ArrayList<Player> Players=new ArrayList<Player>();
        @Override
        public void start(Stage primaryStage) throws Exception{
            GetRootLinks() ;
            Parent root = FXMLLoader.load(getClass().getResource(Pages.get(Scene.StartGame)));
            primaryStage.setTitle("Destroy Or Defend");
            primaryStage.setScene(new javafx.scene.Scene(root, 1000  , 800));
            primaryStage.show();
        }
        public static void ChangeScene (AnchorPane oldPane , Scene scene) throws IOException {
            AnchorPane pane = FXMLLoader.load(GUIManager.class.getResource(Pages.get(scene)));
            oldPane.getChildren().setAll(pane) ;
        }
        static void GetRootLinks()
        {
            Pages.put(Scene.StartGame,"FXML/StartGame.fxml") ;
            Pages.put(Scene.About , "FXML/About.fxml") ;
            Pages.put(Scene.PlayerCounter , "FXML/PlayersCounter.fxml") ;
            Pages.put(Scene.ShopMenu , "FXML/ShopMenu.fxml") ;
            Pages.put(Scene.Strategy , "FXML/SelectStrategy.fxml") ;
            Pages.put(Scene.SetUnits , "FXML/SetUnits.fxml") ;
            Pages.put(Scene.Arena , "FXML/Arena.fxml") ;
        }
    static void  GenerateWorld(Pane pane)
    {
        for (int i = 0; i< Grid.CellNum ; i++)
            for (int j = 0; j< Grid.CellNum ; j++)
            {
                TerrainType terrainType = Grid.GetTerrain(new Position(i,j));
                if (terrainType==null)
                    continue;
                if (terrainType== TerrainType.River) {
                    Circle circle = new Circle(4);
                    circle.setFill(Color.BLUE);
                    circle.setCenterX(i);
                    circle.setCenterY(j);
                    pane.getChildren().add(circle);

                }
                else if (terrainType== TerrainType.Valley) {
                    Circle circle = new Circle(4);
                    circle.setFill(Color.DARKGREEN);
                    circle.setCenterX(i);
                    circle.setCenterY(j);
                    pane.getChildren().add(circle);
                }
                else if (terrainType== TerrainType.Bridge) {
                    Circle circle = new Circle(4);
                    circle.setFill(Color.BLACK);
                    circle.setCenterX(i);
                    circle.setCenterY(j);
                    pane.getChildren().add(circle);
                }
            }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
