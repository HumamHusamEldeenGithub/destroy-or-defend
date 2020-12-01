package Arena;

import javafx.geometry.Pos;
import unit.Unit;
import unit.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class Grid {
    static Grid grid;
    public static int CellNum;
    public static int CellSize ;
    static HashMap<Position, Cell> Cells ;

    Grid(){ }
    public Grid GetGrid(){
        if(grid==null){
            grid = new Grid();
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

    public static boolean addUnit(Unit unit){
        return Cells.get(unit.getPosition()).AddUnit(unit);
    }
    public static void RemoveUnit(Unit unit){
        Cells.get(unit.getPosition()).RemoveUnit(unit);
    }
}
