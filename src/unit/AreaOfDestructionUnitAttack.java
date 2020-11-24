package unit;

import Auxiliary.AttackResult;

public class AreaOfDestructionUnitAttack extends UnitAttack{

    public AreaOfDestructionUnitAttack(){
        this.unitAttack = this;
    }
    @Override
    public AttackResult PerformAttack(Unit unit) {
        return null;
    }
}