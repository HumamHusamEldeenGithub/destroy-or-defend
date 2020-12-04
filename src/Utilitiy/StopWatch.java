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
            elapsedTime+= System.nanoTime() - StartTime;
        }
        else if(DoDGameManager.GetState()==GameState.Running && IsPaused){
            StartTime = System.nanoTime();
        }
    }
    public double GetElapsedSeconds(){
        if(DoDGameManager.GetState()==GameState.Paused){
            return elapsedTime / 1e9;
        }
        return elapsedTime + (System.nanoTime() - StartTime) / 1e9;
    }

    public void Reset(){
        elapsedTime = 0;
    }
}
