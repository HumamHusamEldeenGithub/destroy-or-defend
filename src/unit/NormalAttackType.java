package unit;

public class NormalAttackType extends AttackType {
    public NormalAttackType(){

    }
    @Override
    public boolean PerformAttack(Unit unit,double Damage) {
        if(unit!=null && unit.GetHealth().GetValue()!=0){
            unit.AcceptDamage(Damage);
            return true;
        }
        return false;
    }
}
