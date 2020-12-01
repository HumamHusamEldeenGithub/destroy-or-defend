package Arena;

import unit.Unit;
import Utilitiy.Position;

import java.util.HashMap;

public class Grid {
    static Grid grid =null;
    public static int CellNum;
    public static int CellSize ;
    static HashMap<Position, Cell> Cells ;

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
    public static void Initialize(int NumOfCells,int Cellsize){
        CellSize = Cellsize;
        CellNum = NumOfCells;
        Cells = new HashMap<Position,Cell>();
        for(int i=0;i<NumOfCells;i++){
            for(int j=0;j<NumOfCells;j++){
                Cells.put(new Position(i,j),new Cell(Cellsize,new Position(i,j)));
            }
        }
    }
    public static TerrainType GetTerrain(Position position){
        return Cells.get(position).GetTerrain();
    }
    public static boolean HasSpace(Position position,int Size){
        if(Cells.get(position).GetSize() >= Size){
            return true;
        }
        return false;
    }
    public synchronized static boolean addUnit(Unit unit){
        return Cells.get(unit.getPosition()).AddUnit(unit);
    }
    public synchronized static void RemoveUnit(Unit unit){
        Cells.get(unit.getPosition()).RemoveUnit(unit);
    }
}
