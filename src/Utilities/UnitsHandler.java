package Utilities;

import Units.Unit;

public class UnitsHandler extends Thread {
    Unit unit = null ;
    public UnitsHandler(Unit unit){
        this.unit = unit ;
    }

    @Override
    public void run() {
        while (unit!=null)
            unit.Move();
    }
}
