package Units;

import Arena.Arena;
import Enums.UnitType;
import Interfaces.IAttack;
import Interfaces.IMove;
import Interfaces.ITarget;
import Tactics.Tactic;
import Utilities.Position;

import java.util.ArrayList;
import java.util.List;

public class Forces extends Unit implements IMove , ITarget, IAttack {

    public Forces(UnitType tyPe){
        this.type = tyPe;
        Initialize();
    }
    @Override
    public void Move(Tactic PlayerTactic) {
        List<Object> Enemies = PlayerTactic.SortByTactic(CheckRange()) ;
        if (Enemies.size()==0)
        {
            if(Arena.BasePosition.x > position.x){
                if(Arena.BasePosition.y > position.y){
                    if(Arena.Move(this,new Position(position.x+1,position.y+1))){
                        position.x++;
                        position.y++;
                    }
                }
                else if(Arena.BasePosition.y < position.y){
                    if(Arena.Move(this,new Position(position.x+1,position.y-1))){
                        position.x++;
                        position.y--;
                    }
                }
                else{
                    if(Arena.Move(this,new Position(position.x+1,position.y))){
                        position.x++;
                    }
                }
            }
            else if(Arena.BasePosition.x < position.x){
                if(Arena.BasePosition.y > position.y){
                    if(Arena.Move(this,new Position(position.x-1,position.y+1))){
                        position.x--;
                        position.y++;
                    }
                }
                else if(Arena.BasePosition.y < position.y){
                    if(Arena.Move(this,new Position(position.x-1,position.y-1))){
                        position.x--;
                        position.y--;
                    }
                }
                else{
                    if(Arena.Move(this,new Position(position.x-1,position.y))){
                        position.x--;
                    }
                }
            }
            else{
                if(Arena.BasePosition.y > position.y){
                    if(Arena.Move(this,new Position(position.x,position.y+1))){
                        position.y++;
                    }
                }
                else{
                    if(Arena.Move(this,new Position(position.x,position.y-1))){
                        position.y--;
                    }
                }
            }
        }
        else
        {
            for (Object obj : Enemies)
                if (CanTarget((Unit) obj)) {
                    Attack((Unit) obj);
                    return;
                }
        }
    }

    public void Attack (Unit unit )
    {
        while (unit.hp>0)
        {
            unit.GetDamage(this.damage);
        }
    }
    public List<Object> CheckRange() {
        List<Object> Enemies = new ArrayList<>();
        for(int i=this.position.x - this.range ; i<= this.position.x + this.range ; i++){
            for(int j=this.position.y - this.range ; j<= this.position.y + this.range ; j++){
                if(i==position.x && j==position.y)
                    continue;
                Position curPos = new Position(i,j);
                Unit curUnit = Arena.CheckPos(curPos);
                if(curUnit!=null && CanTarget(curUnit)){
                    Enemies.add(curUnit);
                }
            }
        }


        return Enemies;
    }

    @Override
    public boolean CanTarget(Unit unit) {
        return false;
    }


}
