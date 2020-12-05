package Utilitiy;

import gameManager.DoDGameManager;
import gameManager.GameState;

public class StopWatch {
    double elapsedTime = 0;
    double StartTime;
    boolean IsPaused = false;
    boolean Initialized = false;
    public StopWatch(){

    }
    public void Start(){
        if(!Initialized)
            StartTime = System.nanoTime();
        Initialized = true;
    }
    public void Update(){
        if(DoDGameManager.GetState()== GameState.Paused && !IsPaused){
            elapsedTime+= System.nanoTime() - StartTime;
            IsPaused = true;
        }
        else if(DoDGameManager.GetState()==GameState.Running && IsPaused){
            StartTime = System.nanoTime();
            IsPaused = false;

        }
    }
    public double GetElapsedSeconds(){
        if(DoDGameManager.GetState()==GameState.Paused){
            return elapsedTime / 1e9;
        }
        return elapsedTime/1e9 + (System.nanoTime() - StartTime) / 1e9;
    }

    public void Reset(){
        elapsedTime = 0;
        StartTime = System.nanoTime();
    }
}
