package player;

public abstract class Player {
    protected int teamId;
    protected int coins;
    public abstract void NewGame();
    public abstract void LoadGame();
    public void buyUnit(){}
}
