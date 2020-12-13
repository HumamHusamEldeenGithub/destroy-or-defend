package GUI;

import Utilitiy.Position;
import gameManager.DoDGameManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Pair;
import player.PlayerType;
import unit.Unit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Arena.* ;
import unit.UnitFactory;

public class SetUnits {
    static int id = 0 ;
    static Circle SelectedCircle= null ;
    List<Unit>playerUnits=new ArrayList<>();


    @FXML
    private AnchorPane rootAnchor;

   @FXML
    private Pane Map ;

    @FXML
    private Pane UnitsPane;
    @FXML
    private Label PlayerId;


    @FXML
    private Pane P1;

    public void initialize(){
       // this.getPlayerId();
        getPlayerId();
        GUIManager.GenerateWorld(this.Map);
        Map.setOnMouseClicked(ZoomIn);
        this.DrawUnitsCircles(UnitsPane); ;
        playerUnits=GUIManager.Players.get(id).GetUnits();
        DrawUnitsOnArena();
    }
    public void DrawUnitsCircles(Pane pane)  {
        int x = 0;
        int y =60 ;
        List<Unit>playerUnits=new ArrayList<>();
        playerUnits=GUIManager.Players.get(id).GetUnits();
        for (int i =0 ; i<playerUnits.size() ; i++){
            Unit unit = playerUnits.get(i) ;
            Pair<Integer,Integer> dim  = GetDimensions(x,y) ;
            x = dim.getKey() ;
            y = dim.getValue();
            Circle circle = new Circle(x,y, GUIManager.Radius);
            circle.setId(i+"");
            circle.setFill(new ImagePattern(new Image("\\Images\\"+ UnitFactory.GetImagePath(unit.GetType()))));
            circle.setOnMouseClicked(OnSelectedCircleHandler);
            pane.getChildren().add(circle);
        }
    }

    public void getPlayerId ()
    {
        PlayerId.setText(GUIManager.Players.get(SetUnits.id).GetType().toString() + "  id : "+SetUnits.id); ;
    }

    public Pair<Integer, Integer> GetDimensions(int x , int y )
    {
        if (x+ GUIManager.Radius *3 +GUIManager.margin>UnitsPane.getPrefWidth())
            return new Pair<Integer, Integer>(GUIManager.firstPixel_X,y+GUIManager.firstPixel_Y) ;
        else if (x==0)
            return new Pair<Integer, Integer>(GUIManager.firstPixel_X,y) ;
        else
            return new Pair<Integer, Integer>(x+GUIManager.Radius*2 + GUIManager.margin,y) ;
    }

