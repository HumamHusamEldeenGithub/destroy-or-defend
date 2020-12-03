package movement;

import Arena.Grid;
import Utilitiy.* ;
import Utilitiy.Position;
import gameManager.DoDGameManager;
import unit.Unit;

public class AttackerMovement extends Movement {
    private static AttackerMovement attackerMovement=null ;
    private AttackerMovement(){}
    public synchronized static Movement getObj(){
        if (AttackerMovement.attackerMovement==null)
        {
            synchronized (AttackerMovement.class){
                if (AttackerMovement.attackerMovement==null)
                    AttackerMovement.attackerMovement = new AttackerMovement() ;
            }
        }
        return AttackerMovement.attackerMovement ;
    }

    public synchronized void move(Unit unit) {
        Position unitPosition = PathFinder.GetObj().GetPos(unit.getPosition(), DoDGameManager.mainBase.getPosition(),unit.GetRange().GetValue(), unit.GetSize().GetValue() ) ;
        if (unitPosition!=null)
        {
            System.out.println(unitPosition);
            unit.SetPosition(unitPosition);
            Grid.addUnit(unit) ;
        }
        else
            System.out.println("Error");
    }
}
