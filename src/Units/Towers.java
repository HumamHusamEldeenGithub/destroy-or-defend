package Units;

import Interfaces.ITarget;

import java.io.FileReader;

public class Towers extends Unit implements ITarget{
    @Override
    public boolean CanTarget(Unit unit) {
        return false;
    }
}
