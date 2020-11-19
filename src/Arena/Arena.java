package Arena;

import Enums.UnitType;
import Units.Base;
import Units.Unit;
import Utilities.Position;

import java.util.HashMap;

public class Arena {
    public static Position BasePosition ;
    static Unit[][] mainGrid = new Unit[10000][10000];

    public static boolean Deploy(Unit unit, Position pos){

        if(mainGrid[pos.x][pos.y]!=null)
            return false ;
        mainGrid[pos.x][pos.y] = unit;
        if (unit instanceof Base)
            BasePosition = new Position(pos.x, pos.y) ;
        return true;
    }

    public static boolean CanMove(Position pos){
        if(pos.x < 0 || pos.x > 9999 || pos.y < 0 || pos.y > 9999 || mainGrid[pos.x][pos.y] != null)
            return false;
        return true;
    }

    public static void  Move(Unit unit,Position pos){
        mainGrid[pos.x][pos.y] = unit;
        mainGrid[unit.GetPos().x][unit.GetPos().y] = null;
        unit.SetPos(pos);
    }

    public static void Remove(Unit unit){
        mainGrid[unit.GetPos().x][unit.GetPos().y] = null;
    }

    public static UnitType[][] ZoomIn(Position left_upper_corner){
        if(left_upper_corner.x + 500 >= 10000){
            left_upper_corner.x = 8999;
        }
        if(left_upper_corner.y + 500 >= 10000){
            left_upper_corner.y = 8999;
        }
        UnitType[][] zoomedUnits = new UnitType[500][500];
        for(int i=left_upper_corner.y,cnt = 0;i < 10000 && i >= 0 && cnt < 500;i++){
            for(int j = left_upper_corner.x,cnt2 = 0;j < 10000 && j >= 0 && cnt2 < 500;j++){
                if (mainGrid[i][j]!=null)
                zoomedUnits[i][j] = mainGrid[i][j].GetType();
            }
        }
        return zoomedUnits;
    }

    public static UnitType[][] ZoomOut(){
        UnitType finalUnitType = null;
        int max = 0;
        UnitType[][] zoomOutArena = new UnitType[500][500];
        HashMap<UnitType,Integer> unitNum = new HashMap<UnitType,Integer>();
        // Dividing the 10k x 10k grid to 500 x 500 and counting the average of each 20x20
        for(int k=0;k<500;k++){
            for(int l=0;l<500;l++) {
                for (int i = 0 + (20*k); i < 20 * (k + 1); i++) {
                    for(int j = 0 + (20 * l); j < 20 * (l + 1);j++){
                        if (mainGrid[i][j]!=null) {
                            if (unitNum.containsKey(mainGrid[i][j].GetType())) {
                                unitNum.replace(mainGrid[i][j].GetType(), (int) unitNum.get(mainGrid[i][j].GetType()) + 1);
                            } else {
                                unitNum.put(mainGrid[i][j].GetType(), 1);
                            }
                        }
                    }
                }
                // Deciding the final type
                for(UnitType type : UnitType.values()){
                    if(unitNum.containsKey(type)){
                        if(max < unitNum.get(type)){
                            max = unitNum.get(type);
                            finalUnitType = type;
                        }
                    }
                }
                zoomOutArena[k][l] = finalUnitType;
                max = 0;
                finalUnitType = null;
                unitNum.clear();
            }
        }
        return zoomOutArena;
    }
    public static void PrintArena ()
    {
        UnitType[][] newArena = ZoomIn(new Position(0,0)) ;
        for (int i =0 ; i< newArena.length ; i++)
        {
            System.out.println();
            for (int j=0 ; j< newArena.length ; j++)
            {
                if (newArena[i][j]!=null)
                    System.out.print("s ");
                else
                    System.out.print(". ");
            }
        }
    }

    public static Unit CheckPos(Position position){
        Unit unit = null;
        try{
            unit = mainGrid[position.x][position.y];
        }
        catch (Exception e){

        }
        return unit;
    }

}