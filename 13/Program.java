import java.util.*;
import java.io.*;

public class Program {
    
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File("input"));

        int arrival;
        ArrayList<Integer> buses = new ArrayList<Integer>();
        HashMap<Integer, Integer> index = new HashMap<Integer, Integer>();

        String first = scan.nextLine();
        String second = scan.nextLine();

        scan.close();

        arrival = Integer.parseInt(first);
        String[] split = second.split(",");
        int ind = 0;
        for(String s : split) {
            if(!s.equals("x")) {
                int bus = Integer.parseInt(s);
                buses.add(bus);
                index.put(bus, ind);
            }
            ind++;
        }

        int diff = Integer.MAX_VALUE;
        int busid = -1;

        for(int i : buses) {
            int m = (int)Math.ceil((double)arrival / i);

            int thisdiff = m * i - arrival;
            if(thisdiff < diff) {
                diff = thisdiff;
                busid = i;
            }
        }

        System.out.printf("P1: %d\n", diff * busid);

        int[] num = new int[buses.size()];
        int[] rem = new int[buses.size()];
        for(int i = 0; i < num.length; i++) {
            num[i] = buses.get(i);
            rem[i] = index.get(buses.get(i));
        }

        long p2count = num[0] + rem[0];
        long inc = num[0];

        for(int i = 1; i < num.length; i++) {
            while((p2count + rem[i]) % num[i] != 0) {
                p2count += inc;
            }
            inc *= num[i];
        }

        System.out.printf("P2: %d\n", p2count);
    }
}
