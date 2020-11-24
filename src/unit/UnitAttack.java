package unit;

import Auxiliary.AttackResult;

public abstract class UnitAttack {
    UnitAttack unitAttack;
    AttackResult attackResult;
    public UnitAttack(UnitAttack unitAttack){
        this.unitAttack = unitAttack;
    }
    UnitAttack(){

    }
    public abstract AttackResult PerformAttack(Unit unit);
}
