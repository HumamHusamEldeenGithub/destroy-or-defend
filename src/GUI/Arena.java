package GUI;

import gameManager.DoDGameManager;
import gameManager.GameState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import unit.Unit;



public class Arena extends Thread {
    AnchorPane rootAnchor=new AnchorPane();
    Pane Map=new Pane();
    Pane UnitsPane=new Pane();
    Stage Build()
    {
//        Button Start =new Button("Start");
//        Button ZoomOut=new Button("ZoomOut");
//        Start.setLayoutX(850);
//        Start.setLayoutY(550);
//        ZoomOut.setLayoutX(850);
//        ZoomOut.setLayoutY(600);
//        UnitsPane.getChildren().addAll(Start,ZoomOut);
//
//        UnitsPane.setPrefHeight(180);
//        UnitsPane.setPrefWidth(200);

        Map.setLayoutX(0);
        Map.setLayoutY(0);
        Map.setPrefWidth(800);
        Map.setPrefHeight(800);
        Map.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY,null)));
        rootAnchor.setPrefHeight(800);
        rootAnchor.setPrefWidth(800);
        GUIManager.GenerateWorld(Map);
        PutUnitsOnArena() ;
        rootAnchor.getChildren().addAll(Map);
        Map.setOnMouseClicked(this::ZoomInZoomOut);
        Scene scene=new Scene(rootAnchor,800,800);
        Stage stage=new Stage();
        stage.setScene(scene);
        return  stage;

    }
    public void ZoomInZoomOut(MouseEvent mouseEvent) {
        if (GUIManager.zoomIn) {
            Map.setLayoutX(0);
            Map.setLayoutY(0);
            Map.setScaleX(1);
            Map.setScaleY(1);
            GUIManager.zoomIn=false;
        }
        else {
            int LayOutX = 0, LayOutY = 0;
            LayOutX = 2 * (GUIManager.ScaleUp - 1) * GUIManager.borderWidth;
            LayOutY = 2 * (GUIManager.ScaleUp - 1) * GUIManager.borderHeight;
            int x = (int) (mouseEvent.getX() / GUIManager.borderWidth);
            int y = (int) mouseEvent.getY() / GUIManager.borderHeight;
            //System.out.println(x + " " + y);
            Map.setScaleX(GUIManager.ScaleUp);
            Map.setScaleY(GUIManager.ScaleUp);
            Map.setLayoutX(LayOutX - (GUIManager.borderWidth * x * GUIManager.ScaleUp));
            Map.setLayoutY(LayOutY - (GUIManager.borderHeight * y * GUIManager.ScaleUp));
            GUIManager.zoomIn = true;
            UnitsPane.toFront();
        }
        //System.out.println(LayOutX-(borderWidth*x*GUIManager.ScaleUp) + " " + (LayOutY-(borderHeight*y*GUIManager.ScaleUp)));
    }
    public void PutUnitsOnArena(){
        Map.setScaleX(GUIManager.ScaleUp);
        Map.setScaleY(GUIManager.ScaleUp);
        int i=0 ;
        for (Unit unit :GUIManager.AllUnits)
        {
            Circle circle = new Circle();
            Map.getChildren().add(circle) ;
            circle.setCenterX(unit.GetPosition().Get_X());
            circle.setCenterY(unit.GetPosition().Get_Y());
            circle.setRadius((int)unit.GetSize().GetValue());
            circle.setId(i++ +"");
        }
        Map.setScaleX(1);
        Map.setScaleY(1);
    }

    public void StartGame(ActionEvent actionEvent) {
        //DoDGameManager.InitializePlayers();
        DoDGameManager.StartGame();
    }

    public void MoveUnits() throws InterruptedException {
        while (DoDGameManager.GetState()!= GameState.AttackerWon && DoDGameManager.GetState()!=GameState.DefenderWon ) {
            int i = 0;
            for (Unit unit : GUIManager.AllUnits) {
                for (Node node : Map.getChildren()) {
                    if (node instanceof Circle &&((Circle) node).getId()!=null &&((Circle) node).getId().equals(i + "")) {
                        ((Circle) node).setCenterX(unit.GetPosition().Get_X());
                        ((Circle) node).setCenterY(unit.GetPosition().Get_Y());

                    }

                }
                i++;
            }
        }
    }
    @Override
    public void run() {
        try {
            System.out.println("Enter");
            this.MoveUnits();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
