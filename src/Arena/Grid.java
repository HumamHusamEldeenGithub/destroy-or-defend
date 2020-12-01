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
    HashMap<Position, Cell> Cells ;
    Unit[][] units ;
    public Grid GetGrid(){
        if(grid==null){
            grid = new Grid();
        }
        return grid;
    }
    Grid(){ }
    public void Initialize(int NumOfCells,int Cellsize){
        CellSize = Cellsize;
        CellNum = NumOfCells;
        Cells = new HashMap<Position,Cell>();
        for(int i=0;i<NumOfCells;i++){
            for(int j=0;j<NumOfCells;j++){
                Cells.put(new Position(i,j),new Cell(Cellsize,new Position(i,j)));
            }
        }
    }
  /*  public void addUnit(Unit unit){
        int cellX = (int)(unit.getPosition().getCenterX()/CELL_SIZE) ;
        int cellY = (int)(unit.getPosition().getCenterY()/CELL_SIZE) ;
        unit._prev= null ;
        unit._next = units[cellX][cellY] ;
        units[cellX][cellY] = unit ;
        if (unit._next!=null)
            unit._next._prev = unit ;
    }*/
    public void addUnit(Unit unit){

    }
}
