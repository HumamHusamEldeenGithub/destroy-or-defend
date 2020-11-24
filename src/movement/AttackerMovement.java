package movement;

public class AttackerMovement {
    private static AttackerMovement attackerMovement=null ;
    private AttackerMovement(){

    }
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
}
