import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import kareltherobot.*;

public class Driver implements Directions {
	static final int wx=12;
	static final int wy=12;
	static Scanner in=new Scanner(System.in);
	static automata r;
	static worldbuilder wb=new worldbuilder();
	static ArrayList<Integer> x=new ArrayList<Integer>();
	static ArrayList<Integer> y=new ArrayList<Integer>();
	static ArrayList<Integer> is=new ArrayList<Integer>();
	static int sx; static int sy;
	public static void main(String[] args) throws Exception {
		getInfo();
		//d.cleanRoom();
		//d.displayResults();
	}
	public static void getInfo() throws Exception {
		int choice=-1;
		while (choice<1 || choice>2) {
			System.out.println("Please select a valid world generation method:\n1 - Pure Random\n2 - Preset 1");
			choice=in.nextInt();
		}
		if (choice==1) {PureRandomGen();}
		if (choice==2) {}
		
		System.out.println("Please select a valid robot starting position:\n1 - Bottom Left Corner\n2 - Custom");
		choice=-1;
		if (choice==2) {
			System.out.println("Hmmmmm....");
			System.out.println("nahhhhhhhhhh i'll just do bottom left corner >:)");
		}
		r=new automata(sx, sy, East, 0);
		
		choice=-1;
		while (choice<1 || choice>2) {
			System.out.println("Please select a valid navigation method:\n1 - Shortest Path\n2 - Simple Zigzag");
			choice=in.nextInt();
		}
		if (choice==1) {
			System.out.println("DISCLAIMER: This method of navigation is related to the travelling salesman problem and is NP-Hard. Thus, the calculations for routing the shortest path might take a while...");
			System.out.println("There are certainly faster methods of planning a path, but for simplicity, we use an O(n!) brute force.");
			World.setDelay(3);
			ShortestPath();
		}
	}

	public static void ShortestPath() {
		int mdist=69696969;
		ArrayList<ArrayList<Integer>> ps=permute(is);
		for (ArrayList<Integer> arr : ps) {
			mdist=Math.min(mdist,calc(arr));
		}
		for (ArrayList<Integer> arr:ps) {
			if (calc(arr)==mdist) {
				for (int i=0; i<arr.size(); i++) {
					World.setDelay(4);
					r.goToPosition(x.get(i), y.get(i));
					World.setDelay(0);
					r.pickAll();
				}
				return;
			}
		}
	}
	public static int calc(ArrayList<Integer> arr) {
		int dst=dist(sx,sy,x.get(arr.get(0)),y.get(arr.get(0)));
		for (int i=1; i<arr.size(); i++) {
			dst+=dist(x.get(arr.get(i-1)),y.get(arr.get(i-1)),x.get(arr.get(i)),y.get(arr.get(i)));
		}
		return dst;
	}
	public static int dist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2)+Math.abs(y1-y2);
	}


	public static void PureRandomGen() throws Exception {
		int mnx=rand(1,4);
		int mxx=rand(6,11);
		int mny=rand(1,4);
		int mxy=rand(6,11);
		sx=mnx; sy=mny;
		wb.setDimensions(wx, wy);
		wb.createRectangle(mnx, mny, mxx, mxy);
		int np=rand(2, 12);
		for (int i=0; i<np; i++) {
			int xx=rand(mnx,mxx); int yy=rand(mny,mxy);
			x.add(xx); y.add(yy); is.add(i);
			wb.placeBeeper(xx, yy, rand(1,10));
		}
		wb.create("NewWorld"); World.readWorld("NewWorld.wld"); World.setVisible(true);
	}

	public static int rand(int lo, int hi) {
		return ThreadLocalRandom.current().nextInt(lo,++hi);
	}

	public static ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        permuteHelper(result, new ArrayList<>(), nums);
        return result;
    }
    private static void permuteHelper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> current, ArrayList<Integer> remaining) {
        if (remaining.isEmpty()) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < remaining.size(); i++) {
            int num = remaining.get(i);
            current.add(num);
            ArrayList<Integer> newRemaining = new ArrayList<>(remaining);
            newRemaining.remove(i);
            permuteHelper(result, current, newRemaining);
            current.remove(current.size() - 1);
        }
    }
}