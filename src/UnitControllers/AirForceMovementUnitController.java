package UnitControllers;

import Arena.Grid;
import Utilitiy.Position;
import unit.Unit;

public class AirForceMovementUnitController extends MovementUnitController {
    public void Execute (Unit unit,Position pos)
    {
        Position nextPos = BestPosition(unit.GetPosition(), pos) ;
        if (nextPos!=null)
        {
                // System.out.println(nextPos);
                Grid.RemoveUnit(unit);
                unit.SetPosition(nextPos);
                Grid.addUnit(unit);
                // System.out.println("has moved");
        }
        else{
             System.err.println("Error in finding path");
        }
    }
    public synchronized Position BestPosition (Position unitPosition,Position basePosition)
    {
        if (unitPosition.Get_X()== basePosition.Get_X())
        {
            if (unitPosition.Get_Y()<basePosition.Get_Y())
                return new Position(unitPosition.Get_X(), unitPosition.Get_Y()+1) ;
            else
                return new Position(unitPosition.Get_X(), unitPosition.Get_Y()-1) ;
        }
        else if (unitPosition.Get_Y()== basePosition.Get_Y())
        {
            if (unitPosition.Get_X()<basePosition.Get_X())
                return new Position(unitPosition.Get_X()+1, unitPosition.Get_Y() ) ;
            else
                return new Position(unitPosition.Get_X()-1, unitPosition.Get_Y() ) ;
        }
        else
        {
            if (unitPosition.Get_X()< basePosition.Get_X())
            {
                if (unitPosition.Get_Y()< basePosition.Get_Y())
                {
                    return new Position(unitPosition.Get_X()+1, unitPosition.Get_Y()+1) ;
                }
                else
                    return new Position(unitPosition.Get_X()+1, unitPosition.Get_Y()-1 ) ;
            }
            else
            {
                if (unitPosition.Get_Y()< basePosition.Get_Y())
                {
                    return new Position(unitPosition.Get_X()-1, unitPosition.Get_Y()+1) ;
                }
                else
                    return new Position(unitPosition.Get_X()-1, unitPosition.Get_Y()-1 ) ;
            }
        }

    }
}
