package Game;
import Arena.Arena;
import Enums.TacticsTypes;
import Tactics.RandomlyTactic;
import Tactics.Tactic;
import Units.Forces;
import Units.Unit;
import Utilities.Position;
import Utilities.UnitsHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Game {
    public class Team {
        List<Unit> Units = new ArrayList<>();
        public class Player{
            Tactic PlayerTactic = new RandomlyTactic();
            public synchronized Tactic GetTacticObject(){ return PlayerTactic ; }
            public boolean AddUnit(Unit unit, Position position){
                if(Arena.Deploy(unit,position)){
                    Units.add(unit);
                    return true;
                }
                return false;
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
        List<Player> players = new ArrayList<>() ;
        public Player AddPlayer(){
            Player player = new Player();
            players.add(player);
            return player;

        }
        public List<Player> GetPlayerList(){
            return (Collections.unmodifiableList(players));
        }
    }
    List<Team> teams = new ArrayList<>() ;
    public Game(){
        teams.add(new Team());
        teams.add(new Team());
    }
    public List<Team> GetTeamList(){
        return (Collections.unmodifiableList(teams));
    }
}
