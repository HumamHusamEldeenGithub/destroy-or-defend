package gameManager;

import player.Player;
import team.Team;
import unit.*;

public class DoDGameManager extends GameManager implements UnitDestroyObserver {
    //Grid grid ;
    Unit mainBase ;
    int remainingAttackerUnits ;
    double remainingTime ;
    Team[] teams ;
    UnitFactory unitFactory ;

    private static DoDGameManager doDGameManager=null ;
    private DoDGameManager(){}

    public synchronized static DoDGameManager getObj(){
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
    public synchronized UnitPosition getBasePosition(){
        return this.mainBase.getPosition() ;
    }

}