    EventHandler<MouseEvent> ZoomIn =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!GUIManager.zoomIn) {
                        double BorderWidthScaleUp = 0, BorderHeightScaleUp = 0, LayOutX = 0, LayOutY = 0;
                        BorderWidthScaleUp = 2 * (GUIManager.ScaleUp - 2) * GUIManager.borderWidth;
                        BorderHeightScaleUp = 2 * (GUIManager.ScaleUp - 2) * GUIManager.borderHeight;
                        LayOutX = 2 * (GUIManager.ScaleUp - 1) * GUIManager.borderWidth;
                        LayOutY = 2 * (GUIManager.ScaleUp - 1) * GUIManager.borderHeight;
                        double x = (mouseEvent.getX() - ((float) GUIManager.borderWidth / 2.0f));
                        double y = (mouseEvent.getY()) - ((float) GUIManager.borderHeight / 2.0f);
                       // System.out.println(x + " " + y);
                        double MapNewWidth = 0 ,MapNewHeight=0 , newLayouytX =0 , newLayoutY=0 ;
                        MapNewWidth = Map.getPrefWidth()/GUIManager.borderWidth * BorderWidthScaleUp ;
                        MapNewHeight = Map.getPrefHeight()/GUIManager.borderHeight * BorderHeightScaleUp ;
                        Map.setScaleX(GUIManager.ScaleUp);
                        Map.setScaleY(GUIManager.ScaleUp);
                        newLayouytX = getLayout(LayOutX - ((x / (float) GUIManager.borderWidth) * BorderWidthScaleUp)) ;
                        newLayoutY = getLayout(LayOutY - ((y / (float) GUIManager.borderHeight) * BorderHeightScaleUp)) ;
                        Map.setLayoutX(newLayouytX);
                        Map.setLayoutY(newLayoutY);
                        GUIManager.zoomIn = true;
                    }
                }
            };
                EventHandler<MouseEvent> OnSelectedCircleHandler =
                        new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (GUIManager.zoomIn) {
                        if (SetUnits.SelectedCircle!=null)
                            RawShape(SetUnits.SelectedCircle) ;
                        SetUnits.SelectedCircle = (Circle) mouseEvent.getSource();
                        SetUnits.SelectedCircle.setOpacity(0.5);
                    }

                }
            };

    public double getLayout(double x )
    {
        if (x<-1200)
            return -1200 ;
        if (x>1200)
            return 1200 ;
        return x ;
    }
    public void GetPosition(MouseEvent mouseEvent) {
            if (SetUnits.SelectedCircle != null) {
                TerrainType terrainType = Grid.GetTerrain(new Position((int)mouseEvent.getX(),(int)mouseEvent.getY()) );
                if (terrainType==TerrainType.Valley) {
                    ErrorMessage errorMessage = new ErrorMessage();
                    errorMessage.PrintError("You can't put units on Vally .. try another position .");
                }
                else {
                    UnitsPane.getChildren().remove(SetUnits.SelectedCircle);
                    RawShape(SetUnits.SelectedCircle);
                    Map.getChildren().add(SetUnits.SelectedCircle);
                    SetUnits.SelectedCircle.setCenterX(mouseEvent.getX());
                    SetUnits.SelectedCircle.setCenterY(mouseEvent.getY());
                    SetUnits.SelectedCircle.setRadius((playerUnits.get(Integer.parseInt(SelectedCircle.getId())).GetSize().GetValue()));
                    SetUnits.SelectedCircle = null;
                }
            }
    }

    public void ZoomOut(ActionEvent actionEvent) {
        GUIManager.zoomIn = false ;
        Map.setLayoutX(0);
        Map.setLayoutY(0);
        Map.setScaleX(1);
        Map.setScaleY(1);
        //Move() ;
    }
    public void  RawShape(Node node)
    {
        node.setStyle("");
        node.setOpacity(1);
    }

    public void Done(ActionEvent actionEvent) throws IOException {
        GUIManager.zoomIn=false ;
        if (AllUnitsOnArena()) {
            for (Node node : Map.getChildren()) {
                if (node instanceof Circle && node.getId() != null) {
                    Circle circle = (Circle) node;
                    Unit unit = playerUnits.get(Integer.parseInt(circle.getId()));
                    unit.SetPosition(new Position((int) circle.getCenterX(), (int) circle.getCenterY()));
                    GUIManager.AllUnits.add(unit);
                }
            }
            if (SetUnits.id == GUIManager.Players.size() - 1) {
                DoDGameManager.InitializePlayers(GUIManager.Players);
                DoDGameManager.StartGame();
                Stage MyNewStage = new Stage();
                Arena Arena = new Arena();
                MyNewStage = Arena.Build();
                Arena.start();
                MyNewStage.showAndWait();

            } else {
                SetUnits.id++;
                GUIManager.ChangeScene(rootAnchor, WindowType.SetUnits);
            }
        }
        else {
            ErrorMessage errorMessage = new ErrorMessage() ;
            errorMessage.PrintError("You Must put all the units on Arena ! ");
        }

    }
    boolean AllUnitsOnArena()
    {
        for (Node node : UnitsPane.getChildren())
        {
            if (node instanceof Circle&&node.getId()!=null)
                return false ;
        }
        return true ;
    }
    public void DrawUnitsOnArena()
    {
        Map.setScaleX(GUIManager.ScaleUp);
        Map.setScaleY(GUIManager.ScaleUp);
        for (Unit unit :GUIManager.AllUnits)
        {
            Circle  circle = new Circle();
            Map.getChildren().add(circle) ;
            circle.setCenterX(unit.GetPosition().Get_X());
            circle.setCenterY(unit.GetPosition().Get_Y());
            circle.setRadius((int)unit.GetSize().GetValue());
            circle.setFill(new ImagePattern(new Image("\\Images\\"+ UnitFactory.GetImagePath(unit.GetType()))));
        }
        Map.setScaleX(1);
        Map.setScaleY(1);
    }


}
