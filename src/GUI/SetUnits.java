package GUI;

import Utilitiy.Position;
import gameManager.DoDGameManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Pair;
import unit.Unit;
import unit.UnitFactory;
import unit.UnitType;

import java.util.List;

public class SetUnits {
    static int id = 0 ;
    static int margin = 20 ;
    static int firstPixel_X = 60 ;
    static int firstPixel_Y = 150 ;
    static int Radius =25 ;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    @FXML
    private Pane Grid;
//    @FXML
//    private TitledPane PlayerId;

    @FXML
    private AnchorPane UnitsPane;


    public void initialize(){
       // this.getPlayerId();
        this.DrawUnitsCircles(UnitsPane); ;
    }

    public void DrawUnitsCircles(AnchorPane pane)
    {
        int x = 0;
        int y =60 ;
        List<Unit>PlayerUnits = DoDGameManager.Players.get(id).GetUnits() ;
        for (Unit unit : PlayerUnits) {
            Pair<Integer,Integer> dim  = GetDimensions(x,y) ;
            x = dim.getKey() ;
            y = dim.getValue();
            Circle circle = new Circle(x,y, Radius);
            circle.setId(unit.GetType().toString());
            //circle.addEventFilter(MouseEvent.MOUSE_CLICKED,this::showUnitInfo);
            circle.setOnMousePressed(circleOnMousePressedEventHandler);
            circle.setOnMouseDragged(circleOnMouseDraggedEventHandler);
            pane.getChildren().add(circle);
        }
    }

    @FXML
    void CheckUnitsPositions(ActionEvent event) {
        int x =0 , y=0 ;
        List<Unit> units = DoDGameManager.Players.get(SetUnits.id).GetUnits() ;
        boolean[] flag = new boolean[units.size()] ;
        for (Node node: UnitsPane.getChildren()) {
            if (Circle.class.isInstance(node)) {
                Bounds boundsInScene = node.localToScene(node.getBoundsInLocal());
                Circle circle = (Circle) node;
                x = (int) boundsInScene.getMinX();
                y = (int) boundsInScene.getMinY();
//                System.out.println(circle.getId());
                  System.out.println(x+" "+ y );
                for (int i = 0; i < units.size(); i++) {
                    if (units.get(i).GetType() == UnitType.valueOf(circle.getId()) && !flag[i]) {

                        units.get(i).SetPosition(new Position(x, y));
                        System.out.println(circle.getId());
                        flag[i] = true;
                    }
                }
            }
        }
//        for (Unit unit : DoDGameManager.Players.get(SetUnits.id).GetUnits())
//            System.out.println(unit.GetPosition());
    }


    EventHandler<MouseEvent> circleOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((Circle)(t.getSource())).getTranslateX();
                    orgTranslateY = ((Circle)(t.getSource())).getTranslateY();
                }
            };

    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    ((Circle)(t.getSource())).setTranslateX(newTranslateX);
                    ((Circle)(t.getSource())).setTranslateY(newTranslateY);
                    //((Circle)(t.getSource())).setRadius(UnitFactory.GetSize(UnitType.valueOf(((Circle)(t.getSource())).getId())));
                    ((Circle)(t.getSource())).setRadius(10);
                }
            };
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
}
