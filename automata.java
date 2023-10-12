import kareltherobot.*;

public class automata extends Robot {
    public int x;
    public int y;
    public int numBeepers;
    public Direction dir;
    public automata(int x1, int y1, Direction dir1, int numBeepers1) {
        super(x1, y1, dir1, numBeepers1);
        x=x1; y=y1; numBeepers=numBeepers1;
        dir=dir1;
    }
    public boolean pick() {
        if (this.nextToABeeper()) {
            this.pickBeeper();
            numBeepers++;
            return true;
        }
        return false;
    }
    public int pickAll() {
        int count=0;
        while (this.pick()) {count++;}
        return count;
    }
    public void faceNorth() {while (!this.facingNorth()) {this.turnLeft();} dir=North;}
    public void faceEast() {while (!this.facingEast()) {this.turnLeft();} dir=East;}
    public void faceSouth() {while (!this.facingSouth()) {this.turnLeft();} dir=South;}
    public void faceWest() {while (!this.facingWest()) {this.turnLeft();} dir=West;}
    public void move1() {
        if (this.facingNorth()) {this.goNorth();}
        if (this.facingEast()) {this.goEast();}
        if (this.facingSouth()) {this.goSouth();}
        if (this.facingWest()) {this.goWest();}
    }
    public boolean goBackward() {
        this.turnLeft(); this.turnLeft();
        this.move1();
        this.turnLeft(); this.turnLeft();
        return true;
    }
    public boolean goNorth() {
        faceNorth(); x++;
        if (!this.frontIsClear()) {return false;}
        this.move(); return true;
    }
    public boolean goEast() {
        faceEast(); y++;
        if (!this.frontIsClear()) {return false;}
        this.move(); return true;
    }
    public boolean goSouth() {
        faceSouth(); x--;
        if (!this.frontIsClear()) {return false;}
        this.move(); return true;
    }
    public boolean goWest() {
        faceWest(); y--;
        if (!this.frontIsClear()) {return false;}
        this.move(); return true;
    }
    public void goToPosition(int x1, int y1) {
        while (x<x1) {goNorth();}
        while (x>x1) {goSouth();}
        while (y<y1) {goEast();}
        while (y>y1) {goWest();}
    }
}
