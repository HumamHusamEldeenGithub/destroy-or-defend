package Utilitiy;

import gameManager.DoDGameManager;
import gameManager.GameState;

public class StopWatch {
    double elapsedTime = 0;
    double StartTime;
    boolean IsPaused = false;
    public StopWatch(){

    }
    public void Start(){
        StartTime = System.nanoTime();
    }
    public void Update(){
        if(DoDGameManager.GetState()== GameState.Paused && !IsPaused){

        }
    }
}
