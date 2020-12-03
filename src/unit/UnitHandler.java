package unit;

public class UnitHandler extends Thread {
    private Unit unit  ;
    private UnitHandler() {
    }
    public UnitHandler(Unit unit)
    {
        this.unit = unit ;
    }

    @Override
    public void run() {
        Unit temp = unit.CheckRange() ;
        if (temp!=null)
            unit.AttackUnit(temp);
        else {
            unit.Move();
        }
    }
    public static void CreateThread(Unit unit)
    {
        UnitHandler unitHandler = new UnitHandler(new Unit()) ;
        unitHandler.start();
    }
    public static void main(String[] args) {
    }

//    public void run() {

//    }

}
