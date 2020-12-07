package OldFX;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import unit.Unit;

import java.util.ArrayList;

public class Cell {
    //get from the arena the Position {i,j} of the cell
    GridPane gridPane=new GridPane();
    Label unitName=new Label();
    Label num=new Label();
    Stage Build(int x,int y)
    {
        //get from hashmap of units by the key of {i,j}
        //thats method return arraylist of units
        ArrayList<Unit>arrayList=new ArrayList<>();
        for(int i=0;i<arrayList.size();i++)
        {

        }
        Stage stage=new Stage();
        return stage;
    }
}
