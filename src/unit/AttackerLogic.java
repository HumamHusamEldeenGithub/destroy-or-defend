package unit;

import Arena.Grid;

import java.util.List;

public class AttackerLogic extends Logic{
    @Override
    public void Execute(Unit unit) {
        if(unit.targetedUnit!=null && unit.targetedUnit.GetHealth().GetValue()>0){
            attack.Execute(unit,unit.targetedUnit);
        }
        else{
            List<Unit> ToAttack = unit.CheckRange();

            if(ToAttack.size()>0){
                attack.Execute(unit,unit.strategy.PrioritizeUnitToAttack(ToAttack));
            }
            else{
                movement.Execute(unit, Grid.getBasePosition());
            }
        }
    }
}
