package GUI.GUIObserver;


import javafx.scene.Node;
import unit.Unit;

public abstract class GUIObserver {
    protected Unit unit ;
    protected Node node ;
    public abstract void update() ;
}
