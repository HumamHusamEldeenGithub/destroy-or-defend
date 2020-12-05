package Strategies;

import unit.Unit;
import unitProperty.DamageUnitProperty;

import java.util.List;

public class HighestDamageAttackStrategy extends AttackStrategy {
    static HighestDamageAttackStrategy highestDamageAttackStrategy;
    HighestDamageAttackStrategy(){}
    public synchronized static HighestDamageAttackStrategy getObj() {
        if (HighestDamageAttackStrategy.highestDamageAttackStrategy==null)
        {
            synchronized (HighestDamageAttackStrategy.class){
                if (HighestDamageAttackStrategy.highestDamageAttackStrategy==null)
                    highestDamageAttackStrategy = new HighestDamageAttackStrategy() ;
            }
        }
        return HighestDamageAttackStrategy.highestDamageAttackStrategy ;
    }
    @Override
    public Unit PrioritizeUnitToAttack(List<Unit> units) {
        Unit unit = null;
        double damage = 0;
        for(Unit curUnit : units){
            DamageUnitProperty curDamage = curUnit.GetDamage();
            if(curDamage != null){
                if(damage < curDamage.GetValue()){
                    damage = curDamage.GetValue();
                    unit = curUnit;
                }
            }
        }
        return unit;
    }

}
