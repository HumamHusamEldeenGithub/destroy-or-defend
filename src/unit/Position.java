package unit;

public class Position {
    int centerX ;
    int centerY ;
    public Position(int x , int y) {
        this.centerX = x ;
        this.centerY =y ;
    }
    public boolean squareIsOccupied(){
        return false;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

}
