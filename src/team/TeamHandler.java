package team;

import gameManager.DoDGameManager;
import gameManager.GameState;
import player.Player;
import player.PlayerHandler;

import java.util.ArrayList;
import java.util.List;

public class TeamHandler extends Thread {
    List<PlayerHandler> Players = new ArrayList<PlayerHandler>();
    public TeamHandler(List<PlayerHandler> Playeres){
        this.Players = Playeres;
    }
    @Override
    public void run() {
        for(PlayerHandler handler : Players){
            handler.start();
            try {
                handler.join();
            }
            catch (Exception e){
                System.err.println("Error joining player thread");
            }
        }
    }
}
