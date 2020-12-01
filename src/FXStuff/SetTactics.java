package FXStuff;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class SetTactics implements EventHandler {

    AnchorPane root =new AnchorPane();
    VBox vBox=new VBox();
    ToggleGroup toggleGroup=new ToggleGroup();
    Label label =new Label("Choose your Tactics :");
    RadioButton r1=new RadioButton("Random");
    RadioButton r2=new RadioButton("Highest Damage ");
    RadioButton r3=new RadioButton("Lowest Health");
    Button nextButton=new Button("Next");
    Stage prevStage;

    Stage Build()
    {
        r1.setToggleGroup(toggleGroup);
        r2.setToggleGroup(toggleGroup);
        r3.setToggleGroup(toggleGroup);
        /////
        vBox.getChildren().add(r1);
        vBox.getChildren().add(r2);
        vBox.getChildren().add(r3);
        /////
        label.setLayoutX(10);
        label.setLayoutY(0);
        //////
        vBox.setLayoutX(10);
        vBox.setLayoutY(30);
        /////
        nextButton.setLayoutX(10);
        nextButton.setLayoutY(100);

        root.getChildren().addAll(label,vBox,nextButton);
        nextButton.setOnAction(this::handle);

        Scene scene=new Scene(root,400,300);
        Stage stage=new Stage();
        stage.setScene(scene);
        prevStage=stage;

        return  stage;



    }

    @Override
    public void handle(Event event) {
        if(event.getSource()==nextButton)
        {
            String Tactics=new String();

            if(r1.isSelected())
            {Tactics="Random";
                prevStage.hide();}
            else if(r2.isSelected())
            {Tactics="HighestDamage";
                prevStage.hide();}

            else if(r3.isSelected())
            { Tactics="LowestHealth";
                prevStage.hide();
            }




            else
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("You have to choose of the Tactics above... ");
                alert.show();

            }



        }

    }
}
