package unit;

import Utilities.Position;
import movement.Movement;
import unitProperty.DamageUnitProperty;
import unitProperty.HealthUnitProperty;
import unitProperty.SizeUnitProperty;
import unitProperty.UnitProperty;

public class Unit implements UnitDestroyObserver {
    //Props
    public Unit _next ,_prev ;
    AttackType activeAttackType;
    UnitType[] canAttack  ;
    Movement movement ;
    Position position ;
    Unit targetedUnit ;
    UnitDestroyObserver[] unitDestroyObservers ;
    UnitProperty[] unitProperties ;
    UnitType unitType ;
    UnitDestroyObserver destructionObserver ;

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
    public void SetPosition (int x , int y )
    {
        this.position = new Position(x , y ) ;
    }
    public Position getPosition() {
        return position;
    }
    public Unit CheckRange ()
    {
        return null ;
    }
}
