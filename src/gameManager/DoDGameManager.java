package gameManager;

import Arena.Grid;
import Utilitiy.Position;
import player.Player;
import player.PlayerType;
import team.Team;
import unit.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DoDGameManager extends GameManager implements UnitDestroyObserver {
    Grid grid ;
    public static Unit mainBase ;
    int remainingAttackerUnits ;
    double remainingTime ;
    Team[] teams ;
    UnitFactory unitFactory ;

    private static DoDGameManager doDGameManager=null ;
    private DoDGameManager() throws FileNotFoundException {
        unitFactory = new UnitFactory() ;
        grid = Grid.GetGrid() ;
        grid.Initialize(100,5);

    }

    public synchronized static DoDGameManager getObj() throws FileNotFoundException {
        if (DoDGameManager.doDGameManager==null)
        {
            synchronized (DoDGameManager.class){
                if (DoDGameManager.doDGameManager==null)
                    DoDGameManager.doDGameManager = new DoDGameManager() ;
            }
        }
        return DoDGameManager.doDGameManager ;
    }

    //Methods
    public void onUnitDestroy(Unit destroyedUnit){

    }
    public void BuyUnit(Player player , int x , int y , UnitType unitType){

    }
    public synchronized Position getBasePosition(){
        return mainBase.getPosition() ;
    }


    public static void  main(String[] args) throws FileNotFoundException, InterruptedException {
        DoDGameManager Game = new DoDGameManager() ;
        Unit unit= Game.unitFactory.CreateUnit(UnitType.TeslaTank, PlayerType.Attacker) ;
        mainBase = Game.unitFactory.CreateUnit(UnitType.MainBase, PlayerType.Defender) ;
        mainBase.SetPosition(new Position(100,100));
        unit.SetPosition(new Position(0,0));
        UnitHandler.CreateThread(unit);
    }

}
