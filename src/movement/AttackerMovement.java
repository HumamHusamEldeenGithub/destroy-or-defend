package movement;

import Arena.Grid;
import Utilitiy.* ;
import Utilitiy.Position;
import gameManager.DoDGameManager;
import unit.Unit;

import java.util.List;

public class AttackerMovement extends Movement {
    private static AttackerMovement attackerMovement=null ;
    private AttackerMovement(){}
    public synchronized static Movement getObj(){
        if (AttackerMovement.attackerMovement==null) {
            synchronized (AttackerMovement.class){
                if (AttackerMovement.attackerMovement==null)
                    AttackerMovement.attackerMovement = new AttackerMovement() ;
            }
        }
        return AttackerMovement.attackerMovement ;
    }

    public synchronized void move(Unit unit) {
        Unit nextAttackUnit = null;
        if(!unit.ReAttack()){
            List<Unit> ToAttack = unit.CheckRange();
            nextAttackUnit = unit.GetPrioritizedUnit(ToAttack);
        }
        if(nextAttackUnit!=null){
            unit.AttackUnit(nextAttackUnit);
        }
        else {
            Position nextPos = PathFinder.GetObj().GetPos(unit.GetPosition(), Grid.getBasePosition(), unit.GetRange().GetValue(), unit.GetSize().GetValue());
            if (nextPos != null) {
                System.out.println(nextPos);
                Grid.RemoveUnit(unit);
                unit.SetPosition(nextPos);
                Grid.addUnit(unit);
            } else
                System.err.println("Error in finding path");
        }
    }
}
