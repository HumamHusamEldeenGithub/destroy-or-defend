package FXStuff;

import javafx.scene.control.Alert;

public class CoinsMessage {
    void print()
    {

        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR..");
        alert.setContentText("You Don't Have Enough Money..");
        alert.show();
    }
}
