package OldFX;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import unit.UnitFactory;
import unit.UnitType;


import java.util.HashMap;

public class UnitsInfo implements EventHandler {
    Pane root =new Pane();
    ///////
    Label header=new Label();
    Label UnitName=new Label();
    Label maxHealth=new Label();
    Label Armor=new Label();
    Label AttackDamage=new Label();
    Label AttackRange=new Label();
    Label AttackFrequency=new Label();
    Label Size=new Label();
    Label MovementSpeed =new Label();
    Label CanTarget=new Label();
    Label UnitPrice=new Label();
    ImageView imageView=new ImageView("\\Images\\infoScreen.jpg");
    ////////
    Button ok=new Button("OK");
    Stage prevStage;
    String[]prop;

    Stage Build(String unitType, HashMap<UnitType,String[]>AllUnits)
    {   VBox vBox=new VBox();
        prop= UnitFactory.getUnitPropertiesNames();
        UnitType unitType1=UnitType.valueOf(unitType);
        String[]info=AllUnits.get(unitType1);
        /////
        header.setText("INFORMATION...");
        header.setFont(new Font("Areial",18));
        header.setLayoutY(10);
        header.setTextFill(Color.color(1,1,1));
        vBox.getChildren().add(header);
        //////
        int y=30;
        for(int i=0;i<prop.length;i++)
        {
            Label temp=new Label();
            String s=new String();
            if (i==0)
                 s=prop[i]+":"+unitType;
            else

             s=prop[i]+":"+info[i-1];

            temp.setText(s);
            temp.setTextFill(Color.color(1,1,1));
            temp.setLayoutY(y);
            y+=20;
            temp.setLayoutX(150);
            temp.setFont(new Font(16));
            vBox.getChildren().add(temp);

        }
        ok.setLayoutX(400);
        ok.setLayoutY(340);
        ok.setOnAction(this::handle);
        ////
        imageView.setFitWidth(800);
        imageView.setFitHeight(400);
        ////
        root.getChildren().addAll(imageView,vBox,ok);

        //////
        Scene scene=new Scene(root,800,400);
        Stage stage=new Stage();
        stage.setScene(scene);
        /////
        prevStage=stage;
        return stage;
    }

    @Override
    public void handle(Event event) {
        if(event.getSource()==ok)
        {
            prevStage.close();
        }
    }
}
