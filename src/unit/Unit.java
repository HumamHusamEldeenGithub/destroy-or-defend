package unit;

import Utilitiy.Position;
import movement.AttackerMovement;
import movement.Movement;
import unitProperty.*;

import java.util.ArrayList;

public class Unit implements UnitDestroyObserver {
    //Props
    public Unit _next ,_prev ;
    AttackType activeAttackType;
    ArrayList<UnitType> canAttack  ;
    Movement movement ;
    Position position ;
    Unit targetedUnit ;
    UnitDestroyObserver[] unitDestroyObservers ;
    ArrayList<UnitProperty> unitProperties ;
    UnitType unitType ;
    UnitDestroyObserver destructionObserver ;
    public Unit (UnitType type , ArrayList<UnitProperty> list , Movement movementType , ArrayList<UnitType> canTarget)
    {
        this.unitType = type ;
        unitProperties = list ;
        movement = movementType ;
        canAttack = canTarget ;
    }


    public DamageUnitProperty GetDamage(){
        for(UnitProperty unitProperty : unitProperties){
            if(DamageUnitProperty.class.isInstance(unitProperty)){
                return (DamageUnitProperty) unitProperty;
            }
        }
        return null;
    }

    public HealthUnitProperty GetHealth(){
        for(UnitProperty unitProperty : unitProperties){
            if(HealthUnitProperty.class.isInstance(unitProperty)){
                return (HealthUnitProperty) unitProperty;
            }
        }
        return null;
    }

    public SizeUnitProperty GetSize(){
        for(UnitProperty unitProperty : unitProperties){
            if(SizeUnitProperty.class.isInstance(unitProperty)){
                return (SizeUnitProperty) unitProperty;
            }
        }
        return null;
    }
    public RangeUnitProperty GetRange()
    {
        for(UnitProperty unitProperty : unitProperties){
            if(RangeUnitProperty.class.isInstance(unitProperty)){
                return (RangeUnitProperty) unitProperty;
            }
        }
        return null;
    }

    public Unit(){

    }
    public void  AcceptDamage (double damage){

    }
    public boolean AttackUnit (Unit targetUnit ){
        this.targetedUnit = targetUnit ;
       // this.activeAttackType = attackType;
        double Damage = 0;
        for(UnitProperty property : unitProperties){
            if(DamageUnitProperty.class.isInstance(property)){
                Damage = property.GetValue();
                break;
            }
        }
        return this.activeAttackType.PerformAttack(this.targetedUnit,Damage);
    }
    public void onDestroy (){

    }
    public void addUnitObserver(UnitDestroyObserver unitDestroyObserver){

    }
    public void onUnitDestroy(Unit destroyedUnit) {
    }
    public void SetPosition (Position position )
    {
        this.position = position ;
    }
    public Position getPosition() {
        return position;
    }
    public Unit CheckRange ()
    {
        return null ;
    }
}
