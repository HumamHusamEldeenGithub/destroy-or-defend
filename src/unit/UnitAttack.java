package unit;

import Auxiliary.AttackResult;

public abstract class UnitAttack {
    UnitAttack unitAttack;
    AttackResult attackResult;
    UnitAttack(UnitAttack unitAttack){
        this.unitAttack = unitAttack;
    }
    UnitAttack(){

    }
    public abstract AttackResult PerformAttack(Unit unit);
}
