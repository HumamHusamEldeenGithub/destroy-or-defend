package Strategies;

import movement.DefenderMovement;
import unit.Unit;

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
        return null;
    }

}
