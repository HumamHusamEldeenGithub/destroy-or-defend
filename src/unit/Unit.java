package unit;

public class Unit implements UnitDestroyObserver {
    //Props
    Unit _next ,_prev ;
    UnitAttack activeUnitAttac ;
    UnitType[] canAttack  ;
    Movement movement ;
    UnitPosition position ;
    Unit targetedUnit ;
    UnitDestroyObserver[] unitDestroyObservers ;
    UnitProperty[] unitProperties ;
    UnitType unitType ;
    UnitDestroyObserver destructionObserver ;


    //Methods
    public Unit(){

    }
    public void  AcceptDamage (double damage){

    }
    public void AttackUnit (Unit targetUnit , UnitAtack unitAtack){

    }
    public void onDestroy (){

    }
    public void addUnitObserver(UnitDestroyObserver unitDestroyObserver){

    }
    public void onUnitDestroy(Unit destroyedUnit) {
    }
}
