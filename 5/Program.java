import java.util.*;
import java.io.*;

public class Program {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File("input.txt"));

        ArrayList<String> seats = new ArrayList<String>();
        ArrayList<Integer> ids = new ArrayList<Integer>();

        while(scan.hasNextLine()) {
            seats.add(scan.nextLine());
        }

        scan.close();

        int max = Integer.MIN_VALUE;

        for(String s : seats) {
            int low = 0;
            int high = 127;

            String row = s.substring(0, 7);
            String col = s.substring(7);

            for(char c : row.toCharArray()) {
                if(c == 'F') {
                    high = (low + high) / 2; 
                } else if(c == 'B') {
                    low = (low + high) / 2 + 1;
                }
            }

            int value = high * 8;

            low = 0; 
            high = 7;

            for(char c : col.toCharArray()) {
                if(c == 'L') {
                    high = (low + high) / 2; 
                } else if(c == 'R') {
                    low = (low + high) / 2 + 1;
                }
            }

            value += low;

            ids.add(value);

            max = Math.max(max, value);
        }

        System.out.println("P1: " + max);

        Collections.sort(ids);
        for(int i = 1; i < ids.size() - 1; i++) {
            int curr = ids.get(i);
            int last = ids.get(i - 1);
            if(last != curr - 1) {
                System.out.println("P2: " + (curr - 1));
            }
        }

        System.out.println(ids);
    }
}