package GUI;

import gameManager.DoDGameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Pair;
import unit.UnitFactory;
import unit.UnitType;
import unitProperty.HealthUnitProperty;


import java.io.IOException;

public class ShopMenu {
    static int margin = 20 ;
    static int firstPixel_X = 60 ;
    static int firstPixel_Y = 150 ;
    final int Radius =50 ;
    static int  playerID =0 ;
    static UnitType LastClickedType ;
    public void initialize()
    {
        LastClickedType = null ;
        this.DrawUnitsCircles() ;
        this.getPlayerId();
        this.getCoins();
    }

    @FXML
    private AnchorPane rootAnchor;
    @FXML
    private Pane UnitsPane;
    @FXML
    private Pane UnitsProperties;
    @FXML
    private Label PlayerID;

    @FXML
    private Label Coins;


    public void DrawUnitsCircles()
    {
        int x = 0;
        int y =60 ;
        for (UnitType unitType : UnitFactory.UnitsInfo.keySet()) {
            Pair<Integer,Integer> dim  = GetDimensions(x,y) ;
            x = dim.getKey() ;
            y = dim.getValue();
            Circle circle = new Circle(x,y, Radius);
            circle.setId(unitType.toString());
            circle.addEventFilter(MouseEvent.MOUSE_CLICKED,this::showUnitInfo);
            UnitsPane.getChildren().add(circle);
        }
    }

    public void showUnitInfo(MouseEvent mouseEvent) {
        /*String source1 = mouseEvent.getSource().toString(); //yields complete string
        String source2 = mouseEvent.getPickResult().getIntersectedNode().getId(); //returns JUST the id of the object that was clicked
        mouseEvent.getPickResult().getIntersectedNode().setTranslateX(mouseEvent.getPickResult().getIntersectedNode().getLayoutX()+0.05);
        //System.out.println("Full String: " + source1);
        System.out.println(source1);
        System.out.println(source2);*/
        UnitType unitType = UnitType.valueOf(mouseEvent.getPickResult().getIntersectedNode().getId()) ;
        LastClickedType = unitType ;
        String[] info  =  UnitFactory.UnitsInfo.get(unitType) ;
        String[] PropNames = UnitFactory.getUnitPropertiesNames() ;
        int i =0 ;
        for (Node node : UnitsProperties.getChildren())
        {
            if (Label.class.isInstance(node)) {
                Label label = (Label) node;
                if (i == 0) {
                    label.setText(PropNames[i] + " : " + unitType.toString());
                    i++;
                    continue;
                }
                label.setText(PropNames[i] + " : " + info[i - 1]);
                i++;
            }

        }

    }

    public void getPlayerId ()
    {
        PlayerID.setText(DoDGameManager.Players.get(ShopMenu.playerID).GetType().toString() + " id:"+ShopMenu.playerID) ;
    }
    public void getCoins()
    {
        Coins.setText("Coins :"+String.valueOf(DoDGameManager.Players.get(ShopMenu.playerID).GetCoins()));
    }

    public void NextScene(ActionEvent actionEvent) throws IOException {
        if (ShopMenu.playerID== DoDGameManager.Players.size()-1)
            GUIManager.ChangeScene(rootAnchor,Scene.Strategy);
        else
        {
            ShopMenu.playerID++ ;
            GUIManager.ChangeScene(rootAnchor,Scene.ShopMenu);
        }
    }
    public void BuyUnit(ActionEvent actionEvent) throws IOException {
        if(ShopMenu.LastClickedType!=null) {

            if (DoDGameManager.Players.get(ShopMenu.playerID).BuyUnit(LastClickedType)) {
                Coins.setText("Coins :"+String.valueOf(DoDGameManager.Players.get(ShopMenu.playerID).GetCoins()));
            }
            else
            {
                ErrorMessage errorMessage = new ErrorMessage() ;
                errorMessage.PrintError("You Don't Have Enough Coins ! ");
            }
        }

    }

    public Pair<Integer, Integer> GetDimensions(int x ,int y )
    {
        if (x+ Radius *3 +margin>UnitsPane.getPrefWidth())
            return new Pair<Integer, Integer>(firstPixel_X,y+firstPixel_Y) ;
        else if (x==0)
            return new Pair<Integer, Integer>(firstPixel_X,y) ;
        else
            return new Pair<Integer, Integer>(x+Radius*2 + margin,y) ;
    }
}
