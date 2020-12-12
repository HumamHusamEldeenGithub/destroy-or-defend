package GUI.GUIObserver;

import Utilitiy.Position;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

import unit.Unit;

public class MoveGUIObserver extends GUIObserver {

    public MoveGUIObserver(Unit unit, Node node)
    {
        this.unit = unit ;
        this.node = node;
       // this.unit.SetMoveGUIObserver(this);
    }
    public void update()
    {
//        System.out.println("Enter" + unit.GetType().toString());
        Position position = unit.GetPosition() ;
        ((Circle)this.node).setCenterX(position.Get_X());
        ((Circle)this.node).setCenterY(position.Get_Y());
//        System.out.println("Circle : " +((Circle)this.node).getCenterX() + " " + ((Circle)this.node).getCenterY() );
    }
}
