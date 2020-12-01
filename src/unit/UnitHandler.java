package unit;

public class UnitHandler implements Runnable {
    private Unit unit  ;
    private UnitHandler() {
    }
    public UnitHandler(Unit unit)
    {
        this.unit = unit ;
    }

    public void run() {
        Unit temp = unit.CheckRange() ;
        if (temp!=null)
            unit.AttackUnit(temp);
        else
            unit.movement.getObj().move(unit);
    }
}
