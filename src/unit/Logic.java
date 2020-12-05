package unit;

import UnitControllers.AttackUnitController;
import UnitControllers.MovementUnitController;

public abstract class Logic {
    MovementUnitController movementUnitController;
    AttackUnitController attackUnitController;
    Logic(){
    }
    public abstract void Execute(Unit unit);
}
