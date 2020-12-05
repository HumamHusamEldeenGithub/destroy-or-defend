package FXStuff;

import javafx.scene.control.Alert;

public class About {

    static void printAbout()
    {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Our Game ::");
        alert.setHeaderText("Welcome to our game");
        alert.setContentText("This game created by :\n ");
        alert.show();
    }
}
