package UnitControllers;

import Arena.Grid;
import Utilitiy.*;
import unit.Unit;

public class NormalMovementUnitController extends MovementUnitController{
    public NormalMovementUnitController(){
        stopWatch = new StopWatch() ;
        StopWatchPool.GetObj().AddObj(stopWatch);
    }
    public void Execute(Unit unit, Position position){
        if(stopWatch.GetElapsedSeconds() > 1/(unit.GetMovementSpeed().GetValue())*unit.GetSpeedMovementEffectorValue()) {
            Position nextPos = PathFinder.GetObj().GetPos(unit.GetPosition(), position, unit.GetRange().GetValue(), unit.GetSize().GetValue());
            if (nextPos != null) {
                Grid.RemoveUnit(unit);
                unit.SetPosition(nextPos);
                Grid.addUnit(unit);
                Logger.Movelog(unit.GetPosition(), unit.GetType());
            } else
                System.err.println("Error in finding path");
            stopWatch.Reset();
        }
        stopWatch.Start();
    }
}
