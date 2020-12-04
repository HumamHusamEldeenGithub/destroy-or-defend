package movement;

import unit.Unit;

import java.util.List;

public class DefenderMovement extends Movement {
    private static DefenderMovement defenderMovement =null ;
    private DefenderMovement(){}
    public synchronized static Movement getObj() {
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
        /*
        Unit nextAttackUnit = null;
        if(!unit.ReAttack()){
            List<Unit> ToAttack = unit.CheckRange();
            nextAttackUnit = unit.GetPrioritizedUnit(ToAttack);
        }
        if(nextAttackUnit!=null){
            unit.AttackUnit(nextAttackUnit);
        }

         */
    }
}
