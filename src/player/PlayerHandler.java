package player;

import unit.Unit;

public class PlayerHandler{
    private Player player;
    private PlayerHandler() {
    }
    public PlayerHandler(Player player)
    {
        this.player = player;
    }

    public void start() {
        player.MakeMove();
    }
}
