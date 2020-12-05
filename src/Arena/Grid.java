package Arena;

import Strategies.LowestHealthAttackStrategy;
import gameManager.DoDGameManager;
import javafx.geometry.Pos;
import javafx.util.Pair;
import player.Player;
import player.PlayerType;
import unit.Unit;
import Utilitiy.Position;
import unit.UnitType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        if(unit.GetType() == UnitType.MainBase){
            Grid.mainBase = unit;
        }
        return Cells.get(unit.GetPosition()).AddUnit(unit);
    }

    public synchronized static void RemoveUnit(Unit unit){
        Cells.get(unit.GetPosition()).RemoveUnit(unit);
    }

    public int GetSizeAtPosition(Position pos) {return Grid.Cells.get(pos).GetSize() ;  }
    public void SetSizeAtPosition(Position pos ,int size) {Grid.Cells.get(pos).SetSize(size); }

    public static synchronized Position getBasePosition(){
        return mainBase.GetPosition() ;
    }

    public static synchronized Cell CheckCell(Position pos){
        if(Cells.containsKey(pos))
            return Cells.get(pos);
        return null;
    }
    public static Cell[][] GetCut(Position position){
        if(position.Get_X()<0){
            position.Set_X(0);
        }
        if(position.Get_Y()<0){
            position.Set_Y(0);
        }
        if(position.Get_X()>= CellNum){
            position.Set_X(CellNum - 10);
        }
        if(position.Get_Y()>= CellNum){
            position.Set_Y(CellNum - 10);
        }
        Cell[][] cells = new Cell[10][10];
        for(int i=position.Get_X();i<position.Get_X()+10;i++){
            for(int j=position.Get_Y();j<position.Get_Y()+10;j++){
                cells[i][j] = Cells.get(new Position(i,j));
            }
        }
        return cells;
    }
}
