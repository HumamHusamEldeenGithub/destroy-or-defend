package GUI.GUIObserver;


import javafx.scene.Node;
import unit.Unit;

public class DestroyGUIObserver extends GUIObserver {

    public DestroyGUIObserver(Unit unit , Node node)
    {
        this.unit = unit ;
        this.node = node ;
    }
    public void  update()
    {

    }

}
