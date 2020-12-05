package FXStuff;

import Utilitiy.PathFinder;
import gameManager.DoDGameManager;
import gameManager.GameState;

public class GameRunner {
    public static void StartGame(){
        DoDGameManager.StartGame();
        while (DoDGameManager.GetState()!= GameState.AttackerWon && DoDGameManager.GetState() != GameState.DefenderWon)
            DoDGameManager.UpdateGame();
    }
    public GameRunner(){}
    static GameRunner runner;
    public synchronized static GameRunner GetObj (){
        if (GameRunner.runner==null)
        {
            synchronized (GameRunner.class)
            {
                if (GameRunner.runner==null)
                    runner = new GameRunner() ;
            }
        }
        return GameRunner.runner ;
    }
}
