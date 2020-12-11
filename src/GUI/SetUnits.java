package GUI;

import GUI.GUIObserver.MoveGUIObserver;
import Utilitiy.Position;
import gameManager.DoDGameManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Pair;
import unit.Unit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Arena.* ;

public class SetUnits {
    static int id = 0 ;
    static Circle SelectedCircle= null ;
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
                    int LayOutX  =0 , LayOutY=0 ;
                    LayOutX = 2*(GUIManager.ScaleUp-1) * GUIManager.borderWidth ;
                    LayOutY = 2*(GUIManager.ScaleUp-1) * GUIManager.borderHeight ;
                    int x = (int) (mouseEvent.getX()/GUIManager.borderWidth);
                    int y = (int)mouseEvent.getY()/GUIManager.borderHeight ;
                   // System.out.println(x + " " + y );
                    Map.setScaleX(GUIManager.ScaleUp);
                    Map.setScaleY(GUIManager.ScaleUp);
                    Map.setLayoutX(LayOutX-(GUIManager.borderWidth*x*GUIManager.ScaleUp));
                    Map.setLayoutY(LayOutY-(GUIManager.borderHeight*y*GUIManager.ScaleUp));
                    GUIManager.zoomIn=true ;
                    //System.out.println(LayOutX-(borderWidth*x*SetUnits.ScaleUp) + " " + (LayOutY-(borderHeight*y*SetUnits.ScaleUp)));
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
                        SetUnits.SelectedCircle.setStyle("-fx-background-color : red") ;
                        SetUnits.SelectedCircle.setOpacity(0.5);
                        //System.out.println(SetUnits.SelectedCircle.getId());
                    }

                }
            };
    public void GetPosition(MouseEvent mouseEvent) {
            if (SetUnits.SelectedCircle != null) {
                TerrainType terrainType = Grid.GetTerrain(new Position((int)mouseEvent.getX(),(int)mouseEvent.getY()) );
                if (terrainType==TerrainType.Valley) {
                    ErrorMessage errorMessage = new ErrorMessage();
                    errorMessage.PrintError("You can't put units on Vally .. try another position .");
                }
                else {
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
                new MoveGUIObserver(unit,circle);
                unit.SetPosition(new Position((int)circle.getCenterX() , (int)circle.getCenterY()));
                GUIManager.AllUnits.add(unit);
            }
        }
        if (SetUnits.id==GUIManager.Players.size()-1)

        {
            System.out.println("Enter");
            DoDGameManager.InitializePlayers(GUIManager.Players);
            DoDGameManager.StartGame();
            Stage MyNewStage=new Stage();
            Arena Arena =new Arena();
            MyNewStage= Arena.Build();
            Arena.start();
            MyNewStage.showAndWait();

        }
        else
        {
            SetUnits.id++ ;
            GUIManager.ChangeScene(rootAnchor, WindowType.SetUnits);
        }

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
        }
        Map.setScaleX(1);
        Map.setScaleY(1);
    }


}
