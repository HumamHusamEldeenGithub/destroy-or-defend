package unit;

import movement.Movement;
import unitProperty.DamageUnitProperty;
import unitProperty.HealthUnitProperty;
import unitProperty.UnitProperty;

public class Unit implements UnitDestroyObserver {
    //Props
    public Unit _next ,_prev ;
    UnitAttack activeUnitAttack ;
    UnitType[] canAttack  ;
    Movement movement ;
    UnitPosition position ;
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
    //Methods
    public Unit(){

    }
    public void  AcceptDamage (double damage){

    }
    public void AttackUnit (Unit targetUnit , UnitAttack unitAttack){
        this.targetedUnit = targetUnit ;
        this.activeUnitAttack =unitAttack ;
        this.activeUnitAttack.PerformAttack(this.targetedUnit) ;
    }
    public void onDestroy (){

    }
    public void addUnitObserver(UnitDestroyObserver unitDestroyObserver){

    }
    public void onUnitDestroy(Unit destroyedUnit) {
    }

    public UnitPosition getPosition() {
        return position;
    }
}
