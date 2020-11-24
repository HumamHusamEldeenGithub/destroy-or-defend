package unit;

import Auxiliary.AttackResult;
import Auxiliary.EmptyAttackResult;

public class NormalUnitAttack extends UnitAttack{
    public NormalUnitAttack(){
        this.unitAttack = this;
    }
    @Override
    public AttackResult PerformAttack(Unit unit) {
        unit.AcceptDamage(1);
        return new EmptyAttackResult();
    }
}
