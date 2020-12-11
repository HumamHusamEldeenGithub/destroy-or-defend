package GUI;

import Utilitiy.Position;
import gameManager.DoDGameManager;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import unit.Unit;
import unit.UnitFactory;
import unit.UnitType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Arena.* ;

public class SetUnits {
    static int id = 0 ;
    static int margin = 20 ;
    static int firstPixel_X = 60 ;
    static int firstPixel_Y = 100 ;
    static int Radius =25 ;

    static boolean zoomIn = false ;
    static List<Unit> AllUnits =new ArrayList<>() ;
    static boolean[] flag;
    static Circle SelectedCircle= null ;
    static int borderWidth = 200  ;
    static int borderHeight = 200 ;
    static int ScaleUp = 4 ;
    List<Unit>playerUnits=new ArrayList<>();


    @FXML
    private AnchorPane rootAnchor;

//    @FXML
//    private TitledPane PlayerId;

   @FXML
    private Pane Map ;

    @FXML
    private Pane UnitsPane;
    @FXML
    private ScrollPane scrollPane ;


    @FXML
    private Pane P1;

    public void initialize(){
       // this.getPlayerId();
        GUIManager.GenerateWorld(this.Map);
        Map.setOnMouseClicked(ZoomIn);
        this.DrawUnitsCircles(UnitsPane); ;
        playerUnits=DoDGameManager.Players.get(id).GetUnits();
        DrawUnitsOnArena();
    }
    public void DrawUnitsCircles(Pane pane)  {
        int x = 0;
        int y =60 ;
        List<Unit>playerUnits=new ArrayList<>();
        playerUnits=DoDGameManager.Players.get(id).GetUnits();
        for (int i =0 ; i<playerUnits.size() ; i++){
            Unit unit = playerUnits.get(i) ;
            Pair<Integer,Integer> dim  = GetDimensions(x,y) ;
            x = dim.getKey() ;
            y = dim.getValue();
            Circle circle = new Circle(x,y, Radius);
            circle.setId(i+"");
            circle.setOnMouseClicked(OnSelectedCircleHandler);
            pane.getChildren().add(circle);
        }
    }

//    public void getPlayerId ()
//    {
//        PlayerId.setText(DoDGameManager.Players.get(SetUnits.id).GetType().toString() + "  id : "+SetUnits.id) ;
//    }

    public Pair<Integer, Integer> GetDimensions(int x , int y )
    {
        if (x+ Radius *3 +margin>UnitsPane.getPrefWidth())
            return new Pair<Integer, Integer>(firstPixel_X,y+firstPixel_Y) ;
        else if (x==0)
            return new Pair<Integer, Integer>(firstPixel_X,y) ;
        else
            return new Pair<Integer, Integer>(x+Radius*2 + margin,y) ;
    }

    EventHandler<MouseEvent> ZoomIn =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {
                    int LayOutX  =0 , LayOutY=0 ;
                    LayOutX = 2*(SetUnits.ScaleUp-1) * borderWidth ;
                    LayOutY = 2*(SetUnits.ScaleUp-1) * borderHeight ;
                    int x = (int) (mouseEvent.getX()/borderWidth);
                    int y = (int)mouseEvent.getY()/borderHeight ;
                   // System.out.println(x + " " + y );
                    Map.setScaleX(SetUnits.ScaleUp);
                    Map.setScaleY(SetUnits.ScaleUp);
                    Map.setLayoutX(LayOutX-(borderWidth*x*SetUnits.ScaleUp));
                    Map.setLayoutY(LayOutY-(borderHeight*y*SetUnits.ScaleUp));
                    SetUnits.zoomIn=true ;
                    //System.out.println(LayOutX-(borderWidth*x*SetUnits.ScaleUp) + " " + (LayOutY-(borderHeight*y*SetUnits.ScaleUp)));
                }
            };


                EventHandler<MouseEvent> OnSelectedCircleHandler =
                        new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (zoomIn) {
                        if (SetUnits.SelectedCircle!=null)
                            RawShape(SetUnits.SelectedCircle) ;
                        SetUnits.SelectedCircle = (Circle) mouseEvent.getSource();
                        SetUnits.SelectedCircle.setStyle("-fx-background-color : red") ;
                        SetUnits.SelectedCircle.setOpacity(0.5);
                        //System.out.println(SetUnits.SelectedCircle.getId());
                    }

                }
            };
    public void GetPosition(MouseEvent mouseEvent) {
            if (SetUnits.SelectedCircle != null) {
                System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());
                UnitsPane.getChildren().remove(SetUnits.SelectedCircle);
                RawShape(SetUnits.SelectedCircle);
                Map.getChildren().add(SetUnits.SelectedCircle);
                SetUnits.SelectedCircle.setCenterX(mouseEvent.getX());
                SetUnits.SelectedCircle.setCenterY(mouseEvent.getY());
                SetUnits.SelectedCircle.setRadius((playerUnits.get(Integer.parseInt(SelectedCircle.getId())).GetSize().GetValue()));
                SetUnits.SelectedCircle = null;
            }
    }

    public void ZoomOut(ActionEvent actionEvent) {
        SetUnits.zoomIn = false ;
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
//    public void Move() throws InterruptedException {
//        int x =1 ;
//        for (int i =0 ; i<200 ; i++)
//        {
//            SelectedCircle.setCenterX(SelectedCircle.getCenterX()+x);
//
//        }
//        System.out.println(SelectedCircle.getCenterX() + " " + SelectedCircle.getCenterY());
//    }

    public void Done(ActionEvent actionEvent) throws IOException {
        for (Node node :Map.getChildren())
        {
            if (node instanceof Circle&&node.getId()!=null)
            {
                Circle circle = (Circle)node ;
                System.out.println(circle.getId());
                Unit unit = playerUnits.get(Integer.parseInt(circle.getId())) ;
                unit.SetPosition(new Position((int)circle.getCenterX() , (int)circle.getCenterY()));
                AllUnits.add(unit);
            }
        }
        if (SetUnits.id==DoDGameManager.Players.size()-1)
            GUIManager.ChangeScene(rootAnchor,Scene.Arena);
        else
        {
            SetUnits.id++ ;
            GUIManager.ChangeScene(rootAnchor,Scene.SetUnits);
        }

    }
    public void DrawUnitsOnArena()
    {
        Map.setScaleX(SetUnits.ScaleUp);
        Map.setScaleY(SetUnits.ScaleUp);
        for (Unit unit :AllUnits)
        {
            Circle  circle = new Circle();
            Map.getChildren().add(circle) ;
            circle.setCenterX(unit.GetPosition().Get_X());
            circle.setCenterY(unit.GetPosition().Get_Y());
            circle.setRadius((int)unit.GetSize().GetValue());
        }
        Map.setScaleX(1);
        Map.setScaleY(1);
    }


}
