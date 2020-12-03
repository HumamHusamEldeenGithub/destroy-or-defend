package unit;

import Arena.Grid;
import Utilitiy.Position;
import movement.AttackerMovement;
import movement.Movement;
import unitProperty.*;

import java.util.ArrayList;

public class Unit {
    //Props
    public Unit _next ,_prev ;
    AttackType activeAttackType;
    ArrayList<UnitType> canAttack  ;
    public Movement movement ;
    Position position ;
    Unit targetedUnit ;
    ArrayList<UnitProperty> unitProperties ;
    UnitType unitType;
    public Unit (UnitType type , ArrayList<UnitProperty> list , Movement movementType , ArrayList<UnitType> canTarget)
    {
        this.unitType = type ;
        unitProperties = (ArrayList<UnitProperty>) list.clone();
        movement = movementType ;
        canAttack = (ArrayList<UnitType>) canTarget.clone();
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

    public PriceUnitProperty GetPrice(){
        for(UnitProperty unitProperty : unitProperties){
            if(PriceUnitProperty.class.isInstance(unitProperty)){
                return (PriceUnitProperty) unitProperty;
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
    public void AcceptDamage (double damage){
        double armor = 0;
        for(UnitProperty property : unitProperties){
            if(ArmorUnitProperty.class.isInstance(property)){
                armor = property.GetValue();
            }
        }
        double FinalDamage = damage - damage*armor;
        for(UnitProperty property : unitProperties){
            if(HealthUnitProperty.class.isInstance(property)){
                property.SetValue(property.GetValue() - FinalDamage);
                if(property.GetValue() <= 0){
                    this.Destroy();
                }
            }
        }
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
    public void Move()
    {
       movement.move(this);
    }
    public void Destroy (){
        Grid.RemoveUnit(this);
    }
    public void SetPosition (Position position )
    {
        this.position = position ;
        Grid.addUnit(this);
    }
    public Position getPosition() {
        return position;
    }
    public Unit CheckRange (){
        return null;
    }
}
