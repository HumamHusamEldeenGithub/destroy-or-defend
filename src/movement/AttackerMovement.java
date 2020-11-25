package movement;

import unit.Unit;
import unit.UnitPosition;

public class AttackerMovement extends Movement {
    private static AttackerMovement attackerMovement=null ;
    private AttackerMovement(){}
    public synchronized static AttackerMovement getObj(){
        if (AttackerMovement.attackerMovement==null)
        {
            synchronized (AttackerMovement.class){
                if (AttackerMovement.attackerMovement==null)
                    AttackerMovement.attackerMovement = new AttackerMovement() ;
            }
        }
        return AttackerMovement.attackerMovement ;
    }
    @Override
    public void move(Unit unit) {
        /*UnitPosition unitPosition = Pathfinder.getPath() ;
        if (Grid.AcceptUnitMovement(unit , unitPosition.getCenterX() , unitPosition.getCenterY))
        {

        }*/
    }
}
