package FXStuff;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;




public class AttackersScreen implements EventHandler {

    //DataMembers

    Button nextButton=new Button("Next");
    ImageView imageView=new ImageView("\\Images\\Attackers.jpg");
    Label mainLabel=new Label("ATTACKERS TEAM");

    Pane root =new Pane();
    Label numofPlayer=new Label();
    Stage prevStage;


    Stage Build()
    {




        imageView.setFitWidth(400);
        imageView.setFitHeight(400);

        mainLabel.setLayoutX(200);
        mainLabel.setLayoutY(10);

        nextButton.setLayoutX(200);
        nextButton.setLayoutY(350);


        root.getChildren().addAll(imageView,nextButton,mainLabel);
        nextButton.setOnAction(this::handle);

        Scene scene=new Scene(root,400,400);
        Stage stage=new Stage();
        stage.setScene(scene);
        prevStage=stage;

        return stage;




    }


    @Override
    public void handle(Event event) {
        if(event.getSource()==nextButton)
        {
            prevStage.hide();
        }
    }
}
