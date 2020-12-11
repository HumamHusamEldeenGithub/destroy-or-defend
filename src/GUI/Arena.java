package GUI;

import Utilitiy.Position;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import Arena.* ;
import org.w3c.dom.css.Rect;
import unit.Unit;

public class Arena {



    @FXML
    private AnchorPane rootAnchor;

    @FXML
    private Pane Map;

    @FXML
    private AnchorPane UnitsPane;




    public void initialize() {
        GUIManager.GenerateWorld(this.Map);
        Map.setScaleX(SetUnits.ScaleUp);
        Map.setScaleY(SetUnits.ScaleUp);
        for (Unit unit :SetUnits.AllUnits)
        {
            Circle circle = new Circle();
            Map.getChildren().add(circle) ;
            circle.setCenterX(unit.GetPosition().Get_X());
            circle.setCenterY(unit.GetPosition().Get_Y());
            circle.setRadius((int)unit.GetSize().GetValue());
        }
        Map.setScaleX(1);
        Map.setScaleY(1);

    }
    public void ZoomOut(ActionEvent actionEvent) {
        Map.setLayoutX(0);
        Map.setLayoutY(0);
        Map.setScaleX(1);
        Map.setScaleY(1);
    }
    public void ZoomIn(MouseEvent mouseEvent) {
        int LayOutX  =0 , LayOutY=0 ;
        LayOutX = 2*(SetUnits.ScaleUp-1) * SetUnits.borderWidth ;
        LayOutY = 2*(SetUnits.ScaleUp-1) * SetUnits.borderHeight ;
        int x = (int) (mouseEvent.getX()/SetUnits.borderWidth);
        int y = (int)mouseEvent.getY()/SetUnits.borderHeight ;
        // System.out.println(x + " " + y );
        Map.setScaleX(SetUnits.ScaleUp);
        Map.setScaleY(SetUnits.ScaleUp);
        Map.setLayoutX(LayOutX-(SetUnits.borderWidth*x*SetUnits.ScaleUp));
        Map.setLayoutY(LayOutY-(SetUnits.borderHeight*y*SetUnits.ScaleUp));
        SetUnits.zoomIn=true ;
        //System.out.println(LayOutX-(borderWidth*x*SetUnits.ScaleUp) + " " + (LayOutY-(borderHeight*y*SetUnits.ScaleUp)));
    }
}
