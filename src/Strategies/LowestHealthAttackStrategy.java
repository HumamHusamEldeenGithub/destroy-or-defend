package Strategies;

import unit.Unit;

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
        return null;
    }
}
