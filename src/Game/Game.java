package Game;

import Arena.Arena;
import Interfaces.IAttack;
import Units.Unit;

import java.util.ArrayList;

public class Game {

    class Team {
        ArrayList<Unit> Units = new ArrayList<Unit>();
        class Player{}
        public void AddUnit(Unit unit){
            Units.add(unit);
        }
        public void DestroyUnit(Unit unit){
            Units.remove(unit);
        }
        class Tactics implements IAttack {

        }
    }
}
