package movement;

import gameManager.DoDGameManager;
import unit.Unit;
import unit.Position;

public class DefenderMovement extends Movement {
    private static DefenderMovement defenderMovement =null ;
    private DefenderMovement(){}
    public synchronized static DefenderMovement getObj() {
        if (DefenderMovement.defenderMovement==null)
        {
            synchronized (DefenderMovement.class){
                if (DefenderMovement.defenderMovement==null)
                    defenderMovement = new DefenderMovement() ;
            }
        }
        return DefenderMovement.defenderMovement ;
    }

    @Override
    public void move(Unit unit) {
        //Position BasePosition = DoDGameManager.getObj().getBasePosition() ;
    }
}
