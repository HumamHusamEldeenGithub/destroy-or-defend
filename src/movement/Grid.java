package movement;

import unit.Unit;
import unit.UnitPosition;

import java.util.ArrayList;
import java.util.HashMap;

public class Grid {
    public static int NUM_CELLS ;
    public static int CELL_SIZE ;
    HashMap<UnitPosition, ArrayList<Object>> map ;
    Unit[][] units ;
  /*  public void addUnit(Unit unit){
        int cellX = (int)(unit.getPosition().getCenterX()/CELL_SIZE) ;
        int cellY = (int)(unit.getPosition().getCenterY()/CELL_SIZE) ;
        unit._prev= null ;
        unit._next = units[cellX][cellY] ;
        units[cellX][cellY] = unit ;
        if (unit._next!=null)
            unit._next._prev = unit ;
    }*/
    public void addUnit(Unit unit){

    }
}
