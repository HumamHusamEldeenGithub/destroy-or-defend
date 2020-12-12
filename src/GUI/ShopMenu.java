package GUI;

import GUI.GUIManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.util.Pair;
import player.PlayerType;
import unit.AttackerLogic;
import unit.Unit;
import unit.UnitFactory;
import unit.UnitType;


import java.io.IOException;

public class ShopMenu {
    static int margin = 20 ;
    static int firstPixel_X = 60 ;
    static int firstPixel_Y = 150 ;
    final int Radius =50 ;
    static int  playerID =0 ;
    static Node LastClickedType ;
    static boolean BaseHasBeenPurchased =false;
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
            if (ShopRestriction(unitType)) {
                    Pair<Integer, Integer> dim = GetDimensions(x, y);
                    x = dim.getKey();
                    y = dim.getValue();
                    Circle circle = new Circle(x, y, Radius);
                    circle.setId(unitType.toString());
                    circle.addEventFilter(MouseEvent.MOUSE_CLICKED, this::showUnitInfo);
                    circle.setFill(new ImagePattern(new Image("\\Images\\Wa2el_CanonBig.png")));
                    UnitsPane.getChildren().add(circle);

            }
        }
    }

    boolean ShopRestriction(UnitType unitType)
    {
        if (GUIManager.Players.get(ShopMenu.playerID).GetType()==PlayerType.Attacker)
            return !(UnitFactory.GetMovementSpeed(unitType)==0 );
        else
        {
            return unitType != UnitType.MainBase || !ShopMenu.BaseHasBeenPurchased;
        }

    }

    public void showUnitInfo(MouseEvent mouseEvent) {
        Node ClickedNode = (Node) mouseEvent.getSource();
        ClickedNode.setOpacity(0.5);
        UnitType unitType = UnitType.valueOf(ClickedNode.getId()) ;
        if (LastClickedType!=null)
            LastClickedType.setOpacity(1);
        LastClickedType = ClickedNode ;
        String[] info  =  UnitFactory.UnitsInfo.get(unitType) ;
        String[] PropNames = UnitFactory.getUnitPropertiesNames() ;
        int i =0 ;
        for (Node node : UnitsProperties.getChildren())
        {
            if (Label.class.isInstance(node)) {
                Label label = (Label) node;
                if (i == 0) {
                    label.setText(PropNames[i] + " : " + unitType.toString());
                    label.setWrapText(true);
                    label.setTextAlignment(TextAlignment.JUSTIFY);
                    i++;
                    continue;
                }
                label.setText(PropNames[i] + " : " + info[i - 1]);
                label.setWrapText(true);
                label.setTextAlignment(TextAlignment.JUSTIFY);
                i++;
            }

        }

    }

    public void getPlayerId ()
    {
        PlayerID.setText(GUIManager.Players.get(ShopMenu.playerID).GetType().toString() + " id:"+ShopMenu.playerID) ;
    }
    public void getCoins()
    {
        Coins.setText("Coins :"+String.valueOf(GUIManager.Players.get(ShopMenu.playerID).GetCoins()));
    }

    public void NextScene(ActionEvent actionEvent) throws IOException {
        if (ShopMenu.playerID==GUIManager.Players.size()-1)
            GUIManager.ChangeScene(rootAnchor, WindowType.SetUnits);
        else if (ShopMenu.playerID== PlayersCounter.DefenderNumber-1&&!ShopMenu.BaseHasBeenPurchased)
        {
            ErrorMessage errorMessage = new ErrorMessage() ;
            errorMessage.PrintError("You Must Purchase a MainBase ! ");
        }
        else if (ShopMenu.playerID!= GUIManager.Players.size()-1)
        {
            ShopMenu.playerID++ ;
            GUIManager.ChangeScene(rootAnchor, WindowType.ShopMenu);
        }

    }
    public void BuyUnit(ActionEvent actionEvent) throws IOException {
        if(ShopMenu.LastClickedType!=null) {

            if (GUIManager.Players.get(ShopMenu.playerID).BuyUnit(UnitType.valueOf(LastClickedType.getId()))) {
                Coins.setText("Coins :"+String.valueOf(GUIManager.Players.get(ShopMenu.playerID).GetCoins()));
                if (UnitType.valueOf(LastClickedType.getId())==UnitType.MainBase) {
                    LastClickedType.setDisable(true);
                    LastClickedType = null ;
                    ShopMenu.BaseHasBeenPurchased = true;
                }

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
