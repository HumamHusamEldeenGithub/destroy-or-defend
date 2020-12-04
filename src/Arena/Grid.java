package Arena;

import javafx.geometry.Pos;
import javafx.util.Pair;
import unit.Unit;
import Utilitiy.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class Grid {
    static Grid grid =null;
    public static int CellNum;
    public static int CellSize ;
    static HashMap<Position, Cell> Cells ;
    static Unit mainBase ;
    Grid(){ }
    public synchronized static Grid GetGrid(){
        if(grid==null){
            synchronized (Grid.class){
                if(grid==null)
                    grid = new Grid();
            }
        }
        return grid;
    }
    public  void Initialize(int NumOfCells,int Cellsize){
        CellSize = Cellsize;
        CellNum = NumOfCells;
        Cells = new HashMap<Position, Cell>();
        for(int i=0;i<NumOfCells;i++){
            for(int j=0;j<NumOfCells;j++){
                Cells.put(new Position(i,j),new Cell(Cellsize,new Position(i,j)));
            }
        }
    }
    public static TerrainType GetTerrain(Position position){
        return Cells.get(position).GetTerrain();
    }
    public synchronized static boolean HasSpace(Position position,int Size){
        //System.out.println(position);
        if(Cells.get(position).GetSize()>Size){
            return true;
        }
        return false;
    }
    public synchronized static boolean addUnit(Unit unit){
        return Cells.get(unit.GetPosition()).AddUnit(unit);
    }

    public synchronized static void RemoveUnit(Unit unit){
        Cells.get(unit.GetPosition()).RemoveUnit(unit);
    }

    public static synchronized Position getBasePosition(){
        return mainBase.GetPosition() ;
    }

    public static synchronized Cell CheckCell(Position pos){
        if(Cells.containsKey(pos))
            return Cells.get(pos);
        return null;
    }

}
