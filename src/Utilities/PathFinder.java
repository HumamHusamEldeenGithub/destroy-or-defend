package Utilities;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

class Pathfinder {
    class GridNode{
        public int weight;
        public Position pos;
        public GridNode(){
            pos = new Position(0,0);
        }
    }
    boolean[][] visited = new boolean[10000][10000];
    Comparator cmp = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            if(((GridNode ) o1).weight > ((GridNode) o2).weight)
                return 1;
            else if(((GridNode) o1).weight == ((GridNode) o2).weight)
                return 0;
            else
                return -1;
        }
    };
    SortedSet<GridNode> GridSet = new TreeSet<>(cmp);
    public Position GetPos(Position currentPos,Position Destination,int Range){
        // Fill nodes
        for(int i = currentPos.Get_X() - 1; i <= currentPos.Get_X() + 1;i++){
            for(int j = currentPos.Get_Y() - 1;j<= currentPos.Get_Y()+1;j++){
                if(i>=0 && i<10000 && j>=0 && j<10000 ) {
                    if (GridSet.GetGrid().addUnit(new Position(i, j)) || !visited[j][j]) {
                        GridNode node = new GridNode();
                        node.weight = Math.abs(Destination.Get_X() - j + Destination.Get_Y() - i);
                        node.pos.Set_X(j);
                        node.pos.Set_Y(i);
                        GridSet.add(node);
                    }
                }
            }
        }
        // Check best solution
        for(GridNode node : GridSet){
            Position newCur = node.pos;
            //DFS
            for(int i = 0;i < 50;i++){
                newCur = Think(newCur,Destination,Range);
                if(newCur==null){
                    break;
                }
            }
            if(newCur!=null) {
                visited[node.pos.getCenterX()][node.pos.getCenterY()] = true;
                return node.pos;
            }
            if(node.equals(GridSet.last())){
                visited[node.pos.getCenterX()][node.pos.getCenterY()] = true;
                return node.pos;
            }
        }
        return null;
    }

    Position Think(Position currentPos,Position Destination,int Range){
        if(Math.abs(currentPos.getCenterX() - Destination.getCenterX()) <=Range && Math.abs(currentPos.getCenterY() - Destination.getCenterY()) <= Range){
            return currentPos;
        }
        if(Destination.getCenterX() < currentPos.getCenterX()){
            if(Grid.GetGrid().addUnit(new Position(currentPos.getCenterX() - 1,currentPos.getCenterY())) )
                return new Position(currentPos.getCenterX() - 1,currentPos.getCenterY());
            if(Grid.GetGrid().addUnit(new Position(currentPos.getCenterX()-1 , currentPos.getCenterY()+1)))
                return new Position(currentPos.getCenterX()-1 , currentPos.getCenterY()+1);
            if(Grid.GetGrid().addUnit(new Position(currentPos.getCenterX()-1 , currentPos.getCenterY()-1)))
                return new Position(currentPos.getCenterX()-1 , currentPos.getCenterY()-1);
        }
        else if(Destination.getCenterX() > currentPos.getCenterX()){
            if(Grid.GetGrid().addUnit(new Position(currentPos.getCenterX() + 1,currentPos.getCenterY())) )
                return new Position(currentPos.getCenterX() + 1,currentPos.getCenterY());
            if(Grid.GetGrid().addUnit(new Position(currentPos.getCenterX() + 1 , currentPos.getCenterY()+1)))
                return new Position(currentPos.getCenterX() + 1 , currentPos.getCenterY()+1);
            if(Grid.GetGrid().addUnit(new Position(currentPos.getCenterX()  + 1 , currentPos.getCenterY()-1)))
                return new Position(currentPos.getCenterX()  + 1 , currentPos.getCenterY()-1);
        }
        if(Destination.getCenterY() < currentPos.getCenterY()){
            if(Grid.GetGrid().addUnit(new Position(currentPos.getCenterX(),currentPos.getCenterY() - 1)))
                return new Position(currentPos.getCenterX(),currentPos.getCenterY() - 1);
            if(Grid.GetGrid().addUnit(new Position(currentPos.getCenterX() + 1 , currentPos.getCenterY() - 1)))
                return new Position(currentPos.getCenterX() + 1 , currentPos.getCenterY() - 1);
            if(Grid.GetGrid().addUnit(new Position(currentPos.getCenterX() - 1 , currentPos.getCenterY() - 1)))
                return new Position(currentPos.getCenterX() - 1 , currentPos.getCenterY() - 1);
        }
        else if(Destination.getCenterY() > currentPos.getCenterY()){
            if(Grid.GetGrid().addUnit(new Position(currentPos.getCenterX(),currentPos.getCenterY() + 1)))
                return new Position(currentPos.getCenterX(),currentPos.getCenterY() + 1);
            if(Grid.GetGrid().addUnit(new Position(currentPos.getCenterX() + 1 , currentPos.getCenterY() + 1)))
                return new Position(currentPos.getCenterX() + 1 , currentPos.getCenterY() + 1);
            if(Grid.GetGrid().addUnit(new Position(currentPos.getCenterX() - 1 , currentPos.getCenterY() + 1)))
                return new Position(currentPos.getCenterX() - 1 , currentPos.getCenterY() + 1);
        }
        return currentPos;
    }
}