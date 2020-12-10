package unit;

import Arena.Grid;
import UnitControllers.AirForceMovementUnitController;
import UnitControllers.AttackUnitController;
import UnitControllers.MovementUnitController;
import Utilitiy.Logger;

import java.util.List;

public class AirForceLogic extends Logic {

    boolean Reloading = false   ;

    public AirForceLogic(){
        movementUnitController = new AirForceMovementUnitController();
        attackUnitController = new AttackUnitController();
    }
    @Override
    public void Execute(Unit unit) {
        if (Reloading)
        {
            movementUnitController.Execute(unit, unit.initializePosition);
            if (unit.position.Get_X() == unit.initializePosition.Get_X() && unit.position.Get_Y() == unit.initializePosition.Get_Y() )
                Reloading=false ;
        }
        else {
            List<Unit> list = unit.CheckRange()  ;
            if (list.size()>0)
            {
                attackUnitController.Execute(unit,unit.strategy.PrioritizeUnitToAttack(list));
                Reloading = true ;
            }
            else{
                movementUnitController.Execute(unit, Grid.getBasePosition());
                Logger.Movelog(unit.position, unit.unitType);
            }
        }
    }
}
