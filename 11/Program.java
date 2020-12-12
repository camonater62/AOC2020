import java.util.*;
import java.io.*;

public class Program {
    private static boolean equals(ArrayList<ArrayList<Character>> a, ArrayList<ArrayList<Character>> b) {
        if(a.size() != b.size()) {
            return false;
        }
        if(a.get(0).size() != b.get(0).size()) {
            return false;
        }
        for(int y = 0; y < a.size(); y++) {
            for(int x = 0; x < a.get(y).size(); x++) {
                if(a.get(y).get(x).charValue() != b.get(y).get(x).charValue()) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File("input"));

        ArrayList<ArrayList<Character>> seats = new ArrayList<ArrayList<Character>>();

        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            ArrayList<Character> row = new ArrayList<Character>();
            for(char c : line.toCharArray()) {
                row.add(c);
            }
            seats.add(row);
        }

        scan.close();

        ArrayList<ArrayList<Character>> orig = seats;
        do {
            orig = seats;
            seats = new ArrayList<ArrayList<Character>>();
            for(int y = 0; y < orig.size(); y++) {
                ArrayList<Character> row = new ArrayList<Character>();
                for(int x = 0; x < orig.get(y).size(); x++) {
                    row.add(orig.get(y).get(x));
                }
                seats.add(row);
            }
            
            for(int y = 0; y < seats.size(); y++) {
                for(int x = 0; x < seats.get(y).size(); x++) {
                    if(orig.get(y).get(x) != '.') {
                        int count = 0;
                        for(int oy = -1; oy < 2; oy++) {
                            for(int ox = -1; ox < 2; ox++) {
                                if(!(oy == 0 && ox == 0) && oy + y >= 0 && oy + y < seats.size()
                                && ox + x >= 0 && ox + x < seats.get(y).size()) {
                                //    if(x == 0 && y == 0) System.out.println(oy);
                                    if(orig.get(y + oy).get(x + ox) == '#') {
                                        count++;
                                    }
                                }
                            }
                        }
                        // if(x == 0 && y == 0) System.out.println(count);
                        if(orig.get(y).get(x) == '#' && count >= 4) {
                            seats.get(y).set(x, 'L');
                        } else if(orig.get(y).get(x) == 'L' && count == 0) {
                            seats.get(y).set(x, '#');
                        }
                    }
                }
            }
           //  System.out.println(seats.toString().replace("],", "],\n") + "\n\n");
        } while(!equals(orig, seats));
        // System.out.println(seats.toString().replace("],", "],\n") + "\n\n");

        int p1count = 0;
        for(ArrayList<Character> al : seats) {
            for(Character c : al) {
                if(c == '#') p1count++;
            }
        }
        System.out.printf("P1: %d\n", p1count);

        // BEGIN PART 2

        scan = new Scanner(new File("input"));

        seats = new ArrayList<ArrayList<Character>>();

        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            ArrayList<Character> row = new ArrayList<Character>();
            for(char c : line.toCharArray()) {
                row.add(c);
            }
            seats.add(row);
        }

        scan.close();

        do {
            // System.out.println(seats.toString().replace("],", "],\n") + "\n\n");
            orig = seats;
            seats = new ArrayList<ArrayList<Character>>();
            for(int y = 0; y < orig.size(); y++) {
                ArrayList<Character> row = new ArrayList<Character>();
                for(int x = 0; x < orig.get(y).size(); x++) {
                    row.add(orig.get(y).get(x));
                }
                seats.add(row);
            }
            
            for(int y = 0; y < seats.size(); y++) {
                for(int x = 0; x < seats.get(y).size(); x++) {
                    if(orig.get(y).get(x) != '.') {
                        int count = 0;
                        for(int oy = -1; oy < 2; oy++) {
                            for(int ox = -1; ox < 2; ox++) {
                                if(oy != 0 || ox != 0) {
                                    int cy = y, cx = x;
                                    while((cy == y && cx == x) 
                                    || (cy >= 0 && cy < seats.size() 
                                    && cx >= 0 && cx < seats.get(0).size()
                                    && orig.get(cy).get(cx) == '.')) {
                                        cy += oy;
                                        cx += ox;
                                    }
                                    if(cy >= 0 && cy < seats.size() 
                                    && cx >= 0 && cx < seats.get(0).size()
                                    && orig.get(cy).get(cx) == '#') {
                                    //    if(y == 1 && x == 1) System.out.printf("CX: %d, CY: %d\n", cx, cy);
                                        count++;
                                    }
                                    
                                } 
                            }
                        }
                        //if(x == 1 && y == 1) System.out.println(count);
                        if(orig.get(y).get(x) == '#' && count >= 5) {
                            seats.get(y).set(x, 'L');
                        } else if(orig.get(y).get(x) == 'L' && count == 0) {
                            seats.get(y).set(x, '#');
                        }
                    }
                }
            }
            
        } while(!equals(orig, seats));

        int p2count = 0;
        for(ArrayList<Character> al : seats) {
            for(Character c : al) {
                if(c == '#') p2count++;
            }
        }
        System.out.printf("P2: %d\n", p2count);
    }
}