package movement;

class Pathfinder {
   /* class GridNode{
        public int weight;
        public UnitPosition pos;
        public GridNode(){
            pos = new UnitPosition(0,0);
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
        for(int i = currentPos.x - 1; i <= currentPos.x + 1;i++){
            for(int j = currentPos.y - 1;j<= currentPos.y+1;j++){
                if(i>=0 && i<10000 && j>=0 && j<10000 ) {
                    if (Arena.CanMove(new Position(i, j)) || !visited[j][j]) {
                        GridNode node = new GridNode();
                        node.weight = Math.abs(Destination.x - j + Destination.y - i);
                        node.pos.x = j;
                        node.pos.y = i;
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
                visited[node.pos.x][node.pos.y] = true;
                return node.pos;
            }
            if(node.equals(GridSet.last())){
                visited[node.pos.x][node.pos.y] = true;
                return node.pos;
            }
        }
        return null;
    }

    Position Think(Position currentPos,Position Destination,int Range){
        if(Math.abs(currentPos.x - Destination.x) <=Range && Math.abs(currentPos.y - Destination.y) <= Range){
            return currentPos;
        }
        if(Destination.x < currentPos.x){
            if(Arena.CanMove(new Position(currentPos.x - 1,currentPos.y)) )
                return new Position(currentPos.x - 1,currentPos.y);
            if(Arena.CanMove(new Position(currentPos.x-1 , currentPos.y+1)))
                return new Position(currentPos.x-1 , currentPos.y+1);
            if(Arena.CanMove(new Position(currentPos.x-1 , currentPos.y-1)))
                return new Position(currentPos.x-1 , currentPos.y-1);
        }
        else if(Destination.x > currentPos.x){
            if(Arena.CanMove(new Position(currentPos.x + 1,currentPos.y)) )
                return new Position(currentPos.x + 1,currentPos.y);
            if(Arena.CanMove(new Position(currentPos.x + 1 , currentPos.y+1)))
                return new Position(currentPos.x + 1 , currentPos.y+1);
            if(Arena.CanMove(new Position(currentPos.x  + 1 , currentPos.y-1)))
                return new Position(currentPos.x  + 1 , currentPos.y-1);
        }
        if(Destination.y < currentPos.y){
            if(Arena.CanMove(new Position(currentPos.x,currentPos.y - 1)))
                return new Position(currentPos.x,currentPos.y - 1);
            if(Arena.CanMove(new Position(currentPos.x + 1 , currentPos.y - 1)))
                return new Position(currentPos.x + 1 , currentPos.y - 1);
            if(Arena.CanMove(new Position(currentPos.x - 1 , currentPos.y - 1)))
                return new Position(currentPos.x - 1 , currentPos.y - 1);
        }
        else if(Destination.y > currentPos.y){
            if(Arena.CanMove(new Position(currentPos.x,currentPos.y + 1)))
                return new Position(currentPos.x,currentPos.y + 1);
            if(Arena.CanMove(new Position(currentPos.x + 1 , currentPos.y + 1)))
                return new Position(currentPos.x + 1 , currentPos.y + 1);
            if(Arena.CanMove(new Position(currentPos.x - 1 , currentPos.y + 1)))
                return new Position(currentPos.x - 1 , currentPos.y + 1);
        }
        return currentPos;
    }*/
}