package Units;

import Enums.UnitType;
import Interfaces.IMove;
import Interfaces.ITarget;

public class Forces extends Unit implements IMove , ITarget {

    public Forces(UnitType tyPe){
        this.type = tyPe;
        Initialize();
    }
    @Override
    public void Move() {
        if (Ca)

    }
    @Override
    public boolean CanTarget(Unit unit) {
        return false;
    }
}
