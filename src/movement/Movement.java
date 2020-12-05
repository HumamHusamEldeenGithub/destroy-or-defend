package movement;

import Arena.Grid;
import Utilitiy.PathFinder;
import Utilitiy.Position;
import unit.Unit;

public class Movement {
    public void move(Unit unit,Position position){
        Position nextPos = PathFinder.GetObj().GetPos(unit.GetPosition(), position, unit.GetRange().GetValue(), unit.GetSize().GetValue());
        if (nextPos != null) {
            // System.out.println(nextPos);
            Grid.RemoveUnit(unit);
            unit.SetPosition(nextPos);
            Grid.addUnit(unit);
            // System.out.println("has moved");
        } else
            System.err.println("Error in finding path");
    }

}
