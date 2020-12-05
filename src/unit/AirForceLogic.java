package unit;

import UnitControllers.AirForceMovementUnitController;
import UnitControllers.MovementUnitController;

import java.util.List;

public class AirForceLogic extends Logic {

    MovementUnitController movement = new AirForceMovementUnitController() ;
    boolean Reloading = false   ;
    @Override
    public void Execute(Unit unit) {
        if (Reloading)
        {
            movement.Execute(unit, unit.initializePosition);
            if (unit.position== unit.initializePosition)
                Reloading=false ;
        }
        else {
            List<Unit> list = unit.CheckRange()  ;
            if (list.size()>0)
            {
                attackUnitController.Execute(unit,unit.GetPrioritizedUnit(list));
                Reloading = true ;
            }
        }
    }
}
