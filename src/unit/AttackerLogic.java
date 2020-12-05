package unit;

import Arena.Grid;
import UnitControllers.AttackUnitController;
import UnitControllers.NormalMovementUnitController;

import java.util.List;

public class AttackerLogic extends Logic{

    public AttackerLogic(){
        movementUnitController = new NormalMovementUnitController();
        attackUnitController = new AttackUnitController();
    }
    @Override
    public void Execute(Unit unit) {
        if(unit.targetedUnit!=null && unit.targetedUnit.GetHealth().GetValue()>0){
            attackUnitController.Execute(unit,unit.targetedUnit);
        }
        else{
            List<Unit> ToAttack = unit.CheckRange();

            if(ToAttack.size()>0){
                attackUnitController.Execute(unit,unit.strategy.PrioritizeUnitToAttack(ToAttack));
            }
            else{
                movementUnitController.Execute(unit, Grid.getBasePosition());
            }
        }
    }
}
