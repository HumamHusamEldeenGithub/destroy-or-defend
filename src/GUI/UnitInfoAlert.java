package GUI;

import javafx.scene.control.Alert;

public class UnitInfoAlert {
    void PrintError(String info)
    {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("UnitInfo");
        alert.setContentText(info);
        alert.show();
    }
}
