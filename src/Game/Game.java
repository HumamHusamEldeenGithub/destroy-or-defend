package Game;

import Arena.Arena;
import Enums.TacticsTypes;
import Interfaces.IAttack;
import Units.Unit;

import java.util.ArrayList;

public class Game {

    class Team {
        ArrayList<Unit> Units = new ArrayList<Unit>();
        Tactics TeamTactics = new Tactics(TacticsTypes.Randomly);
        class Player{}
        class Tactics  {
            TacticsTypes TeamTactic = null ;
            public Tactics(TacticsTypes tacticsTypes)
            {
                TeamTactic = tacticsTypes ;
            }
        }
        public void AddUnit(Unit unit){
            Units.add(unit);
        }
        public void DestroyUnit(Unit unit){
            Units.remove(unit);
        }

    }
}
