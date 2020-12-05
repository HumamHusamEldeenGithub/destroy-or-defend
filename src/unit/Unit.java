package unit;

import Arena.Cell;
import Arena.Grid;
import Strategies.AttackStrategy;
import Utilitiy.Position;
import Utilitiy.StopWatch;
import Utilitiy.StopWatchPool;
import movement.AttackerMovement;
import movement.Movement;
import unitProperty.*;

import java.util.ArrayList;
import java.util.List;

public class Unit {
    //Props
    public Unit _next ,_prev ;
    AttackType activeAttackType = new NormalAttackType() ;
    List<UnitType> canAttack;
    public Movement movement ;
    Position position ;
    Unit targetedUnit ;
    List<UnitProperty> unitProperties ;
    UnitType unitType;
    AttackStrategy strategy;
    StopWatch AttackStopWatch;
    StopWatch MoveStopWatch;
    Unit(){

    }

    public Unit (UnitType type , ArrayList<UnitProperty> list , Movement movementType , List<UnitType> canTarget, AttackStrategy strategy)
    {
        this.unitType = type ;
        this.strategy = strategy;
        unitProperties = list;
        movement = movementType ;
        canAttack =  canTarget;
        AttackStopWatch = new StopWatch();
        MoveStopWatch = new StopWatch();
        StopWatchPool pool = StopWatchPool.GetObj();
        pool.AddObj(AttackStopWatch);
        pool.AddObj(MoveStopWatch);
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

    public RangeUnitProperty GetRange() {
        for(UnitProperty unitProperty : unitProperties){
            if(RangeUnitProperty.class.isInstance(unitProperty)){
                return (RangeUnitProperty) unitProperty;
            }
        }
        return null;
    }

    public AttackSpeedUnitProperty GetAttackSpeed() {
        for(UnitProperty unitProperty : unitProperties){
            if(AttackSpeedUnitProperty.class.isInstance(unitProperty)){
                return (AttackSpeedUnitProperty) unitProperty;
            }
        }
        return null;
    }

    public MovementSpeedUnitProperty GetMovementSpeed() {
        for(UnitProperty unitProperty : unitProperties){
            if(MovementSpeedUnitProperty.class.isInstance(unitProperty)){
                return (MovementSpeedUnitProperty) unitProperty;
            }
        }
        return null;
    }

    public UnitType GetType() {
        return unitType;
    }

    public Position GetPosition() {
        return position;
    }

    public Unit GetPrioritizedUnit(List<Unit> Units){
        return  strategy.PrioritizeUnitToAttack(Units);
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

    public boolean AttackUnit (Unit targetUnit){
        if(AttackStopWatch.GetElapsedSeconds() >= 1/GetAttackSpeed().GetValue()) {
            this.targetedUnit = targetUnit;
            double Damage = GetDamage().GetValue();
            boolean success = this.activeAttackType.PerformAttack(this.targetedUnit, Damage);
            if(success) {
                System.out.println(AttackStopWatch.GetElapsedSeconds());
                AttackStopWatch.Reset();
            }
            AttackStopWatch.Start();
            return success;
        }
        else
            AttackStopWatch.Start();
        return (targetUnit!=null && targetUnit.GetHealth().GetValue()>0);
    }

    public void Move() {
        Unit nextAttackUnit = null;
        boolean hasReattacked = false;
        hasReattacked = this.ReAttack();
        if(!hasReattacked){
            List<Unit> ToAttack = this.CheckRange();
            nextAttackUnit = this.GetPrioritizedUnit(ToAttack);
        }
        if(nextAttackUnit!=null){
            this.AttackUnit(nextAttackUnit);
        }
        else if(!hasReattacked && AttackerMovement.class.isInstance(movement)){
            if (MoveStopWatch.GetElapsedSeconds() >= 1 / GetMovementSpeed().GetValue()) {
                movement.move(this);
                MoveStopWatch.Reset();
                System.out.println(position);
            }
            MoveStopWatch.Start();
        }
    }

    public void Destroy (){
        Grid.RemoveUnit(this);
    }

    public void SetPosition (Position position ) { this.position = position ; }

    public boolean ReAttack(){
        return AttackUnit(targetedUnit);
    }

    public List<Unit> CheckRange (){
        List<Unit> ToAttack = new ArrayList<Unit>();
        for(int i=position.Get_X() - (int)GetRange().GetValue();i<=position.Get_X()+ (int)GetRange().GetValue();i++){
            for(int j = position.Get_Y() - (int)GetRange().GetValue() ;j<=position.Get_Y() + (int)GetRange().GetValue();j++){
                Cell curCell = Grid.CheckCell(new Position(i,j));
                if(curCell!=null){
                    for(Unit target : curCell.GetUnits()){
                        if(this == target)
                            continue;
                        for(UnitType type : this.canAttack){
                            if(type == target.GetType()){
                                ToAttack.add(target);
                            }
                        }
                    }
                }
            }
        }
        return ToAttack;
    }
}
