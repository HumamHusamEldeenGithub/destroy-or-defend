package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class ShopMenu {

    @FXML
    private AnchorPane rootAnchor;
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
        mouseEvent.getPickResult().getIntersectedNode().setTranslateX(mouseEvent.getPickResult().getIntersectedNode().getLayoutX()+0.05);
        //System.out.println("Full String: " + source1);
        System.out.println(source2);
    }


    public void NextScene(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXML/ShopMenu.fxml"));
        rootAnchor.getChildren().setAll(pane) ;
    }
}
