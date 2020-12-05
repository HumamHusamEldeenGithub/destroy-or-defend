package UnitControllers;

import Arena.Grid;
import Utilitiy.PathFinder;
import Utilitiy.Position;
import Utilitiy.StopWatch;
import unit.Unit;

public abstract class MovementUnitController {
    StopWatch stopWatch = new StopWatch() ;
    public abstract void Execute(Unit unit,Position position);

}
