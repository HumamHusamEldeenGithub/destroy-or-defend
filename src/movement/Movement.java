package movement;

import unit.Unit;

public abstract class Movement {
    public abstract void move(Unit unit);

    public static Movement getObj() {
        return null;
    }
}
