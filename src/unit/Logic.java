package unit;

import movement.Attack;
import movement.Movement;

public abstract class Logic {
    Movement movement;
    Attack attack;
    public Logic(){
        movement = new Movement();
        attack = new Attack();
    }
    public abstract void Execute(Unit unit);
}
