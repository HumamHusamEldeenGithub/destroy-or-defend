package unit;

public class AreaOfDestructionAttackType extends AttackType {

    public AreaOfDestructionAttackType(){

    }

    @Override
    public boolean PerformAttack(Unit unit, double Damage) {
        return false;
    }

}