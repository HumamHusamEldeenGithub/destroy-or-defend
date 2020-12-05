package Strategies;

import unit.Unit;

import java.util.List;

public class RandomAttackStrategy extends AttackStrategy {
    static RandomAttackStrategy randomAttackStrategy = null ;
    RandomAttackStrategy(){}
    public synchronized static RandomAttackStrategy getObj()
    {
        if (RandomAttackStrategy.randomAttackStrategy==null)
        {
            synchronized (RandomAttackStrategy.class)
            {
                if (RandomAttackStrategy.randomAttackStrategy==null)
                    RandomAttackStrategy.randomAttackStrategy = new RandomAttackStrategy() ;
            }
        }
        return RandomAttackStrategy.randomAttackStrategy ;
    }
    @Override
    public Unit PrioritizeUnitToAttack(List<Unit> units) {
        if (units.size()>0)
            return units.get(0);
        return null ;
    }
}
