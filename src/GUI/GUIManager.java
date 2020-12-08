package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import player.Player;

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
        }

    public static void main(String[] args) {
        launch(args);
    }
}
