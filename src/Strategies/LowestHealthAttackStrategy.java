package Strategies;

import unit.Unit;
import unitProperty.DamageUnitProperty;
import unitProperty.HealthUnitProperty;

import java.util.List;

public class LowestHealthAttackStrategy extends AttackStrategy{
    static LowestHealthAttackStrategy lowestHealthAttackStrategy;
    LowestHealthAttackStrategy(){}
    public synchronized static LowestHealthAttackStrategy getObj() {
        if (LowestHealthAttackStrategy.lowestHealthAttackStrategy==null)
        {
            synchronized (LowestHealthAttackStrategy.class){
                if (LowestHealthAttackStrategy.lowestHealthAttackStrategy==null)
                    lowestHealthAttackStrategy = new LowestHealthAttackStrategy() ;
            }
        }
        return LowestHealthAttackStrategy.lowestHealthAttackStrategy ;
    }
    @Override
    public Unit PrioritizeUnitToAttack(List<Unit> units) {
        Unit unit = null;
        double health = Double.MAX_VALUE;
        for(Unit curUnit : units){
            HealthUnitProperty curHealth = curUnit.GetHealth();
            if(curHealth != null){
                if(health > curHealth.GetValue()){
                    health = curHealth.GetValue();
                    unit = curUnit;
                }
            }
        }
        return unit;
    }
}
