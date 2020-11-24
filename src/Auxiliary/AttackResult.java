package Auxiliary;

public abstract class AttackResult {
    AttackResult attackResult;
    public AttackResult(AttackResult attackResult){
        this.attackResult = attackResult;
    }
    AttackResult(){

    }
    public abstract void ApplyAttackResultOnAttacker();
    public abstract void ApplyAttackResultOnDefender();
}
