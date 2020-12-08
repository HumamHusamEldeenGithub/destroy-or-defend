package player;

import Arena.Grid;
import Strategies.*;
import unit.AttackType;
import unit.Unit;
import unit.UnitFactory;
import unit.UnitType;

import java.util.ArrayList;
import java.util.List;

public class Player {
    protected int teamId;
    protected int coins;
    protected PlayerType playerType = null;
    AttackStrategy strategy = null;
    List<Unit> MyUnits = new ArrayList<Unit>();
    int CurrentUnit = 0;
    public Player(PlayerType playerType, int teamId, AttackStrategy strategy){
        this.playerType = playerType;
        this.teamId = teamId;
        this.strategy = strategy;
        coins = 1000;
    }
    public boolean  BuyUnit(UnitType unitType){
        if (coins<UnitFactory.getPrice(unitType))
            return false ;
        Unit newUnit = UnitFactory.CreateUnit(unitType,playerType,strategy);
        MyUnits.add(newUnit);
        coins -= UnitFactory.getPrice(unitType);
        return true ;
    }
    public List<Unit> GetUnits(){
        return MyUnits;
    }
    public PlayerType GetType(){ return playerType; }
    public AttackStrategy GetStrategy(){ return strategy; }
    public int GetCoins(){
        return coins;
    }

    public void MakeMove(){
        if(MyUnits.get(CurrentUnit).GetHealth().GetValue()>0)
            MyUnits.get(CurrentUnit).Execute();
        else{
            MyUnits.remove(CurrentUnit);
        }
        CurrentUnit++;
        if(CurrentUnit>=MyUnits.size())
            CurrentUnit = 0;
    }

    public void PutOnArena(){
        for(Unit unit : MyUnits){
            Grid.addUnit(unit);
        }
    }

    public boolean HasLost(){
        if(MyUnits.size()==0){
            return true;
        }
        return false;
    }
    public synchronized void SetStrategy(Strategies strategies){
        if (strategies==Strategies.HighestDamageAttack)
            this.strategy = HighestDamageAttackStrategy.getObj();
        else if (strategies==Strategies.LowestHealthAttack)
            this.strategy = LowestHealthAttackStrategy.getObj() ;
        else
            this.strategy = RandomAttackStrategy.getObj() ;
    }
    public int GetId(){
        return teamId;
    }
}