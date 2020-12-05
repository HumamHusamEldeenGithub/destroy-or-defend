package unit;

import UnitControllers.AttackUnitController;
import UnitControllers.MovementUnitController;

public abstract class Logic {
    MovementUnitController movementUnitController;
    AttackUnitController attackUnitController;
    public Logic(){
        movementUnitController = new MovementUnitController();
        attackUnitController = new AttackUnitController();
    }
    public abstract void Execute(Unit unit);
}
