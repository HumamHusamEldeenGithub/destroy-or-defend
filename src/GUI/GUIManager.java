package GUI;

import Utilitiy.Position;
import Utilitiy.TerrainGenerator;
import gameManager.DoDGameManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Pair;
import player.Player;
import Arena.* ;
import unit.Unit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GUIManager extends Application {
    static HashMap<WindowType,String> Pages = new HashMap<>();
    static ArrayList<Player> Players=new ArrayList<Player>();

    static int margin = 20 ;
    static int firstPixel_X = 60 ;
    static int firstPixel_Y = 100 ;
    static int Radius =25 ;

    static boolean zoomIn = false ;
    static List<Player>players = new ArrayList<>() ;
    static List<Unit> AllUnits =new ArrayList<>() ;
    static boolean[] flag;

    static int borderWidth = 200  ;
    static int borderHeight = 200 ;
    static int ScaleUp = 4 ;



        @Override
        public void start(Stage primaryStage) throws Exception{
            GetRootLinks() ;
            Parent root = FXMLLoader.load(getClass().getResource(Pages.get(WindowType.StartGame)));
            primaryStage.setTitle("Destroy Or Defend");
            primaryStage.setScene(new javafx.scene.Scene(root, 1000  , 800));

            primaryStage.show();

        }
        public static void ChangeScene (AnchorPane oldPane , WindowType scene) throws IOException {
            AnchorPane pane = FXMLLoader.load(GUIManager.class.getResource(Pages.get(scene)));
            oldPane.getChildren().setAll(pane) ;
        }
        static void GetRootLinks()
        {
            Pages.put(WindowType.StartGame,"FXML/StartGame.fxml") ;
            Pages.put(WindowType.About , "FXML/About.fxml") ;
            Pages.put(WindowType.PlayerCounter , "FXML/PlayersCounter.fxml") ;
            Pages.put(WindowType.ShopMenu , "FXML/ShopMenu.fxml") ;
            Pages.put(WindowType.Strategy , "FXML/SelectStrategy.fxml") ;
            Pages.put(WindowType.SetUnits , "FXML/SetUnits.fxml") ;

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
                    Circle circle = new Circle(1);
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
    static DoDGameManager manager;
    public static void main(String[] args) {
        manager = DoDGameManager.getObj();
        DoDGameManager.Initialize();
        TerrainGenerator terrainGenerator = new TerrainGenerator();
        terrainGenerator.GenerateTerrain();
        launch(args);
    }
}
