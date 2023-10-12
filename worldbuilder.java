import java.io.PrintWriter;
import java.util.*;
public class worldbuilder {
    public int xdim;
    public int ydim;
    public ArrayList<Integer> beepersX;
    ArrayList<Integer> beepersY;
    ArrayList<Integer> beepersN;
    int cx1;
    int cx2;
    int cy1;
    int cy2;
    public worldbuilder() {
        beepersX = new ArrayList<Integer>();
        beepersY = new ArrayList<Integer>();
        beepersN = new ArrayList<Integer>();
    }
    public void setDimensions(int xdim1, int ydim1) {
        xdim = xdim1;
        ydim = ydim1;
    }
    public void createRectangle(int x1, int y1, int x2, int y2) {
        cx1=x1; cx2=x2; cy1=y1; cy2=y2;
    }
    public boolean placeBeeper(int x, int y, int n) {
        if (x<cx1 || x>cx2 || y<cy1 || y>cy2) {return false;}
        beepersX.add(x); beepersY.add(y); beepersN.add(n);
        return true;
    }
    public void create(String name) throws Exception {
        PrintWriter out = new PrintWriter(name+".wld", "UTF-8");
        out.println("KarelWorld");
        out.println("streets "+xdim);
        out.println("avenues"+ydim);
        for (int i=0; i<beepersX.size(); i++) {
            out.println("beepers "+beepersX.get(i)+" "+beepersY.get(i)+" "+beepersN.get(i));
        }
        out.println("eastwestwalls "+(cx1-1)+" "+cy1+" "+cy2);
        out.println("eastwestwalls "+cx2+" "+cy1+" "+cy2);
        out.println("northsouthwalls "+(cy1-1)+" "+cx1+" "+cx2);
        out.println("northsouthwalls "+cy2+" "+cx1+" "+cx2);
        out.close();
    }
    public void createWithDoor(String name) throws Exception {
        PrintWriter out = new PrintWriter(name+".wld", "UTF-8");
        out.println("KarelWorld");
        out.println("streets "+xdim);
        out.println("avenues"+ydim);
        for (int i=0; i<beepersX.size(); i++) {
            out.println("beepers "+beepersX.get(i)+" "+beepersY.get(i)+" "+beepersN.get(i));
        }
        out.println("eastwestwalls "+(cx1-1)+" "+(cy1+1)+" "+cy2);
        out.println("eastwestwalls "+cx2+" "+cy1+" "+cy2);
        out.println("northsouthwalls "+(cy1-1)+" "+cx1+" "+cx2);
        out.println("northsouthwalls "+cy2+" "+cx1+" "+cx2);
        out.close();
    }
}