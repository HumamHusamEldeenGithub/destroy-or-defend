package Utilitiy;

public class Position {
    int centerX;
    int centerY;

    public Position(int x, int y) {
        this.centerX = x;
        this.centerY = y;
    }

    public int Get_X() {
        return centerX;
    }

    public int Get_Y() {
        return centerY;
    }

    public void Set_X(int X) {
        centerX = X;
    }

    public void Set_Y(int Y) {
        centerY = Y;
    }

    public String toString() {
        return "X = " + this.centerX + "  Y = " + this.centerY;
    }

    @Override
    public boolean equals(Object o) {
        return this.centerX == ((Position) o).Get_X() && this.centerY == ((Position) o).Get_Y();

    }
    @Override
    public int hashCode() {
        final int prime = 402653189;
        int result = 1;
        result = prime * result + Get_X()*Get_Y() + Get_Y();
        return result;
    }
}
