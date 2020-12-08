package GUI;

import javafx.scene.control.Alert;

public class ErrorMessage {

    void PrintError(String error)
    {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(error);
        alert.show();
    }
}
