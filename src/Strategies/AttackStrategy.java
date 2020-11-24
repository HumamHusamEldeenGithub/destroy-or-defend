package Strategies;

import unit.Unit;

import java.util.List;

public abstract class AttackStrategy {
    public abstract Unit PrioritizeUnitToAttack(List<Unit> units);
}
