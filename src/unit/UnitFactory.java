package unit;

import java.util.HashMap;

public class  UnitFactory {
    HashMap<UnitType,String>UnitsInfo ;
    public Unit CreateUnit(UnitType unitType) {
        return new Unit() ;
    }
}
