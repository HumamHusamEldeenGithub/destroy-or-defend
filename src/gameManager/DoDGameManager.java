package gameManager;

import Utilities.Position;
import player.Player;
import team.Team;
import unit.*;

import java.io.FileNotFoundException;

public class DoDGameManager extends GameManager implements UnitDestroyObserver {
    //Grid grid ;
    Unit mainBase ;
    int remainingAttackerUnits ;
    double remainingTime ;
    Team[] teams ;
    UnitFactory unitFactory ;

    private static DoDGameManager doDGameManager=null ;
    private DoDGameManager() throws FileNotFoundException {
        unitFactory = new UnitFactory() ;

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
        return this.mainBase.getPosition() ;
    }


    public static void main(String[] args) throws FileNotFoundException {
        DoDGameManager Game = new DoDGameManager() ;
        Unit unit= Game.unitFactory.CreateUnit(UnitType.TeslaTank) ;
        unit.SetPosition(0,0);
        UnitHandler unitHandler = new UnitHandler(unit) ;
       // unitHandler.run();
    }

}
