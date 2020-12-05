package unit;

import Arena.Grid;
import UnitControllers.AttackUnitController;
import UnitControllers.NormalMovementUnitController;

import java.util.List;

public class DefenderLogic extends Logic{
    public DefenderLogic(){
        movementUnitController = new NormalMovementUnitController();
        attackUnitController = new AttackUnitController();
    }
    public void Execute(Unit unit) {
        if(unit.targetedUnit!=null && unit.targetedUnit.GetHealth().GetValue()>0){
            attackUnitController.Execute(unit,unit.targetedUnit);
        }
        else{
            List<Unit> ToAttack = unit.CheckRange();

            if(ToAttack.size()>0){
                attackUnitController.Execute(unit,unit.strategy.PrioritizeUnitToAttack(ToAttack));
            }
        }
    }
}
