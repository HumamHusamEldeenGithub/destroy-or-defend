package unit;

public abstract class AttackType {
    AttackType attackType;
    AttackType(AttackType attackType){
        this.attackType = attackType;
    }
    AttackType(){

    }
    public abstract boolean PerformAttack(Unit unit,double Damage) ;
}
