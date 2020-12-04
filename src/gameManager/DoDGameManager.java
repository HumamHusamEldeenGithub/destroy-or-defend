package gameManager;

import Arena.Grid;
import Utilitiy.Position;
import player.Player;
import player.PlayerHandler;
import player.PlayerType;
import team.TeamHandler;
import unit.*;

import java.util.ArrayList;
import java.util.List;

public class DoDGameManager extends GameManager {
    static Grid grid;
    static double remainingTime ;
    static TeamHandler Attackers;
    static TeamHandler Defenders;
    static GameState state;
    static UnitFactory unitFactory ;
    private static DoDGameManager doDGameManager=null ;
    private static long StartTime=0;
    private static long OldElapsedTime=0;
    private static UnitFactory factory;
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
    }

    public static void Initialize(){
        grid = Grid.GetGrid();
        grid.Initialize(1000,200);
        factory = UnitFactory.GetObj();
        UnitFactory.LoadData();
    }

    public static void InitializePlayers(List<Player> Players){
        StartTime = System.nanoTime();
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

    public static void UpdateGame(){
        if(!Attackers.isAlive()){
            state = GameState.DefenderWon;
        }
        else if(!Defenders.isAlive()){
            state = GameState.AttackerWon;
            System.out.println("Win");
        }
    }

}
