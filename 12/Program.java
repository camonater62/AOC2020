import java.util.*;
import java.io.*;

public class Program {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File("input"));

        ArrayList<String> instructions = new ArrayList<String>();
        while(scan.hasNextLine()) {
            instructions.add(scan.nextLine());
        }
        scan.close();
        
        int x = 0, y = 0;
        int ang = 0;

        for(String s : instructions) {
            char inst = s.charAt(0);
            int amt = Integer.parseInt(s.substring(1));
            switch(inst) {
                case 'N': y += amt; break;
                case 'S': y -= amt; break;
                case 'E': x += amt; break;
                case 'W': x -= amt; break;
                case 'L': ang -= amt; break;
                case 'R': ang += amt; break;
                case 'F': x += amt * (int)Math.cos(Math.toRadians(ang)); 
                          y -= amt * (int)Math.sin(Math.toRadians(ang)); break;
            }
        }

        System.out.printf("P1: %d\n", Math.abs(x) + Math.abs(y));

        // Part 2

        x = 0; y = 0;
        int wx = 10, wy = 1;

        for(String s : instructions) {
            char inst = s.charAt(0);
            int amt = Integer.parseInt(s.substring(1));
            System.out.printf("x: %d, y: %d, wx: %d, wy: %d ; next: %s\n", x, y, wx, wy, s);
            int owx = wx, owy = wy;
            switch(inst) {
                case 'N': wy += amt; break;
                case 'S': wy -= amt; break;
                case 'E': wx += amt; break;
                case 'W': wx -= amt; break;

                // rotated point x = original x * cosine (angle in radians to change by) – original y  * sine (angle in radians to change by)
                // rotated point y = original x * sine (angle in radians to change by) + original y * cosine (angle in radians to change by)
                
                case 'L': 
                          wx = owx * (int)Math.cos(Math.toRadians(-amt)) + owy * (int)Math.sin(Math.toRadians(-amt)); 
                          wy = -owx * (int)Math.sin(Math.toRadians(-amt)) + owy * (int)Math.cos(Math.toRadians(-amt)); break;
                case 'R': //System.out.println( owx * -(int)Math.sin(Math.toRadians(amt)) + owy * (int)Math.cos(Math.toRadians(amt))); 
                          wx = owx * (int)Math.cos(Math.toRadians(amt)) + owy * (int)Math.sin(Math.toRadians(amt)); 
                          wy = -owx * (int)Math.sin(Math.toRadians(amt)) + owy * (int)Math.cos(Math.toRadians(amt)); break;
                case 'F': x += amt * wx; y += amt * wy; break;
            }
        }  
        
        System.out.printf("P2: %d\n", Math.abs(x) + Math.abs(y));
    }
}