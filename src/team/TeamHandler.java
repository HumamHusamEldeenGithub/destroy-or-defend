package team;

import gameManager.DoDGameManager;
import gameManager.GameState;
import player.Player;
import player.PlayerHandler;
import unit.UnitType;

import java.util.ArrayList;
import java.util.List;

public class TeamHandler extends Thread {
    List<Player> Players = new ArrayList<Player>();
    public TeamHandler(List<Player> Playeres){
        this.Players = Playeres;
    }
    @Override
    public void run() {
        while (DoDGameManager.GetState()!=GameState.AttackerWon && DoDGameManager.GetState()!=GameState.DefenderWon && Players.size()>0) {
            for (Player player : Players) {
                if(player.HasLost()) {
                    Players.remove(player);
                    break;
                }
                PlayerHandler handler = new PlayerHandler(player);
                handler.start();
            }
            DoDGameManager.UpdateGame();
        }
    }
}
