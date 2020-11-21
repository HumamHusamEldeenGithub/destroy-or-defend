package Utilities;

import Units.Forces;
import Units.Unit;

public class UnitsHandler extends Thread {
    Unit unit = null ;
    public UnitsHandler(Unit unit){
        this.unit = unit ;
    }

    @Override
    public void run() {
        while (unit!=null) {
            try {
                ((Forces)unit).Move();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
