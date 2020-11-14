package Utilities;

import Arena.Arena;
import Enums.UnitType;
import Units.Forces;
import Units.Unit;

public class main {
    public static void main(String[] args){
        Unit soldier = new Forces(UnitType.RifleSoldier) ;
        Arena.Deploy(soldier,new Position(5,5)) ;
        Arena.PrintArena();

    }
}
