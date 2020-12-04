package FXStuff;

import javafx.scene.control.Alert;

public class ErrorMessage {

    void PrintError()
    {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText("You have to choose a unit first");
        alert.show();
    }
}
