package movement;

import unit.Unit;

public class Attack {
    public void Execute(Unit unit,Unit target){
        unit.AttackUnit(target);
    }
}
