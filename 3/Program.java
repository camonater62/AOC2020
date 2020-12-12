import java.util.*;
import java.io.*;

public class Program {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File("input"));

        ArrayList<char[]> map = new ArrayList<char[]>();

        long prod = 1;

        while(scan.hasNextLine()) {
            String in = scan.nextLine().trim();
            char[] line = new char[in.length()];
            int i = 0;
            for(char c : in.toCharArray()) {
                line[i++] = c;
            }
            map.add(line);
        }

        scan.close();

        int[] xvals = {1, 3, 5, 7, 1};
        int[] yvals = {1, 1, 1, 1, 2};

        for(int i = 0; i < xvals.length; i++) {
            int count = 0;
            int x = 0, y = 0;
            while(y < map.size()) {
                if(map.get(y)[x % map.get(y).length] == '#') {
                    count++;
                }
                x += xvals[i];
                y += yvals[i];
            }

            prod *= count;
        }
        

        System.out.println(prod);
    }
}