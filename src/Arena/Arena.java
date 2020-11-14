package Arena;
import Enums.Direction;
import Enums.UnitType;
import Units.*;
import Utilities.Position;
import java.util.HashMap;

public class Arena {
    static Unit[][] mainGrid = new Unit[10000][10000];

    public static boolean Deploy(Unit unit, Position pos){
        if(mainGrid[pos.x][pos.y]!=null)
            return false;
        mainGrid[pos.x][pos.y] = unit;
        return true;
    }

    public static boolean Move(Unit unit,Position pos){
        if(mainGrid[pos.x][pos.y]!=null)
            return false;
        mainGrid[pos.x][pos.y] = unit;
        mainGrid[unit.GetPos().x][unit.GetPos().y] = null;
        return true;
    }

    public static void Remove(Unit unit){
        mainGrid[unit.GetPos().x][unit.GetPos().y] = null;
    }

    public static UnitType[][] ZoomIn(Position left_upper_corner){
        if(left_upper_corner.x + 1000 >= 10000){
            left_upper_corner.x = 8999;
        }
        if(left_upper_corner.y + 1000 >= 10000){
            left_upper_corner.y = 8999;
        }
        UnitType[][] zoomedUnits = new UnitType[1000][1000];
        for(int i=left_upper_corner.y,cnt = 0;i < 10000 && i >= 0 && cnt < 1000;i++){
            for(int j = left_upper_corner.x,cnt2 = 0;j < 10000 && j >= 0 && cnt2 < 1000;j++){
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
                                unitNum.replace(mainGrid[i][j].GetType(), (int) unitNum.get(mainGrid[i][j]) + 1);
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
                unitNum.clear();
            }
        }
        return zoomOutArena;
    }
    public static void PrintArena ()
    {
        UnitType[][] newArena = ZoomIn(new Position(0,0)) ;
        for (int i =0 ; i<500 ; i++)
        {
            System.out.println();
            for (int j=0 ; j<500 ; j++)
            {
                if (mainGrid[i][j]!=null)
                    System.out.print("s ");
                else
                    System.out.print(". ");
            }
        }
    }
}