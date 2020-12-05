package FXStuff;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;




public class SetUnitsScreen implements EventHandler {

    Button okButton =new Button("OK");
    Pane root =new Pane();
    ImageView imageView=new ImageView("\\Images\\setUnits.jpg");
    Stage prevStage;
    Label setUnitLabel=new Label("Set Unit ");

    Stage Build()
    {
        imageView.setFitHeight(400);
        imageView.setFitWidth(600);;

        okButton.setLayoutY(350);
        okButton.setLayoutX(300);
        okButton.setOnAction(this::handle);

        setUnitLabel.setFont(new Font("Aerial",18));
        setUnitLabel.setLayoutX(300);
        setUnitLabel.setLayoutY(30);

        root.getChildren().addAll(imageView,okButton,setUnitLabel);

        Scene scene=new Scene(root,600,400);

        Stage stage=new Stage();
        stage.setScene(scene);
        prevStage=stage;
        return  stage;
    }
    @Override
    public void handle(Event event) {
        if(event.getSource()==okButton)
        {
            prevStage.close();
        }

    }
}
