package unit;

import Arena.GridCell;
import Arena.Grid;
import Strategies.AttackStrategy;
import Utilitiy.Logger;
import Utilitiy.Position;
import Utilitiy.StopWatchPool;
import Utilitiy.UniqueIDGenerator;
import player.PlayerType;
import unitProperty.*;

import java.util.ArrayList;
import java.util.List;

public class Unit {
    //Props
    AttackType activeAttackType = new NormalAttackType() ;
    List<UnitType> canAttack;
    Logic logic;
    Position position =null  ;
    Position initializePosition;
    Unit targetedUnit ;
    List<UnitProperty> unitProperties ;
    UnitType unitType;
    PlayerType playerType ;
    AttackStrategy strategy;
    double SpeedMovementEffectorValue = 1 ;
    String uniqueId=null ;
    boolean isAlive  ;
    Unit(){

    }

    public Unit (UnitType type ,PlayerType playerType , ArrayList<UnitProperty> list , Logic logic, List<UnitType> canTarget, AttackStrategy strategy)
    {
        this.logic = logic;
        this.unitType = type ;
        this.playerType=playerType ;
        this.strategy = strategy;
        this.unitProperties = list;
        this.canAttack =  canTarget;
        this.uniqueId = UniqueIDGenerator.GenerateID(this.unitType) ;
        this.isAlive = true ;
        StopWatchPool pool = StopWatchPool.GetObj();
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

    public String GetUniqueId()
    {
        return this.uniqueId;
    }
    public boolean GetIsAlive() {return this.isAlive ; }

    public UnitType GetType() {
        return this.unitType;
    }

    public PlayerType GetPlayerType(){return this.playerType ; }

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
                System.out.println("Damaged");
                if(property.GetValue() <= 0){
                    this.Destroy();
                }
            }
        }
    }

    public void AttackUnit (Unit targetUnit){
        this.targetedUnit = targetUnit;
        double Damage = GetDamage().GetValue();
        if(this.activeAttackType.PerformAttack(this.targetedUnit, Damage)){
            Logger.Damagelog(this,targetUnit,Damage);
        }
    }

    public void Execute() {

        logic.Execute(this);
    }

    public void Destroy (){

        Grid.RemoveUnit(this);
        Logger.Deadlog(this);
        this.isAlive=false ;
    }

    public void SetPosition (Position position ) {
        if (this.position==null)
        {
            this.initializePosition = position ;
        }
        this.position = position ;
        //moveGUIObserver.update();
    }
//    public void SetMoveGUIObserver (MoveGUIObserver moveGUIObserver)
//    {this.moveGUIObserver = moveGUIObserver ; }
    public void SetSpeedMovementEffectorValue (double value)
    {
        this.SpeedMovementEffectorValue = value ;
    }
    public double GetSpeedMovementEffectorValue(){return this.SpeedMovementEffectorValue ; }
    public Logic GetLogic(){return this.logic ; }

    public List<Unit> CheckRange (){
        List<Unit> ToAttack = new ArrayList<Unit>();
        for(int i=position.Get_X() - (int)GetRange().GetValue();i<=position.Get_X()+ (int)GetRange().GetValue();i++){
            for(int j = position.Get_Y() - (int)GetRange().GetValue() ;j<=position.Get_Y() + (int)GetRange().GetValue();j++){
                GridCell curGridCell = Grid.GetCell(new Position(i,j));
                if(curGridCell !=null){
                    for(Unit target : curGridCell.GetUnits()){
                        if(this == target||target.GetPlayerType()==this.playerType)
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
