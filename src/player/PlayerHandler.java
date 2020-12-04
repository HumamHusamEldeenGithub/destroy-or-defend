package player;

import unit.Unit;

public class PlayerHandler extends Thread {
    private Player player;
    private PlayerHandler() {
    }
    public PlayerHandler(Player player)
    {
        this.player = player;
    }

    @Override
    public void run() {
        player.MakeMove();
    }
}
