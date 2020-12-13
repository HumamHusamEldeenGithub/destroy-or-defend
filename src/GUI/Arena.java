package GUI;

import gameManager.DoDGameManager;
import gameManager.GameState;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import unit.Unit;
import unit.UnitFactory;

public class Arena extends Thread {
    AnchorPane rootAnchor=new AnchorPane();
    Pane Map=new Pane();
    TitledPane UnitsPane=new TitledPane();
    Button Pause = new Button() ;
    ProgressBar progressBar = new ProgressBar(0) ;
    Node LastClickedNode = null ;
    Pane Result = new Pane() ;
    Label AttackerWon = new Label("AttackerWon") ;
    Label DefenderWon = new Label("DefenderWon") ;
    Stage Build()
    {
        setPauseButton() ;
        setMapLayout();
        setProgressBar() ;
        setResultPane() ;
        rootAnchor.setPrefHeight(800);
        rootAnchor.setPrefWidth(800);
        GUIManager.GenerateWorld(Map);
        PutUnitsOnArena() ;
        rootAnchor.getChildren().addAll(Map,Result,Pause,progressBar);
        Scene scene=new Scene(rootAnchor,800,800);
        Stage stage=new Stage();
        stage.setScene(scene);
        return  stage;

    }
    void setPauseButton()
    {
        Pause.setLayoutX(725);
        Pause.setLayoutY(25);
        Pause.setPrefSize(35,35);
        ImageView imageView = new ImageView("\\Images\\Pause.png") ;
        imageView.setFitWidth(35);
        imageView.setFitHeight(35);
        Pause.setGraphic(imageView);
        Pause.setOnAction(this::Pause_Unpause);
    }
    void setMapLayout()
    {
        Map.setLayoutX(0);
        Map.setLayoutY(0);
        Map.setPrefWidth(800);
        Map.setPrefHeight(800);
        Map.setMaxSize(800,800);
        Map.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY,null)));
        Map.setOnMouseClicked(this::ZoomInZoomOut);
    }
    void setProgressBar()
    {
        progressBar.setPrefWidth(800);
        progressBar.setPrefHeight(20);
        progressBar.setLayoutY(780);
    }
    void setUnitsPane()
    {
        UnitsPane.setLayoutX(800);
        UnitsPane.setPrefSize(200,800);
        UnitsPane.setText("Units info");
    }
    void setResultPane()
    {
        Result.setLayoutX(300);
        Result.setLayoutY(400);
        Result.setPrefSize(200,50);
        AttackerWon.setPrefSize(200,50);
        AttackerWon.setFont(new Font("Yu Gothic" , 16));
        DefenderWon.setFont(new Font("Yu Gothic" , 16));
        AttackerWon.setTextFill(Color.WHITE);
        DefenderWon.setTextFill(Color.WHITE);
        AttackerWon.setAlignment(Pos.CENTER) ;
        DefenderWon.setAlignment(Pos.CENTER);
        DefenderWon.setPrefSize(200,50);
        AttackerWon.setOpacity(0);
        DefenderWon.setOpacity(0);
        Result.setOpacity(0);
        Result.getChildren().addAll(AttackerWon,DefenderWon) ;
    }
    public void Pause_Unpause(ActionEvent actionEvent)
    {
        DoDGameManager.Pause_Unpause();
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
            double BorderWidthScaleUp = 0, BorderHeightScaleUp = 0, LayOutX = 0, LayOutY = 0;
            BorderWidthScaleUp = 2 * (GUIManager.ScaleUp - 2) * GUIManager.borderWidth;
            BorderHeightScaleUp = 2 * (GUIManager.ScaleUp - 2) * GUIManager.borderHeight;
            LayOutX = 2 * (GUIManager.ScaleUp - 1) * GUIManager.borderWidth;
            LayOutY = 2 * (GUIManager.ScaleUp - 1) * GUIManager.borderHeight;
            double x = (mouseEvent.getX() - ((float) GUIManager.borderWidth / 2.0f));
            double y = (mouseEvent.getY()) - ((float) GUIManager.borderHeight / 2.0f);
            //System.out.println(x + " " + y);
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
    public double getLayout(double x )
    {
        if (x<-1200)
            return -1200 ;
        if (x>1200)
            return 1200 ;
        return x ;
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
            circle.setFill(new ImagePattern(new Image("\\Images\\"+ UnitFactory.GetImagePath(unit.GetType()))));
            circle.setId(unit.GetUniqueId());
            circle.setOnMouseClicked(this::ShowUnitInfo);
        }
        Map.setScaleX(1);
        Map.setScaleY(1);
    }
    public void MoveUnits() throws InterruptedException {
        while (DoDGameManager.GetState()!= GameState.AttackerWon && DoDGameManager.GetState()!=GameState.DefenderWon ) {
            progressBar.setProgress(DoDGameManager.GetTime()/DoDGameManager.GetRemainingTime());
            for (int i =0 ; i<GUIManager.AllUnits.size() ; i++) {
                Unit unit = GUIManager.AllUnits.get(i);
                for (Node node : Map.getChildren()) {
                    if (node instanceof Circle &&((Circle) node).getId()!=null &&((Circle) node).getId().equals(unit.GetUniqueId())) {
                        if (!unit.GetIsAlive())
                        {
                            GUIManager.AllUnits.remove(unit) ;
                            ((Circle) node).setOpacity(0.2);
                            i-- ;
                            break;
                        }
                            ((Circle) node).setCenterX(unit.GetPosition().Get_X());
                            ((Circle) node).setCenterY(unit.GetPosition().Get_Y());


                    }

                }
            }
            DoDGameManager.UpdateGame();
            ResultPaneUpdate();
        }

    }
    @Override
    public void run() {
        try {
            //System.out.println("Enter");
            this.MoveUnits();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void ShowUnitInfo(MouseEvent mouseEvent)
    {
        LastClickedNode = (Node) mouseEvent.getSource();
        for (Unit unit :GUIManager.AllUnits)
        {
            if (unit.GetUniqueId().equals(LastClickedNode.getId()))
            {
                UnitInfoAlert unitInfoAlert = new UnitInfoAlert() ;
                unitInfoAlert.PrintError("Unit = " + unit.GetType()+"\nPlayerType =" +unit.GetPlayerType()+"\nPosition ="+unit.GetPosition()  +"\nHealth = " + unit.GetHealth().GetValue() + "\nDamage = " + unit.GetSize().GetValue() + "\nSpeed = " + unit.GetMovementSpeed().GetValue() + "\nAttackSpeed = " + unit.GetAttackSpeed().GetValue());
            }
        }
    }
    void ResultPaneUpdate()
    {
        if (DoDGameManager.GetState()== GameState.AttackerWon) {
            AttackerWon.setOpacity(1);
            Result.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, null)));
            Result.setOpacity(1);
        }
        else if (DoDGameManager.GetState()==GameState.DefenderWon){
            Result.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, null)));
            DefenderWon.setOpacity(1);
            Result.setOpacity(1);
        }
    }
}
