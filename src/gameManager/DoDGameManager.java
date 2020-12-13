package gameManager;

import Arena.Grid;
import Utilitiy.Position;
import Utilitiy.StopWatch;
import Utilitiy.StopWatchPool;
import player.Player;
import player.PlayerHandler;
import player.PlayerType;
import team.TeamHandler;
import unit.*;

import java.util.ArrayList;
import java.util.List;

public class DoDGameManager extends GameManager {
    static Grid grid;
    static StopWatchPool stopWatchPool;
    static double remainingTime = 90 ;
    static TeamHandler Attackers;
    static TeamHandler Defenders;
    static GameState state;
    static UnitFactory unitFactory ;
    private static DoDGameManager doDGameManager=null ;
    private static long StartTime=0;
    private static long OldElapsedTime=0;
    private static UnitFactory factory;
    private static StopWatch GameStopWatch;
    private DoDGameManager() {
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

    public synchronized static GameState GetState(){
        return state;
    }

    //Methods

    public static void StartGame(){
        state = GameState.Running;
        Attackers.start();
        Defenders.start();
        GameStopWatch.Start();
    }

    public synchronized static void AddStopWatch(StopWatch stopWatch){
        stopWatchPool.AddObj(stopWatch);
    }

    public static void Initialize(){
        grid = Grid.GetGrid();
        grid.Initialize(800,20);
        factory = UnitFactory.GetObj();
        UnitFactory.LoadData();
        stopWatchPool = StopWatchPool.GetObj();
        GameStopWatch = new StopWatch();
        stopWatchPool.AddObj(GameStopWatch);
    }

    public static void InitializePlayers(List<Player>Players){
        StartTime = System.nanoTime();//Check
        List<Player> Attackers = new ArrayList<Player>();
        List<Player> Defenders = new ArrayList<Player>();
        DoDGameManager.Attackers = new TeamHandler(Attackers);
        DoDGameManager.Defenders = new TeamHandler(Defenders);
        for(Player player : Players){
            if(player.GetType()==PlayerType.Attacker){
                Attackers.add(player);
            }
            else
                Defenders.add(player);
            player.PutOnArena();
        }
    }
    public synchronized static double GetTime(){
        return GameStopWatch.GetElapsedSeconds();
    }
    public synchronized static double GetRemainingTime() {return DoDGameManager.remainingTime ; }

    public synchronized static void Pause_Unpause(){
        if(DoDGameManager.state == GameState.Running){
            OldElapsedTime += System.nanoTime() - StartTime;
            DoDGameManager.state = GameState.Paused;
        }
        else{
            StartTime = System.nanoTime();
            DoDGameManager.state = GameState.Running;
        }
    }

    public synchronized static void UpdateGame(){
        stopWatchPool.UpdateObjs();
        if(state==GameState.Running) {
            if (!Attackers.isAlive()) {
                state = GameState.DefenderWon;
                System.out.println("Defenders Won");
            } else if (!Defenders.isAlive()||Grid.getBaseHealthValue()<=0) {
                state = GameState.AttackerWon;
                System.out.println("Attackers Won");
            }
            else if(GameStopWatch.GetElapsedSeconds() >= remainingTime){
                state = GameState.DefenderWon;
                System.out.println("Defenders Won");
            }
        }
    }

}
