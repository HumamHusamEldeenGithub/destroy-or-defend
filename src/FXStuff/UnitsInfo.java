package FXStuff;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import movement.Movement;
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
    ////////
    Button ok=new Button("OK");
    Stage prevStage;





    Stage Build(String unitType, HashMap<UnitType,String[]>AllUnits)
    {

        UnitType unitType1=UnitType.valueOf(unitType);
        String[]info=AllUnits.get(unitType1);
        /////
        VBox vBox=new VBox();
        vBox.getChildren().addAll(header,UnitName,maxHealth,Armor,AttackDamage,AttackRange,AttackFrequency,Size, MovementSpeed,CanTarget,UnitPrice);

        /////
        header.setText("INFORMATION...");
        header.setFont(new Font("Areial",18));
        header.setLayoutY(10);
        ////
        UnitName.setText("Name:"+info[0]);
        UnitName.setLayoutY(30);
        ////
        maxHealth.setText("Health:"+info[1]);
        maxHealth.setLayoutY(50);
        ////
        Armor.setText("Armor:"+info[2]);
        Armor.setLayoutY(70);
        ///
        AttackDamage.setText("AttackDamage:"+info[3]);
        AttackDamage.setLayoutY(90);
        ///
        AttackRange.setText("AttackRange:"+info[4]);
        AttackRange.setLayoutY(110);
        ///
        AttackFrequency.setText("AttackFrequency:"+info[5]);
        AttackFrequency.setLayoutY(130);
        ///
        Size.setText("Size:"+info[6]);
        Size.setLayoutY(150);
        ///
        MovementSpeed.setText("MovementSpeed:"+info[6]);
        MovementSpeed.setLayoutY(170);
        ///
        CanTarget.setText("CanTarget:"+info[7]);
        CanTarget.setLayoutY(190);
        ///
        UnitPrice.setText("UnitPrice:"+info[8]);
        UnitPrice.setLayoutY(210);
        ///
        ok.setLayoutX(200);
        ok.setLayoutY(240);
        ok.setOnAction(this::handle);
        ////
        root.getChildren().addAll(vBox);

        //////
        Scene scene=new Scene(root,300,300);
        Stage stage=new Stage();
        stage.setScene(scene);
        /////

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
