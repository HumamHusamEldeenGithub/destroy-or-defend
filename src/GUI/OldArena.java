package GUI;

import gameManager.DoDGameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import unit.Unit;


public class OldArena {

    @FXML
    private AnchorPane rootAnchor;

    @FXML
    private Pane Map;

    @FXML
    private Pane UnitsPane;

    public void initialize() {
        GUIManager.GenerateWorld(Map);
        PutUnitsOnArena();
    }
    public void ZoomOut(ActionEvent actionEvent) {
        Map.setLayoutX(0);
        Map.setLayoutY(0);
        Map.setScaleX(1);
        Map.setScaleY(1);
    }
    public void ZoomIn(MouseEvent mouseEvent) {
        int LayOutX  =0 , LayOutY=0 ;
        LayOutX = 2*(GUIManager.ScaleUp-1) * GUIManager.borderWidth ;
        LayOutY = 2*(GUIManager.ScaleUp-1) * GUIManager.borderHeight ;
        int x = (int) (mouseEvent.getX()/GUIManager.borderWidth);
        int y = (int)mouseEvent.getY()/GUIManager.borderHeight ;
        Map.setScaleX(GUIManager.ScaleUp);
        Map.setScaleY(GUIManager.ScaleUp);
        Map.setLayoutX(LayOutX-(GUIManager.borderWidth*x*GUIManager.ScaleUp));
        Map.setLayoutY(LayOutY-(GUIManager.borderHeight*y*GUIManager.ScaleUp));
        GUIManager.zoomIn=true ;
        UnitsPane.toFront();
    }
    public void PutUnitsOnArena(){
//        Map.setScaleX(GUIManager.ScaleUp);
//        Map.setScaleY(GUIManager.ScaleUp);
//        for (Unit unit :GUIManager.AllUnits)
//        {
//            Circle circle = new Circle();
//            Map.getChildren().add(circle) ;
//            circle.setCenterX(unit.GetPosition().Get_X());
//            circle.setCenterY(unit.GetPosition().Get_Y());
//            circle.setRadius((int)unit.GetSize().GetValue());
//        }
//        Map.setScaleX(1);
//        Map.setScaleY(1);
    }

    public void StartGame(ActionEvent actionEvent) {
        //DoDGameManager.InitializePlayers();
        DoDGameManager.StartGame();
    }
}
