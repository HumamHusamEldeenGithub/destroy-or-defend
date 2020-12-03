package gameManager;

import Arena.Grid;
import Utilitiy.Position;
import player.Player;
import player.PlayerHandler;
import player.PlayerType;
import team.Team;
import team.TeamHandler;
import unit.*;

import java.util.ArrayList;
import java.util.List;

public class DoDGameManager extends GameManager {
    static Grid grid ;
    public static Unit mainBase ;
    static double remainingTime ;
    static TeamHandler Attackers;
    static TeamHandler Defenders;
    static GameState state;
    static UnitFactory unitFactory ;
    private static DoDGameManager doDGameManager=null ;
    private static long StartTime=0;
    private static long OldElapsedTime=0;
    private DoDGameManager() {
        unitFactory = UnitFactory.GetObj() ;
        grid = Grid.GetGrid() ;
        grid.Initialize(100,5);
    }

    public synchronized static DoDGameManager getObj() {
        if (DoDGameManager.doDGameManager==null)
        {
            synchronized (DoDGameManager.class){
                if (DoDGameManager.doDGameManager==null)
                    DoDGameManager.doDGameManager = new DoDGameManager() ;
            }
        }
        return DoDGameManager.doDGameManager ;
    }

    //Methods
    public static synchronized Position getBasePosition(){
        return mainBase.getPosition() ;
    }

    public static void Initialize(ArrayList<Player> Players){
        StartTime = System.nanoTime();
        List<PlayerHandler> Attackers = new ArrayList<PlayerHandler>();
        List<PlayerHandler> Defenders = new ArrayList<PlayerHandler>();
        DoDGameManager.Attackers = new TeamHandler(Attackers);
        DoDGameManager.Defenders = new TeamHandler(Defenders);
        for(Player player : Players){
            if(player.GetType()==PlayerType.Attacker){
                Attackers.add(new PlayerHandler(player));
            }
            else
                Defenders.add(new PlayerHandler(player));
        }
    }

    public static void Pause_Unpause(){
        if(DoDGameManager.state == GameState.Running){
            OldElapsedTime += System.nanoTime() - StartTime;
            DoDGameManager.state = GameState.Paused;
        }
        else{
            StartTime = System.nanoTime();
            DoDGameManager.state = GameState.Running;
        }
    }
    public static void  main(String[] args) {
        DoDGameManager Game = DoDGameManager.getObj() ;
        Unit unit= unitFactory.CreateUnit(UnitType.TeslaTank, PlayerType.Attacker) ;
        mainBase = unitFactory.CreateUnit(UnitType.MainBase, PlayerType.Defender) ;
        TeamHandler teamHandler = new TeamHandler(new ArrayList<PlayerHandler>());
    }

}
