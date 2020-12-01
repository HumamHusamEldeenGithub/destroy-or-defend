package unit;

public class AreaOfDestructionAttackType extends AttackType {

    public AreaOfDestructionAttackType(AttackType type){
        super(type);
    }

    @Override
    public boolean PerformAttack(Unit unit, double Damage) {
        return false;
    }

}