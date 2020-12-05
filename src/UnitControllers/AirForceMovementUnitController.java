package UnitControllers;

import Arena.Grid;
import Utilitiy.Position;
import Utilitiy.StopWatch;
import Utilitiy.StopWatchPool;
import unit.Unit;

public class AirForceMovementUnitController extends MovementUnitController {
    public AirForceMovementUnitController(){
        stopWatch = new StopWatch() ;
        StopWatchPool.GetObj().AddObj(stopWatch);
    }
    public void Execute (Unit unit,Position pos)
    {
        if(stopWatch.GetElapsedSeconds() > 1/unit.GetMovementSpeed().GetValue()) {
            Position nextPos = BestPosition(unit.GetPosition(), pos);
            if (nextPos != null) {
                // System.out.println(nextPos);
                Grid.RemoveUnit(unit);
                unit.SetPosition(nextPos);
                Grid.addUnit(unit);
               // System.out.println(nextPos);
            } else {
                System.err.println("Error in finding path");
            }
            stopWatch.Reset();
        }
        stopWatch.Start();
    }
    public synchronized Position BestPosition (Position unitPosition,Position Destination)
    {
        Position nextPos = new Position(unitPosition.Get_X(),unitPosition.Get_Y());
        if(unitPosition.Get_X() < Destination.Get_X()){
            nextPos.Set_X(nextPos.Get_X() + 1);
        }
        else if(unitPosition.Get_X() > Destination.Get_X()){
            nextPos.Set_X(nextPos.Get_X() - 1);
        }
        if(unitPosition.Get_Y() < Destination.Get_Y()){
            nextPos.Set_Y(nextPos.Get_Y() + 1);
        }
        else if(unitPosition.Get_Y() > Destination.Get_Y()){
            nextPos.Set_Y(nextPos.Get_Y() - 1);
        }
        return nextPos;
    }
}
