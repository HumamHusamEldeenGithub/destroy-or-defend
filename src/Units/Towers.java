package Units;

import Arena.Arena;
import Interfaces.ITarget;
import Utilities.Position;

import java.util.ArrayList;
import java.util.List;

public class Towers extends Unit implements ITarget{

    @Override
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
        return true;
    }
}
