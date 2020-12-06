package FXStuff;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class ShopMenu {

    @FXML
    private Pane UnitsPane;

    @FXML
    private Circle unit0;

    @FXML
    private Circle unit1;

    @FXML
    private Circle unit2;

    public void showUnitInfo(MouseEvent mouseEvent) {
        String source1 = mouseEvent.getSource().toString(); //yields complete string
        String source2 = mouseEvent.getPickResult().getIntersectedNode().getId(); //returns JUST the id of the object that was clicked
        System.out.println("Full String: " + source1);
        System.out.println("Just the id: " + source2);
        System.out.println(" " + source2);
    }

    /*@FXML
    void showUnitInfo(MouseEvent event) {



    }*/

}
