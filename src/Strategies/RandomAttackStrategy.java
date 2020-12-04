package Strategies;

import unit.Unit;

import java.util.List;

public class RandomAttackStrategy extends AttackStrategy {
    @Override
    public Unit PrioritizeUnitToAttack(List<Unit> units) {
        if (units.size()>0)
            return units.get(0);
        return null ;
    }
}
