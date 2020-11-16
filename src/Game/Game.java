package Game;

import Enums.TacticsTypes;
import Tactics.RandomlyTactic;
import Tactics.Tactic;
import Units.Forces;
import Units.Unit;
import Utilities.UnitsHandler;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public Team[] teams = new Team[2] ;
    public Game(){
        teams[0] = new Team() ;
        teams[1] = new Team() ;
    }
    class Team {
        TacticsTypes tacticsTypes = TacticsTypes.Randomly ;
        ArrayList<Unit> Units = new ArrayList<>();
        public ArrayList<Player> players = new ArrayList<>() ;
        class Player{

            Tactic PlayerTactic = new RandomlyTactic() ;
            public synchronized Tactic GetTacticObject(){return PlayerTactic ; }
            public void AddUnit(Unit unit){
                Units.add(unit);
            }
            public void Start ()
            {
                for (Unit unit:Units)
                {
                    UnitsHandler unitsHandler = new UnitsHandler(unit) ;
                    unitsHandler.start();
                }
            }
            public void DestroyUnit(Unit unit){
                Units.remove(unit);
            }
        }




    }
}
