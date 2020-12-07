package OldFX;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenu implements EventHandler {
    //DATAMEMBERS
    HBox hBox=new HBox();
    Button Play_btn=new Button("Play");
    Button About_btn=new Button("About");
    Button Exit_btn=new Button("Exit");
    Pane root=new Pane();
    Stage PrevStage;


    Stage BuildMainMenu()
    {
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println((int)e.getSceneX());
                System.out.println((int)e.getSceneY());

            }
        };
        ImageView imageView=new ImageView("\\Images\\planet_light.jpg");
        hBox.getChildren().add(Play_btn);
        hBox.getChildren().add(About_btn);
        hBox.getChildren().add(Exit_btn);
        hBox.setLayoutX(350);
        hBox.setLayoutY(350);
        imageView.setFitHeight(1000);
        imageView.setFitWidth(1000);

        Play_btn.setOnAction(this::handle);
        root.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        About_btn.setOnAction(this::handle);
        Exit_btn.setOnAction(e -> Platform.exit());

        root.getChildren().addAll(imageView,hBox);

        Scene scene = new Scene(root , 1000 , 1000);
        Stage stage=new Stage();

        stage.setScene(scene);
        stage.show();
        PrevStage=stage;
        return stage;
    }

    public void handle(Event event) {


        if(event.getSource()==Play_btn)
        {
            Stage MyNewStage=new Stage();
            NumOfPlayers numOfPlayers=new NumOfPlayers();
            MyNewStage=numOfPlayers.Build();
            PrevStage.hide();
            MyNewStage.show();

        }
        else if(event.getSource()==About_btn)
        {
            About.printAbout();
        }

    }

}
